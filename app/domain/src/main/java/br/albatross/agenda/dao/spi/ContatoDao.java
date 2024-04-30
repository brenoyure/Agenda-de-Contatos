package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.models.Contato;

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
