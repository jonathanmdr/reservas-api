package com.teste.reservasapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public class Cliente_ {

    public static volatile SingularAttribute<Cliente, Long> id;
    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile SingularAttribute<Cliente, String> documento;
    public static volatile SingularAttribute<Cliente, String> telefone;

}
