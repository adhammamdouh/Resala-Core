package org.resala.Annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if(phoneNumber==null||phoneNumber.length() != 11 || !phoneNumber.startsWith("01"))
            return false;
        switch (phoneNumber.charAt(2)){
            case '0':
            case '1':
            case '2':
            case '5':
                return true;
            default:
                return false;
        }
    }
}
