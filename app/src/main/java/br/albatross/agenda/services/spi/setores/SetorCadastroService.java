package br.albatross.agenda.services.spi.setores;

import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.exceptions.CadastroException;
import jakarta.validation.Valid;

public interface SetorCadastroService {

	DadosParaListagemDeSetor cadastrar(@Valid DadosParaCadastroDeNovoSetor dadosNovos) throws CadastroException ;
	DadosParaListagemDeSetor atualizar(@Valid DadosParaAtualizacaoDeSetor dadosAtualizados) throws CadastroException ;
	void excluir(Integer id) throws CadastroException ;		

}
