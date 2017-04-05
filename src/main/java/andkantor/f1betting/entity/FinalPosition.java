package andkantor.f1betting.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "final_position")
@IdClass(FinalPosition.FinalPositionId.class)
public class FinalPosition {

    @Id
    @ManyToOne
    private Race race;

    //TODO make it Position
    @Id
    @Column(name = "position")
    private int positionAsInt;

    @ManyToOne
    private Driver driver;

    public FinalPosition() {
    }

    public FinalPosition(Race race, Driver driver, Position position) {
        this.race = race;
        this.driver = driver;
        this.positionAsInt = position.getPosition();
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

    public int getPositionAsInt() {
        return positionAsInt;
    }

    public void setPositionAsInt(int positionAsInt) {
        this.positionAsInt = positionAsInt;
    }

    public String getDriverName() {
        if (driver == null) {
            return "N/A";
        }
        return driver.getName();
    }

    public static class FinalPositionId implements Serializable {
        public Long race;
        public int positionAsInt;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FinalPositionId that = (FinalPositionId) o;

            if (positionAsInt != that.positionAsInt) return false;
            return race.equals(that.race);

        }

        @Override
        public int hashCode() {
            int result = race.hashCode();
            result = 31 * result + positionAsInt;
            return result;
        }
    }
}
