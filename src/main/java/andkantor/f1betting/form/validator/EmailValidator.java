package andkantor.f1betting.form.validator;

import andkantor.f1betting.form.annotation.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, Object> {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;

    @Override
    public void initialize(Email constraintAnnotation) {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        String email = (String) obj;
        return pattern.matcher(email).matches();
    }
}
