package br.albatross.agenda.services.spi.unidades;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.models.UnidadeAdministrativa;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;

public interface UnidadeConsultaService {

    List<DadosParaListagemDeUnidade> listar();

    boolean existePorId(Integer id);

    Optional<DadosParaListagemDeUnidade> buscarPorId(Integer id);

    Optional<UnidadeAdministrativa> obterReferenciaPorId(Integer id);

}
