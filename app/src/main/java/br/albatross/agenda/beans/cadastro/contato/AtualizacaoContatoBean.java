package br.albatross.agenda.beans.cadastro.contato;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;
import static java.lang.String.format;

import java.io.Serializable;

import br.albatross.agenda.dto.impl.contato.DadosParaAtualizacaoDeContatoDto;
import br.albatross.agenda.dto.spi.contato.DadosParaAtualizacaoDeContato;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.contatos.ContatoCadastroService;
import br.albatross.agenda.services.spi.contatos.ContatoConsultaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class AtualizacaoContatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

    @Inject
	private FacesContext context;

	@Inject
	private ContatoCadastroService cadastroService;
	
	@Inject
	private ContatoConsultaService consultaService;

	@Getter @Setter
	private DadosParaAtualizacaoDeContato contato;

	@Getter @Setter
	private boolean continuarNestaTela = true;

	@Transactional
	public String atualizar() {
		try {

			context.getExternalContext().getFlash().setKeepMessages(true);
			var novoContato = cadastroService.atualizar(contato);

            String detailMessage = format("%s - %s", novoContato.getNome(), novoContato.getNumero());

            if (novoContato.getSetor() != null) {
                detailMessage.concat(format(" -%s", novoContato.getSetor().getSigla()));
            }

            context.addMessage(null, new FacesMessage(format("Dados do Contato %s Atualizado", novoContato.getNome()), detailMessage));			

			if (!continuarNestaTela) {

			    return "consultaContatos?faces-redirect=true";

			}

		} catch (CadastroException e) {

			context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));

		}

		return format("%s?id=%d&faces-redirect=true", context.getViewRoot().getViewId(), contato.getId());

	}

	@Transactional
    public void carregar(Long id) {
        consultaService
            .buscarPorId(id)
            .ifPresentOrElse(dto -> contato = new DadosParaAtualizacaoDeContatoDto(dto),

        () -> context
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(context, null, "cadastroContato?faces-redirect=true"));
    }	

}
