package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;
import andkantor.f1betting.model.race.RaceResult;
import andkantor.f1betting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/race")
public class RaceController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    FinalPositionRepository finalPositionRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam Long seasonId, Race race, Model model) {
        Season season = seasonRepository.findOne(seasonId);
        race.setSeason(season);
        model.addAttribute("race", race);
        return "admin/race/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Race race) {
        raceRepository.save(race);
        return "redirect:/admin/season/" + race.getSeason().getId() + "/view";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Race race = raceRepository.findOne(id);
        model.addAttribute("race", race);
        return "admin/race/form";
    }

    @RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
    public String save(@PathVariable Long id, @Valid Race race) {
        race.setId(id);
        raceRepository.save(race);
        return "redirect:/admin/season/" + race.getSeason().getId() + "/view";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        Race race = raceRepository.findOne(id);
        finalPositionRepository.deleteByRace(race);
        raceRepository.delete(race);

        return "redirect:/admin/season/" + race.getSeason().getId() + "/view";
    }

    @RequestMapping("/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        Race race = raceRepository.findOne(id);
        List<FinalPosition> finalPositions = finalPositionRepository.findByRace(race);

        model.addAttribute("race", race);
        model.addAttribute("penalties", penaltyRepository.findByRace(race));
        model.addAttribute("raceResult", new RaceResult(race, finalPositions));

        return "admin/race/view";
    }
}
