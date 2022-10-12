package com.ecreyes.springbootbackendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecreyes.springbootbackendapi.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}