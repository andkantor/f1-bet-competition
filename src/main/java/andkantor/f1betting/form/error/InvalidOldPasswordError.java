package andkantor.f1betting.form.error;

import org.springframework.validation.ObjectError;

public class InvalidOldPasswordError extends ObjectError {
    public InvalidOldPasswordError() {
        super("Old password", "Invalid old password");
    }
}
