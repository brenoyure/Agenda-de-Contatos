package br.albatross.agenda.beans.cadastro.contato;

import static java.lang.String.format;

import br.albatross.agenda.beans.interceptors.CadastroExceptionHandler;
import br.albatross.agenda.dto.impl.contato.DadosParaCadastroDeNovoContatoDto;
import br.albatross.agenda.dto.spi.contato.DadosParaCadastroDeNovoContato;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.contatos.ContatoCadastroService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class CadastroContatoBean {

	@Inject
	private FacesContext context;

	@Inject
	private ContatoCadastroService cadastroService;

	@Getter @Setter
	private DadosParaCadastroDeNovoContato contato = new DadosParaCadastroDeNovoContatoDto();

	@Getter @Setter
	private boolean continuarNestaTela = true;

	@Transactional
	@CadastroExceptionHandler
	public String cadastrar() throws CadastroException {

        context.getExternalContext().getFlash().setKeepMessages(true);
        var novoContato = cadastroService.cadastrar(contato);

        String detailMessage = format("%s - %s", novoContato.getNome(), novoContato.getNumero());

        if (novoContato.getSetor() != null) {
            detailMessage = detailMessage.concat(format(" -%s", novoContato.getSetor().getSigla()));
        }

        context.addMessage(null, new FacesMessage(format("Contato %s Cadastrado", novoContato.getNome()), detailMessage));			

        if (!continuarNestaTela) {

            return "/consultaContatos?faces-redirect=true";

        }

		return context.getViewRoot().getViewId() + "?faces-redirect=true";		

	}

}
