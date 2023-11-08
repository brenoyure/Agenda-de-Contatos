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

@Entity
@Table(name =  "setor")
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Setor {

    @Id @GeneratedValue(strategy=IDENTITY)
    private Short id;

    @Column(length = 55, unique = true, nullable = false)
    private String sigla;

    @Column(length = 100, unique = false, nullable = true)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_unidade_administrativa_id")
    private UnidadeAdministrativa unidadeAdministrativa;

}
