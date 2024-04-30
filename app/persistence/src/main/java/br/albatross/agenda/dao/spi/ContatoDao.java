package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.models.Contato;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;

public interface ContatoDao {

    DadosParaListagemDeContato persist(Contato contato);
    DadosParaListagemDeContato merge(Contato contato);

    boolean existsById(Long id);
    boolean existsByNome(String nome);
    boolean existsByNomeAndNotById(String nome, Long id);

    List<DadosParaListagemDeContato> findAll();

    Optional<DadosParaListagemDeContato> findById(Long id);

    void delete(Long id);

    Optional<Contato> getReferenceById(Long id);

}
