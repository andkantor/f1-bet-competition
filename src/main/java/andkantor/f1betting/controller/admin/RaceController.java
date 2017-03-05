package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;
import andkantor.f1betting.model.race.RaceResult;
import andkantor.f1betting.repository.DriverRepository;
import andkantor.f1betting.repository.FinalPositionRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static andkantor.f1betting.entity.Position.createPosition;

@Controller
@RequestMapping(value = "/admin/season/{seasonId}/race")
public class RaceController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    FinalPositionRepository finalPositionRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Race race, Model model) {
        model.addAttribute("race", race);
        return "admin/race/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@PathVariable Long seasonId, @Valid Race race) {
        Season season = seasonRepository.findOne(seasonId);
        race.setSeason(season);
        raceRepository.save(race);
        return "redirect:/admin/season/" + seasonId + "/view";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long seasonId, @PathVariable Long id) {
        Race race = raceRepository.findOne(id);
        finalPositionRepository.deleteByRace(race);
        raceRepository.delete(race);
        return "redirect:/admin/season/" + seasonId + "/view";
    }

    @RequestMapping("/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        Race race = raceRepository.findOne(id);
        Iterable<Driver> drivers = driverRepository.findByActive(true);
        List<FinalPosition> finalPositionList = finalPositionRepository.findByRace(race);

        List<FinalPosition> finalPositions = StreamSupport.stream(drivers.spliterator(), false)
                .map(driver -> finalPositionList.stream()
                            .filter(finalPosition -> finalPosition.getDriver() == driver)
                            .findAny()
                            .orElse(new FinalPosition(race, driver, createPosition(0))))
                .collect(Collectors.toList());

        model.addAttribute("race", race);
        model.addAttribute("raceResult", new RaceResult(race, finalPositions));

        return "admin/race/view";
    }

    @RequestMapping("/{id}/saveRaceResult")
    public String saveRaceResult(@PathVariable Long seasonId, @PathVariable Long id, @Valid RaceResult raceResult) {
        Race race = raceRepository.findOne(id);
        raceResult.getFinalPositions().forEach(finalPosition -> {
            finalPosition.setRace(race);
            finalPositionRepository.save(finalPosition);
        });

        return "redirect:/admin/season/" + seasonId + "/race/" + id + "/view";
    }
}
