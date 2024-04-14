package br.albatross.agenda.beans.consulta.andares;

import java.util.List;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.services.spi.andares.AndarConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @RequestScoped
public class ConsultaAndaresBean {

    @Inject
    private AndarConsultaService consultaService;

    @Getter
    private List<DadosParaListagemDoAndar> andares;

    @PostConstruct
    void init() {

        andares = consultaService.listar();

    }

}
