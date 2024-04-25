package br.albatross.agenda.security.models;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaAtualizacaoDeUsuarioDto extends DadosParaCadastroDeUsuarioDto {

	@Positive
	private int id;

	public DadosParaAtualizacaoDeUsuarioDto(User userEntity) {
		super(userEntity.getUsername(), null, userEntity.getRole().getId());
		this.id = userEntity.getId();
	}

	public DadosParaAtualizacaoDeUsuarioDto(DadosParaListagemDoUsuarioDto dtoListagem) {
		super(dtoListagem.getUsername(), null, dtoListagem.getRole().getId());
		this.id = dtoListagem.getId();
	}

}
