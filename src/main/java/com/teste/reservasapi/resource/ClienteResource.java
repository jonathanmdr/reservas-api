package com.teste.reservasapi.resource;

import com.teste.reservasapi.model.Cliente;
import com.teste.reservasapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findOne(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findOne(id);

        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

}
