package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.Point;
import andkantor.f1betting.entity.Position;
import andkantor.f1betting.entity.Bet;

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
