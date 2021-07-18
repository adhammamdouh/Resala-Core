package org.resala.Annotation.Domain.Name;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DomainNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainName {
    String locale() default "";

    String message() default "This field should like @gmail.com";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
