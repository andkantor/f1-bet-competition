package andkantor.f1betting.config;

import andkantor.f1betting.form.UserDetailsProvider;
import andkantor.f1betting.model.Flash;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Flash flash;

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
                .antMatchers("/race/**").hasAuthority("USER")
                .antMatchers("/login", "/register").anonymous()
                .antMatchers("/logout", "/password-reset").authenticated()

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
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    flash.setMessage("You have successfully logged out");
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath().concat("/home"));
                })
                .permitAll();
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            if (authentication.getName().equals("admin")) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath().concat("/admin/home"));
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath().concat("/home"));
            }
        };
    }
}