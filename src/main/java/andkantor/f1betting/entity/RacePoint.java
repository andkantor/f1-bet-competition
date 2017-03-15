package andkantor.f1betting.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "race_point")
@IdClass(RacePoint.RacePointId.class)
public class RacePoint {

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Race race;

    @Embedded
    @Column(name = "point")
    private Point point;

    public RacePoint() {
    }

    public RacePoint(User user, Race race, Point point) {
        this.user = user;
        this.race = race;
        this.point = point;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public static class RacePointId implements Serializable {
        public String user;
        public Long race;
    }
}
