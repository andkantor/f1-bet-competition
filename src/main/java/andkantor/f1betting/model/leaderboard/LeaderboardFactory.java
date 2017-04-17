package andkantor.f1betting.model.leaderboard;

import andkantor.f1betting.entity.CumulativePoint;
import andkantor.f1betting.entity.User;
import andkantor.f1betting.model.race.RaceList;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.repository.RacePointRepository;

import java.util.List;
import java.util.Map;

public class LeaderboardFactory {

    private ConfigurationManager configurationManager;
    private RacePointRepository racePointRepository;

    public LeaderboardFactory(ConfigurationManager configurationManager, RacePointRepository racePointRepository) {
        this.configurationManager = configurationManager;
        this.racePointRepository = racePointRepository;
    }

    public Leaderboard create(List<User> users, RaceList raceList) {
        if (configurationManager.getConfiguration().getActiveSeason() == 0) {
            return new EmptyLeaderboard(users);
        }

        return new DefaultLeaderboard(
                users,
                racePointRepository.sumUserPoints(users, raceList.getSeason()),
                createPreviousLeaderboard(users, raceList));
    }

    private Leaderboard createPreviousLeaderboard(List<User> users, RaceList raceList) {
        Map<User, CumulativePoint> cumulativePoints = racePointRepository.sumUserPointsUntilRace(
                users, raceList.getRaceBeforeLast());

        return new SkeletonLeaderboard(users, cumulativePoints);
    }
}
