package andkantor.f1betting.model.penalty;

import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.model.race.RaceResult;
import andkantor.f1betting.repository.FinalPositionRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CalculationDataProvider {

    private FinalPositionRepository finalPositionRepository;

    public CalculationDataProvider(FinalPositionRepository finalPositionRepository) {
        this.finalPositionRepository = finalPositionRepository;
    }

    public List<Race> getPreviousRaces(Race race) {
        return new ArrayList<>();
    }

    public List<RaceResult> createRaceResults(List<Race> previousRaces) {
        previousRaces.sort((o1, o2) -> o1.getStartDateTime().compareTo(o2.getStartDateTime()));

        return previousRaces.subList(0, 3)
                .stream()
                .map(this::createRaceResult)
                .collect(toList());
    }

    private RaceResult createRaceResult(Race race) {
        List<FinalPosition> finalPositions = finalPositionRepository.findByRace(race);
        return new RaceResult(race, finalPositions);
    }

}
