package br.albatross.agenda.models.metamodels;

import br.albatross.agenda.models.entities.UnidadeAdministrativa;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UnidadeAdministrativa.class)
public abstract class UnidadeAdministrativa_ {

    public static volatile SingularAttribute<UnidadeAdministrativa, Short>  id;
    public static volatile SingularAttribute<UnidadeAdministrativa, String> sigla;
    public static volatile SingularAttribute<UnidadeAdministrativa, String> descricao;

}
