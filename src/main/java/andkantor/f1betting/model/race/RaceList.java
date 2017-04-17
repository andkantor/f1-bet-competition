package andkantor.f1betting.model.race;

import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RaceList {

    private Season season;
    private List<Race> races;

    public RaceList(Season season, List<Race> races) {
        this.season = season;
        this.races = races;
    }

    public Season getSeason() {
        return season;
    }

    public List<Race> getRaces() {
        return races;
    }

    public Race getNextRace() {
        return races.stream()
                .filter(Race::canBeBetOn)
                .findFirst()
                .orElse(null);
    }

    public Race getLastRace() {
        List<Race> result = races.stream()
                .filter(Race::isResultSet)
                .collect(toList());

        return result.get(result.size() - 1);
    }

    public Race getRaceBeforeLast() {
        Race lastRace = getLastRace();
        if (races.indexOf(lastRace) > 0) {
            return races.get(races.indexOf(lastRace) - 1);
        }
        return null;
    }
}
