package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.bet.Penalty;
import andkantor.f1betting.model.race.Driver;
import andkantor.f1betting.model.race.Position;
import andkantor.f1betting.model.race.RaceResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static andkantor.f1betting.model.race.Position.createPosition;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CalculationContextTest {

    @Mock
    private RaceResult raceResult;

    @Mock
    private Penalty penalty;

    private CalculationContext underTest;

    @Before
    public void setUp() {
        underTest = new CalculationContext(raceResult, Arrays.asList(penalty));
    }

    @Test
    public void getPosition() {
        //given
        Driver driver = mock(Driver.class);
        Position finalPosition = createPosition(5);
        doReturn(finalPosition).when(raceResult).getFinalPosition(driver);

        //when
        Position position = underTest.getPosition(driver);

        //then
        assertThat(position).isEqualTo(finalPosition);
    }

    @Test
    public void getPenaltyShouldReturnEmptyWhenNotFound() {
        //given
        Driver driver = mock(Driver.class);
        Position finalPosition = createPosition(5);
        doReturn(driver).when(penalty).getDriver();
        doReturn(createPosition(4)).when(penalty).getPosition();

        //when
        Optional<Penalty> result = underTest.getPenalty(driver, finalPosition);

        //then
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void getPenaltyShouldReturnPenaltyWhenFound() {
        //given
        Driver driver = mock(Driver.class);
        Position finalPosition = createPosition(5);
        doReturn(driver).when(penalty).getDriver();
        doReturn(createPosition(5)).when(penalty).getPosition();

        //when
        Optional<Penalty> result = underTest.getPenalty(driver, finalPosition);

        //then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(penalty);
    }

}