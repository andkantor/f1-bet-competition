package andkantor.f1betting.controller;

import andkantor.f1betting.entity.Season;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.repository.RaceRepository;
import andkantor.f1betting.repository.SeasonRepository;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    ConfigurationManager configurationManager;

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/home")
    public String home(Model model) {
        Long activeSeason = configurationManager.getConfiguration().getActiveSeason();

        if (activeSeason != 0) {
            Season season = seasonRepository.findOne(activeSeason);
            model.addAttribute("season", season);
            model.addAttribute("races", raceRepository.findBySeason(season));
        }

        model.addAttribute("users", userRepository.findByEnabledAndUsernameNot(true, "admin"));

        return "home";
    }

}
