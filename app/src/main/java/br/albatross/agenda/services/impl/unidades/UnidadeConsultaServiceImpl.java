package br.albatross.agenda.services.impl.unidades;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.UnidadeAdministrativaDao;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.entities.UnidadeAdministrativa;
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
    public boolean existePorId(Serializable id) {

        return dao.existsById(id);

    }

    @Override
    public Optional<DadosParaListagemDeUnidade> buscarPorId(Serializable id) {

        return dao.findById(id);

    }

    @Override
    public Optional<UnidadeAdministrativa> obterReferenciaPorId(Serializable id) {

        return dao.getReferenceById(id);

    }

}
