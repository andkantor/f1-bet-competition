package andkantor.f1betting.model.leaderboard;

import andkantor.f1betting.entity.Point;
import andkantor.f1betting.entity.User;

import java.util.List;

public interface Leaderboard {

    List<User> getUsers();

    int getPlace(User user);

    Point getPoints(User user);

    int getPlaceChange(User user);

    default boolean movedUp(User user) {
        return getPlaceChange(user) > 0;
    }

    default boolean movedDown(User user) {
        return getPlaceChange(user) < 0;
    }

    default boolean noPlaceChange(User user) {
        return getPlaceChange(user) == 0;
    }
}
