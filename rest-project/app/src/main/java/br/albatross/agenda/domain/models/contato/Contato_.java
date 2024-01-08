package br.albatross.agenda.domain.models.contato;

import br.albatross.agenda.domain.models.setor.Setor;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Contato.class)
public abstract class Contato_ {

	public static volatile SingularAttribute<Contato, Short>   id;
	public static volatile SingularAttribute<Contato, String>  nome;
	public static volatile SingularAttribute<Contato, String>  numero;
	public static volatile SingularAttribute<Contato, Setor>   setor;	
	public static volatile SingularAttribute<Contato, String>  andar;

}
