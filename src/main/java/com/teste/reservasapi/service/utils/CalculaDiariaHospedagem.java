package com.teste.reservasapi.service.utils;

import com.teste.reservasapi.model.Reserva;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class CalculaDiariaHospedagem {

    private static Double DIARIA_NORMAL = 120D;
    private static Double DIARIA_FINAL_SEMANA = 150D;
    private static Double CARRO_NORMAL = 15D;
    private static Double CARRO_FINAL_SEMANA = 20D;

    public static Double getValor(Reserva reserva) {
        return calcularDiaria(reserva.getDataCheckIn(), reserva.getDataCheckOut(), reserva.getAdicionalCarro());
    }

    public static Double getValor(LocalDateTime dataCheckin, boolean adicionalVeiculo) {
        return calcularDiaria(dataCheckin, adicionalVeiculo);
    }

    private static Double calcularDiaria(LocalDateTime dataCheckin, LocalDateTime dataCheckout, boolean adicionalVeiculo) {
        Double valorTotal = 0D;

        if ((dataCheckout.getHour() * 60 + dataCheckout.getMinute()) / 60 >= 16.5) {
            dataCheckout = dataCheckout.plusDays(1);
        }

        while (dataCheckout.toLocalDate().isAfter(dataCheckin.toLocalDate())) {
            valorTotal += calcularDiaria(dataCheckin, adicionalVeiculo);
            dataCheckin = dataCheckin.plusDays(1);
        }

        return valorTotal;
    }

    private static Double calcularDiaria(LocalDateTime dia, boolean adicionalVeiculo) {
        Double valor = 0D;

        if (dia.getDayOfWeek() != DayOfWeek.SATURDAY && (dia.getDayOfWeek() != DayOfWeek.SUNDAY)) {
            valor += DIARIA_NORMAL;
            if (adicionalVeiculo) {
                valor += CARRO_NORMAL;
            }
        } else {
            valor += DIARIA_FINAL_SEMANA;
            if (adicionalVeiculo) {
                valor += CARRO_FINAL_SEMANA;
            }
        }

        return valor;
    }

}
