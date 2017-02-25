package andkantor.f1betting.model.bet;

import andkantor.f1betting.model.race.Position;
import andkantor.f1betting.model.race.Race;
import andkantor.f1betting.model.race.Racer;

public class Penalty {

    private Race race;
    private Racer racer;
    private Position position;
    private Point point;

    public Penalty(Race race, Racer racer, Position position, Point point) {
        this.race = race;
        this.racer = racer;
        this.position = position;
        this.point = point;
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

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
