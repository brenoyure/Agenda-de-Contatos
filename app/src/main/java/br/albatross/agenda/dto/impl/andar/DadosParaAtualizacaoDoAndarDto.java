package br.albatross.agenda.dto.impl.andar;

import br.albatross.agenda.dto.spi.andar.DadosParaAtualizacaoDoAndar;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaAtualizacaoDoAndarDto extends DadosParaCadastroDoAndarDto implements DadosParaAtualizacaoDoAndar {

    @NotNull @Positive
    private Integer id;

    public DadosParaAtualizacaoDoAndarDto(DadosParaListagemDoAndar dto) {
        super(dto.getNome());
        this.id = dto.getId();
    }

}
