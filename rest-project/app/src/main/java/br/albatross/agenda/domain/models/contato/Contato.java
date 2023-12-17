package br.albatross.agenda.domain.models.contato;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Cacheable 
@Table(name = "contato")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter @Setter
public class Contato {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Short id;
	
	@Column(length = 50, unique = false, nullable = false)
	private String nome;

	@Column(length = 13, unique = false, nullable = false)
	private String numero;

	@Column(length = 50, unique = false, nullable = false)
	private String setor;	

	@Column(length = 10, unique = false, nullable = false)
	private String andar;

	public Contato(DadosParaCadastroDeNovoContatoDto dados) {
		this.nome=dados.nome();
		this.numero=dados.ramal();
		this.setor=dados.setor();
		this.andar=dados.andar();
	}

}
