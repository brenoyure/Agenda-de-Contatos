package br.albatross.agenda.dto.spi.contato;

import java.io.Serializable;

public interface DadosParaAtualizacaoDeContato extends DadosParaCadastroDeContato {

    Serializable getId();
    void setId();
}
