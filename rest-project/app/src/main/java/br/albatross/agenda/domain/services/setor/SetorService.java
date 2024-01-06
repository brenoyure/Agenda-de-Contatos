package br.albatross.agenda.domain.services.setor;

import br.albatross.agenda.domain.models.contato.Pagina;
import br.albatross.agenda.domain.models.setor.DadosParaAtualizacaoDeSetorDto;
import br.albatross.agenda.domain.models.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.domain.models.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.domain.models.setor.Setor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class SetorService {

	@Inject
	private SetorCadastroService cadastroService;
	
	@Inject
	private SetorConsultaService consultaService;

	public DadosParaListagemDeSetorDto salvar(@Valid DadosParaCadastroDeNovoSetor dados) {
		return cadastroService.salvar(dados);
	}

	public Setor getReferenceById(int id) {
		return consultaService.getReferenceById(id);
	}

	public Pagina<DadosParaListagemDeSetorDto> listaPaginada(int pagina, byte resultadosPorPagina) {
		return consultaService.listaPaginada(pagina, resultadosPorPagina);
	}

	public DadosParaListagemDeSetorDto atualizarCadastro(@Valid DadosParaAtualizacaoDeSetorDto dadosAtualizados) {
		return cadastroService.atualizar(dadosAtualizados);
	}

	public void excluir(int id) {
		cadastroService.excluir(id);
		
	}

}
