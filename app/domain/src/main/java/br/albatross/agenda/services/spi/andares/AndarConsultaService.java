package br.albatross.agenda.services.spi.andares;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.models.Andar;

public interface AndarConsultaService {

    List<DadosParaListagemDoAndar> listar();

    Optional<DadosParaListagemDoAndar> buscarPorId(Integer id);    

    Optional<Andar> obterReferenciaPorId(Integer id);

    boolean existePorId(Integer id);

}