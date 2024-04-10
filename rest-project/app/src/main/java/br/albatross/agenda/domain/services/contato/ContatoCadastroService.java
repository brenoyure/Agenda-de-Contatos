package br.albatross.agenda.domain.services.contato;

import br.albatross.agenda.domain.dao.ContatoDao;
import br.albatross.agenda.domain.exceptions.ContatoExistenteException;
import br.albatross.agenda.domain.models.contato.Contato;
import br.albatross.agenda.domain.models.contato.DadosParaAtualizacaoDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaCadastroDeNovoContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import br.albatross.agenda.domain.services.setor.SetorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class ContatoCadastroService {

	@Inject
	private ContatoDao dao;

	@Inject
	private SetorService setorService;

	public DadosParaListagemDeContatoDto salvar(@Valid DadosParaCadastroDeNovoContatoDto dados) {

		if (dao.existePorNome(dados.nome())) {
			throw new ContatoExistenteException();
		}

		Contato contato = new Contato(dados);
		contato.setSetor(setorService.getReferenceById(dados.setorId()));
		return dao.persist(contato);

	}

	public DadosParaListagemDeContatoDto atualizarCadastro(@Valid DadosParaAtualizacaoDeContatoDto dados) {
		return new DadosParaListagemDeContatoDto(dao.atualizar(new Contato(dados)));
	}

	public void excluir(short id) {
		dao.excluir(id);
	}

}
