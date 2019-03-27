package com.teste.reservasapi.resource;

import com.teste.reservasapi.event.RecursoCriadoEvent;
import com.teste.reservasapi.exceptionhandler.ReservasApiExceptionHandler;
import com.teste.reservasapi.model.Reserva;
import com.teste.reservasapi.repository.ReservaRepository;
import com.teste.reservasapi.service.ReservaService;
import com.teste.reservasapi.service.exception.ClienteInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaResource {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public Page<Reserva> findAll(Pageable pageable) {
        return reservaRepository.findAll(pageable);
    }

    @GetMapping(params = "checkout")
    public Page<Reserva> findAllByDataCheckOutIsNotNull(Pageable pageable) {
        return reservaRepository.findAllByDataCheckOutIsNotNull(pageable);
    }

    @GetMapping(params = "checkin")
    public Page<Reserva> findAllByDataCheckOutIsNull(Pageable pageable) {
        return reservaRepository.findAllByDataCheckOutIsNull(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findOne(@PathVariable Long id) {
        Reserva reserva = reservaRepository.findOne(id);

        return reserva != null ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Reserva> save(@Valid @RequestBody Reserva reserva, HttpServletResponse response) {
        Reserva reservaSalva = reservaService.save(reserva);

        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, reservaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @Valid @RequestBody Reserva cliente) {
        Reserva reservaSalva = reservaService.update(id, cliente);

        return ResponseEntity.ok(reservaSalva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        reservaRepository.delete(id);
    }

    @ExceptionHandler(ClienteInexistenteException.class)
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(ClienteInexistenteException ex) {
        String mensagemUsuario = messageSource.getMessage("cliente.inexistente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();

        List<ReservasApiExceptionHandler.Erro> erros = Arrays.asList(new ReservasApiExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));

        return ResponseEntity.badRequest().body(erros);
    }

}
