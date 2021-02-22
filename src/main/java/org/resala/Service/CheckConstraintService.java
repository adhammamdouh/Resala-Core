package org.resala.Service;

import org.resala.Exceptions.ConstraintViolationException;
import org.resala.Models.Volunteer.Volunteer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CheckConstraintService {

    public static <T> void checkConstraintViolations(T obj,Class<T> projection) {
        Set<ConstraintViolation<T>> constraintViolations = getConstraintViolations(obj);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException(constraintViolations.stream().findFirst().get().getMessage());
        }
    }
    private static <T> Set<ConstraintViolation<T>> getConstraintViolations(T obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(obj);
    }
}
