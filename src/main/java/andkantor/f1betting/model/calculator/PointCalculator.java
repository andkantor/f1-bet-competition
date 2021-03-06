package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.Point;
import andkantor.f1betting.entity.Bet;

public interface PointCalculator {

    Point calculate(Bet bet, CalculationContext context);

}
