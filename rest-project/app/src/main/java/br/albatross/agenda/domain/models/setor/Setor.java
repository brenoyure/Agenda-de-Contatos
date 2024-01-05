package br.albatross.agenda.domain.models.setor;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Cacheable 
@Table(name="setor")
@EqualsAndHashCode(of = "id")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Setor {

	@Id @GeneratedValue(strategy = IDENTITY)
	private int id;

	@Column(name="sigla", length = 50, unique = true, nullable = false)
	private String sigla;

	public Setor(@Valid DadosParaCadastroDeNovoSetor dados) {
		this.sigla = dados.sigla();
	}

}
