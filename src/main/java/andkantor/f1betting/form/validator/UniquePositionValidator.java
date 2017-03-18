package andkantor.f1betting.form.validator;

import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.Position;
import andkantor.f1betting.form.BetForm;
import andkantor.f1betting.form.annotation.UniquePosition;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;

public class UniquePositionValidator implements ConstraintValidator<UniquePosition, Object> {

    @Override
    public void initialize(UniquePosition constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        BetForm betForm = (BetForm) obj;
        Set<Position> positionSet = betForm.getBets().stream()
                .map(Bet::getFinalPosition)
                .collect(Collectors.toSet());

        return positionSet.size() == betForm.getBets().size();
    }
}
