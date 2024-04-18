package br.albatross.agenda.beans.cadastro.unidades;

import static java.lang.String.format;

import java.io.Serializable;

import br.albatross.agenda.dto.impl.unidades.DadosParaAtualizacaoDeUnidadeDto;
import br.albatross.agenda.dto.spi.unidades.DadosParaAtualizacaoDeUnidade;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.unidades.UnidadeService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class AtualizacaoUnidadeAdministrativaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
	private FacesContext context;

	@Inject
	private UnidadeService service;

	@Getter @Setter
	private DadosParaAtualizacaoDeUnidade unidadeAdmin;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	public void carregar(Integer id) {

	    service
	        .buscarPorId(id)
	        .ifPresentOrElse(dto -> unidadeAdmin = new DadosParaAtualizacaoDeUnidadeDto(dto),

	        () -> context
	                .getApplication()
	                .getNavigationHandler()
	                .handleNavigation(context, null, "/administracao/cadastros/unidades/cadastroUnidadeAdmin?faces-redirect=true"));

	}

	@Transactional
	public String atualizar() {
		try {

			context.getExternalContext().getFlash().setKeepMessages(true);
			service.atualizar(unidadeAdmin);
			context.addMessage(null, new FacesMessage("Cadastro da Unidade atualizado para " + unidadeAdmin.getSigla(), unidadeAdmin.getDescricao()));

			if (!continuarNestaTela) {

			    return "/administracao/consultas/unidades/consultaUnidades?faces-redirect=true";

			}

		} catch (CadastroException e) {

			context.addMessage(null, new FacesMessage(e.getMessage()));

		}

		return format("%s?unidadeId=%d&faces-redirect=true", context.getViewRoot().getViewId(), unidadeAdmin.getId());

	}

}
