package andkantor.f1betting.config;

import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class F1BettingAppConfig {

    @Autowired
    SettingRepository settingRepository;

    @Bean
    public ConfigurationManager configurationManager() {
        return new ConfigurationManager(settingRepository);
    }

}
