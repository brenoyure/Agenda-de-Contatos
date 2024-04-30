package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.models.Andar;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;

public interface AndarDao {

    DadosParaListagemDoAndar persist(Andar andar);
    DadosParaListagemDoAndar merge(Andar andar);

    List<DadosParaListagemDoAndar> findAll();

    Optional<Andar> getReferenceById(Integer id);
    Optional<DadosParaListagemDoAndar> findById(Integer id);

    boolean existsById(Integer id);
    boolean existsByNome(String nome);
    boolean existsByNome(Integer id, String nome);

    void delete(Integer id);

    boolean hasContatos(Integer id);

}
