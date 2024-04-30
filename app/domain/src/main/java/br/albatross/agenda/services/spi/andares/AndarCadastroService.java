package br.albatross.agenda.services.spi.andares;

import br.albatross.agenda.dto.spi.andar.DadosParaAtualizacaoDoAndar;
import br.albatross.agenda.dto.spi.andar.DadosParaCadastroDoAndar;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.exceptions.CadastroException;
import jakarta.validation.Valid;

public interface AndarCadastroService {

    DadosParaListagemDoAndar cadastrar(@Valid DadosParaCadastroDoAndar dadosNovos) throws CadastroException;
    DadosParaListagemDoAndar atualizar(@Valid DadosParaAtualizacaoDoAndar dadosAtualizados) throws CadastroException;

    void excluir(Integer id) throws CadastroException;

}
