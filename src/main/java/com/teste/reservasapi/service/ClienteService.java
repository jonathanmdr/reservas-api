package com.teste.reservasapi.service;

import com.teste.reservasapi.model.Cliente;
import com.teste.reservasapi.repository.ClienteRepository;
import com.teste.reservasapi.repository.filter.ClienteFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> findAllFiltered(ClienteFilter filter, Pageable pageable) {
        return clienteRepository.findAllFiltered(filter, pageable);
    }

    public Cliente findOne(Long id) {
        return clienteRepository.findOne(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente cliente) {
        Cliente clienteSalvo = findById(id);
        BeanUtils.copyProperties(cliente, clienteSalvo, "id");

        return clienteRepository.save(clienteSalvo);
    }

    public void delete(Long id) {
        clienteRepository.delete(id);
    }

    private Cliente findById(Long id) {
        Cliente cliente = clienteRepository.findOne(id);

        if (cliente == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return cliente;
    }

}
