package br.albatross.agenda.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = Contato.class)
public abstract class Contato_ {

    public static volatile SingularAttribute<Contato, Long>   id;
    public static volatile SingularAttribute<Contato, String> nome;
    public static volatile SingularAttribute<Contato, String> numero;
    public static volatile SingularAttribute<Contato, Setor>  setor;
    public static volatile SingularAttribute<Contato, Andar>  andar;

}
