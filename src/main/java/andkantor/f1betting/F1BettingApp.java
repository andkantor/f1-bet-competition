package andkantor.f1betting;

import andkantor.f1betting.config.F1BettingAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        F1BettingAppConfig.class
})
public class F1BettingApp {
    public static void main(String[] args) {
        SpringApplication.run(F1BettingApp.class, args);
    }
}
