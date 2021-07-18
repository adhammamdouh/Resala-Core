package org.resala.Annotation.Domain.Name;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainNameValidator implements ConstraintValidator<DomainName, String> {
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^@[\\w]+\\.[A-Za-z]{2,3}$", Pattern.CASE_INSENSITIVE);

    private boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public void initialize(DomainName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return validate(userName);
    }
}
