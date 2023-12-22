package br.albatross.agenda.domain.models.usuario;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "usuario")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class Usuario {

	@Id	@GeneratedValue(strategy = IDENTITY)
	private int id;

	@Column(length = 55, unique = true, nullable = false)
	private String username;

	@Column(length = 100, unique = false, nullable = false)
	private String password;
	
	@ManyToOne @JoinColumn(name = "fk_role_id", nullable = false)
	private Role role;

}
