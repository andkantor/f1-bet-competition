package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.*;
import andkantor.f1betting.model.user.UserProvider;
import andkantor.f1betting.repository.BetRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static andkantor.f1betting.entity.Point.HIT;
import static andkantor.f1betting.entity.Point.ZERO;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RacePointCalculatorTest {

    private BetRepository betRepository = mock(BetRepository.class);
    private UserProvider userProvider = mock(UserProvider.class);
    private BetPointCalculator betPointCalculator = mock(BetPointCalculator.class);
    private CalculationContext context = mock(CalculationContext.class);
    private User user1 = mock(User.class);
    private User user2 = mock(User.class);
    private Race race = mock(Race.class);
    private Bet bet1 = mock(Bet.class);
    private Bet bet2 = mock(Bet.class);
    private Bet bet3 = mock(Bet.class);
    private Bet bet4 = mock(Bet.class);
    private Bet bet5 = mock(Bet.class);

    private RacePointCalculator underTest;

    @Before
    public void setUp() {
        underTest = new RacePointCalculator(betRepository, userProvider, betPointCalculator);
    }

    @Test
    public void calculate() {
        //given
        doReturn(Arrays.asList(bet1, bet2)).when(betRepository).findByRace(race);
        doReturn(Arrays.asList(user1, user2)).when(userProvider).getActiveUsers();
        doReturn(user1).when(bet1).getUser();
        doReturn(user1).when(bet2).getUser();
        doReturn(HIT).when(betPointCalculator).calculatePoints(bet1, context);
        doReturn(ZERO).when(betPointCalculator).calculatePoints(bet2, context);

        //when
        List<RacePoint> racePoints = underTest.calculate(race, context);

        //then
        assertThat(racePoints.size()).isEqualTo(2);

        assertThat(racePoints.get(0).getUser()).isEqualTo(user1);
        assertThat(racePoints.get(0).getRace()).isEqualTo(race);
        assertThat(racePoints.get(0).getPoint()).isEqualTo(HIT);

        assertThat(racePoints.get(1).getUser()).isEqualTo(user2);
        assertThat(racePoints.get(1).getRace()).isEqualTo(race);
        assertThat(racePoints.get(1).getPoint()).isEqualTo(ZERO);
    }

    @Test
    public void calculateWithMoreBets() {
        //given
        doReturn(Arrays.asList(bet1, bet2, bet3, bet4, bet5)).when(betRepository).findByRace(race);
        doReturn(Arrays.asList(user1, user2)).when(userProvider).getActiveUsers();
        doReturn(user1).when(bet1).getUser();
        doReturn(user1).when(bet2).getUser();
        doReturn(user2).when(bet3).getUser();
        doReturn(user2).when(bet4).getUser();
        doReturn(user2).when(bet5).getUser();
        doReturn(HIT).when(betPointCalculator).calculatePoints(bet1, context);
        doReturn(ZERO).when(betPointCalculator).calculatePoints(bet2, context);
        doReturn(new Point(2)).when(betPointCalculator).calculatePoints(bet3, context);
        doReturn(new Point(5)).when(betPointCalculator).calculatePoints(bet4, context);
        doReturn(new Point(-1)).when(betPointCalculator).calculatePoints(bet5, context);

        //when
        List<RacePoint> racePoints = underTest.calculate(race, context);

        //then
        assertThat(racePoints.size()).isEqualTo(2);

        assertThat(racePoints.get(0).getUser()).isEqualTo(user1);
        assertThat(racePoints.get(0).getRace()).isEqualTo(race);
        assertThat(racePoints.get(0).getPoint()).isEqualTo(HIT);

        assertThat(racePoints.get(1).getUser()).isEqualTo(user2);
        assertThat(racePoints.get(1).getRace()).isEqualTo(race);
        assertThat(racePoints.get(1).getPoint()).isEqualTo(new Point(6));
    }
}