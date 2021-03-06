package andkantor.f1betting.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Comparable<User> {

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>(0);

    public User() {
    }

    public User(String username, String email, String password, boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String email, String password, boolean enabled, Set<UserRole> userRoles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isAdmin() {
        return username.equals("admin");
    }

    @Override
    public int compareTo(User o) {
        return this.username.compareTo(o.username);
    }
}