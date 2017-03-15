package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.*;
import andkantor.f1betting.model.user.UserProvider;
import andkantor.f1betting.repository.BetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RacePointCalculator {

    private BetRepository betRepository;
    private UserProvider userProvider;
    private BetPointCalculator betPointCalculator;

    public RacePointCalculator(
            BetRepository betRepository,
            UserProvider userProvider,
            BetPointCalculator betPointCalculator
    ) {
        this.betRepository = betRepository;
        this.userProvider = userProvider;
        this.betPointCalculator = betPointCalculator;
    }

    public List<RacePoint> calculate(Race race, CalculationContext context) {
        List<Bet> bets = betRepository.findByRace(race);
        List<User> users = userProvider.getActiveUsers();

        List<RacePoint> racePoints = new ArrayList<>();

        users.forEach(user -> {
            Optional<Point> point = bets.stream()
                    .filter(bet -> bet.getUser().equals(user))
                    .map(bet -> betPointCalculator.calculatePoints(bet, context))
                    .reduce(Point::add);

            racePoints.add(new RacePoint(user, race, point.orElse(Point.ZERO)));
        });

        return racePoints;
    }
}
