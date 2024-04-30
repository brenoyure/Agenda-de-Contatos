package br.albatross.agenda.dto.impl.setor;

import br.albatross.agenda.dto.spi.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaCadastroDeNovoSetorDto implements DadosParaCadastroDeNovoSetor {

	@NotBlank(message = "{setor.sigla.obrigatoria}")
	@Size(message = "{setor.sigla.tamanho.permitido}", min = 2, max = 55)
	private String sigla;

	@NotBlank(message = "{setor.descricao.obrigatoria}")
	@Size(message = "{setor.descricao.tamanho.permitido}", min = 2, max = 100)
	private String descricao;

	@NotNull(message = "{setor.unidade.obrigatoria}")
	@Positive(message = "{setor.unidade.id.positivo.obrigatorio}")
	private Integer unidadeId;

	public DadosParaCadastroDeNovoSetorDto(DadosParaListagemDeSetor dto) {
	    this.sigla = dto.getSigla();
	    this.descricao = dto.getDescricao();
	    this.unidadeId = dto.getUnidadeAdministrativa().getId();
	}

}
