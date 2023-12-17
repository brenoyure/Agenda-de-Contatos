package br.albatross.agenda.domain.models;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unidade_administrativa")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class UnidadeAdministrativa {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Short id;

	@Column(length = 55, unique = true, nullable = false)
	private String sigla;

	@Column(length = 100, unique = true, nullable = true)
	private String descricao;

}
