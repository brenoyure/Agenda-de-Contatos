package br.albatross.agenda.beans.cadastro.setores;

import br.albatross.agenda.beans.interceptors.CadastroExceptionHandler;
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
	@CadastroExceptionHandler
	public String cadastrar() throws CadastroException {

	    context.getExternalContext().getFlash().setKeepMessages(true);
	    setorService.cadastrar(setor);
		context.addMessage(null, new FacesMessage("Setor " + setor.getSigla() + " cadastrado", setor.getDescricao()));

	    if (!continuarNestaTela) {

	        return "/administracao/consultas/setores/consultaSetores?faces-redirect=true";

	    }

	    return context.getViewRoot().getViewId() + "?faces-redirect=true";		

	}

}
