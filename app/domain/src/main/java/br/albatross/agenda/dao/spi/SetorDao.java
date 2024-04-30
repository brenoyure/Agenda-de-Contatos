package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.models.Setor;

public interface SetorDao {

	DadosParaListagemDeSetor persist(Setor setor);
	DadosParaListagemDeSetor merge(Setor setor);

	boolean existsById(Integer id);
	boolean existsBySigla(String sigla);
	boolean existsByDescricao(String descricao);

	boolean existsBySigla(Integer id, String sigla);
	boolean existsByDescricao(Integer id, String descricao);

	List<DadosParaListagemDeSetor> findAll();
	List<DadosBasicosDoSetor> findAllWithBasicData();
	Optional<DadosParaListagemDeSetor> findById(Integer id);
	Optional<Setor> getReferenceById(Integer id);

	void delete(Integer id);
    boolean hasContatos(Integer id);

}
