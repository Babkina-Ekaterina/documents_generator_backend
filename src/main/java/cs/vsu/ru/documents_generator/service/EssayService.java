package cs.vsu.ru.documents_generator.service;

import cs.vsu.ru.documents_generator.data.entity.EssayEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class EssayService {
    public byte[] generateEssay(EssayEntity essayEntity) throws JRException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("documents/essay.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("programName", essayEntity.getProgramName());
        parameters.put("annotation", essayEntity.getAnnotation());
        parameters.put("implement", essayEntity.getImplement());
        parameters.put("language", essayEntity.getLanguage());
        parameters.put("programSize", essayEntity.getProgramSize());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JRDocxExporter exporter = new JRDocxExporter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream.toByteArray();
    }
}
