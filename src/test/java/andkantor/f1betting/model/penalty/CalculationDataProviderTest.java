package andkantor.f1betting.model.penalty;

import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;
import andkantor.f1betting.repository.FinalPositionRepository;
import andkantor.f1betting.repository.RaceRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CalculationDataProviderTest {

    private CalculationDataProvider underTest;
    private Season season;
    private RaceRepository raceRepository;
    private FinalPositionRepository finalPositionRepository;

    @Before
    public void setUp() {
        season = mock(Season.class);
        raceRepository = mock(RaceRepository.class);
        finalPositionRepository = mock(FinalPositionRepository.class);

        underTest = new CalculationDataProvider(raceRepository, finalPositionRepository);
    }

    @Test
    public void testGetPreviousRacesWithDifferentOrders() {
        //given
        Race race = createRace("2017-04-16T14:00:00", false);
        List<Race> races = Arrays.asList(
                createRace("2017-04-18T14:00:00", false),
                createRace("2017-04-07T14:00:00", true),
                race,
                createRace("2017-04-10T14:00:00", true),
                createRace("2017-04-23T14:00:00", false),
                createRace("2017-04-05T14:00:00", true));
        doReturn(races).when(raceRepository).findBySeason(season);

        //then
        List<Race> previousRaces = underTest.getPreviousRaces(race);

        //then
        assertThat(previousRaces).hasSize(3);
        assertThat(previousRaces.get(0).isResultSet()).isTrue();
        assertThat(previousRaces.get(0).getStartDateTime()).isEqualTo(LocalDateTime.parse("2017-04-10T14:00:00"));
        assertThat(previousRaces.get(1).isResultSet()).isTrue();
        assertThat(previousRaces.get(1).getStartDateTime()).isEqualTo(LocalDateTime.parse("2017-04-07T14:00:00"));
        assertThat(previousRaces.get(2).isResultSet()).isTrue();
        assertThat(previousRaces.get(2).getStartDateTime()).isEqualTo(LocalDateTime.parse("2017-04-05T14:00:00"));
    }

    @Test
    public void testGetPreviousRacesNoPreviousRace() {
        //given
        Race race = createRace("2017-04-16T14:00:00", false);
        List<Race> races = Arrays.asList(
                createRace("2017-04-18T14:00:00", false),
                race,
                createRace("2017-04-23T14:00:00", false));
        doReturn(races).when(raceRepository).findBySeason(season);

        //then
        List<Race> previousRaces = underTest.getPreviousRaces(race);

        //then
        assertThat(previousRaces).hasSize(0);
    }

    @Test
    public void testGetPreviousRacesNoNextRace() {
        //given
        Race race = createRace("2017-04-16T14:00:00", false);
        List<Race> races = Arrays.asList(
                createRace("2017-04-09T14:00:00", true),
                race,
                createRace("2017-04-07T14:00:00", false));
        doReturn(races).when(raceRepository).findBySeason(season);

        //then
        List<Race> previousRaces = underTest.getPreviousRaces(race);

        //then
        assertThat(previousRaces).hasSize(2);
        assertThat(previousRaces.get(0).isResultSet()).isTrue();
        assertThat(previousRaces.get(0).getStartDateTime()).isEqualTo(LocalDateTime.parse("2017-04-09T14:00:00"));
        assertThat(previousRaces.get(1).isResultSet()).isFalse();
        assertThat(previousRaces.get(1).getStartDateTime()).isEqualTo(LocalDateTime.parse("2017-04-07T14:00:00"));
    }

    private Race createRace(String startDateTime, boolean resultIsSet) {
        Race race = new Race();
        race.setSeason(season);
        race.setStartDateTime(LocalDateTime.parse(startDateTime));
        race.setResultSet(resultIsSet);
        return race;
    }
}