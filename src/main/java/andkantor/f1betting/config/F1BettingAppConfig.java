package andkantor.f1betting.config;

import andkantor.f1betting.model.calculator.*;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.model.user.UserProvider;
import andkantor.f1betting.repository.BetRepository;
import andkantor.f1betting.repository.SettingRepository;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class F1BettingAppConfig {

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BetRepository betRepository;

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
                new PenaltyCalculator()
        ));
    }

}
