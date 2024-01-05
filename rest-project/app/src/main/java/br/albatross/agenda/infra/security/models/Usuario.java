package br.albatross.agenda.infra.security.models;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "usuario")
@EqualsAndHashCode(of = "id")
@Getter @Setter
@Cacheable
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

	@Id	@GeneratedValue(strategy = IDENTITY)
	private int id;

	@Column(length = 55, unique = true, nullable = false)
	private String username;

	@Lob
	@Column(length = 100 ,unique = false, nullable = false)
	private String password;
	
	@ManyToOne @JoinColumn(name = "fk_role_id", nullable = false)
	private Role role;

	public Usuario(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

}
