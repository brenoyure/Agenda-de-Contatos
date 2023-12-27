package br.albatross.agenda.domain.services;

import static org.apache.poi.xwpf.usermodel.TableRowAlign.CENTER;

import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class XwpfTableService {

	private static final String RGB_TABLE_HEADER_COLOR = "66B2FF";

	public XWPFParagraph createCellAlignedParagraph(XWPFDocument doc, XWPFTableCell cell, String text, ParagraphAlignment paragraphAlignment) {
		XWPFParagraph paragraph = cell.addParagraph();
		paragraph.setAlignment(paragraphAlignment);
		paragraph.setVerticalAlignment(TextAlignment.AUTO);
		paragraph.createRun().setText(text);
		return paragraph;
	}

	public void createCellAlignedBoldParagraph(XWPFDocument doc, XWPFTableCell cell, String text, ParagraphAlignment paragraphAlignment) {
		XWPFParagraph paragraph = createCellAlignedParagraph(doc, cell, text, paragraphAlignment);
		XWPFRun run = paragraph.createRun();
		run.setBold(true);
		run.setCapitalized(true);
	}

	public void createXwpfTableHeaders(XWPFDocument doc, XWPFTable table) {
		var header = table.createRow();
		XWPFTableCell headerNomeCell = header.addNewTableCell();
		headerNomeCell.setColor(RGB_TABLE_HEADER_COLOR);
		createCellAlignedBoldParagraph(doc , headerNomeCell, "Nome", ParagraphAlignment.CENTER);

		XWPFTableCell headerNumeroCell = header.addNewTableCell();
		headerNumeroCell.setColor(RGB_TABLE_HEADER_COLOR);
		createCellAlignedBoldParagraph(doc, headerNumeroCell, "Número", ParagraphAlignment.CENTER);

		XWPFTableCell headerSetorCell = header.addNewTableCell();
		headerSetorCell.setColor(RGB_TABLE_HEADER_COLOR);
		createCellAlignedBoldParagraph(doc, headerSetorCell, "Setor", ParagraphAlignment.CENTER);

		XWPFTableCell headerAndarCell = header.addNewTableCell();
		headerAndarCell.setColor(RGB_TABLE_HEADER_COLOR);
		createCellAlignedBoldParagraph(doc, headerAndarCell, "Andar", ParagraphAlignment.CENTER);
	}

	public XWPFTable createPageCenterAlignedXwpfTable(XWPFDocument document) {
		XWPFTable table = document.createTable();
		table.setWidth(8000);
		table.setTableAlignment(CENTER);
		createXwpfTableHeaders(document, table);
		return table;
	}

	public XWPFTable createNewPageAndTableWhenAndarChanges(XWPFDocument doc, List<DadosParaListagemDeContatoDto> contatos, int iterator) {
		doc.createParagraph().setPageBreak(true);
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = paragraph.createRun();
		run.setFontSize(32);
		run.setBold(true);
		run.setCapitalized(true);
		run.setText(contatos.get(iterator).andar());
		doc.createParagraph().setPageBreak(true);
		return createPageCenterAlignedXwpfTable(doc);
	}

}
