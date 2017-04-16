package andkantor.f1betting.model.penalty;

import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.model.race.RaceResult;
import andkantor.f1betting.repository.FinalPositionRepository;
import andkantor.f1betting.repository.RaceRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CalculationDataProvider {

    private RaceRepository raceRepository;
    private FinalPositionRepository finalPositionRepository;

    public CalculationDataProvider(RaceRepository raceRepository, FinalPositionRepository finalPositionRepository) {
        this.raceRepository = raceRepository;
        this.finalPositionRepository = finalPositionRepository;
    }

    List<Race> getPreviousRaces(Race race) {
        List<Race> races = raceRepository.findBySeason(race.getSeason());
        races.sort((o1, o2) -> o2.getStartDateTime().compareTo(o1.getStartDateTime()));
        int index = races.indexOf(race);

        return races.subList(index + 1, races.size());
    }

    List<RaceResult> createRaceResults(List<Race> previousRaces) {
        return previousRaces
                .stream()
                .map(this::createRaceResult)
                .collect(toList());
    }

    private RaceResult createRaceResult(Race race) {
        List<FinalPosition> finalPositions = finalPositionRepository.findByRace(race);
        return new RaceResult(race, finalPositions);
    }
}
