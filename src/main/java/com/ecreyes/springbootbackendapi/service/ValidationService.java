package com.ecreyes.springbootbackendapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ValidationService implements IValidationService {

    @Override
    public List<Map<String, Object>> getErrors(BindingResult bindingResult) {
        List<Map<String, Object>> errors = bindingResult.getFieldErrors().stream().map(bindingError -> {
            Map<String, Object> errorField = new HashMap<>();
            errorField.put(bindingError.getField(), bindingError.getDefaultMessage());
            return errorField;
        }).collect(Collectors.toList());
        return errors;
    }

}
