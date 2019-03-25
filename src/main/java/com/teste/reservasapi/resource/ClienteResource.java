package com.teste.reservasapi.resource;

import com.teste.reservasapi.event.RecursoCriadoEvent;
import com.teste.reservasapi.model.Cliente;
import com.teste.reservasapi.repository.ClienteRepository;
import com.teste.reservasapi.repository.filter.ClienteFilter;
import com.teste.reservasapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping
    public Page<Cliente> findAll(ClienteFilter clienteFilter, Pageable pageable) {
        return clienteRepository.findAllFiltered(clienteFilter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findOne(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findOne(id);

        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
        Cliente clienteSalvo = clienteRepository.save(cliente);

        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.update(id, cliente);

        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteRepository.delete(id);
    }

}
