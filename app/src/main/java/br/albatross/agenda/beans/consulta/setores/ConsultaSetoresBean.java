package br.albatross.agenda.beans.consulta.setores;

import java.util.List;

import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.models.entities.Setor;
import br.albatross.agenda.services.spi.setores.SetorService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @RequestScoped
public class ConsultaSetoresBean {

	@Inject
	private FacesContext context;

	@Inject
	private SetorService service;

	@Getter
	private List<DadosParaListagemDeSetor> setores;

	@PostConstruct
	void init() {
		setores = service.listar();
	}

	@Transactional
	public String excluir(Setor setor) {
		try {
		    context.getExternalContext().getFlash().setKeepMessages(true);
            service.excluir(setor.getId());
            context.addMessage(null, new FacesMessage("Setor: " + setor.getSigla() + " excluído."));

        } catch (CadastroException e) {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao excluir", e.getMessage()));

        }

		return context.getViewRoot().getViewId() + "?faces-redirect=true";
	}

}
