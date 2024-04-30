package br.albatross.agenda.domain.models;

import static jakarta.persistence.GenerationType.IDENTITY;

import br.albatross.agenda.dto.spi.andar.DadosParaAtualizacaoDoAndar;
import br.albatross.agenda.dto.spi.andar.DadosParaCadastroDoAndar;
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

/**
 * Entidade Representando os Andares
 */
@Entity
@Table(name = "andar")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter @Setter
@Cacheable
public class Andar {

	@Id	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(name = "nome", length = 10, unique = true, nullable = false)
	private String nome;

	public Andar(DadosParaCadastroDoAndar dadosNovos) {
	    this.nome = dadosNovos.getNome();
	}

    public Andar(DadosParaAtualizacaoDoAndar dadosAtualizados) {
        this.id = dadosAtualizados.getId();
        this.nome = dadosAtualizados.getNome();
    }

}
