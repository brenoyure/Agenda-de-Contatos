package br.albatross.agenda.dto.impl.contato;

import br.albatross.agenda.dto.spi.contato.DadosParaAtualizacaoDeContato;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosParaAtualizacaoDeContatoDto extends DadosParaCadastroDeNovoContatoDto implements DadosParaAtualizacaoDeContato {

    @NotNull(message = "{atualizacao.contato.id.obrigatorio}")
    @Positive(message = "{atualizacao.contato.id.positivo.obrigatorio}")
    private Long id;

    public DadosParaAtualizacaoDeContatoDto(DadosParaListagemDeContato dto) {
        super(dto);
        this.id = dto.getId();
    }

}
