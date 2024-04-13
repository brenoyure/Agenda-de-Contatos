package br.albatross.agenda.models;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaCadastroDeNovoSetor;
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

@Entity @Table(name =  "setor")
@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Setor {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(length = 55, unique = true, nullable = false)
    private String sigla;

    @Column(length = 100, unique = true, nullable = false)
    private String descricao;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_unidade_administrativa_id", nullable = false)
    private UnidadeAdministrativa unidadeAdministrativa;

    public Setor(DadosParaCadastroDeNovoSetor dadosNovos, UnidadeAdministrativa unidadeAdministrativa) {
        this.sigla = dadosNovos.getSigla();
        this.descricao = dadosNovos.getDescricao();
        this.unidadeAdministrativa = unidadeAdministrativa;
    }

    public Setor(DadosParaAtualizacaoDeSetor dadosAtualizados, UnidadeAdministrativa unidadeAdministrativa) {
        this.id = dadosAtualizados.getId();
        this.sigla = dadosAtualizados.getSigla();
        this.descricao = dadosAtualizados.getDescricao();
        this.unidadeAdministrativa = unidadeAdministrativa;
    }

}
