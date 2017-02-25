package andkantor.f1betting.model.race;

import andkantor.f1betting.model.race.exception.RacerNotFoundException;

import java.util.List;
import java.util.Optional;

public class RaceResult {

    private Race race;
    private List<FinalPosition> finalPositions;

    public RaceResult(Race race, List<FinalPosition> finalPositions) {
        this.race = race;
        this.finalPositions = finalPositions;
    }

    public Position getFinalPosition(Racer racer) {
        Optional<FinalPosition> position = finalPositions.stream()
                .filter(finalPosition -> finalPosition.getRacer() == racer)
                .findFirst();

        return position
                .orElseThrow(RacerNotFoundException::new)
                .getPosition();
    }
}
