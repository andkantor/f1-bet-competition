package andkantor.f1betting.repository;

import andkantor.f1betting.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    List<User> findByEnabledAndUsernameNot(boolean enabled, String username);

}
