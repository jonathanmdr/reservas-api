package com.teste.reservasapi.resource;

import com.teste.reservasapi.model.Reserva;
import com.teste.reservasapi.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaResource {

    @Autowired
    private ReservaRepository reservaRepository;

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

}
