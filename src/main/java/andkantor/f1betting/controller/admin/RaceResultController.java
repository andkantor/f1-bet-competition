package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.RacePoint;
import andkantor.f1betting.form.PenaltyForm;
import andkantor.f1betting.model.calculator.CalculationContext;
import andkantor.f1betting.model.calculator.RacePointCalculator;
import andkantor.f1betting.model.race.RaceResult;
import andkantor.f1betting.repository.*;
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
@RequestMapping(value = "/admin/race/{id}/result")
public class RaceResultController {

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    FinalPositionRepository finalPositionRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    @Autowired
    RacePointRepository racePointRepository;

    @Autowired
    RacePointCalculator racePointCalculator;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, PenaltyForm form, Model model) {
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
        model.addAttribute("drivers", drivers);

        return "admin/race/result-form";
    }

    @RequestMapping("/save")
    public String save(@PathVariable Long id, @Valid RaceResult raceResult) {
        Race race = raceRepository.findOne(id);
        race.setResultSet(true);
        raceRepository.save(race);

        raceResult.getFinalPositions().forEach(finalPosition -> {
            finalPosition.setRace(race);
            finalPositionRepository.save(finalPosition);
        });

        CalculationContext context = new CalculationContext(raceResult, penaltyRepository.findByRace(race));
        racePointCalculator.calculate(race, context)
                .forEach(racePointRepository::save);

        return "redirect:/admin/race/" + id + "/view";
    }

}
