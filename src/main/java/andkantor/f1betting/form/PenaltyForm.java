package andkantor.f1betting.form;

import andkantor.f1betting.entity.Penalty;
import andkantor.f1betting.entity.Race;

import java.util.List;

public class PenaltyForm {

    private Race race;
    private List<Penalty> penalties;

    public PenaltyForm() {
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }
}
