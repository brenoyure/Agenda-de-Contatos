package br.albatross.agenda.beans.consulta.contatos;

import java.util.List;

import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.services.spi.contatos.ContatoCadastroService;
import br.albatross.agenda.services.spi.contatos.ContatoConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @RequestScoped
public class ConsultaContatosBean {

	@Inject
	private FacesContext context;

	@Inject
	private ContatoConsultaService consultaService;

	@Inject
	private ContatoCadastroService cadastroService;

	@Getter
	private List<DadosParaListagemDeContato> contatos;

	@PostConstruct
	void init() {
		contatos = consultaService.listar();
	}

	@Transactional
	public String excluir(DadosParaListagemDeContato contato) {
		context.getExternalContext().getFlash().setKeepMessages(true);
		cadastroService.excluir(contato.getId());
		context.addMessage(null, new FacesMessage("Contato " + contato.getNome() + " excluído."));
		return context.getViewRoot().getViewId() + "?faces-redirect=true";
	}

}
