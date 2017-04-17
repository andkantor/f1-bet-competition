package andkantor.f1betting.controller;

import andkantor.f1betting.controller.user.BaseController;
import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.entity.Season;
import andkantor.f1betting.entity.User;
import andkantor.f1betting.model.leaderboard.EmptyLeaderboard;
import andkantor.f1betting.model.leaderboard.Leaderboard;
import andkantor.f1betting.model.leaderboard.LeaderboardFactory;
import andkantor.f1betting.model.race.RaceList;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.model.user.UserProvider;
import andkantor.f1betting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
public class HomeController extends BaseController {

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

    @Autowired
    BetRepository betRepository;

    @Autowired
    WatchRepository watchRepository;

    @Autowired
    LeaderboardFactory leaderboardFactory;

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) {
        Long activeSeason = configurationManager.getConfiguration().getActiveSeason();
        List<User> users = userProvider.getActiveUsers();
        Leaderboard leaderboard = new EmptyLeaderboard(users);

        if (activeSeason != 0) {
            Season season = seasonRepository.findOne(activeSeason);
            RaceList raceList = new RaceList(season, raceRepository.findBySeasonOrderByStartDateTime(season));
            leaderboard = leaderboardFactory.create(users, raceList);

            model.addAttribute("season", season);
            model.addAttribute("races", raceList.getRaces());
            model.addAttribute("nextRace", raceList.getNextRace());

            if (userLoggedIn()) {
                User user = getUser();
                List<String> watchList = watchRepository.findByWatcher(user).stream()
                        .map(watch -> watch.getWatched().getUsername())
                        .collect(toList());
                model.addAttribute("watchList", watchList);

                if (raceList.getNextRace() != null) {
                    List<Bet> bets = betRepository.findByUserAndRace(user, raceList.getNextRace());
                    model.addAttribute("showBetWarning", bets.isEmpty());
                }
            }
        }

        model.addAttribute("leaderboard", leaderboard);

        return "home";
    }



}
