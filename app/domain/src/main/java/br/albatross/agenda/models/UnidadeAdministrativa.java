package br.albatross.agenda.models;

import static jakarta.persistence.GenerationType.IDENTITY;

import br.albatross.agenda.dto.spi.unidades.DadosParaAtualizacaoDeUnidade;
import br.albatross.agenda.dto.spi.unidades.DadosParaCadastroDeNovaUnidade;
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
@Table(name = "unidade_administrativa")
@EqualsAndHashCode(of = "id")
@Getter @Setter
@NoArgsConstructor
public class UnidadeAdministrativa {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(length = 55, unique = true, nullable = false)
	private String sigla;

	@Column(length = 100, unique = true, nullable = true)
	private String descricao;

	public UnidadeAdministrativa(DadosParaCadastroDeNovaUnidade dados) {
		this.sigla = dados.getSigla();
		this.descricao = dados.getDescricao();
	}

	public UnidadeAdministrativa(DadosParaAtualizacaoDeUnidade dadosAtualizados) {
		this.id = dadosAtualizados.getId();
		this.sigla = dadosAtualizados.getSigla();
		this.descricao = dadosAtualizados.getDescricao();
	}

}
