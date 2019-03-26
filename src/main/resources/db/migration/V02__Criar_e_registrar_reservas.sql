CREATE TABLE reserva(
  id BIGSERIAL PRIMARY KEY,
  id_cliente BIGINT NOT NULL,
  data_checkin TIMESTAMP NOT NULL,
  data_checkout TIMESTAMP,
  adicional_veiculo BOOLEAN NOT NULL,
  valor NUMERIC(10,2) NOT NULL DEFAULT 0.0,
CONSTRAINT FK_RESERVA_CLIENTE_ID FOREIGN KEY(id_cliente) REFERENCES cliente(id)
);


INSERT INTO reserva(id_cliente, data_checkin, data_checkout, adicional_veiculo, valor) VALUES (1, '2019-03-26 10:11:00', NULL, FALSE, 120),
                                                                                              (3, '2019-03-26 11:23:15', '2019-03-26 13:07:18', TRUE, 135),
                                                                                              (2, '2019-03-26 08:01:52', '2019-03-26 12:11:50', FALSE, 120),
                                                                                              (5, '2019-03-26 07:35:54', NULL, FALSE, 120),
                                                                                              (4, '2019-03-26 09:34:22', NULL, TRUE, 135);