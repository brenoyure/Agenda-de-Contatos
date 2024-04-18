package br.albatross.agenda.dto.impl.andar;

import br.albatross.agenda.dto.spi.andar.DadosParaCadastroDoAndar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaCadastroDoAndarDto implements DadosParaCadastroDoAndar {

    @NotBlank
    @Size(min = 1, max = 10)
    private String nome;

}
