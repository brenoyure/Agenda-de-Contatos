package br.albatross.agenda.interceptors;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import br.albatross.agenda.exceptions.AgendaApplicationException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@AgendaApplicationExceptionHandler
public class AgendaApplicationExceptionInterceptor {

	@Inject
	private FacesContext facesContext;

	@AroundInvoke
	public Object handleException(InvocationContext invocationContext) throws Exception {

		try {

			return invocationContext.proceed();

		} catch (AgendaApplicationException e) {

			facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, e.getMessage(), null));
			return null;

		} catch (Exception e) { throw new RuntimeException(e); }

	}

}
