package br.albatross.agenda.domain.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static java.io.File.createTempFile;
import static java.lang.String.format;
import static java.util.UUID.randomUUID;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class GerarAgendaDocx implements GeradorDeArquivo<DadosParaListagemDeContatoDto> {

	private static final String RGB_COLOR_ALTERNATING_CELL = "A0A0A0";

	// private static final String DOC_PARENT_PATH = System.getProperty("user.home");
	private static final String AGENDA_DOCX_FILE_NAME = format("agenda %s ", randomUUID().toString());
	private static final String AGENDA_DOCX_FILE_EXTESION = ".docx";
	// private static final String AGENDA_DOCX_FILE_NAME = "agenda " + UUID.randomUUID().toString() + ".docx";

	@Inject
	private XwpfTableService tableService;

	public File gerar(List<DadosParaListagemDeContatoDto> contatos) throws IOException {
		try (XWPFDocument doc = new XWPFDocument()) {

			File file = createTempFile(AGENDA_DOCX_FILE_NAME, AGENDA_DOCX_FILE_EXTESION);
			int i = 0;

			tableService.writeAndarInTheBlankPage(doc, contatos.get(i).andar());
			doc.createParagraph().setPageBreak(true);

			XWPFTable table = tableService.createNewPageWithANewCenterAlignedXwpfTable(doc);


			for (var contato : contatos) {
				XWPFTableRow  row        =  table.createRow();
				XWPFTableCell cellNome   =  row.createCell();
				XWPFTableCell cellNumero =  row.createCell();
				XWPFTableCell cellSetor  =  row.createCell();
				XWPFTableCell cellAndar  =  row.createCell();

				tableService.alternateCellColor(i, RGB_COLOR_ALTERNATING_CELL, cellNome, cellNumero, cellSetor, cellAndar);

				tableService.createCellAlignedParagraph(doc, cellNome, contato.nome(), ParagraphAlignment.CENTER);
				tableService.createCellAlignedParagraph(doc, cellNumero, (contato.ramal().substring(contato.ramal().length() - 4)), ParagraphAlignment.CENTER);
				tableService.createCellAlignedParagraph(doc, cellSetor, contato.setor(), ParagraphAlignment.CENTER);
				tableService.createCellAlignedParagraph(doc, cellAndar, contato.andar(), ParagraphAlignment.CENTER);

				i++;

				table = tableService.createNewPageAndNewTableWhenAndarChanges(contatos, doc, table, i);

			}

			try(OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
				doc.write(os);
				return file;
			}
		}

	}

}