package andkantor.f1betting.model.bet;

import andkantor.f1betting.entity.Race;

import java.util.List;
import java.util.stream.Collectors;

public class PenaltyProvider {

    private List<Penalty> penalties;

    public List<Penalty> getPenalties(Race race) {
        return penalties.stream()
                .filter(penalty -> penalty.getRace() == race)
                .collect(Collectors.toList());
    }

}
