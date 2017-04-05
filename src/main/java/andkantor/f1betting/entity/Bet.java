package andkantor.f1betting.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bet")
@IdClass(Bet.BetId.class)
public class Bet {

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Race race;

    @Id
    @ManyToOne
    private Driver driver;

    @Embedded
    @Column(name = "final_position")
    private Position finalPosition;

    public Bet() {
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(Position finalPosition) {
        this.finalPosition = finalPosition;
    }

    public static class BetId implements Serializable {
        public String user;
        public Long race;
        public Long driver;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BetId betId = (BetId) o;

            if (!user.equals(betId.user)) return false;
            if (!race.equals(betId.race)) return false;
            return driver.equals(betId.driver);

        }

        @Override
        public int hashCode() {
            int result = user.hashCode();
            result = 31 * result + race.hashCode();
            result = 31 * result + driver.hashCode();
            return result;
        }
    }
}
