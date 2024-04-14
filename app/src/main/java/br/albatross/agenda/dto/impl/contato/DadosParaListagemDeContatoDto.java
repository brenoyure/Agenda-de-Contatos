package br.albatross.agenda.dto.impl.contato;

import br.albatross.agenda.dto.impl.andar.DadosParaListagemDoAndarDto;
import br.albatross.agenda.dto.impl.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.models.Contato;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class DadosParaListagemDeContatoDto implements DadosParaListagemDeContato {

    private final Long id;

    private final String nome;

    private final String numero;

    private final DadosParaListagemDeSetor setor;

    private final DadosParaListagemDoAndar andar;

    public DadosParaListagemDeContatoDto(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.numero = contato.getNumero();
        this.setor = new DadosParaListagemDeSetorDto(contato.getSetor());
        this.andar = new DadosParaListagemDoAndarDto(contato.getAndar());
    }

}
