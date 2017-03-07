package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.Penalty;
import andkantor.f1betting.entity.Point;
import andkantor.f1betting.model.bet.Bet;

import java.util.Optional;

public class PenaltyCalculator implements PointCalculator {

    @Override
    public Point calculate(Bet bet, CalculationContext context) {
        Optional<Penalty> penalty = context.getPenalty(bet.getDriver(), bet.getFinalPosition());

        if (penalty.isPresent()) {
            return penalty.get().getPoint();
        }

        return Point.ZERO;
    }
}
