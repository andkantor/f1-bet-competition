package andkantor.f1betting.form.validator;

import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.entity.Position;
import andkantor.f1betting.form.BetForm;
import andkantor.f1betting.form.annotation.PositionRange;
import andkantor.f1betting.model.setting.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
public class PositionRangeValidator implements ConstraintValidator<PositionRange, Object> {

    @Autowired
    ConfigurationManager configurationManager;

    @Override
    public void initialize(PositionRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        BetForm betForm = (BetForm) obj;

        int maximum = configurationManager.getConfiguration().getNumberOfPositionsToBetOn();
        int minimum = 1;

        Optional<Position> invalidPosition = betForm.getBets().stream()
                .map(Bet::getFinalPosition)
                .filter(position -> position.getPosition() < minimum || position.getPosition() > maximum)
                .findAny();

        return !invalidPosition.isPresent();
    }
}
