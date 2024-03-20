package cs.vsu.ru.documents_generator.service;

import cs.vsu.ru.documents_generator.data.entity.ConsentForAuthorshipInformationEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConsentForAuthorshipInformationService {
    public byte[] generateConsentForAuthorshipInformation(ConsentForAuthorshipInformationEntity consentForAuthorshipInformationEntity) throws JRException, FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:сonsentForAuthorshipInformation.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
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
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JRDocxExporter exporter = new JRDocxExporter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream.toByteArray();
    }
}