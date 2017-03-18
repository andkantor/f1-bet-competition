package andkantor.f1betting.controller.user;

import andkantor.f1betting.entity.*;
import andkantor.f1betting.form.BetForm;
import andkantor.f1betting.model.Flash;
import andkantor.f1betting.model.setting.Configuration;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping(value = "/race/{id}")
public class BetController extends BaseController {

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BetRepository betRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    @Autowired
    ConfigurationManager configurationManager;

    @RequestMapping(value = "/bet", method = RequestMethod.GET)
    public String bet(@PathVariable Long id, @ModelAttribute BetForm betForm) {
        Configuration configuration = configurationManager.getConfiguration();
        User user = getUser();
        Race race = race(id);

        if (!race.canBeBetOn()) {
            return "redirect:/race/" + id + "/view";
        }

        List<Bet> bets = betRepository.findByUserAndRace(user, race);
        if (bets.isEmpty()) {
            IntStream.range(0, configuration.getNumberOfDriversToBetOn())
                    .forEach(value -> bets.add(value, new Bet()));
        }
        betForm.setBets(bets);

        return "user/bet/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@PathVariable Long id, @ModelAttribute @Valid BetForm betForm, BindingResult result) {
        User user = getUser();
        Race race = raceRepository.findOne(id);

        if (!race.canBeBetOn()) {
            return "redirect:/race/" + id + "/view";
        }

        if (result.hasErrors()) {
            return "user/bet/form";
        }

        betRepository.deleteByUserAndRace(user, race);

        betForm.getBets().forEach(bet -> {
            bet.setUser(user);
            bet.setRace(race);
            betRepository.save(bet);
        });

        flash.setMessage("You have successfully bet on " + race.getName());

        return "redirect:/race/" + id + "/view";
    }

    @ModelAttribute(name = "race")
    public Race race(@PathVariable Long id) {
        return raceRepository.findOne(id);
    }

    @ModelAttribute(name = "penalties")
    public List<Penalty> penalties(@PathVariable Long id) {
        return penaltyRepository.findByRace(race(id));
    }

    @ModelAttribute(name = "drivers")
    public List<Driver> drivers() {
        return driverRepository.findByActive(true);
    }

    @ModelAttribute(name = "positions")
    public int[] positions() {
        return IntStream.range(1, configurationManager.getConfiguration().getNumberOfPositionsToBetOn() + 1).toArray();
    }

}
