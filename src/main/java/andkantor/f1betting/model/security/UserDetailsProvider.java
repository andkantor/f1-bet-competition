package andkantor.f1betting.model.security;

import andkantor.f1betting.entity.UserRole;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsProvider implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        andkantor.f1betting.entity.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " is not found!");
        }
        return buildUserForAuthentication(user, buildUserAuthority(user.getUserRoles()));
    }

    private User buildUserForAuthentication(andkantor.f1betting.entity.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
       return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole()))
                .collect(Collectors.toList());
    }
}
