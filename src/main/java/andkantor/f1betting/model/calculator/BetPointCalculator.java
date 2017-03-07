package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.Point;
import andkantor.f1betting.model.bet.Bet;

import java.util.List;
import java.util.Optional;

public class BetPointCalculator {

    private List<PointCalculator> pointCalculators;

    public BetPointCalculator(List<PointCalculator> pointCalculators) {
        this.pointCalculators = pointCalculators;
    }

    public Point calculatePoints(Bet bet, CalculationContext context) {
        Optional<Point> result = pointCalculators.stream()
                .map(pointCalculator -> pointCalculator.calculate(bet, context))
                .reduce(Point::add);

        return result.orElse(Point.ZERO);
    }

}
