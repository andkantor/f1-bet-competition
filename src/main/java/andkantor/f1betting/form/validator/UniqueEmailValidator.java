package andkantor.f1betting.form.validator;

import andkantor.f1betting.form.RegistrationForm;
import andkantor.f1betting.form.annotation.UniqueEmail;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        RegistrationForm registrationForm = (RegistrationForm) obj;
        return userRepository.findByEmail(registrationForm.getEmail()) == null;
    }
}
