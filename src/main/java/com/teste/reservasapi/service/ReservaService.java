package com.teste.reservasapi.service;

import com.teste.reservasapi.model.Cliente;
import com.teste.reservasapi.model.Reserva;
import com.teste.reservasapi.repository.ClienteRepository;
import com.teste.reservasapi.repository.ReservaRepository;
import com.teste.reservasapi.service.exception.ClienteInexistenteException;
import com.teste.reservasapi.service.utils.CalculaDiariaHospedagem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Reserva> findAll(Pageable pageable) {
        return reservaRepository.findAll(pageable);
    }

    public Page<Reserva> findAllByDataCheckOutIsNotNull(Pageable pageable) {
        return reservaRepository.findAllByDataCheckOutIsNotNull(pageable);
    }

    public Page<Reserva> findAllByDataCheckOutIsNull(Pageable pageable) {
        return reservaRepository.findAllByDataCheckOutIsNull(pageable);
    }

    public Reserva findOne(Long id) {
        return reservaRepository.findOne(id);
    }

    public Reserva save(Reserva reserva) {
        reserva.setValor(new BigDecimal(CalculaDiariaHospedagem.getValor(reserva.getDataCheckIn(), reserva.getAdicionalCarro())));

        validarClienteExistente(reserva);

        return reservaRepository.save(reserva);
    }

    public Reserva update(Long id, Reserva reserva) {
        Reserva reservaSalva = findById(id);

        if (!reserva.getCliente().equals(reservaSalva.getCliente())) {
            validarClienteExistente(reservaSalva);
        }

        BeanUtils.copyProperties(reserva, reservaSalva, "id");

        reservaSalva.setValor(new BigDecimal(CalculaDiariaHospedagem.getValor(reservaSalva)));

        return reservaRepository.save(reservaSalva);
    }

    public void delete(Long id) {
        reservaRepository.delete(id);
    }

    private Reserva findById(Long id) {
        Reserva reserva = reservaRepository.findOne(id);

        if (reserva == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return reserva;
    }

    private void validarClienteExistente(Reserva reserva) {
        Cliente cliente = null;

        if (reserva.getCliente().getId() != null) {
            cliente = clienteRepository.findOne(reserva.getCliente().getId());
        }

        if (cliente == null) {
            throw new ClienteInexistenteException();
        }
    }

}
