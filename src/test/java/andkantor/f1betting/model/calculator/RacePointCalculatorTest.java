package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.Race;
import andkantor.f1betting.model.bet.Point;
import andkantor.f1betting.model.bet.Bet;
import andkantor.f1betting.model.bet.Bettor;
import andkantor.f1betting.model.bet.BetProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static andkantor.f1betting.model.bet.Point.HIT;
import static andkantor.f1betting.model.bet.Point.ZERO;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RacePointCalculatorTest {

    private BetProvider betProvider = mock(BetProvider.class);
    private BetPointCalculator betPointCalculator = mock(BetPointCalculator.class);
    private CalculationContext context = mock(CalculationContext.class);
    private Bettor bettor = mock(Bettor.class);
    private Race race = mock(Race.class);
    private Bet bet1 = mock(Bet.class);
    private Bet bet2 = mock(Bet.class);

    private RacePointCalculator underTest;

    @Before
    public void setUp() {
        underTest = new RacePointCalculator(betProvider, betPointCalculator, context);
    }

    @Test
    public void calculate() {
        //given
        doReturn(Arrays.asList(bet1, bet2)).when(betProvider).getBets(bettor, race);
        doReturn(HIT).when(betPointCalculator).calculatePoints(bet1, context);
        doReturn(ZERO).when(betPointCalculator).calculatePoints(bet2, context);

        //when
        Map<Bet, Point> points = underTest.calculate(bettor, race);

        //then
        assertThat(points.size()).isEqualTo(2);
        assertThat(points.get(bet1)).isEqualTo(HIT);
        assertThat(points.get(bet2)).isEqualTo(ZERO);
    }
}