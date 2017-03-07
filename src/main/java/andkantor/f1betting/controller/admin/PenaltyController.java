package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.form.PenaltyForm;
import andkantor.f1betting.repository.DriverRepository;
import andkantor.f1betting.repository.PenaltyRepository;
import andkantor.f1betting.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/race/{id}/penalties")
public class PenaltyController {

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, PenaltyForm form, Model model) {
        Race race = raceRepository.findOne(id);
        Iterable<Driver> drivers = driverRepository.findByActive(true);

        form.setRace(race);
        form.setPenalties(penaltyRepository.findByRace(race));

        model.addAttribute("race", race);
        model.addAttribute("form", form);
        model.addAttribute("drivers", drivers);

        return "admin/penalty/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@PathVariable Long id, @Valid PenaltyForm form) {
        Race race = raceRepository.findOne(id);
        penaltyRepository.deleteByRace(race);
        form.getPenalties()
                .forEach(penaltyRepository::save);

        return "redirect:/admin/season/" + race.getSeason().getId() + "/race/" + id + "/view";
    }

}
