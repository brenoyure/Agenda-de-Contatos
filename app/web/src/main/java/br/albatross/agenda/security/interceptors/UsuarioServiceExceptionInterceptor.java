package br.albatross.agenda.security.interceptors;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;

import br.albatross.agenda.security.exceptions.CadastroException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@UsuarioServiceExceptionHandler
public class UsuarioServiceExceptionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@AroundInvoke
	public Object handleException(InvocationContext invocationContext) throws Exception {

		try {

			return invocationContext.proceed();

		} catch (CadastroException e) {

			facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, e.getMensagem(), e.getMensagemDetalhada()));
			return null;

		} catch (Exception e) { throw new RuntimeException(e); }

	}

}
