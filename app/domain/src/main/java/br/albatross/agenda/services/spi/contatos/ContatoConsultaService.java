package br.albatross.agenda.services.spi.contatos;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.models.Contato;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;

public interface ContatoConsultaService {

    List<DadosParaListagemDeContato> listar();

    boolean existePorId(Long id);

    boolean existePorNome(String nome);

    boolean existePorNome(Long id, String nome);

    Optional<DadosParaListagemDeContato> buscarPorId(Long id);

    Optional<Contato> obterReferenciaPorId(Long id);

}
