package cs.vsu.ru.documents_generator.service.documents;

import cs.vsu.ru.documents_generator.data.entity.documents.ConsentForAuthorshipInformationEntity;
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
public class ConsentForAuthorshipInformationService {
    public byte[] generateConsentForAuthorshipInformation(ConsentForAuthorshipInformationEntity consentForAuthorshipInformationEntity)
            throws JRException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("documents/сonsentForAuthorshipInformation.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("computerProgramCheckbox", consentForAuthorshipInformationEntity.isComputerProgramCheckbox());
        parameters.put("databaseCheckbox1", consentForAuthorshipInformationEntity.isDatabaseCheckbox1());
        parameters.put("databaseCheckbox2", consentForAuthorshipInformationEntity.isDatabaseCheckbox2());
        parameters.put("programName", consentForAuthorshipInformationEntity.getProgramName());
        parameters.put("name", consentForAuthorshipInformationEntity.getName());
        parameters.put("dayOfBirth", consentForAuthorshipInformationEntity.getDayOfBirth());
        parameters.put("monthOfBirth", consentForAuthorshipInformationEntity.getMonthOfBirth());
        parameters.put("yearOfBirth", consentForAuthorshipInformationEntity.getYearOfBirth());
        parameters.put("citizenship", consentForAuthorshipInformationEntity.getCitizenship());
        parameters.put("address", consentForAuthorshipInformationEntity.getAddress());
        parameters.put("description", consentForAuthorshipInformationEntity.getDescription());
        parameters.put("nameCheckbox", consentForAuthorshipInformationEntity.isNameCheckbox());
        parameters.put("anonymousCheckbox", consentForAuthorshipInformationEntity.isAnonymousCheckbox());
        parameters.put("pseudoNameCheckbox", consentForAuthorshipInformationEntity.isPseudoNameCheckbox());
        parameters.put("year", consentForAuthorshipInformationEntity.getYear());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JRAbstractExporter exporter = null;
        switch (consentForAuthorshipInformationEntity.getFormat()) {
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
