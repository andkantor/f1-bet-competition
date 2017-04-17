package andkantor.f1betting.model.leaderboard;

import andkantor.f1betting.entity.CumulativePoint;
import andkantor.f1betting.entity.User;

import java.util.List;
import java.util.Map;

public class DefaultLeaderboard extends SkeletonLeaderboard {

    private Leaderboard previousLeaderboard;

    public DefaultLeaderboard(List<User> users, Map<User, CumulativePoint> cumulativePoints, Leaderboard previousLeaderboard) {
        super(users, cumulativePoints);
        this.previousLeaderboard = previousLeaderboard;
    }

    @Override
    public int getPlaceChange(User user) {
        return previousLeaderboard.getPlace(user) - getPlace(user);
    }
}
