package cs.vsu.ru.documents_generator.service;

import cs.vsu.ru.documents_generator.data.entity.ConsentForPersonalDataProcessingEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConsentForPersonalDataProcessingService {
    public byte[] generateConsentForPersonalDataProcessing(ConsentForPersonalDataProcessingEntity consentForPersonalDataProcessingEntity)
            throws JRException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("documents/сonsentForPersonalDataProcessing.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("programName", consentForPersonalDataProcessingEntity.getProgramName());
        parameters.put("name", consentForPersonalDataProcessingEntity.getName());
        parameters.put("address", consentForPersonalDataProcessingEntity.getAddress());
        parameters.put("series", consentForPersonalDataProcessingEntity.getSeries());
        parameters.put("number", consentForPersonalDataProcessingEntity.getNumber());
        parameters.put("dateOfIssue", consentForPersonalDataProcessingEntity.getDateOfIssue());
        parameters.put("issuedBy", consentForPersonalDataProcessingEntity.getIssuedBy());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JRAbstractExporter exporter = null;
        switch (consentForPersonalDataProcessingEntity.getFormat()) {
            case "DOCX (Microsoft Word)":
                exporter = new JRDocxExporter();
                break;
            case "ODT (OpenOffice Writer)":
                exporter = new JROdtExporter();
                break;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream.toByteArray();
    }
}
