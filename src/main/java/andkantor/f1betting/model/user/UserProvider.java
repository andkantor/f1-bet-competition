package andkantor.f1betting.model.user;

import andkantor.f1betting.entity.User;
import andkantor.f1betting.repository.UserRepository;

import java.util.List;

public class UserProvider {

    private UserRepository userRepository;

    public UserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getActiveUsers() {
        return userRepository.findByEnabledAndUsernameNot(true, "admin");
    }
}
