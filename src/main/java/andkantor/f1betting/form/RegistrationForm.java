package andkantor.f1betting.form;

import andkantor.f1betting.form.annotation.UniqueUsername;
import andkantor.f1betting.form.annotation.PasswordMatches;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@UniqueUsername
@PasswordMatches
public class RegistrationForm {

    @NotNull(message = "Username cannot be empty")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be empty")
    @NotEmpty(message = "Password cannot be empty")
    @Length(min = 6, message = "Password min length is 6 characters")
    private String password;

    private String confirmPassword;

    public RegistrationForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
