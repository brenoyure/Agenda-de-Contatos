package br.albatross.agenda.domain.services;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.dao.ContatoDao;
import br.albatross.agenda.domain.models.contato.Contato;
import br.albatross.agenda.domain.models.contato.DadosParaAtualizacaoDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaCadastroDeNovoContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ContatoService {

	@Inject
	private ContatoDao dao;

	@Transactional
	public DadosParaListagemDeContatoDto salvar(@Valid DadosParaCadastroDeNovoContatoDto dados) {
		return dao.persist(new Contato(dados));
	}

	public List<DadosParaListagemDeContatoDto> listar(int pagina, byte resultadosPorPagina) {
		return dao.listar(pagina, resultadosPorPagina);
	}

	public Optional<DadosParaListagemDeContatoDto> buscarPorId(short contatoId) {
		return dao.buscarPorId(contatoId);
	}

	@Transactional
	public DadosParaListagemDeContatoDto atualizarCadastro(@Valid DadosParaAtualizacaoDeContatoDto dados) {
		return new DadosParaListagemDeContatoDto(dao.atualizar(new Contato(dados)));
	}

	@Transactional
	public void excluir(short id) {
		dao.excluir(id);
	}

}
