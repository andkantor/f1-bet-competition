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
    }
}
