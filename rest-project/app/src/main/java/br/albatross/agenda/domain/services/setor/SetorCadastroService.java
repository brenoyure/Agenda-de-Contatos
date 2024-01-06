package br.albatross.agenda.domain.services.setor;

import br.albatross.agenda.domain.dao.SetorDao;
import br.albatross.agenda.domain.models.setor.DadosParaAtualizacaoDeSetorDto;
import br.albatross.agenda.domain.models.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.domain.models.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.domain.models.setor.Setor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class SetorCadastroService {

	@Inject
	private SetorDao dao;

	public DadosParaListagemDeSetorDto salvar(@Valid DadosParaCadastroDeNovoSetor dados) {
		return dao.persist(new Setor(dados));
	}

	public DadosParaListagemDeSetorDto atualizar(@Valid DadosParaAtualizacaoDeSetorDto dadosAtualizados) {
		return new DadosParaListagemDeSetorDto(dao.atualizar(new Setor(dadosAtualizados)));
	}

	public void excluir(int id) {
		dao.excluir(id);
	}

}
