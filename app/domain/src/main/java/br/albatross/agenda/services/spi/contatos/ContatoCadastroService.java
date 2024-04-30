package br.albatross.agenda.services.spi.contatos;

import br.albatross.agenda.dto.spi.contato.DadosParaAtualizacaoDeContato;
import br.albatross.agenda.dto.spi.contato.DadosParaCadastroDeNovoContato;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.exceptions.CadastroException;
import jakarta.validation.Valid;

public interface ContatoCadastroService {

    DadosParaListagemDeContato cadastrar(@Valid DadosParaCadastroDeNovoContato dadosNovos) throws CadastroException;

    DadosParaListagemDeContato atualizar(@Valid DadosParaAtualizacaoDeContato dadosAtualizados) throws CadastroException;

    void excluir(Long id);

}
