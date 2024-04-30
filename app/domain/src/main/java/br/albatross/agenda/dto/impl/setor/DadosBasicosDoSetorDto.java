package br.albatross.agenda.dto.impl.setor;

import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;
import br.albatross.agenda.models.Setor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class DadosBasicosDoSetorDto implements DadosBasicosDoSetor {

    private final Integer id;
    private final String sigla;
    private final String descricao;

    public DadosBasicosDoSetorDto(Setor setor) {
        this.id = setor.getId();
        this.sigla = setor.getSigla();
        this.descricao = setor.getDescricao();
    }

}
