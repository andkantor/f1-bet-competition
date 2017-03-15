package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.repository.RaceRepository;
import andkantor.f1betting.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {

    @Autowired
    ConfigurationManager configurationManager;

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    RaceRepository raceRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        Long activeSeasonId = configurationManager.getConfiguration().getActiveSeason();
        Season season = seasonRepository.findOne(activeSeasonId);
        List<Race> races =  new ArrayList<>();

        if (season != null) {
            races = raceRepository.findBySeason(season);
        }

        model.addAttribute("season", season);
        model.addAttribute("races", races);

        return "admin/home";
    }

}
