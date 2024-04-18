package br.albatross.agenda.beans.consulta.setores;

import java.io.Serializable;
import java.util.List;

import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;
import br.albatross.agenda.services.spi.setores.SetorConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ConsultaDadosBasicosSetoresBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private SetorConsultaService consultaService;

    @Getter
    private List<DadosBasicosDoSetor> setores;

    @PostConstruct
    void init() {

        setores = consultaService.listarDadosBasicos();

    }    

}
