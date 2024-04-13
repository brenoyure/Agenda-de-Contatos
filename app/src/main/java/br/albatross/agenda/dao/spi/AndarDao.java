package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.models.Andar;

public interface AndarDao {

    List<DadosParaListagemDoAndar> findAll();
    Optional<Andar> getReferenceById(Integer id);

}
