package br.albatross.agenda.services.spi.unidades;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.entities.UnidadeAdministrativa;

public interface UnidadeConsultaService {

	List<DadosParaListagemDeUnidade> listar();
	boolean existePorId(Serializable id);
	Optional<DadosParaListagemDeUnidade> buscarPorId(Serializable id);
	Optional<UnidadeAdministrativa> obterReferenciaPorId(Serializable id);

}
