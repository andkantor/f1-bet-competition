package andkantor.f1betting.model.race;

import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RaceListTest {

    private Season season;

    @Before
    public void setUp() {
        season = mock(Season.class);
    }

    @Test
    public void testGetRacesIsOrdered() {
        Race race1 = race("2017-04-17T14:00:00", false, true);
        Race race2 = race("2017-04-20T14:00:00", false, true);
        Race race3 = race("2017-04-24T14:00:00", false, true);

        List<Race> races = raceList(race2, race3, race1).getRaces();

        assertThat(races).hasSize(3);
        assertThat(races.get(0)).isEqualTo(race1);
        assertThat(races.get(1)).isEqualTo(race2);
        assertThat(races.get(2)).isEqualTo(race3);
    }

    @Test
    public void testGetNextRaceIfEmpty() {
        assertThat(raceList().getNextRace()).isNull();
    }

    @Test
    public void testGetNextRaceWhenHasNextRace() {
        Race race = race("2017-04-17T14:00:00", false, true);
        assertThat(raceList(race).getNextRace()).isEqualTo(race);
    }

    @Test
    public void testGetNextRaceWhenHasPreviousAndNextRace() {
        Race race1 = race("2017-04-17T14:00:00", true, false);
        Race race2 = race("2017-04-20T14:00:00", false, true);
        assertThat(raceList(race1, race2).getNextRace()).isEqualTo(race2);
    }

    @Test
    public void testGetNextRaceWhenHasPreviousRace() {
        Race race = race("2017-04-17T14:00:00", false, false);
        assertThat(raceList(race).getNextRace()).isNull();
    }

    @Test
    public void testGetLastRaceNoLastRace() {
        Race race = race("2017-04-17T14:00:00", false, true);
        assertThat(raceList(race).getLastRace()).isNull();
    }

    @Test
    public void testGetLastRaceHasLastRace() {
        Race race1 = race("2017-04-17T14:00:00", true, false);
        Race race2 = race("2017-04-20T14:00:00", true, false);
        Race race3 = race("2017-04-25T14:00:00", false, true);
        assertThat(raceList(race1, race2, race3).getLastRace()).isEqualTo(race2);
    }

    @Test
    public void testGetLastRaceNoNextRace() {
        Race race1 = race("2017-04-17T14:00:00", true, false);
        Race race2 = race("2017-04-20T14:00:00", true, false);
        Race race3 = race("2017-04-25T14:00:00", true, false);
        assertThat(raceList(race1, race2, race3).getLastRace()).isEqualTo(race3);
    }

    @Test
    public void testGetRaceBeforeLastNoRace() {
        assertThat(raceList().getRaceBeforeLast()).isNull();
    }

    @Test
    public void testGetRaceBeforeLastNoPreviousRace() {
        Race race1 = race("2017-04-17T14:00:00", false, true);
        assertThat(raceList(race1).getRaceBeforeLast()).isNull();
    }

    @Test
    public void testGetRaceBeforeLastOnlyOnePreviousRace() {
        Race race1 = race("2017-04-17T14:00:00", true, false);
        Race race2 = race("2017-04-20T14:00:00", false, true);
        assertThat(raceList(race1, race2).getRaceBeforeLast()).isNull();
    }

    @Test
    public void testGetRaceBeforeLastMorePreviousRaces() {
        Race race1 = race("2017-04-17T14:00:00", true, false);
        Race race2 = race("2017-04-20T14:00:00", true, false);
        Race race3 = race("2017-04-25T14:00:00", false, true);
        assertThat(raceList(race1, race2, race3).getRaceBeforeLast()).isEqualTo(race1);
    }

    @Test
    public void testGetRaceBeforeLastMorePreviousRacesNoNextRace() {
        Race race1 = race("2017-04-17T14:00:00", true, false);
        Race race2 = race("2017-04-20T14:00:00", true, false);
        Race race3 = race("2017-04-25T14:00:00", true, false);
        assertThat(raceList(race1, race2, race3).getRaceBeforeLast()).isEqualTo(race2);
    }

    private RaceList raceList(Race... races) {
        return new RaceList(season, Arrays.asList(races));
    }

    private Race race(String startDateTime, boolean resultIsSet, boolean canBeBetOn) {
        Race race = mock(Race.class);
        doReturn(LocalDateTime.parse(startDateTime)).when(race).getStartDateTime();
        doReturn(resultIsSet).when(race).isResultSet();
        doReturn(canBeBetOn).when(race).canBeBetOn();
        return race;
    }

}