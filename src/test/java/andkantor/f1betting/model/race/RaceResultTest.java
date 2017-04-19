package andkantor.f1betting.model.race;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.Race;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static andkantor.f1betting.entity.Position.NOT_FINISHED;
import static andkantor.f1betting.entity.Position.createPosition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class RaceResultTest {

    @Test
    public void testGetDriversShouldNotReturnNullReferences() {
        //given
        Race race = mock(Race.class);
        Driver driver1 = mock(Driver.class);
        Driver driver2 = mock(Driver.class);

        RaceResult raceResult = new RaceResult(race, Arrays.asList(
                new FinalPosition(race, driver1, createPosition(1)),
                new FinalPosition(race, driver2, NOT_FINISHED),
                new FinalPosition(race, null, createPosition(2)),
                new FinalPosition(race, null, NOT_FINISHED)
        ));

        //when
        List<Driver> drivers = raceResult.getDrivers();

        //then
        assertThat(drivers).hasSize(2);
        assertThat(drivers.get(0)).isEqualTo(driver1);
        assertThat(drivers.get(1)).isEqualTo(driver2);
    }

}