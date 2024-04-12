package br.albatross.agenda.dao.spi;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.models.entities.Setor;

public interface SetorDao {

	DadosParaListagemDeSetor persist(Setor setor);
	DadosParaListagemDeSetor merge(Setor setor);

	boolean existsById(Serializable id);
	boolean existsBySigla(String sigla);
	boolean existsByDescricao(String descricao);

	boolean existsBySigla(Serializable id, String sigla);
	boolean existsByDescricao(Serializable id, String descricao);

	List<DadosParaListagemDeSetor> findAll();
	Optional<DadosParaListagemDeSetor> findById(Serializable id);
	Optional<Setor> getReferenceById(Serializable id);

	void delete(Serializable id);
    boolean hasContatos(Serializable id);

}
