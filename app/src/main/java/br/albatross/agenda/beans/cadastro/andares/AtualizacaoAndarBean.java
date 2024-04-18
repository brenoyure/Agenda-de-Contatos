package br.albatross.agenda.beans.cadastro.andares;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;
import static java.lang.String.format;

import java.io.Serializable;

import br.albatross.agenda.dto.impl.andar.DadosParaAtualizacaoDoAndarDto;
import br.albatross.agenda.dto.spi.andar.DadosParaAtualizacaoDoAndar;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.andares.AndarCadastroService;
import br.albatross.agenda.services.spi.andares.AndarConsultaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class AtualizacaoAndarBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
	private FacesContext context;

	@Inject
	private AndarCadastroService cadastroService;

    @Inject
    private AndarConsultaService consultaService;	

	@Getter @Setter
	private DadosParaAtualizacaoDoAndar andar;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	public void carregar(Integer id) {

	    consultaService
	        .buscarPorId(id)
	        .ifPresentOrElse(dto -> andar = new DadosParaAtualizacaoDoAndarDto(dto),

	        () -> context
	                .getApplication()
	                .getNavigationHandler()
	                .handleNavigation(context, null, "/administracao/cadastros/andares/cadastroAndar?faces-redirect=true"));

	}

	@Transactional
	public String atualizar() {
		try {

			context.getExternalContext().getFlash().setKeepMessages(true);
			cadastroService.atualizar(andar);
			context.addMessage(null, new FacesMessage("Cadastro do Andar atualizado para " + andar.getNome()));

			if (!continuarNestaTela) {

			    return "/administracao/consultas/andares/consultaAndares?faces-redirect=true";

			}

		} catch (CadastroException e) {

			context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));

		}

		return format("%s?andarId=%d&faces-redirect=true", context.getViewRoot().getViewId(), andar.getId());

	}

}
