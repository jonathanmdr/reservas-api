package com.teste.reservasapi.service.utils;

import com.teste.reservasapi.model.Reserva;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CalculaDiariaHospedagem {

    private static Double DIARIA_NORMAL = 120D;
    private static Double DIARIA_FINAL_SEMANA = 150D;
    private static Double CARRO_NORMAL = 15D;
    private static Double CARRO_FINAL_SEMANA = 20D;

    public static Double getValor(Reserva reserva) {
        return calcularDiaria(toDate(reserva.getDataCheckIn()), toDate(reserva.getDataCheckOut()), reserva.getAdicionalCarro());
    }

    public static Double getValor(LocalDateTime data, boolean veiculo) {
        return calcularDiaria(data, veiculo);
    }

    private static Double calcularDiaria(Date dataCheckin, Date dataCheckout, boolean veiculo) {
        Double valorTotal = 0D;

        LocalDateTime dataEntrada = toDateTime(dataCheckin);
        LocalDateTime dataSaida = toDateTime(dataCheckout);

        if ((dataSaida.getHour() * 60 + dataSaida.getMinute()) / 60 >= 16.5) {
            dataSaida = dataSaida.plusDays(1);
        }

        while (dataSaida.toLocalDate().isAfter(dataEntrada.toLocalDate())) {
            valorTotal += calcularDiaria(dataEntrada, veiculo);
            dataEntrada = dataEntrada.plusDays(1);
        }

        return valorTotal;
    }

    private static Double calcularDiaria(LocalDateTime dia, boolean veiculo) {
        Double valor = 0D;

        if (dia.getDayOfWeek().getValue() != 5 && (dia.getDayOfWeek().getValue() != 6)) {
            valor += DIARIA_NORMAL;
            if (veiculo) {
                valor += CARRO_NORMAL;
            }
        } else {
            valor += DIARIA_FINAL_SEMANA;
            if (veiculo) {
                valor += CARRO_FINAL_SEMANA;
            }
        }

        return valor;
    }

    private static LocalDateTime toDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date toDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

}
