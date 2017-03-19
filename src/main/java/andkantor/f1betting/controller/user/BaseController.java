package andkantor.f1betting.controller.user;

import andkantor.f1betting.entity.User;
import andkantor.f1betting.model.DateTimeFormatter;
import andkantor.f1betting.model.Flash;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Flash flash;

    @Autowired
    DateTimeFormatter dateTimeFormatter;

    protected boolean userLoggedIn() {
        return getAuthentication().isAuthenticated()
                && getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("USER"));
    }

    protected User getUser() {
        String name = getAuthentication().getName();
        return userRepository.findByUsername(name);
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @ModelAttribute
    protected DateTimeFormatter dateTimeFormatter() {
        return dateTimeFormatter;
    }

    @ModelAttribute
    protected Flash flash() {
        return flash;
    }
}
