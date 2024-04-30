package br.albatross.agenda.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = Setor.class)
public abstract class Setor_ {

    public static volatile SingularAttribute<Setor, Integer>                id;
    public static volatile SingularAttribute<Setor, String>                 sigla;
    public static volatile SingularAttribute<Setor, String>                 descricao;
    public static volatile SingularAttribute<Setor, UnidadeAdministrativa>  unidadeAdministrativa;

}
