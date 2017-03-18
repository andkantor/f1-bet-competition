package andkantor.f1betting.form.annotation;

import andkantor.f1betting.form.validator.UniqueDriverValidator;

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
@Constraint(validatedBy = UniqueDriverValidator.class)
@Documented
public @interface UniqueDriver {
    String message() default "Drivers must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
