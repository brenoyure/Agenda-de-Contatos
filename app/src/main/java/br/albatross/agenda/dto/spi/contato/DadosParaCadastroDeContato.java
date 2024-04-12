package br.albatross.agenda.dto.spi.contato;

import java.io.Serializable;

public interface DadosParaCadastroDeContato {

    String getNome();
    String getNumero();

    Serializable getSetorId();
    Serializable getAndarId();

    void setNome(String nome);
    void setNumero(String numero);

    void setSetorId(Serializable id);
    void setAndarId(Serializable id);

}
