package org.resala.Annotation.Domain.UserName;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DomainUserNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainUserName {
    String locale() default "";

    String message() default "This field should like aZ1@gmail.com";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
