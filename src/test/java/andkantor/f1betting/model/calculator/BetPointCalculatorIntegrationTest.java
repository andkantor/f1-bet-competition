package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.race.*;
import andkantor.f1betting.model.bet.Penalty;
import andkantor.f1betting.model.bet.Point;
import andkantor.f1betting.model.bet.Bet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static andkantor.f1betting.model.race.Position.createPosition;
import static andkantor.f1betting.model.bet.Point.*;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BetPointCalculatorIntegrationTest {

    private static final Point PENALTY_POINTS = new Point(-3);

    private static Race race = mock(Race.class);
    private static Racer racer1 = mock(Racer.class);
    private static Racer racer2 = mock(Racer.class);
    private static Racer racer3 = mock(Racer.class);
    private static Racer racer4 = mock(Racer.class);

    private BetPointCalculator underTest;

    private CalculationContext calculationContext;

    private Racer racer;
    private Position finalPosition;
    private Point expectedPoints;

    public BetPointCalculatorIntegrationTest(Racer racer, Position finalPosition, Point expectedPoints) {
        this.racer = racer;
        this.finalPosition = finalPosition;
        this.expectedPoints = expectedPoints;
    }

    @Before
    public void setUp() {
        List<PointCalculator> pointCalculators = Arrays.asList(
                new HitCalculator(),
                new NearMissCalculator(),
                new PenaltyCalculator()
        );

        underTest = new BetPointCalculator(pointCalculators);

        List<FinalPosition> finalPositions = Arrays.asList(
                new FinalPosition(race, racer1, createPosition(1)),
                new FinalPosition(race, racer2, createPosition(2)),
                new FinalPosition(race, racer3, createPosition(3)),
                new FinalPosition(race, racer4, createPosition(4))
        );

        RaceResult raceResult = new RaceResult(race, finalPositions);

        List<Penalty> penalties = Arrays.asList(
                new Penalty(race, racer1, createPosition(1), PENALTY_POINTS),
                new Penalty(race, racer2, createPosition(3), PENALTY_POINTS),
                new Penalty(race, racer4, createPosition(2), PENALTY_POINTS)
        );

        calculationContext = new CalculationContext(raceResult, penalties);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
                        {racer2, createPosition(2), HIT},
                        {racer2, createPosition(1), NEAR_MISS},
                        {racer4, createPosition(2), PENALTY_POINTS},
                        {racer1, createPosition(1), HIT.add(PENALTY_POINTS)},
                        {racer2, createPosition(3), NEAR_MISS.add(PENALTY_POINTS)},
                        {racer4, createPosition(1), ZERO},
            });
    }

    @Test
    public void calculatePoints() {
        //given
        Bet bet = mock(Bet.class);
        doReturn(racer).when(bet).getRacer();
        doReturn(finalPosition).when(bet).getFinalPosition();

        //when
        Point actualPoints = underTest.calculatePoints(bet, calculationContext);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }
}
