package br.albatross.agenda.services.impl.andar;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.AndarDao;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.models.entities.Andar;
import br.albatross.agenda.services.spi.andares.AndarConsultaService;
import jakarta.inject.Inject;

public class AndarConsultaServiceImpl implements AndarConsultaService {

    @Inject
    private AndarDao dao; 
    
    @Override
    public List<DadosParaListagemDoAndar> listar() {

        return dao.findAll();

    }

    @Override
    public Optional<Andar> obterReferenciaPorId(Serializable id) {

        return dao.getReferenceById(id);

    }

}
