package andkantor.f1betting.model.race;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.Position;
import andkantor.f1betting.entity.Race;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class RaceResult {

    private Race race;
    private List<FinalPosition> finalPositions;

    public RaceResult() {}

    public RaceResult(Race race, List<FinalPosition> finalPositions) {
        this.race = race;
        this.finalPositions = finalPositions;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public List<FinalPosition> getFinalPositions() {
        return finalPositions;
    }

    public void setFinalPositions(List<FinalPosition> finalPositions) {
        this.finalPositions = finalPositions;
    }

    public List<Driver> getDrivers() {
        return finalPositions.stream()
                .map(FinalPosition::getDriver)
                .filter(driver -> driver != null)
                .collect(toList());
    }

    public Position getFinalPosition(Driver driver) {
        Optional<FinalPosition> position = finalPositions.stream()
                .filter(finalPosition -> finalPosition.getDriver() == driver)
                .findFirst();

        if (position.isPresent()) {
            return position.get().getPosition();
        }
        return Position.NOT_FINISHED;
    }
}
