package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.race.Racer;
import andkantor.f1betting.model.bet.Point;
import andkantor.f1betting.model.bet.Bet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static andkantor.f1betting.model.race.Position.position;
import static andkantor.f1betting.model.bet.Point.HIT;
import static andkantor.f1betting.model.bet.Point.ZERO;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class HitCalculatorTest {

    @Mock
    private Bet bet;

    @Mock
    private Racer racer;

    @Mock
    private CalculationContext context;

    private HitCalculator underTest;

    @Before
    public void setUp() {
        underTest = new HitCalculator();

        doReturn(racer).when(bet).getRacer();
        doReturn(position(1)).when(context).getPosition(racer);
    }

    @Test
    public void calculateShouldReturnHitWhenFinalPositionEquals() {
        //given
        doReturn(position(1)).when(bet).getFinalPosition();
        Point expectedPoints = HIT;

        //when
        Point actualPoints = underTest.calculate(bet, context);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }

    @Test
    public void calculateShouldReturnZeroWhenFinalPositionNotEquals() {
        //given
        doReturn(position(10)).when(bet).getFinalPosition();
        Point expectedPoints = ZERO;

        //when
        Point actualPoints = underTest.calculate(bet, context);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }
}