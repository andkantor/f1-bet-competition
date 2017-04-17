package andkantor.f1betting.model.leaderboard;

import andkantor.f1betting.entity.Point;
import andkantor.f1betting.entity.User;

import java.util.List;

public class EmptyLeaderboard implements Leaderboard {

    private List<User> users;

    public EmptyLeaderboard(List<User> users) {
        this.users = users;
        this.users.sort((user1, user2) -> user1.getUsername().compareTo(user2.getUsername()));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public int getPlace(User user) {
        return users.indexOf(user) + 1;
    }

    @Override
    public Point getPoints(User user) {
        return Point.ZERO;
    }

    @Override
    public int getPlaceChange(User user) {
        return 0;
    }

}
