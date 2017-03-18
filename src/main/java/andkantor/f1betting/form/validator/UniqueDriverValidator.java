package andkantor.f1betting.form.validator;

import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.form.BetForm;
import andkantor.f1betting.form.annotation.UniqueDriver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueDriverValidator implements ConstraintValidator<UniqueDriver, Object> {

    @Override
    public void initialize(UniqueDriver constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        BetForm betForm = (BetForm) obj;
        Set<Driver> driverSet = betForm.getBets().stream()
                .map(Bet::getDriver)
                .collect(Collectors.toSet());

        return driverSet.size() == betForm.getBets().size();
    }
}