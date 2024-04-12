package br.albatross.agenda.services.spi.setores;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.models.entities.Setor;

public interface SetorConsultaService {

	List<DadosParaListagemDeSetor> listar();
	boolean existePorId(Serializable id);
	Optional<DadosParaListagemDeSetor> buscarPorId(Serializable id);	
	Optional<Setor> obterReferenciaPorId(Serializable id);
}
