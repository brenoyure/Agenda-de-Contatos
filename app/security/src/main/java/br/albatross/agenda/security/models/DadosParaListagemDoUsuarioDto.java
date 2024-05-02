package br.albatross.agenda.security.models;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
public final class DadosParaListagemDoUsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final String username;
	private final DadosParaListagemDaRoleDto role;

	public DadosParaListagemDoUsuarioDto(User userEntity) {

		this.id = userEntity.getId();
		this.username = userEntity.getUsername();
		this.role = new DadosParaListagemDaRoleDto(userEntity.getRole());

	}

}
