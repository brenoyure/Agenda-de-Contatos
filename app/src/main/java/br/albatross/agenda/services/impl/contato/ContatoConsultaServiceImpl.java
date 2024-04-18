package br.albatross.agenda.services.impl.contato;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.ContatoDao;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.models.Contato;
import br.albatross.agenda.services.spi.contatos.ContatoConsultaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ContatoConsultaServiceImpl implements ContatoConsultaService {

    @Inject
    private ContatoDao dao;

    @Override
    public List<DadosParaListagemDeContato> listar() {

        return dao.findAll();

    }

    @Override
    public boolean existePorId(Long id) {

        return dao.existsById(id);

    }

    @Override
    public boolean existePorNome(String nome) {

        return dao.existsByNome(nome);

    }

    @Override
    public boolean existePorNome(Long id, String nome) {

        return dao.existsByNomeAndNotById(nome, id);

    }

    @Override
    public Optional<DadosParaListagemDeContato> buscarPorId(Long id) {

        return dao.findById(id);

    }

    @Override
    public Optional<Contato> obterReferenciaPorId(Long id) {

        return dao.getReferenceById(id);

    }

}
