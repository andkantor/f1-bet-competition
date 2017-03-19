package andkantor.f1betting.form.validator;

import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.form.BetForm;
import andkantor.f1betting.form.annotation.DriverNotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class DriverNotEmptyValidator implements ConstraintValidator<DriverNotEmpty, Object> {

    @Override
    public void initialize(DriverNotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        BetForm betForm = (BetForm) obj;
        Optional<Bet> invalidBet = betForm.getBets().stream()
                .filter(bet -> bet.getDriver() == null)
                .findAny();

        return !invalidBet.isPresent();
    }
}