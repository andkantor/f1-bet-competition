package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.race.Race;
import andkantor.f1betting.model.bet.Point;
import andkantor.f1betting.model.bet.Bet;
import andkantor.f1betting.model.bet.Bettor;
import andkantor.f1betting.model.bet.BetProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacePointCalculator {

    private BetProvider betProvider;
    private BetPointCalculator betPointCalculator;
    private CalculationContext context;

    public RacePointCalculator(
            BetProvider betProvider,
            BetPointCalculator betPointCalculator,
            CalculationContext context
    ) {
        this.betProvider = betProvider;
        this.betPointCalculator = betPointCalculator;
        this.context = context;
    }

    public Map<Bet, Point> calculate(Bettor bettor, Race race) {
        List<Bet> bets = betProvider.getBets(bettor, race);

        return bets.stream().collect(Collectors.toMap(
                bet -> bet,
                bet -> betPointCalculator.calculatePoints(bet, context)));
    }
}
