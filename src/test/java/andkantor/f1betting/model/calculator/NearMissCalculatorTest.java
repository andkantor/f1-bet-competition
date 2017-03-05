package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.race.Racer;
import andkantor.f1betting.model.bet.Point;
import andkantor.f1betting.model.bet.Bet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static andkantor.f1betting.model.race.Position.createPosition;
import static andkantor.f1betting.model.bet.Point.NEAR_MISS;
import static andkantor.f1betting.model.bet.Point.ZERO;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class NearMissCalculatorTest {

    @Mock
    private Bet bet;

    @Mock
    private Racer racer;

    @Mock
    private CalculationContext context;

    private NearMissCalculator underTest;

    @Before
    public void setUp() {
        underTest = new NearMissCalculator();

        doReturn(racer).when(bet).getRacer();
        doReturn(createPosition(1)).when(context).getPosition(racer);
    }

    @Test
    public void calculateShouldReturnNearMissWhenDifferenceBetweenPositionsIsOne() {
        //given
        doReturn(createPosition(2)).when(bet).getFinalPosition();
        Point expectedPoints = NEAR_MISS;

        //when
        Point actualPoints = underTest.calculate(bet, context);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }

    @Test
    public void calculateShouldReturnZeroWhenFinalPositionEquals() {
        //given
        doReturn(createPosition(1)).when(bet).getFinalPosition();
        Point expectedPoints = ZERO;

        //when
        Point actualPoints = underTest.calculate(bet, context);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }

    @Test
    public void calculateShouldReturnZeroWhenDifferenceBetweenPositionsIsMoreThanOne() {
        //given
        doReturn(createPosition(10)).when(bet).getFinalPosition();
        Point expectedPoints = ZERO;

        //when
        Point actualPoints = underTest.calculate(bet, context);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }
}