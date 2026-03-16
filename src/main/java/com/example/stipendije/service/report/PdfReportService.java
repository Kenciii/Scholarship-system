package com.example.stipendije.service.report;

import com.example.stipendije.dto.ApplicationRankingDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfReportService {

    public byte[] generateRankingPdf(String academicYear, List<ApplicationRankingDTO> ranking) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("Scholarship Ranking List - " + academicYear, titleFont);

        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        Paragraph datePara = new Paragraph("Generated on: " + java.time.LocalDate.now());
        datePara.setAlignment(Element.ALIGN_CENTER);
        datePara.setSpacingAfter(10);
        document.add(datePara);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{1, 3, 3, 2, 2});

        addTableHeader(table);
        addTableData(table, ranking);

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        table.addCell(new Phrase("Rank", headerFont));
        table.addCell(new Phrase("First Name", headerFont));
        table.addCell(new Phrase("Last Name", headerFont));
        table.addCell(new Phrase("Points", headerFont));
        table.addCell(new Phrase("Status", headerFont));
    }

    private void addTableData(PdfPTable table,
                              List<ApplicationRankingDTO> ranking) {

        for (ApplicationRankingDTO dto : ranking) {
            table.addCell(String.valueOf(dto.getRank()));
            table.addCell(dto.getFirstName());
            table.addCell(dto.getLastName());
            table.addCell(String.valueOf(dto.getTotalPoints()));
            table.addCell(dto.getStatus().name());
        }
    }
}
