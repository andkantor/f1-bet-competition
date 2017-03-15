package andkantor.f1betting.config;

import andkantor.f1betting.model.security.UserDetailsProvider;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsProvider()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsProvider userDetailsProvider() {
        return new UserDetailsProvider(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/css/**", "/js/**", "/font-awesome/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/login", "/register").anonymous()
                .antMatchers("/logout").authenticated()

                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()

                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .permitAll();
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            if (authentication.getName().equals("admin")) {
                httpServletResponse.sendRedirect("/admin/home");
            } else {
                httpServletResponse.sendRedirect("/home");
            }
        };
    }
}