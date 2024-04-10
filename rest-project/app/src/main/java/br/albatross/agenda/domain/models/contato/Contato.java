package br.albatross.agenda.domain.models.contato;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import br.albatross.agenda.domain.models.setor.Setor;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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
	
	@Column(length = 55, unique = true,  nullable = false)
	private String nome;

	@Column(length = 55, unique = false, nullable = false)
	private String numero;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "fk_setor_id")
	private Setor setor;	

	@Column(length = 10, unique = false, nullable = false)
	private String andar;

	public Contato(@Valid DadosParaCadastroDeNovoContatoDto dados) {
		this.nome = dados.nome();
		this.numero = dados.numero();
		this.andar = dados.andar();
	}	

	public Contato(@Valid DadosParaAtualizacaoDeContatoDto dados) {
		this.id = dados.id();
		this.nome = dados.nome();
		this.numero = dados.ramal();
		this.andar = dados.andar();
	}

}
