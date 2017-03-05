package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.bet.Bet;
import andkantor.f1betting.model.bet.Point;
import andkantor.f1betting.entity.Position;

public class NearMissCalculator implements PointCalculator {

    @Override
    public Point calculate(Bet bet, CalculationContext context) {
        Position finalPosition = context.getPosition(bet.getDriver());

        if (bet.getFinalPosition().difference(finalPosition) == 1) {
            return Point.NEAR_MISS;
        }

        return Point.ZERO;
    }
}
