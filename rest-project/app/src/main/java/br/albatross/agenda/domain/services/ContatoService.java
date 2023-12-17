package br.albatross.agenda.domain.services;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.dao.ContatoDao;
import br.albatross.agenda.domain.models.contato.Contato;
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
		Contato contato = new Contato(dados);
		dao.persist(contato);
		return new DadosParaListagemDeContatoDto(contato.getId(), contato.getNome(), contato.getNumero(), contato.getSetor(), contato.getAndar());
	}

	public List<DadosParaListagemDeContatoDto> listar(int pagina, int resultadosPorPagina) {
		return dao.listar(pagina, resultadosPorPagina);
	}

	public Optional<DadosParaListagemDeContatoDto> buscarPorId(int contatoId) {
		return dao.buscarPorId(contatoId);
	}

	public void excluir(Contato contato) {
		dao.excluir(contato);
	}

}
