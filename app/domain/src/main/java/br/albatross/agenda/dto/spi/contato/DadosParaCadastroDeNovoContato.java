package br.albatross.agenda.dto.spi.contato;

public interface DadosParaCadastroDeNovoContato {

    String getNome();
    String getNumero();

    Integer getSetorId();
    Integer getAndarId();

    void setNome(String nome);
    void setNumero(String numero);

    void setSetorId(Integer id);
    void setAndarId(Integer id);

}
