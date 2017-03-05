package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.race.Racer;
import andkantor.f1betting.model.bet.Penalty;
import andkantor.f1betting.model.bet.Point;
import andkantor.f1betting.model.bet.Bet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static andkantor.f1betting.model.race.Position.createPosition;
import static andkantor.f1betting.model.bet.Point.ZERO;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PenaltyCalculatorTest {

    @Mock
    private Bet bet;

    @Mock
    private Racer racer;

    @Mock
    private Penalty penalty;

    @Mock
    private CalculationContext context;

    private PenaltyCalculator underTest;

    @Before
    public void setUp() {
        underTest = new PenaltyCalculator();

        doReturn(racer).when(bet).getRacer();
        doReturn(Optional.of(penalty)).when(context).getPenalty(racer, createPosition(1));
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