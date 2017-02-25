package andkantor.f1betting.model.race;

public class FinalPosition {

    private Race race;
    private Racer racer;
    private Position position;

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
}
