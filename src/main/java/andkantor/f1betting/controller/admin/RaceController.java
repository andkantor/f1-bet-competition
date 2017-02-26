package andkantor.f1betting.controller.admin;

import andkantor.f1betting.model.race.Race;
import andkantor.f1betting.model.race.Season;
import andkantor.f1betting.repository.RaceRepository;
import andkantor.f1betting.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/admin/season/{seasonId}/race")
public class RaceController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    RaceRepository raceRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate(Race race, Model model) {
        model.addAttribute("race", race);
        return "admin/race/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(@PathVariable Long seasonId, Race race) {
        Season season = seasonRepository.findOne(seasonId);
        race.setSeason(season);
        // TODO set with form value
        race.setStartDateTime(LocalDateTime.now());
        raceRepository.save(race);
        return "redirect:/admin/season/" + seasonId + "/view";
    }

    @RequestMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Race race = raceRepository.findOne(id);
        model.addAttribute("race", race);
        return "admin/race/view";
    }
}
