package br.albatross.agenda.infra.security.services;

import br.albatross.agenda.domain.models.usuario.DadosBasicosDoUsuarioParaExibicaoDto;
import br.albatross.agenda.infra.security.credentials.Credenciais;
import br.albatross.agenda.infra.security.dao.LoginDao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class LoginService {

	@Inject
	private LoginDao dao;

	public DadosBasicosDoUsuarioParaExibicaoDto logar(Credenciais credenciais) {
		return dao.logar(credenciais);
	}
	
}