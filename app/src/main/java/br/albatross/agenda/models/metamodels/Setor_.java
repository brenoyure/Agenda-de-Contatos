package br.albatross.agenda.models.metamodels;

import br.albatross.agenda.models.entities.Setor;
import br.albatross.agenda.models.entities.UnidadeAdministrativa;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Setor.class)
public abstract class Setor_ {

    public static volatile SingularAttribute<Setor, Short>                  id;
    public static volatile SingularAttribute<Setor, String>                 sigla;
    public static volatile SingularAttribute<Setor, String>                 descricao;
    public static volatile SingularAttribute<Setor, UnidadeAdministrativa>  unidadeAdministrativa;

}
