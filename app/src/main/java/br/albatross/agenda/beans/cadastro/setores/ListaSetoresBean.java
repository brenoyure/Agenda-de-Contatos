package br.albatross.agenda.beans.cadastro.setores;

import java.util.List;

import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.services.spi.setores.SetorConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @Dependent
public class ListaSetoresBean {

    @Inject
    private SetorConsultaService consultaService;

    @Getter
    private List<DadosParaListagemDeSetor> setores;

    @PostConstruct
    void init() {

        setores = consultaService.listar();

    }

}
