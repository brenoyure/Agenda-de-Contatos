package br.albatross.agenda.beans.cadastro.unidades;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;

import br.albatross.agenda.dto.impl.unidades.DadosParaCadastroDeNovaUnidadeDto;
import br.albatross.agenda.dto.spi.unidades.DadosParaCadastroDeNovaUnidade;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.unidades.UnidadeCadastroService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class CadastroUnidadeAdministrativaBean implements Serializable {

	private static final long serialVersionUID = 1L;

    @Inject
	private FacesContext context;

	@Inject
	private UnidadeCadastroService service;

	@Getter
	private DadosParaCadastroDeNovaUnidade unidadeAdmin = new DadosParaCadastroDeNovaUnidadeDto();

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@Transactional
	public String cadastrar() {
		try {
			service.cadastrar(unidadeAdmin);
			context.addMessage(null, new FacesMessage("Unidade " + unidadeAdmin.getSigla() + " cadastrada"));

			if (continuarNestaTela) {
				return context.getViewRoot().getViewId() + "?faces-redirect=true";
			}

			context.getExternalContext().getFlash().setKeepMessages(true);
			return "consultaUnidades?faces-redirect=true";

		} catch (CadastroException e) {
			context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));
			return null;
		}

	}

}
