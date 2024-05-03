package br.albatross.agenda.security.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String>  username;
	public static volatile SingularAttribute<User, String>  password;
	public static volatile SingularAttribute<User, Role>    role;

}
