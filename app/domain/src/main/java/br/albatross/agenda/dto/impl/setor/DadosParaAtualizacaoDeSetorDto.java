package br.albatross.agenda.dto.impl.setor;

import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaAtualizacaoDeSetorDto extends DadosParaCadastroDeNovoSetorDto implements DadosParaAtualizacaoDeSetor {

	@NotNull @Positive
	private Integer id;

	public DadosParaAtualizacaoDeSetorDto(DadosParaListagemDeSetor dto) {
	    super(dto);
	    this.id = dto.getId();
	}

}
