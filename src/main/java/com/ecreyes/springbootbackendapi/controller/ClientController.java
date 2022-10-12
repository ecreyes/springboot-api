package com.ecreyes.springbootbackendapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecreyes.springbootbackendapi.model.Client;
import com.ecreyes.springbootbackendapi.service.IClientService;
import com.ecreyes.springbootbackendapi.service.IValidationService;

@RestController
public class ClientController {
    @Autowired
    private IClientService clientService;
    @Autowired
    private IValidationService validationService;

    @GetMapping("/clients")
    public ResponseEntity<Map<String, Object>> index() {
        List<Client> clients = this.clientService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("data", clients);
        return new ResponseEntity<Map<String, Object>>(response,
                clients == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/clients/page/{page}")
    public Page<Client> index(@PathVariable Integer page) {
        return this.clientService.findAll(page);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Long id) {
        Client client = this.clientService.findById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("data", client);
        return new ResponseEntity<Map<String, Object>>(response, client == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Client client, BindingResult bindingResult) {
        Client clientDb = this.clientService.save(client);
        Map<String, Object> response = new HashMap<>();
        response.put("data", clientDb);
        if (bindingResult.hasErrors()) {
            List<Map<String, Object>> errors = this.validationService.getErrors(bindingResult);
            response.put("errors", errors);
        }
        return new ResponseEntity<Map<String, Object>>(response,
                clientDb == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Client client, BindingResult bindingResult,
            @PathVariable Long id) {
        Client clientDb = this.clientService.update(client, id);
        Map<String, Object> response = new HashMap<>();
        response.put("data", clientDb);
        if (bindingResult.hasErrors()) {
            List<Map<String, Object>> errors = this.validationService.getErrors(bindingResult);
            response.put("errors", errors);
        }
        return new ResponseEntity<Map<String, Object>>(response,
                clientDb == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Boolean result = this.clientService.delete(id);
        Map<String, Object> response = new HashMap<>();
        response.put("data", result);
        return new ResponseEntity<Map<String, Object>>(response,
                result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
