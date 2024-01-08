package br.albatross.agenda.domain.models.setor;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Setor.class)
public abstract class Setor_ {

	public static volatile SingularAttribute<Setor, Integer> id;
	public static volatile SingularAttribute<Setor, String>  sigla;

}