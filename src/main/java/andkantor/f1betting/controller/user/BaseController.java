package andkantor.f1betting.controller.user;

import andkantor.f1betting.entity.User;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    @Autowired
    UserRepository userRepository;

    protected User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        return userRepository.findByUsername(name);
    }

}
