package andkantor.f1betting.model.penalty;

import andkantor.f1betting.entity.*;
import andkantor.f1betting.model.race.RaceResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static andkantor.f1betting.entity.Position.NOT_FINISHED;
import static andkantor.f1betting.entity.Position.createPosition;
import static andkantor.f1betting.model.penalty.MathExtension.average;
import static andkantor.f1betting.model.penalty.MathExtension.standardDeviation;
import static andkantor.f1betting.model.penalty.PenaltyLevel.penaltyLevel;
import static java.lang.Math.round;

public class PenaltyCalculator {

    private CalculationDataProvider dataProvider;

    public PenaltyCalculator(CalculationDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public boolean canCalculatePenalties(Race race) {
        List<Race> previousRaces = dataProvider.getPreviousRaces(race);
        return canCalculatePenalties(previousRaces);
    }

    public List<Penalty> calculatePenalties(Race race) {
        List<Race> previousRaces = dataProvider.getPreviousRaces(race);
        if (!canCalculatePenalties(previousRaces)) {
            throw new RuntimeException("At least three previous race is needed to calculate penalties");
        }

        List<RaceResult> raceResults = dataProvider.createRaceResults(previousRaces.subList(0, 3));

        HashSet<Driver> drivers = new HashSet<>();
        raceResults.forEach(raceResult -> drivers.addAll(raceResult.getDrivers()));
        ArrayList<Penalty> penalties = new ArrayList<>();

        drivers.forEach(driver -> {
            int[] positions = raceResults.stream()
                    .map(raceResult -> raceResult.getFinalPosition(driver))
                    .filter(position -> !position.equals(NOT_FINISHED))
                    .mapToInt(Position::getPosition)
                    .toArray();

            if (positions.length <= 1) {
                return;
            }

            int average = (int) round(average(positions));
            int standardDeviation = (int) round(standardDeviation(positions));

            PenaltyLevel penaltyLevel = penaltyLevel(standardDeviation);

            if (average > 1) {
                penalties.add(createPenalty(race, driver, average - 1, penaltyLevel.atPrevious));
            }

            penalties.add(createPenalty(race, driver, average, penaltyLevel.onExact));
            penalties.add(createPenalty(race, driver, average + 1, penaltyLevel.atNext));
        });

        return penalties;
    }

    private boolean canCalculatePenalties(List<Race> previousRaces) {
        return previousRaces.size() >= 3
                && previousRaces.get(0).isResultSet()
                && previousRaces.get(1).isResultSet()
                && previousRaces.get(2).isResultSet();
    }

    private Penalty createPenalty(Race race, Driver driver, int position, int point) {
        return new Penalty(race, driver, createPosition(position), new Point(point));
    }
}
