package andkantor.f1betting.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "final_position")
@AttributeOverrides({
        @AttributeOverride(name="position", column = @Column(nullable = true))
})
@IdClass(FinalPosition.FinalPositionId.class)
public class FinalPosition {

    @Id
    @ManyToOne
    private Race race;

    @Id
    @ManyToOne
    private Driver driver;

    @Embedded
    @Column(name = "position")
    private Position position;

    public FinalPosition() {
    }

    public FinalPosition(Race race, Driver driver, Position position) {
        this.race = race;
        this.driver = driver;
        this.position = position;
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
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getDriverName() {
        return driver.getFirstName() + ' ' + driver.getLastName();
    }

    public static class FinalPositionId implements Serializable {
        public Long race;
        public Long driver;
    }
}
