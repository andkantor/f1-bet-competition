package andkantor.f1betting.form;

import andkantor.f1betting.form.annotation.Email;
import andkantor.f1betting.form.annotation.PasswordMatches;
import andkantor.f1betting.form.annotation.UniqueEmail;
import andkantor.f1betting.form.annotation.UniqueUsername;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@UniqueUsername
@UniqueEmail
@PasswordMatches
public class RegistrationForm implements PasswordForm {

    @NotNull(message = "Username cannot be empty")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Email cannot be empty")
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Override
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
