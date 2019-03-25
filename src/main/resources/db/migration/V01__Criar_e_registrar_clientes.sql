CREATE TABLE cliente(
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(300) NOT NULL,
  documento VARCHAR(20) NOT NULL,
  telefone VARCHAR(11) NOT NULL
);

INSERT INTO cliente(nome, documento, telefone) VALUES ('Jonathan Henrique', '123456', '12345678901'),
                                                      ('João da Silva', '7835643', '1122336655'),
                                                      ('Maria Terezinha', '65498383', '4461235976'),
                                                      ('Tiago Albuquerque Soares', '789678936', '45978465310'),
                                                      ('Tiburcio da Silva', '987828586', '46346205798');