package br.albatross.agenda.interceptors;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;

import br.albatross.agenda.exceptions.CadastroException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@CadastroExceptionHandler
public class CadastroExceptionInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
	private FacesContext facesContext;

	@AroundInvoke
	public Object handleException(InvocationContext invocationContext) throws Exception {

		try {

			return invocationContext.proceed();

		} catch (CadastroException e) {

			facesContext.addMessage(null, new FacesMessage(SEVERITY_WARN, e.getMessage(), null));
			return null;

		} catch (Exception e) { throw new RuntimeException(e); }

	}

}
