package br.albatross.agenda.security.daos.impl;

import br.albatross.agenda.dao.impl.DaoImpl;
import br.albatross.agenda.security.daos.spi.RolesDao;
import br.albatross.agenda.security.models.Role;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class RolesDaoImpl extends DaoImpl<Role, Integer> implements RolesDao {

    @PersistenceContext
	private EntityManager entityManager;

}
