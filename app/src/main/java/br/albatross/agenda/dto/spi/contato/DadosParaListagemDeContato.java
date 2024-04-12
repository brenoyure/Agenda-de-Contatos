package br.albatross.agenda.dto.spi.contato;

import java.io.Serializable;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;

public interface DadosParaListagemDeContato {

    Serializable getId();

    String getNome();
    String getNumero();
    
    DadosParaListagemDeSetor getSetor();
    DadosParaListagemDoAndar getAndar();

}
