package br.albatross.agenda.domain.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = UnidadeAdministrativa.class)
public abstract class UnidadeAdministrativa_ {

    public static volatile SingularAttribute<UnidadeAdministrativa, Integer>  id;
    public static volatile SingularAttribute<UnidadeAdministrativa, String>   sigla;
    public static volatile SingularAttribute<UnidadeAdministrativa, String>   descricao;

}
