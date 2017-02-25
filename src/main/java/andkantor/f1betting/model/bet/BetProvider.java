package andkantor.f1betting.model.bet;

import andkantor.f1betting.model.race.Race;

import java.util.List;
import java.util.stream.Collectors;

public class BetProvider {

    private List<Bet> bets;

    public List<Bet> getBets(Bettor bettor, Race race) {
        return bets.stream()
                .filter(bet -> bet.getBettor() == bettor)
                .filter(bet -> bet.getRace() == race)
                .collect(Collectors.toList());
    }

}
