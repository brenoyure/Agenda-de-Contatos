package br.albatross.agenda.dto.impl.contato;

import br.albatross.agenda.domain.models.Contato;
import br.albatross.agenda.dto.impl.andar.DadosParaListagemDoAndarDto;
import br.albatross.agenda.dto.impl.setor.DadosBasicosDoSetorDto;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.dto.spi.contato.DadosBasicosDoContato;
import br.albatross.agenda.dto.spi.setor.DadosBasicosDoSetor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class DadosBasicosDoContatoDto implements DadosBasicosDoContato {

    private final Long id;
    private final String nome;
    private final String numero;
    private DadosBasicosDoSetor setor;
    private DadosParaListagemDoAndar andar;

    public DadosBasicosDoContatoDto(Contato contato) {

        this.id = contato.getId();
        this.nome = contato.getNome();
        this.numero = contato.getNumero();
        this.setor = contato.getSetor() == null ? null : new DadosBasicosDoSetorDto(contato.getSetor());
        this.andar = contato.getAndar() == null ? null : new DadosParaListagemDoAndarDto(contato.getAndar());

    }

}
