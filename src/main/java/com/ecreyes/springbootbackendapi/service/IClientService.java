package com.ecreyes.springbootbackendapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ecreyes.springbootbackendapi.model.Client;

public interface IClientService {

    public List<Client> findAll();

    public Page<Client> findAll(Integer page);

    public Client findById(Long id);

    public Client save(Client client);

    public Client update(Client client, Long id);

    public Boolean delete(Long id);

    public Boolean uploadFile(MultipartFile file, Long id);
}
