package andkantor.f1betting.model.penalty;

import andkantor.f1betting.entity.*;
import andkantor.f1betting.model.race.RaceResult;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static andkantor.f1betting.entity.Position.NOT_FINISHED;
import static andkantor.f1betting.entity.Position.createPosition;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class PenaltyCalculatorTest {

    private PenaltyCalculator underTest;
    private Race race;
    private Driver driver;
    private CalculationDataProvider dataProvider;

    @Before
    public void setUp() {
        race = mock(Race.class);
        doReturn(true).when(race).isResultSet();
        driver = mock(Driver.class);
        dataProvider = mock(CalculationDataProvider.class);

        underTest = new PenaltyCalculator(dataProvider);
    }

    @Test
    public void testCanCalculatePenaltiesForOnlyTwoRaces() {
        //given
        List<Race> previousRaces = Arrays.asList(mock(Race.class), mock(Race.class));
        doReturn(previousRaces).when(dataProvider).getPreviousRaces(race);

        //when
        boolean actual = underTest.canCalculatePenalties(race);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    public void testCanCalculatePenaltiesForAtLeastThreeRaces() {
        //given
        List<Race> previousRaces = Arrays.asList(createRace(true), createRace(true), createRace(true));
        doReturn(previousRaces).when(dataProvider).getPreviousRaces(race);

        //when
        boolean actual = underTest.canCalculatePenalties(race);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    public void testCanCalculatePenaltiesLastRaceResultNotSet() {
        //given
        List<Race> previousRaces = Arrays.asList(createRace(false), createRace(true), createRace(true), createRace(true));
        doReturn(previousRaces).when(dataProvider).getPreviousRaces(race);

        //when
        boolean actual = underTest.canCalculatePenalties(race);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    public void testCalculatePenalties() {
        List<Scenario> scenarios = Arrays.asList(
                scenario(raceResults(1, 1, 1), penalty(1, -6), penalty(2, -3)),
                scenario(raceResults(1, 1, 2), penalty(1, -6), penalty(2, -3)),
                scenario(raceResults(1, 2, 2), penalty(1, -3), penalty(2, -6),  penalty(3, -3)),
                scenario(raceResults(2, 2, 2), penalty(1, -3), penalty(2, -6),  penalty(3, -3)),
                scenario(raceResults(1, 2, 3), penalty(1, -2), penalty(2, -3),  penalty(3, -2)),
                scenario(raceResults(2, 4, 5), penalty(3, -2), penalty(4, -3),  penalty(5, -2)),
                scenario(raceResults(2, 4, 7), penalty(3,  0), penalty(4, -2),  penalty(5,  0)),
                scenario(raceResults(0, 2, 7), penalty(4,  0), penalty(5,  0),  penalty(6,  0)),
                scenario(raceResults(0, 1, 3), penalty(1, -2), penalty(2, -3),  penalty(3, -2)),
                scenario(raceResults(1, 0, 1), penalty(1, -6), penalty(2, -3)),
                scenario(raceResults(0, 3, 0)),
                scenario(raceResults(0, 0, 0))
        );

        scenarios.forEach(scenario -> {
            //given
            List<Race> previousRaces = Arrays.asList(createRace(true), createRace(true), createRace(true));
            List<RaceResult> raceResults = scenario.raceResults;
            List<Penalty> expected = scenario.penalties;

            doReturn(previousRaces).when(dataProvider).getPreviousRaces(race);
            doReturn(raceResults).when(dataProvider).createRaceResults(any());

            //when
            List<Penalty> actual = underTest.calculatePenalties(race);

            //then
            assertThat(actual).hasSize(expected.size());
            assertThat(actual).containsAll(expected);
        });
    }

    private Race createRace(boolean resultIsSet) {
        Race race = new Race();
        race.setResultSet(resultIsSet);
        return race;
    }

    private List<RaceResult> raceResults(Integer... positions) {
        return Arrays.stream(positions)
                .map(position -> position == 0
                        ? raceResult(NOT_FINISHED)
                        : raceResult(createPosition(position)))
                .collect(toList());
    }

    private RaceResult raceResult(Position position) {
        return new RaceResult(
                race,
                singletonList(new FinalPosition(race, driver, position)));
    }

    private Penalty penalty(int position, int penalty) {
        return new Penalty(race, driver, createPosition(position), new Point(penalty));
    }

    private static Scenario scenario(List<RaceResult> raceResult, Penalty... penalties) {
        return new Scenario(raceResult, penalties);
    }

    private static class Scenario {
        private List<RaceResult> raceResults;
        private List<Penalty> penalties;

        Scenario(List<RaceResult> raceResults, Penalty... penalties) {
            this.raceResults = raceResults;
            this.penalties = Arrays.asList(penalties);
        }
    }
}