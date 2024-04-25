package br.albatross.agenda.beans.cadastro.setores;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import br.albatross.agenda.dto.impl.setor.DadosParaCadastroDeNovoSetorDto;
import br.albatross.agenda.dto.spi.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.setores.SetorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class CadastroSetorBean {

    @Inject
	private FacesContext context;

    @Inject
    private SetorService setorService;

	@Getter
	private DadosParaCadastroDeNovoSetor setor = new DadosParaCadastroDeNovoSetorDto();

	@Getter @Setter
	private boolean continuarNestaTela = true;

	@Transactional
	public String cadastrar() {

		try {
			context.getExternalContext().getFlash().setKeepMessages(true);
			setorService.cadastrar(setor);
			context.addMessage(null, new FacesMessage("Setor " + setor.getSigla() + " cadastrado", setor.getDescricao()));

			if (!continuarNestaTela) {

	            return "/administracao/consultas/setores/consultaSetores?faces-redirect=true";

			}

		} catch (CadastroException e) {

			context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));

		}

		return context.getViewRoot().getViewId() + "?faces-redirect=true";		

	}

}
