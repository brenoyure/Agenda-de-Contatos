package br.albatross.agenda.security.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @RequestScoped
public class LogoutBean {

    @Inject
    private FacesContext context;

    public String logout() {
        context.getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

}
