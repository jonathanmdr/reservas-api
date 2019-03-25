package com.teste.reservasapi.repository;

import com.teste.reservasapi.model.Cliente;
import com.teste.reservasapi.repository.Cliente.ClienteRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {

}
