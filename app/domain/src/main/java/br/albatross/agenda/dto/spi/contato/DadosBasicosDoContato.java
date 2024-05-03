package br.albatross.agenda.dto.spi.contato;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;

public interface DadosBasicosDoContato {

    Long getId();

    String getNome();
    String getNumero();

    DadosParaListagemDoAndar getAndar();
    DadosBasicosDoSetor getSetor();
    
}
