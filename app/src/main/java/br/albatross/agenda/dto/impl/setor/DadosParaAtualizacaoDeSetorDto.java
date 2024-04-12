package br.albatross.agenda.dto.impl.setor;

import java.io.Serializable;

import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
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
	private Serializable id;
	
}
