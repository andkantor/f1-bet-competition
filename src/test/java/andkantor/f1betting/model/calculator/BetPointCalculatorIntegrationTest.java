package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.*;
import andkantor.f1betting.model.bet.Bet;
import andkantor.f1betting.model.race.RaceResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static andkantor.f1betting.entity.Point.*;
import static andkantor.f1betting.entity.Position.createPosition;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BetPointCalculatorIntegrationTest {

    private static final Point PENALTY_POINTS = new Point(-3);

    private static Race race = mock(Race.class);
    private static Driver driver1 = mock(Driver.class);
    private static Driver driver2 = mock(Driver.class);
    private static Driver driver3 = mock(Driver.class);
    private static Driver driver4 = mock(Driver.class);

    private BetPointCalculator underTest;

    private CalculationContext calculationContext;

    private Driver driver;
    private Position finalPosition;
    private Point expectedPoints;

    public BetPointCalculatorIntegrationTest(Driver driver, Position finalPosition, Point expectedPoints) {
        this.driver = driver;
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
                new FinalPosition(race, driver1, createPosition(1)),
                new FinalPosition(race, driver2, createPosition(2)),
                new FinalPosition(race, driver3, createPosition(3)),
                new FinalPosition(race, driver4, createPosition(4))
        );

        RaceResult raceResult = new RaceResult(race, finalPositions);

        List<Penalty> penalties = Arrays.asList(
                new Penalty(race, driver1, createPosition(1), PENALTY_POINTS),
                new Penalty(race, driver2, createPosition(3), PENALTY_POINTS),
                new Penalty(race, driver4, createPosition(2), PENALTY_POINTS)
        );

        calculationContext = new CalculationContext(raceResult, penalties);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
                        {driver2, createPosition(2), HIT},
                        {driver2, createPosition(1), NEAR_MISS},
                        {driver4, createPosition(2), PENALTY_POINTS},
                        {driver1, createPosition(1), HIT.add(PENALTY_POINTS)},
                        {driver2, createPosition(3), NEAR_MISS.add(PENALTY_POINTS)},
                        {driver4, createPosition(1), ZERO},
            });
    }

    @Test
    public void calculatePoints() {
        //given
        Bet bet = mock(Bet.class);
        doReturn(driver).when(bet).getDriver();
        doReturn(finalPosition).when(bet).getFinalPosition();

        //when
        Point actualPoints = underTest.calculatePoints(bet, calculationContext);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }
}
