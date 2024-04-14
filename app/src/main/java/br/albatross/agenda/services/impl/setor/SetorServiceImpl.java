package br.albatross.agenda.services.impl.setor;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.setores.SetorCadastroService;
import br.albatross.agenda.services.spi.setores.SetorConsultaService;
import br.albatross.agenda.services.spi.setores.SetorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class SetorServiceImpl implements SetorService {

    @Inject
    private SetorCadastroService cadastroService;

    @Inject
    private SetorConsultaService consultaService;

	@Override
	public DadosParaListagemDeSetor cadastrar(@Valid DadosParaCadastroDeNovoSetor dadosNovos) throws CadastroException {

	    return cadastroService.cadastrar(dadosNovos);

	}

	@Override
	public DadosParaListagemDeSetor atualizar(@Valid DadosParaAtualizacaoDeSetor dadosAtualizados) throws CadastroException {

        return cadastroService.atualizar(dadosAtualizados);

    }

	@Override
	public void excluir(Integer id) throws CadastroException {

	    cadastroService.excluir(id);

	}

	@Override
	public List<DadosParaListagemDeSetor> listar() {

	    return consultaService.listar();

	}

    @Override
    public List<DadosBasicosDoSetor> listarDadosBasicos() {

        return consultaService.listarDadosBasicos();

    }	

	@Override
	public boolean existePorId(Integer id) {

	    return consultaService.existePorId(id);

	}

	@Override
	public Optional<DadosParaListagemDeSetor> buscarPorId(Integer id) {

	    return consultaService.buscarPorId(id);

	}

}
