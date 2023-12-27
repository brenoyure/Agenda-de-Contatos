package br.albatross.agenda.domain.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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

	private static final byte RAMAL_SUBSTRING = 6;
	private static final String RGB_COLOR_ALTERNATING_CELL = "A0A0A0";

	@Inject
	private XwpfTableService tableService;

	public File gerar(List<DadosParaListagemDeContatoDto> contatos) throws IOException {
		try (XWPFDocument doc = new XWPFDocument()) {

			File file = new File(System.getProperty("user.home"), "agenda.docx");

			XWPFTable table = tableService.createPageCenterAlignedXwpfTable(doc);

			int i = 0;

			for (var contato : contatos) {
				XWPFTableRow  row        =  table.createRow();
				XWPFTableCell cellNome   =  row.createCell();
				XWPFTableCell cellNumero =  row.createCell();
				XWPFTableCell cellSetor  =  row.createCell();
				XWPFTableCell cellAndar  =  row.createCell();

				if (i % 2 == 0) {
					cellNome.setColor(RGB_COLOR_ALTERNATING_CELL);
					cellNumero.setColor(RGB_COLOR_ALTERNATING_CELL);
					cellSetor.setColor(RGB_COLOR_ALTERNATING_CELL);
					cellAndar.setColor(RGB_COLOR_ALTERNATING_CELL);
				}

				tableService.createCellAlignedParagraph(doc, cellNome, contato.nome(), ParagraphAlignment.CENTER);
				tableService.createCellAlignedParagraph(doc, cellNumero, (contato.ramal().substring(RAMAL_SUBSTRING)), ParagraphAlignment.CENTER);
				tableService.createCellAlignedParagraph(doc, cellSetor, contato.setor(), ParagraphAlignment.CENTER);
				tableService.createCellAlignedParagraph(doc, cellAndar, contato.andar(), ParagraphAlignment.CENTER);

				i++;

				if (i != contatos.size()) {
					if (!contatos.get(i).andar().equals(contatos.get(i-1).andar())) {
						table = tableService.createNewPageAndTableWhenAndarChanges(doc, contatos, i);
					}

				}

			}

			try(OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
				doc.write(os);
				return file;
			}
		}

	}

}