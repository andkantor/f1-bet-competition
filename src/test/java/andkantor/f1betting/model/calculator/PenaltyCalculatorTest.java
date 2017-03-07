package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.Penalty;
import andkantor.f1betting.entity.Point;
import andkantor.f1betting.model.bet.Bet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static andkantor.f1betting.entity.Point.ZERO;
import static andkantor.f1betting.entity.Position.createPosition;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PenaltyCalculatorTest {

    @Mock
    private Bet bet;

    @Mock
    private Driver driver;

    @Mock
    private Penalty penalty;

    @Mock
    private CalculationContext context;

    private PenaltyCalculator underTest;

    @Before
    public void setUp() {
        underTest = new PenaltyCalculator();

        doReturn(driver).when(bet).getDriver();
        doReturn(Optional.of(penalty)).when(context).getPenalty(driver, createPosition(1));
        doReturn(new Point(-6)).when(penalty).getPoint();
    }

    @Test
    public void calculateShouldReturnPenaltyPointsWhenPenaltyFound() {
        //given
        doReturn(createPosition(1)).when(bet).getFinalPosition();
        Point expectedPoints = new Point(-6);

        //when
        Point actualPoints = underTest.calculate(bet, context);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }

    @Test
    public void calculateShouldReturnZeroWhenPenaltyNotFound() {
        //given
        doReturn(createPosition(2)).when(bet).getFinalPosition();
        Point expectedPoints = ZERO;

        //when
        Point actualPoints = underTest.calculate(bet, context);

        //then
        assertThat(actualPoints).isEqualTo(expectedPoints);
    }
}