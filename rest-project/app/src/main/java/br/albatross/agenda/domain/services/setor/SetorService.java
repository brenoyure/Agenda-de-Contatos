package br.albatross.agenda.domain.services.setor;

import br.albatross.agenda.domain.dao.SetorDao;
import br.albatross.agenda.domain.models.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.domain.models.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.domain.models.setor.Setor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class SetorService {

	@Inject
	private SetorDao dao;

	public DadosParaListagemDeSetorDto salvar(@Valid DadosParaCadastroDeNovoSetor dados) {
		return dao.persist(new Setor(dados));
	}

	public Setor getReferenceById(int id) {
		return dao.getReferenceById(id);
	}

}
