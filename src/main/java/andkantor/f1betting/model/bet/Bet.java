package andkantor.f1betting.model.bet;

import andkantor.f1betting.model.race.Position;
import andkantor.f1betting.model.race.Racer;
import andkantor.f1betting.model.race.Race;

public class Bet {

    private Bettor bettor;
    private Race race;
    private Racer racer;
    private Position finalPosition;

    public Bettor getBettor() {
        return bettor;
    }

    public void setBettor(Bettor bettor) {
        this.bettor = bettor;
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

    public Position getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(Position finalPosition) {
        this.finalPosition = finalPosition;
    }
}
