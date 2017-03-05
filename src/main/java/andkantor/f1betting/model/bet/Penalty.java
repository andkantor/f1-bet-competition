package andkantor.f1betting.model.bet;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.Position;
import andkantor.f1betting.entity.Race;

public class Penalty {

    private Race race;
    private Driver driver;
    private Position position;
    private Point point;

    public Penalty(Race race, Driver driver, Position position, Point point) {
        this.race = race;
        this.driver = driver;
        this.position = position;
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
