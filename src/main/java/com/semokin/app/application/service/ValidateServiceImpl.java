package com.semokin.app.application.service;

import com.semokin.app.application.contract.ValidateService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
    private final Validator validator;
    @Override
    public void validate(Object object) {
        Set<ConstraintViolation<Object>> constraintViolations= validator.validate(object);
        if (!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
