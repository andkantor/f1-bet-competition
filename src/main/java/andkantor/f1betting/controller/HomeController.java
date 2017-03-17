package andkantor.f1betting.controller;

import andkantor.f1betting.entity.CumulativePoint;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;
import andkantor.f1betting.entity.User;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.model.user.UserProvider;
import andkantor.f1betting.repository.RacePointRepository;
import andkantor.f1betting.repository.RaceRepository;
import andkantor.f1betting.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    ConfigurationManager configurationManager;

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    UserProvider userProvider;

    @Autowired
    RacePointRepository racePointRepository;

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) {
        Long activeSeason = configurationManager.getConfiguration().getActiveSeason();
        List<User> users = userProvider.getActiveUsers();
        Map<User, CumulativePoint> cumulativePoints;

        if (activeSeason != 0) {
            Season season = seasonRepository.findOne(activeSeason);
            List<Race> races = raceRepository.findBySeason(season);
            cumulativePoints = racePointRepository.sumUserPoints(users, season);

            races.sort((race1, race2) -> race1.getStartDateTime().compareTo(race2.getStartDateTime()));
            Race nextRace = races.stream()
                    .filter(Race::canBeBetOn)
                    .findFirst()
                    .orElse(null);

            model.addAttribute("season", season);
            model.addAttribute("races", races);
            model.addAttribute("nextRace", nextRace);
        } else {
            cumulativePoints = users.stream()
                    .collect(Collectors.toMap(
                            user -> user,
                            user -> new CumulativePoint(user.getUsername(), BigDecimal.ZERO)));
        }

        users.sort((user1, user2) -> compareUsers(user1, user2, cumulativePoints));

        model.addAttribute("cumulativePoints", cumulativePoints);
        model.addAttribute("users", users);

        return "home";
    }

    private int compareUsers(User user1, User user2, Map<User, CumulativePoint> cumulativePoints) {
        int compareByPoints = cumulativePoints.get(user1).getPoint()
                .compareTo(cumulativePoints.get(user2).getPoint());

        return compareByPoints == 0
                ? user1.getUsername().compareTo(user2.getUsername())
                : compareByPoints;
    }

}
