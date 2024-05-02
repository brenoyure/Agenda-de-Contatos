package br.albatross.agenda.security.models;

import static jakarta.persistence.FetchType.EAGER;
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

@Entity @Table(name = "users")
@EqualsAndHashCode(of = "id")
@Getter @Setter
@Cacheable
@NoArgsConstructor @AllArgsConstructor
public class User {

	@Id	@GeneratedValue(strategy = IDENTITY)
	private int id;

	@Column(length = 55, unique = true, nullable = false)
	private String username;

	@Lob
	@Column(length = 100 ,unique = false, nullable = false)
	private String password;

	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "fk_role_id", nullable = false)
	private Role role;

}
