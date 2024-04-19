package br.albatross.agenda.services.impl.relatorio;

import static java.io.File.createTempFile;
import static java.lang.String.format;
import static java.util.UUID.randomUUID;

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

import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.services.spi.relatorios.RelatorioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class RelatorioToDocxService implements RelatorioService<DadosParaListagemDeContato> {

    private static final String RGB_COLOR_ALTERNATING_CELL = "A0A0A0";

    // private static final String DOC_PARENT_PATH = System.getProperty("user.home");
    private static final String AGENDA_DOCX_FILE_NAME = format("agenda %s ", randomUUID().toString());
    private static final String AGENDA_DOCX_FILE_EXTESION = ".docx";
    // private static final String AGENDA_DOCX_FILE_NAME = "agenda " + UUID.randomUUID().toString() + ".docx";    

    @Inject
    private XwpfTableService tableService;

    @Override
    public File gerar(List<DadosParaListagemDeContato> contatos) {
        try (XWPFDocument doc = new XWPFDocument()) {

            contatos.sort((c1, c2) -> c1.getAndar().getNome().compareToIgnoreCase(c2.getAndar().getNome()));

            File file = createTempFile(AGENDA_DOCX_FILE_NAME, AGENDA_DOCX_FILE_EXTESION);
            int i = 0;

            tableService.writeAndarInTheBlankPage(doc, contatos.get(i).getAndar().getNome());
            doc.createParagraph().setPageBreak(true);

            XWPFTable table = tableService.createNewPageWithANewCenterAlignedXwpfTable(doc);


            for (var contato : contatos) {
                XWPFTableRow  row        =  table.createRow();
                XWPFTableCell cellNome   =  row.createCell();
                XWPFTableCell cellNumero =  row.createCell();
                XWPFTableCell cellSetor  =  row.createCell();
                XWPFTableCell cellAndar  =  row.createCell();

                tableService.alternateCellColor(i, RGB_COLOR_ALTERNATING_CELL, cellNome, cellNumero, cellSetor, cellAndar);

                tableService.createCellAlignedParagraph(doc, cellNome, contato.getNome(), ParagraphAlignment.CENTER);
                tableService.createCellAlignedParagraph(doc, cellNumero, contato.getNumero(), ParagraphAlignment.CENTER);
                tableService.createCellAlignedParagraph(doc, cellSetor, contato.getSetor().getSigla(), ParagraphAlignment.CENTER);
                tableService.createCellAlignedParagraph(doc, cellAndar, contato.getAndar().getNome(), ParagraphAlignment.CENTER);

                i++;

                table = tableService.createNewPageAndNewTableWhenAndarChanges(contatos, doc, table, i);

            }

            try(OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
                doc.write(os);
                return file;
            }

        } catch (IOException e) { throw new RuntimeException(e); }

    }

}
