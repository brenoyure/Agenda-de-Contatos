package br.albatross.agenda.models;

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

@EqualsAndHashCode(of = "id")
@Getter @Setter
@Entity @Table(name = "contato")
public class Contato {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Short id;
	
	@Column(length = 55, unique = true,  nullable = false)
	private String nome;

	@Column(length = 55, unique = false, nullable = false)
	private String numero;

	@ManyToOne
	@JoinColumn(name = "fk_setor_id")
	private Setor setor;	

	@ManyToOne
	@JoinColumn(name = "fk_andar_id")
	private Andar andar;

}
