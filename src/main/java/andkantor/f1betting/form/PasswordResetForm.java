package andkantor.f1betting.form;

import andkantor.f1betting.form.annotation.PasswordMatches;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@PasswordMatches
public class PasswordResetForm implements PasswordForm {

    @NotNull(message = "Old password cannot be empty")
    @NotEmpty(message = "Old password cannot be empty")
    private String oldPassword;

    @NotNull(message = "New password cannot be empty")
    @NotEmpty(message = "New password cannot be empty")
    @Length(min = 6, message = "Password min length is 6 characters")
    private String password;

    private String confirmPassword;

    public PasswordResetForm() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
