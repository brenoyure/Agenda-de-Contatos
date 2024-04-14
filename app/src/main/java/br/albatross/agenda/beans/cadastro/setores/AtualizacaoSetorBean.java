package br.albatross.agenda.beans.cadastro.setores;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;

import br.albatross.agenda.dto.impl.setor.DadosParaAtualizacaoDeSetorDto;
import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.setores.SetorService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class AtualizacaoSetorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
	private FacesContext context;

    @Inject
    private SetorService setorService;

	@Getter
	private DadosParaAtualizacaoDeSetor setor;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@Transactional
	public String atualizar() {

		try {

			context.getExternalContext().getFlash().setKeepMessages(true);
			setorService.atualizar(setor);
			context.addMessage(null, new FacesMessage("Cadastro do Setor atualizado para " + setor.getSigla(), setor.getDescricao()));

			if (!continuarNestaTela) {

	            return "consultaSetores?faces-redirect=true";

			}

		} catch (CadastroException e) {

			context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));

		}

		return context.getViewRoot().getViewId() + "?faces-redirect=true";		

	}

	public void carregar(Integer id) {

	    setorService
            .buscarPorId(id)
            .ifPresentOrElse(dto -> setor = new DadosParaAtualizacaoDeSetorDto(dto),

        () -> context
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(context, null, "cadastroSetor?faces-redirect=true"));
	}

}
