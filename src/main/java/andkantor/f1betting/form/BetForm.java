package andkantor.f1betting.form;

import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.form.annotation.PositionRange;
import andkantor.f1betting.form.annotation.UniqueDriver;
import andkantor.f1betting.form.annotation.UniquePosition;

import java.util.List;

@UniqueDriver
@UniquePosition
@PositionRange
public class BetForm {

    private List<Bet> bets;

    public BetForm() {
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
