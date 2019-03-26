package com.teste.reservasapi.repository;

import com.teste.reservasapi.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Page<Reserva> findAll(Pageable pageable);

}
