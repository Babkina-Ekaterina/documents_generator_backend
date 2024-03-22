package cs.vsu.ru.documents_generator.service;

import cs.vsu.ru.documents_generator.data.entity.SupportingRecommendationEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class SupportingRecommendationService {
    public byte[] generateSupportingRecommendation(SupportingRecommendationEntity supportingRecommendationEntity)
            throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new
                JRBeanCollectionDataSource(supportingRecommendationEntity.getAuthors(), false);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("documents/supportingRecommendation.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("faculty", supportingRecommendationEntity.getFaculty());
        parameters.put("supportingText", supportingRecommendationEntity.getSupportingText());
        parameters.put("dean", supportingRecommendationEntity.getDean());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        JRDocxExporter exporter = new JRDocxExporter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream.toByteArray();
    }
}
