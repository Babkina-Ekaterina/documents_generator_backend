package cs.vsu.ru.documents_generator.service;

import cs.vsu.ru.documents_generator.data.entity.ListingEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class ListingService {
    public byte[] generateListing(ListingEntity listingEntity) throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = new
                JRBeanCollectionDataSource(listingEntity.getAuthors(), false);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("listing.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", listingEntity.getType());
        parameters.put("programName", listingEntity.getProgramName());
        parameters.put("year", listingEntity.getYear());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();

        MultipartFile[] programFiles = listingEntity.getProgramFiles();
        if (programFiles.length == 0) {
            return outputStream.toByteArray();
        }

        XWPFDocument existingDocx = new XWPFDocument(new ByteArrayInputStream(outputStream.toByteArray()));

        addFooter(existingDocx);

        for (int fileIndex = 0; fileIndex < programFiles.length; fileIndex++) {
            MultipartFile programFile = programFiles[fileIndex];
            String fileContent = new String(programFile.getBytes(), StandardCharsets.UTF_8);

            if (!fileContent.isEmpty()) {
                XWPFParagraph paragraph = existingDocx.createParagraph();
                paragraph.setSpacingBetween(1.5, LineSpacingRule.AUTO);
                XWPFRun run = paragraph.createRun();
                run.setFontFamily("Courier New");
                run.setFontSize(12);

                String[] lines = fileContent.split("\\r?\\n");
                for (String line : lines) {
                    run.setText(line);
                    run.addCarriageReturn();
                }
                if (fileIndex < programFiles.length - 1) {
                    run.addCarriageReturn();
                }
            }
        }

        ByteArrayOutputStream finalOutputStream = new ByteArrayOutputStream();
        existingDocx.write(finalOutputStream);
        return finalOutputStream.toByteArray();
    }

    private static void addFooter(XWPFDocument doc) {
        XWPFFooter footer = doc.createFooter(HeaderFooterType.DEFAULT);
        XWPFParagraph paragraph = footer.getParagraphArray(0);
        if (paragraph == null) {
            paragraph = footer.createParagraph();
        }
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.getCTP().addNewFldSimple().setInstr("PAGE");
        paragraph.createRun().setFontSize(100);
        footer = doc.createFooter(HeaderFooterType.FIRST);
        paragraph = footer.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        CTSectPr sectPr = doc.getDocument().getBody().getSectPr();
        if (sectPr == null) sectPr = doc.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.getPgMar();
        if (pageMar == null) pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(BigInteger.valueOf(1417));
        pageMar.setRight(BigInteger.valueOf(1133));
        pageMar.setTop(BigInteger.valueOf(1133));
        pageMar.setFooter(BigInteger.valueOf(1100));
        pageMar.setBottom(BigInteger.valueOf(1133));
    }
}
