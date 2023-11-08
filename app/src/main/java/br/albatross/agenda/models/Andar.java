package br.albatross.agenda.models;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidade Representando os Andares
 */
@Entity
@Table(name = "andar")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class Andar {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Short id;

	@Column(name = "nome", unique = true, nullable = false)
	private String nome;

}
