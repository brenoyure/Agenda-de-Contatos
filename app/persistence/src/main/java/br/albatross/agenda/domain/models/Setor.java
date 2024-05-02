package br.albatross.agenda.domain.models;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name =  "setor")
@Cacheable
@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
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

    public Setor(String sigla, String descricao, UnidadeAdministrativa unidadeAdministrativa) {
        this.sigla = sigla;
        this.descricao = descricao;
        this.unidadeAdministrativa = unidadeAdministrativa;
    }

}
