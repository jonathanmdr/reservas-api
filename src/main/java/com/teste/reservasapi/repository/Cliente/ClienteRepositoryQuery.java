package com.teste.reservasapi.repository.Cliente;

import com.teste.reservasapi.model.Cliente;
import com.teste.reservasapi.repository.filter.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteRepositoryQuery {

    Page<Cliente> findAllFiltered(ClienteFilter clienteFilter, Pageable pageable);

}
