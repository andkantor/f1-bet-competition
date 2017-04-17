package andkantor.f1betting.repository;

import andkantor.f1betting.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static andkantor.f1betting.model.DateTimeFormatter.DATE_TIME_FORMATTER;

public interface RacePointRepository extends CrudRepository<RacePoint, RacePoint.RacePointId> {

    List<RacePoint> findByRace(Race race);

    @Query(value = "" +
            "select rp.user_username, sum(rp.point) as point " +
            "from race_point rp " +
            "inner join race r on rp.race_id = r.id " +
            "inner join season s on r.season_id = s.id " +
            "where s.id = ?1 " +
            "group by rp.user_username " +
            "order by point desc", nativeQuery = true)
    List<Object[]> sumUserPoints(Season season);

    default Map<User, CumulativePoint> sumUserPoints(List<User> users, Season season) {
        Map<String, CumulativePoint> points = sumUserPoints(season).stream()
                .map(objects -> new CumulativePoint(String.valueOf(objects[0]), (BigDecimal) objects[1]))
                .collect(Collectors.toMap(CumulativePoint::getUsername, cp -> cp));

        return users.stream()
                .collect(Collectors.toMap(user -> user, user ->
                        points.containsKey(user.getUsername())
                                ? points.get(user.getUsername())
                                : new CumulativePoint(user.getUsername(), BigDecimal.ZERO)
                ));
    }

    @Query(value = "" +
            "select rp.user_username, sum(rp.point) as point " +
            "from race_point rp " +
            "inner join race r on rp.race_id = r.id " +
            "inner join season s on r.season_id = s.id " +
            "where s.id = ?1 " +
            "and r.start_date_time <= ?2 " +
            "group by rp.user_username " +
            "order by point desc", nativeQuery = true)
    List<Object[]> sumUserPointsUntil(Season season, String startDateTime);

    default Map<User, CumulativePoint> sumUserPointsUntilRace(List<User> users, Race race) {
        Season season = race.getSeason();
        String startDateTime = race.getStartDateTime().format(DATE_TIME_FORMATTER);

        Map<String, CumulativePoint> points = sumUserPointsUntil(season, startDateTime).stream()
                .map(objects -> new CumulativePoint(String.valueOf(objects[0]), (BigDecimal) objects[1]))
                .collect(Collectors.toMap(CumulativePoint::getUsername, cp -> cp));

        return users.stream()
                .collect(Collectors.toMap(user -> user, user ->
                        points.containsKey(user.getUsername())
                                ? points.get(user.getUsername())
                                : new CumulativePoint(user.getUsername(), BigDecimal.ZERO)
                ));
    }
}
