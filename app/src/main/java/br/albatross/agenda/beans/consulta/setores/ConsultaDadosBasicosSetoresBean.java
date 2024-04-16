package br.albatross.agenda.beans.consulta.setores;

import java.util.List;

import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;
import br.albatross.agenda.services.spi.setores.SetorConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @Dependent
public class ConsultaDadosBasicosSetoresBean {

    @Inject
    private SetorConsultaService consultaService;

    @Getter
    private List<DadosBasicosDoSetor> setores;

    @PostConstruct
    void init() {

        setores = consultaService.listarDadosBasicos();

    }    

}
