package andkantor.f1betting.config;

import andkantor.f1betting.controller.exception.ExceptionHandlerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringMvcConfig {

    @Bean
    public ExceptionHandlerController exceptionHandlerController() {
        return new ExceptionHandlerController();
    }
}