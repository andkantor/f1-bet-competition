package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;
import andkantor.f1betting.repository.RaceRepository;
import andkantor.f1betting.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/season")
public class SeasonController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    RaceRepository raceRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate(Season season, Model model) {
        model.addAttribute("season", season);
        return "admin/season/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(@Valid Season season) {
        seasonRepository.save(season);
        return "redirect:/admin/season/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<Season> seasons = seasonRepository.findAll();
        model.addAttribute("seasons", seasons);
        return "admin/season/list";
    }

    @RequestMapping("/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        Season season = seasonRepository.findOne(id);
        List<Race> races = raceRepository.findBySeason(season);
        model.addAttribute("season", season);
        model.addAttribute("races", races);
        return "admin/season/view";
    }
}
