package com.ecreyes.springbootbackendapi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecreyes.springbootbackendapi.model.Client;

public interface IClientService {

    public List<Client> findAll();

    public Page<Client> findAll(Integer page);

    public Client findById(Long id);

    public Client save(Client client);

    public Client update(Client client, Long id);

    public boolean delete(Long id);
}
