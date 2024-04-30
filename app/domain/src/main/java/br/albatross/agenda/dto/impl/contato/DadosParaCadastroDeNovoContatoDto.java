package br.albatross.agenda.dto.impl.contato;

import br.albatross.agenda.dto.spi.contato.DadosParaCadastroDeNovoContato;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosParaCadastroDeNovoContatoDto implements DadosParaCadastroDeNovoContato {

    @NotBlank(message = "{contato.nome.obrigatorio}")
    @Size(message = "{contato.nome.tamanho.permitido}", min = 2, max = 55)
    private String nome;

    @NotBlank(message = "{contato.numero.obrigatorio}")
    @Size(message = "{contato.numero.tamanho.permitido}", min = 4, max = 55)
    private String numero;

    @Positive(message = "{contato.setor.id.positivo.obrigatorio}")
    private Integer setorId;

    @Positive(message = "{contato.andar.id.positivo.obrigatorio}")
    private Integer andarId;

    public DadosParaCadastroDeNovoContatoDto(DadosParaListagemDeContato dto) {
        this.nome = dto.getNome();
        this.numero = dto.getNumero();
        this.setorId = dto.getSetor() == null ? null : dto.getSetor().getId();
        this.andarId = dto.getAndar() == null ? null : dto.getAndar().getId();
    }

}
