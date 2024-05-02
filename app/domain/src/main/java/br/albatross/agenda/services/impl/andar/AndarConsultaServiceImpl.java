package br.albatross.agenda.services.impl.andar;

import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.AndarDao;
import br.albatross.agenda.domain.models.Andar;
import br.albatross.agenda.dto.impl.andar.DadosParaListagemDoAndarDto;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.services.spi.andares.AndarConsultaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
@Transactional
public class AndarConsultaServiceImpl implements AndarConsultaService {

    @Inject
    private AndarDao dao; 
    
    @Override
    public List<DadosParaListagemDoAndar> listar() {

        return dao
                .findAll(Andar.class)
                .stream()
                .map(DadosParaListagemDoAndarDto::new)
                .collect(toList());

    }

    @Override
    public Optional<Andar> obterReferenciaPorId(Integer id) {

        return id == null ? empty() : dao.getReferenceById(Andar.class, id);

    }

    @Override
    public Optional<DadosParaListagemDoAndar> buscarPorId(Integer id) {

        return id == null ? empty() : dao.findById(Andar.class, id).map(DadosParaListagemDoAndarDto::new);

    }

    @Override
    public boolean existePorId(Integer id) {

        return id == null ? false : dao.existsById(Andar.class, id);

    }

}
