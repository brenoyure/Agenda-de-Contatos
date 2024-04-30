package br.albatross.agenda.beans.consulta.andares;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;
import java.util.List;

import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.andares.AndarCadastroService;
import br.albatross.agenda.services.spi.andares.AndarConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @ViewScoped
public class ConsultaAndaresBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext context;

    @Inject
    private AndarCadastroService cadastroService;

    @Inject
    private AndarConsultaService consultaService;

    @Getter
    private List<DadosParaListagemDoAndar> andares;

    @PostConstruct
    void init() {

        andares = consultaService.listar();

    }

    @Transactional
    public String excluir(Integer id) {
        try {
            context.getExternalContext().getFlash().setKeepMessages(true);
            cadastroService.excluir(id);
            context.addMessage(null, new FacesMessage("Andar excluído com sucesso"));

        } catch (CadastroException e) {

            context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));

        }

        return context.getViewRoot().getViewId() + "?faces-redirect=true";        

    }

}
