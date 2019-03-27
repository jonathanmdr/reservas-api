package com.teste.reservasapi.service;

import com.teste.reservasapi.model.Cliente;
import com.teste.reservasapi.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente update(Long id, Cliente cliente) {
        Cliente clienteSalvo = findById(id);
        BeanUtils.copyProperties(cliente, clienteSalvo, "id");

        return clienteRepository.save(clienteSalvo);
    }

    private Cliente findById(Long id) {
        Cliente cliente = clienteRepository.findOne(id);

        if (cliente == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return cliente;
    }

}
