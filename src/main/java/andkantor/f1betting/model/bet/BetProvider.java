package andkantor.f1betting.model.bet;

import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class BetProvider {

    private List<Bet> bets;

    public List<Bet> getBets(User user, Race race) {
        return bets.stream()
                .filter(bet -> bet.getUser() == user)
                .filter(bet -> bet.getRace() == race)
                .collect(Collectors.toList());
    }

}
