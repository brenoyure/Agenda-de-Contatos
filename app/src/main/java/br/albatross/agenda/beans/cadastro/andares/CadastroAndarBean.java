package br.albatross.agenda.beans.cadastro.andares;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import br.albatross.agenda.dto.impl.andar.DadosParaCadastroDoAndarDto;
import br.albatross.agenda.dto.spi.andar.DadosParaCadastroDoAndar;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.andares.AndarCadastroService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class CadastroAndarBean {

    @Inject
    private FacesContext context;

    @Inject
    private AndarCadastroService service;

    @Getter @Setter
    private boolean continuarNestaTela = true;

    @Getter @Setter
    private DadosParaCadastroDoAndar andar = new DadosParaCadastroDoAndarDto();

    @Transactional
    public String cadastrar() {
        try {
            context.getExternalContext().getFlash().setKeepMessages(true);
            service.cadastrar(andar);
            context.addMessage(null, new FacesMessage(andar.getNome() + " cadastrado"));

            if (!continuarNestaTela) {

                return "/administracao/consultas/andares/consultaAndares?faces-redirect=true";

            }

        } catch (CadastroException e) {

            context.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));

        }

        return context.getViewRoot().getViewId() + "?faces-redirect=true";      

    }

}
