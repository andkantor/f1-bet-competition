package andkantor.f1betting.form.validator;

import andkantor.f1betting.form.RegistrationForm;
import andkantor.f1betting.form.annotation.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        RegistrationForm registrationForm = (RegistrationForm) obj;
        return registrationForm.getPassword().equals(registrationForm.getConfirmPassword());
    }
}
