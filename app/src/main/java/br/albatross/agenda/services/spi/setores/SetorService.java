package br.albatross.agenda.services.spi.setores;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.exceptions.CadastroException;
import jakarta.validation.Valid;

public interface SetorService {

	DadosParaListagemDeSetor cadastrar(@Valid DadosParaCadastroDeNovoSetor dadosNovos) throws CadastroException;

	DadosParaListagemDeSetor atualizar(@Valid DadosParaAtualizacaoDeSetor dadosAtualizados) throws CadastroException;

	void excluir(Serializable id) throws CadastroException;

	List<DadosParaListagemDeSetor> listar();

	boolean existePorId(Serializable id);

	Optional<DadosParaListagemDeSetor> buscarPorId(Serializable id);

}
