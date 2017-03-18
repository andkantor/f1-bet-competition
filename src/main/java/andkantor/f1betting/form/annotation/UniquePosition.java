package andkantor.f1betting.form.annotation;

import andkantor.f1betting.form.validator.UniquePositionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniquePositionValidator.class)
@Documented
public @interface UniquePosition {
    String message() default "Positions must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
