package br.albatross.agenda.services.impl.relatorio;

import static org.apache.poi.xwpf.usermodel.TableRowAlign.CENTER;

import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class XwpfTableService {

    private static final short CONTATOS_TABLE_WIDTH = 8000;
    private static final byte ANDAR_IN_BLANK_PAGE_FONT_SIZE = 32;
    private static final String RGB_TABLE_HEADER_COLOR = "66B2FF";

    public XWPFParagraph createCellAlignedParagraph(XWPFDocument doc, XWPFTableCell cell, String text, ParagraphAlignment paragraphAlignment) {
        XWPFParagraph paragraph = cell.addParagraph();
        paragraph.setAlignment(paragraphAlignment);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
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

    public XWPFTable createNewPageWithANewCenterAlignedXwpfTable(XWPFDocument document) {
        XWPFTable table = document.createTable();
        table.setWidth(CONTATOS_TABLE_WIDTH);
        table.setTableAlignment(CENTER);
        createXwpfTableHeaders(document, table);
        return table;
    }

    public XWPFTable createNewPageAndNewTableWhenAndarChanges(List<DadosParaListagemDeContato> contatos, XWPFDocument doc, XWPFTable table, int iterator) {
        if (iterator != contatos.size()) {
            if (!contatos.get(iterator).getAndar().equals(contatos.get(iterator-1).getAndar())) {
                table = createNewPageAndTableWhenAndarChanges(doc, contatos, iterator);
            }
        }

        return table;
    }

    public void alternateCellColor(int iterator, String color, XWPFTableCell... cells) {
        for (var cell : cells) {
            if (iterator % 2 == 0) {
                cell.setColor(color);
            }
        }
    }   

    public XWPFTable createNewPageAndTableWhenAndarChanges(XWPFDocument doc, List<DadosParaListagemDeContato> contatos, int iterator) {
        doc.createParagraph().setPageBreak(true);
        writeAndarInTheBlankPage(doc, contatos.get(iterator).getAndar().getNome());
        doc.createParagraph().setPageBreak(true);
        return createNewPageWithANewCenterAlignedXwpfTable(doc);
    }

    public void writeAndarInTheBlankPage(XWPFDocument doc, String andar) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(ANDAR_IN_BLANK_PAGE_FONT_SIZE);
        run.setBold(true);
        run.setCapitalized(true);
        run.setText(andar);
//        doc.createParagraph().setPageBreak(true);
    }

}
