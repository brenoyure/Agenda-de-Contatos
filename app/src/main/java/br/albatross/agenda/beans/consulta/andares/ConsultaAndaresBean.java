package br.albatross.agenda.beans.consulta.andares;

import java.io.Serializable;
import java.util.List;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.services.spi.andares.AndarConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ConsultaAndaresBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AndarConsultaService consultaService;

    @Getter
    private List<DadosParaListagemDoAndar> andares;

    @PostConstruct
    void init() {

        andares = consultaService.listar();

    }

}
