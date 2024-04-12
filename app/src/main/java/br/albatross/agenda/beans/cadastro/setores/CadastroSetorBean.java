package br.albatross.agenda.beans.cadastro.setores;

import java.io.Serializable;
import java.util.List;

import br.albatross.agenda.dto.impl.setor.DadosParaCadastroDeNovoSetorDto;
import br.albatross.agenda.dto.spi.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.setores.SetorService;
import br.albatross.agenda.services.spi.unidades.UnidadeConsultaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class CadastroSetorBean implements Serializable {

	private static final long serialVersionUID = 1L;

    @Inject
	private FacesContext context;

    @Inject
    private SetorService setorService;

    @Inject
    private UnidadeConsultaService unidadesService;	

	@Getter
	private List<DadosParaListagemDeUnidade> unidadesAdministrativas;

	@Getter
	private DadosParaCadastroDeNovoSetor setor;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@PostConstruct
	void init() {
		setor = new DadosParaCadastroDeNovoSetorDto();
		unidadesAdministrativas = unidadesService.listar();
	}

	@Transactional
	public String cadastrar() {

		try {
			context.getExternalContext().getFlash().setKeepMessages(true);
			setorService.cadastrar(setor);
			context.addMessage(null, new FacesMessage("Setor: " + setor.getSigla() + " cadastrado."));

			if (continuarNestaTela) {
				return context.getViewRoot().getViewId() + "?faces-redirect=true";
			}

			return "consultaSetores?faces-redirect=true";

		} catch (CadastroException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
			return context.getViewRoot().getViewId() + "?faces-redirect=true";
		}

	}

}
