package andkantor.f1betting.form;

import andkantor.f1betting.entity.User;

public class UserForm {

    private String username;
    private String email;
    private String password;
    private boolean enabled;

    public UserForm() {
    }

    public UserForm(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
    }

    public String getUsername() {
        return username;
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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
