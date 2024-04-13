package br.albatross.agenda.services.impl.unidades;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.UnidadeAdministrativaDao;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.UnidadeAdministrativa;
import br.albatross.agenda.services.spi.unidades.UnidadeConsultaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UnidadeConsultaServiceImpl implements UnidadeConsultaService {

    @Inject
    private UnidadeAdministrativaDao dao;

    @Override
    public List<DadosParaListagemDeUnidade> listar() {

        return dao.findAll();

    }

    @Override
    public boolean existePorId(Integer id) {

        return dao.existsById(id);

    }

    @Override
    public Optional<DadosParaListagemDeUnidade> buscarPorId(Integer id) {

        return dao.findById(id);

    }

    @Override
    public Optional<UnidadeAdministrativa> obterReferenciaPorId(Integer id) {

        return dao.getReferenceById(id);

    }

}
