package com.ecreyes.springbootbackendapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

public interface IValidationService {
    public List<Map<String, Object>> getErrors(BindingResult bindingResult);
}
