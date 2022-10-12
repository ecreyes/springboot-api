package com.ecreyes.springbootbackendapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ecreyes.springbootbackendapi.model.Client;
import com.ecreyes.springbootbackendapi.repository.ClientRepository;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void findAllTest() {
        List<Client> clients = Stream.of(
                new Client(),
                new Client()).collect(Collectors.toList());
        when(clientRepository.findAll()).thenReturn(clients);

        assertEquals(clients.size(), clientService.findAll().size());
    }
}
