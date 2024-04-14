package br.albatross.agenda.dto.impl.andar;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.models.Andar;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class DadosParaListagemDoAndarDto implements DadosParaListagemDoAndar {

    private final Integer id;
    private final String nome;

    public DadosParaListagemDoAndarDto(Andar andar) {
        this.id = andar.getId();
        this.nome = andar.getNome();
    }

}
