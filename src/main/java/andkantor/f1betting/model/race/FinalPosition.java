package andkantor.f1betting.model.race;

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
    private Racer racer;

    @Embedded
    @Column(name = "position")
    private Position position;

    public FinalPosition() {
    }

    public FinalPosition(Race race, Racer racer, Position position) {
        this.race = race;
        this.racer = racer;
        this.position = position;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getRacerName() {
        return racer.getFirstName() + ' ' + racer.getLastName();
    }

    public static class FinalPositionId implements Serializable {
        public Long race;
        public Long racer;
    }
}
