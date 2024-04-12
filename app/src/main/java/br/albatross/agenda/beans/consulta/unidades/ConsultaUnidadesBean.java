package br.albatross.agenda.beans.consulta.unidades;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;
import java.util.List;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.models.entities.UnidadeAdministrativa;
import br.albatross.agenda.services.spi.unidades.UnidadeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @ViewScoped
public class ConsultaUnidadesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext context;
	
	@Inject
	private UnidadeService service;

	@Getter
	private List<DadosParaListagemDeUnidade> unidades;

	@PostConstruct
	void init() {
		unidades = service.listar();
	}

    @Transactional
    public String excluir(UnidadeAdministrativa unidade) {
        try {
            service.excluir(unidade.getId());
            context.addMessage(null, new FacesMessage("Unidade: " + unidade.getSigla() + " excluída."));
            context.getExternalContext().getFlash().setKeepMessages(true);
            return context.getViewRoot().getViewId() + "?faces-redirect=true";

        } catch (CadastroException e) {

            context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));
            return null;

        }

    }	

}
