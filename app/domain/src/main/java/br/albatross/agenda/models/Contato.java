package br.albatross.agenda.models;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import br.albatross.agenda.dto.spi.contato.DadosParaAtualizacaoDeContato;
import br.albatross.agenda.dto.spi.contato.DadosParaCadastroDeNovoContato;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(of = "id")
@Getter @Setter
@Entity @Table(name = "contato")
@Cacheable
@NoArgsConstructor
public class Contato {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Column(length = 55, unique = true,  nullable = false)
	private String nome;

	@Column(length = 55, unique = false, nullable = false)
	private String numero;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "fk_setor_id", nullable = true)
	private Setor setor;	

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "fk_andar_id", nullable = true)
	private Andar andar;

	public Contato(DadosParaCadastroDeNovoContato dadosNovos, Andar andar, Setor setor) {
	    this.nome = dadosNovos.getNome();
	    this.numero = dadosNovos.getNumero();
	    this.setor = setor;
	    this.andar = andar;
	}

	public Contato(DadosParaAtualizacaoDeContato dadosAtualizados, Andar andar, Setor setor) {
	    this.id = dadosAtualizados.getId();
	    this.nome = dadosAtualizados.getNome();
	    this.numero = dadosAtualizados.getNumero();
	    this.setor = setor;
	    this.andar = andar;
	}

    public Contato(DadosParaCadastroDeNovoContato dadosNovos) {
        this.nome = dadosNovos.getNome();
        this.numero = dadosNovos.getNumero();
    }

    public Contato(DadosParaAtualizacaoDeContato dadosAtualizados) {
        this.id = dadosAtualizados.getId();
        this.nome = dadosAtualizados.getNome();
        this.numero = dadosAtualizados.getNumero();
    }	

}
