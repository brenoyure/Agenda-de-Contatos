package br.albatross.agenda.beans.cadastro.unidades;

import br.albatross.agenda.dto.impl.unidades.DadosParaAtualizacaoDeUnidadeDto;
import br.albatross.agenda.dto.spi.unidades.DadosParaAtualizacaoDeUnidade;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.unidades.UnidadeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class AtualizacaoUnidadeAdministrativaBean {

	@Inject
	private FacesContext context;

	@Inject
	private UnidadeService service;

	@Getter
	private DadosParaAtualizacaoDeUnidade unidadeAdmin;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@PostConstruct
	void init() {

		service.buscarPorId(1).ifPresentOrElse(dto -> {
			this.unidadeAdmin = new DadosParaAtualizacaoDeUnidadeDto(dto);

		}, () -> {
			context
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(context, null, context.getViewRoot().getViewId() + "?faces-redirect=true");
		});
	}

	public String atualizar() {
		try {
			context.getExternalContext().getFlash().setKeepMessages(true);
			service.atualizar(unidadeAdmin);
			context.addMessage(null, new FacesMessage("Cadastro da Unidade " + unidadeAdmin.getSigla() + " atualizado"));

			if (continuarNestaTela) {
				return context.getViewRoot().getViewId() + "?faces-redirect=true";
			}

			return "consultaUnidades?faces-redirect=true";

		} catch (CadastroException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
			return context.getViewRoot().getViewId() + "?faces-redirect=true";
		}

	}

}
