package br.albatross.agenda.domain.services.setor;

import java.util.List;

import br.albatross.agenda.domain.dao.SetorDao;
import br.albatross.agenda.domain.models.contato.Pagina;
import br.albatross.agenda.domain.models.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.domain.models.setor.Setor;
import br.albatross.agenda.domain.services.paginacao.ServicoDePaginacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SetorConsultaService {

	@Inject
	private SetorDao dao;

	@Inject
	private ServicoDePaginacao<DadosParaListagemDeSetorDto> servicoDePaginacao;

	public Setor getReferenceById(int id) {
		return dao.getReferenceById(id);
	}

	public Pagina<DadosParaListagemDeSetorDto> listaPaginada(int pagina, byte resultadosPorPagina) {
		return servicoDePaginacao.getListagemPaginada(dao.listar(pagina, resultadosPorPagina), pagina, resultadosPorPagina, dao.getTotal());
	}

	public List<DadosParaListagemDeSetorDto> listarTodos() {
		return dao.findAll();
	}

}
