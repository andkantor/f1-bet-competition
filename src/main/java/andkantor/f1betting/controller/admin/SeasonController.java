package andkantor.f1betting.controller.admin;

import andkantor.f1betting.model.race.Season;
import andkantor.f1betting.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/season")
public class SeasonController {

    @Autowired
    SeasonRepository repository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate(@Valid Season season, Model model) {
        model.addAttribute("season", season);
        return "admin/season/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(Season season) {
        repository.save(season);
        return "redirect:/admin/season/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<Season> seasons = repository.findAll();
        model.addAttribute("seasons", seasons);
        return "admin/season/list";
    }

    @RequestMapping("/view/{id}")
    public String list(@PathVariable Long id, Model model) {
        Season season = repository.findOne(id);
        model.addAttribute("season", season);
        return "admin/season/view";
    }
}
