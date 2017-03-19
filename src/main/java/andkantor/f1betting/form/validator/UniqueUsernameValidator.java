package andkantor.f1betting.form.validator;

import andkantor.f1betting.form.RegistrationForm;
import andkantor.f1betting.form.annotation.UniqueUsername;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, Object> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        RegistrationForm registrationForm = (RegistrationForm) obj;
        return userRepository.findByUsername(registrationForm.getUsername()) == null;
    }
}
