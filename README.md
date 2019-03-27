# Reservas-API
CRUD de uma API RESTful desenvolvida em Java utilizando Spring Boot.

[![node](https://img.shields.io/badge/Java-1.8.0-lightgray.svg)](https://www.java.com/pt_BR/download/)
[![node](https://img.shields.io/badge/Maven-3.5.4-steelblue.svg)](https://maven.apache.org/download.cgi)
[![node](https://img.shields.io/badge/Plugin-Lombok_1.18.6-indianRed.svg)](https://projectlombok.org/)
[![node](https://img.shields.io/badge/Database-PostgreSQL--9.4.1212-blue.svg)](https://www.postgresql.org/download/)
[![node](https://img.shields.io/badge/Hibernate-5.4.1--Final-peru.svg)](http://hibernate.org/)

 # Configurações 
 ### Configuração da base de dados PostgreSQL:
 
 Criando o banco de dados: </br>

    CREATE DATABASE reservas
      WITH 
      OWNER = postgres
      ENCODING = 'UTF8'
      CONNECTION LIMIT = -1;    
      
 O usuário padrão de conexão com o banco de dados configurado é: <html><b>postgres</b></html> e o password padrão configurado é <html><b>masterkey</b></html>, caso utilize usuário e, ou senha diferente, você deve alterar tais informações no arquivo ```application.properties```.
 </br>
 Propriedades:
 </br>
 ```spring.datasource.username=postgres```
 </br>
 ```spring.datasource.password=masterkey```
    
 Com a base de dados devidamente criada e configurada, basta iniciar a aplicação e utizá-la a vontade   =)    

