package br.albatross.agenda.services.spi.setores;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.models.Setor;

public interface SetorConsultaService {

    List<DadosParaListagemDeSetor> listar();
    List<DadosBasicosDoSetor> listarDadosBasicos();

    boolean existePorId(Integer id);

    Optional<DadosParaListagemDeSetor> buscarPorId(Integer id);

    Optional<Setor> obterReferenciaPorId(Integer id);
}
