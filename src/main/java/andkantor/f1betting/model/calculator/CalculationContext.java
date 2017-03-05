package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.bet.Penalty;
import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.Position;
import andkantor.f1betting.model.race.RaceResult;

import java.util.List;
import java.util.Optional;

public class CalculationContext {

    private RaceResult raceResult;
    private List<Penalty> penalties;

    public CalculationContext(RaceResult raceResult, List<Penalty> penalties) {
        this.raceResult = raceResult;
        this.penalties = penalties;
    }

    public Position getPosition(Driver driver) {
        return raceResult.getFinalPosition(driver);
    }

    public Optional<Penalty> getPenalty(Driver driver, Position finalPosition) {
        return penalties.stream()
                .filter(penalty -> penalty.getDriver() == driver)
                .filter(penalty -> penalty.getPosition().equals(finalPosition))
                .findAny();
    }
}
