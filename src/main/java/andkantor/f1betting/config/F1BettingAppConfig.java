package andkantor.f1betting.config;

import andkantor.f1betting.model.DateTimeFormatter;
import andkantor.f1betting.model.Flash;
import andkantor.f1betting.model.leaderboard.LeaderboardFactory;
import andkantor.f1betting.model.calculator.*;
import andkantor.f1betting.model.penalty.PenaltyCalculator;
import andkantor.f1betting.model.penalty.CalculationDataProvider;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.model.user.UserProvider;
import andkantor.f1betting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;

@Configuration
@EntityScan("andkantor.f1betting.entity")
public class F1BettingAppConfig {

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BetRepository betRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    FinalPositionRepository finalPositionRepository;

    @Autowired
    RacePointRepository racePointRepository;

    @Bean
    public ConfigurationManager configurationManager() {
        return new ConfigurationManager(settingRepository);
    }

    @Bean
    public UserProvider userProvider() {
        return new UserProvider(userRepository);
    }

    @Bean
    public RacePointCalculator racePointCalculator() {
        return new RacePointCalculator(betRepository, userProvider(), betPointCalculator());
    }

    @Bean
    public BetPointCalculator betPointCalculator() {
        return new BetPointCalculator(Arrays.asList(
                new HitCalculator(),
                new NearMissCalculator(),
                new andkantor.f1betting.model.calculator.PenaltyCalculator()
        ));
    }

    @Bean
    public PenaltyProvider penaltyProvider() {
        return new PenaltyProvider(penaltyRepository, driverRepository);
    }

    @Bean
    @SessionScope
    public Flash flash() {
        return new Flash();
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        return new DateTimeFormatter();
    }

    @Bean
    public PenaltyCalculator penaltyCalculator() {
        return new PenaltyCalculator(new CalculationDataProvider(raceRepository, finalPositionRepository));
    }

    @Bean
    public LeaderboardFactory leaderboardFactory() {
        return new LeaderboardFactory(configurationManager(), racePointRepository);
    }
}
