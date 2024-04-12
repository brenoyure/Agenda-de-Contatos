package br.albatross.agenda.services.spi.unidades;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.unidades.DadosParaAtualizacaoDeUnidade;
import br.albatross.agenda.dto.spi.unidades.DadosParaCadastroDeNovaUnidade;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.exceptions.CadastroException;
import jakarta.validation.Valid;

public interface UnidadeService {

	DadosParaListagemDeUnidade cadastrar(@Valid DadosParaCadastroDeNovaUnidade dadosNovos) throws CadastroException ;
	DadosParaListagemDeUnidade atualizar(@Valid DadosParaAtualizacaoDeUnidade dadosAtualizados) throws CadastroException ;
	List<DadosParaListagemDeUnidade> listar();
	boolean existePorId(Serializable id);
	Optional<DadosParaListagemDeUnidade> buscarPorId(Serializable id);	
	void excluir(Serializable id) throws CadastroException;

}
