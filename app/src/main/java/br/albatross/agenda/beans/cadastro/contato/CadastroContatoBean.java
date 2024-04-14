package br.albatross.agenda.beans.cadastro.contato;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

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
	private boolean continuarNestaTela = false;

	@Transactional
	public String cadastrar() {
		try {

			context.getExternalContext().getFlash().setKeepMessages(true);
			var novoContato = cadastroService.cadastrar(contato);
			context.addMessage(null, new FacesMessage("Contato " + novoContato.getNome() + " cadastrado", novoContato.getNome() + " - " + novoContato.getNome()));

			if (!continuarNestaTela) {

			    return "consultaContatos?faces-redirect=true";

			}

		} catch (CadastroException e) {

			context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));

		}

		return context.getViewRoot().getViewId() + "?faces-redirect=true";		

	}

}
