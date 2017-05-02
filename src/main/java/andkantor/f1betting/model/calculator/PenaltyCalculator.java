package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.entity.Penalty;
import andkantor.f1betting.entity.Point;
import andkantor.f1betting.entity.Position;

public class PenaltyCalculator implements PointCalculator {

    @Override
    public Point calculate(Bet bet, CalculationContext context) {
        Position finalPosition = context.getPosition(bet.getDriver());

        if (bet.getFinalPosition().difference(finalPosition) < 2) {
            return context.getPenalty(bet.getDriver(), finalPosition)
                    .map(Penalty::getPoint)
                    .orElse(Point.ZERO);
        }

        return Point.ZERO;
    }
}
