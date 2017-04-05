package andkantor.f1betting.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "penalty")
@IdClass(Penalty.PenaltyId.class)
public class Penalty {

    @Id
    @ManyToOne
    private Race race;

    @Id
    @ManyToOne
    private Driver driver;

    //TODO make it Position
    @Id
    @Column(name = "position")
    private int positionAsInt;

    @Embedded
    @Column(name = "point")
    private Point point;

    public Penalty() {
    }

    public Penalty(Race race, Driver driver, Position position, Point point) {
        this.race = race;
        this.driver = driver;
        this.positionAsInt = position.getPosition();
        this.point = point;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Position getPosition() {
        return new Position(positionAsInt);
    }

    public void setPosition(Position position) {
        this.positionAsInt = position.getPosition();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getPositionAsInt() {
        return positionAsInt;
    }

    public void setPositionAsInt(int positionAsInt) {
        this.positionAsInt = positionAsInt;
    }

    public static class PenaltyId implements Serializable {
        public Long race;
        public Long driver;
        public int positionAsInt;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PenaltyId penaltyId = (PenaltyId) o;

            if (positionAsInt != penaltyId.positionAsInt) return false;
            if (!race.equals(penaltyId.race)) return false;
            return driver.equals(penaltyId.driver);

        }

        @Override
        public int hashCode() {
            int result = race.hashCode();
            result = 31 * result + driver.hashCode();
            result = 31 * result + positionAsInt;
            return result;
        }
    }
}
