package andkantor.f1betting.controller.user;

import andkantor.f1betting.entity.*;
import andkantor.f1betting.model.calculator.BetPointCalculator;
import andkantor.f1betting.model.calculator.CalculationContext;
import andkantor.f1betting.model.race.RaceResult;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Controller
@RequestMapping(value = "/race")
public class RaceController extends BaseController {

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    BetRepository betRepository;

    @Autowired
    ConfigurationManager configurationManager;

    @Autowired
    FinalPositionRepository finalPositionRepository;

    @Autowired
    BetPointCalculator betPointCalculator;

    @Autowired
    PenaltyRepository penaltyRepository;

    @Autowired
    WatchRepository watchRepository;

    @RequestMapping(value = "/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        User user = getUser();
        Race race = raceRepository.findOne(id);
        List<Bet> userBets = betRepository.findByUserAndRace(user, race);
        Map<User, List<Bet>> watchList = watchRepository.findByWatcher(user).stream()
                .map(Watch::getWatched)
                .collect(toMap(
                        watched -> watched,
                        watched -> betRepository.findByUserAndRace(watched, race),
                        (w1, w2) -> { throw new RuntimeException("Duplicate found"); },
                        TreeMap::new));

        model.addAttribute("race", race);
        model.addAttribute("bets", userBets);
        model.addAttribute("watchList", watchList);
        model.addAttribute("flash", flash);

        if (race.isResultSet()) {
            RaceResult raceResult = new RaceResult(race, finalPositionRepository.findByRace(race));
            CalculationContext context = new CalculationContext(raceResult, penaltyRepository.findByRace(race));
            Map<Bet, Point> betPointMap = createBetPointMap(userBets, watchList, context);

            model.addAttribute("raceResult", raceResult);
            model.addAttribute("betPointMap", betPointMap);
        }

        return "race/view";
    }

    private Map<Bet, Point> createBetPointMap(List<Bet> userBets, Map<User, List<Bet>> watchList, CalculationContext context) {
        List<Bet> betList = watchList.values().stream()
                .flatMap(List::stream)
                .collect(toList());
        betList.addAll(userBets);

        return betList.stream()
                .collect(toMap(b -> b, bet -> betPointCalculator.calculatePoints(bet, context)));
    }

}
