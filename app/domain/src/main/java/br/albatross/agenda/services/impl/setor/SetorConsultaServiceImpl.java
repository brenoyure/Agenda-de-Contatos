package br.albatross.agenda.services.impl.setor;

import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.SetorDao;
import br.albatross.agenda.domain.models.Setor;
import br.albatross.agenda.dto.impl.setor.DadosBasicosDoSetorDto;
import br.albatross.agenda.dto.impl.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.services.spi.setores.SetorConsultaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
@Transactional
public class SetorConsultaServiceImpl implements SetorConsultaService {

    @Inject
    private SetorDao dao;
    
    @Override
    public List<DadosParaListagemDeSetor> listar() {

        return dao
                .findAllInnerJoinFetchUnidadeAdministrativa()
                .stream()
                .map(DadosParaListagemDeSetorDto::new)
                .collect(toList());

    }

    @Override
    public boolean existePorId(Integer id) {

        return dao.existsById(Setor.class, id);

    }

    @Override
    public Optional<DadosParaListagemDeSetor> buscarPorId(Integer id) {

        return dao
                .findByIdInnerJoinFetchUnidadeAdministrativa(id)
                .map(DadosParaListagemDeSetorDto::new);

    }

    @Override
    public Optional<Setor> obterReferenciaPorId(Integer id) {

        return id == null ? empty() : dao.getReferenceById(Setor.class, id);

    }

    @Override
    public List<DadosBasicosDoSetor> listarDadosBasicos() {

        return dao
                .findAll(Setor.class)
                .stream()
                .map(DadosBasicosDoSetorDto::new)
                .collect(toList());

    }

}
