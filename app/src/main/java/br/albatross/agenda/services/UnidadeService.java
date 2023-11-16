package br.albatross.agenda.services;

import java.util.List;

import br.albatross.agenda.dao.UnidadeAdministrativaDao;
import br.albatross.agenda.exceptions.UnidadeExistenteException;
import br.albatross.agenda.models.UnidadeAdministrativa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UnidadeService {

	@Inject
	private UnidadeAdministrativaDao dao;

	public void salvar(UnidadeAdministrativa unidadeAdministrativa) throws UnidadeExistenteException {
		if (dao.existePorSigla(unidadeAdministrativa.getSigla())) {
			throw new UnidadeExistenteException(String.format("Unidade com a sigla informada '%s' já existente.", unidadeAdministrativa.getSigla()));
		}

		if (unidadeAdministrativa.getId() == null) {
			dao.persist(unidadeAdministrativa);
		}
		
		else {
			dao.atualizar(unidadeAdministrativa);
		}


	}

	public void atualizar(UnidadeAdministrativa unidadeAdministrativa) {
		dao.atualizar(unidadeAdministrativa);
	}

	public List<UnidadeAdministrativa> listar() {
		return dao.listar();
	}

	public void excluir(UnidadeAdministrativa unidadeAdministrativa) {
		dao.excluir(unidadeAdministrativa);
	}

	public boolean existePorId(Short unidadeId) {
		return dao.existePorId(unidadeId);
	}

	public UnidadeAdministrativa carregar(Short unidadeId) {
		return dao.carregar(unidadeId);
	}

}
