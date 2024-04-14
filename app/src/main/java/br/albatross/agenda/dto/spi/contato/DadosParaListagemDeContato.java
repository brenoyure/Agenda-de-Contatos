package br.albatross.agenda.dto.spi.contato;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;

public interface DadosParaListagemDeContato {

    Long getId();

    String getNome();
    String getNumero();
    
    DadosParaListagemDeSetor getSetor();
    DadosParaListagemDoAndar getAndar();

}
