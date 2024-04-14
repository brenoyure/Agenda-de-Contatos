package br.albatross.agenda.dto.impl.contato;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosParaAtualizacaoDeContato extends DadosParaCadastroDeNovoContatoDto {

    @NotNull(message = "{atualizacao.contato.id.obrigatorio}")
    @Positive(message = "{atualizacao.contato.id.positivo.obrigatorio}")
    private Long id;

}
