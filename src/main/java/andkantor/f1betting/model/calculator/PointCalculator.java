package andkantor.f1betting.model.calculator;

import andkantor.f1betting.model.bet.Point;
import andkantor.f1betting.model.bet.Bet;

public interface PointCalculator {

    Point calculate(Bet bet, CalculationContext context);

}
