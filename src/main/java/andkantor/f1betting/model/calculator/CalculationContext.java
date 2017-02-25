package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.race.Position;
import andkantor.f1betting.model.race.RaceResult;
import andkantor.f1betting.model.race.Racer;
import andkantor.f1betting.model.bet.Penalty;

import java.util.List;
import java.util.Optional;

public class CalculationContext {

    private RaceResult raceResult;
    private List<Penalty> penalties;

    public CalculationContext(RaceResult raceResult, List<Penalty> penalties) {
        this.raceResult = raceResult;
        this.penalties = penalties;
    }

    public Position getPosition(Racer racer) {
        return raceResult.getFinalPosition(racer);
    }

    public Optional<Penalty> getPenalty(Racer racer, Position finalPosition) {
        return penalties.stream()
                .filter(penalty -> penalty.getRacer() == racer)
                .filter(penalty -> penalty.getPosition().equals(finalPosition))
                .findAny();
    }
}
