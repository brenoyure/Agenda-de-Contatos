package br.albatross.agenda.beans.cadastro.setores;

import static java.lang.String.format;

import java.io.Serializable;

import br.albatross.agenda.dto.impl.setor.DadosParaAtualizacaoDeSetorDto;
import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.interceptors.CadastroExceptionHandler;
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

	@Getter @Setter
	private DadosParaAtualizacaoDeSetor setor;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@Transactional
	@CadastroExceptionHandler
	public String atualizar() throws CadastroException {

		context.getExternalContext().getFlash().setKeepMessages(true);
		setorService.atualizar(setor);
		context.addMessage(null, new FacesMessage("Cadastro do Setor atualizado para " + setor.getSigla(), setor.getDescricao()));

		if (!continuarNestaTela) {

            return "/administracao/consultas/setores/consultaSetores?faces-redirect=true";

		}

		return format("%s?setorId=%d&faces-redirect=true", context.getViewRoot().getViewId(), setor.getId());		

	}

	public void carregar(Integer id) {
	    setorService
            .buscarPorId(id)
            .ifPresentOrElse(dto -> setor = new DadosParaAtualizacaoDeSetorDto(dto),

        () -> context
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(context, null, "/administracao/cadastros/setores/cadastroSetor?faces-redirect=true"));
	}

}
