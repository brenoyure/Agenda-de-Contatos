package br.albatross.agenda.security.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile SingularAttribute<Role, String>  name;

}
