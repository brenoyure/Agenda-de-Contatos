package br.albatross.agenda.services.impl.setor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.SetorDao;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.models.entities.Setor;
import br.albatross.agenda.services.spi.setores.SetorConsultaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetorConsultaServiceImpl implements SetorConsultaService {

    @Inject
    private SetorDao dao;
    
    @Override
    public List<DadosParaListagemDeSetor> listar() {

        return dao.findAll();

    }

    @Override
    public boolean existePorId(Serializable id) {

        return dao.existsById(id);

    }

    @Override
    public Optional<DadosParaListagemDeSetor> buscarPorId(Serializable id) {

        return dao.findById(id);

    }

    @Override
    public Optional<Setor> obterReferenciaPorId(Serializable id) {

        return dao.getReferenceById(id);

    }

}
