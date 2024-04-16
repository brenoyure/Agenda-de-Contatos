package br.albatross.agenda.beans.cadastro.unidades;

import java.util.List;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.services.spi.unidades.UnidadeConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @RequestScoped
public class ListaUnidadesBean {

    @Inject
    private UnidadeConsultaService consultaService;

    @Getter
    private List<DadosParaListagemDeUnidade> unidades;

    @PostConstruct
    void init() {

        unidades = consultaService.listar();

    }

}
