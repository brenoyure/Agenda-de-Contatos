package br.albatross.agenda.api.resources;

import static jakarta.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION;
import static jakarta.ws.rs.core.Response.status;
import static jakarta.ws.rs.core.Response.Status.CREATED;

import java.io.IOException;

import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.services.spi.contatos.ContatoConsultaService;
import br.albatross.agenda.services.spi.relatorios.RelatorioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/contatos")
public class ContatosResource {

    @Inject
    private ContatoConsultaService consultaService;

    @Inject
    private RelatorioService<DadosParaListagemDeContato> relatorioService;

    @GET
    @Path("/docx")
    @Transactional
    @Produces("application/msword")
    public Response gerarDOCX() throws IOException {
        return status(CREATED)
                .header(CONTENT_DISPOSITION, "attachment; filename=\"agenda.docx\"")
                .entity(relatorioService.gerar(consultaService.listar()))
                .build();
    }

}
