package br.albatross.agenda.services.impl.unidades;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.unidades.DadosParaAtualizacaoDeUnidade;
import br.albatross.agenda.dto.spi.unidades.DadosParaCadastroDeNovaUnidade;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.unidades.UnidadeCadastroService;
import br.albatross.agenda.services.spi.unidades.UnidadeConsultaService;
import br.albatross.agenda.services.spi.unidades.UnidadeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class UnidadeServiceImpl implements UnidadeService {

	@Inject
	private UnidadeCadastroService cadastroService;
	
	@Inject
	private UnidadeConsultaService consultaService;

	@Override
	public DadosParaListagemDeUnidade cadastrar(@Valid DadosParaCadastroDeNovaUnidade dadosNovos) throws CadastroException {
		return cadastroService.cadastrar(dadosNovos);
	}

	@Override
	public DadosParaListagemDeUnidade atualizar(@Valid DadosParaAtualizacaoDeUnidade dadosAtualizados) throws CadastroException {
		return cadastroService.atualizar(dadosAtualizados);
	}

	@Override
	public List<DadosParaListagemDeUnidade> listar() {
		return consultaService.listar();
	}

	@Override
	public boolean existePorId(Integer id) {
		return consultaService.existePorId(id);
	}

	@Override
	public Optional<DadosParaListagemDeUnidade> buscarPorId(Integer id) {
		return consultaService.buscarPorId(id);
	}

	@Override
	public void excluir(Integer id) throws CadastroException {
		cadastroService.excluir(id);
	}

}
