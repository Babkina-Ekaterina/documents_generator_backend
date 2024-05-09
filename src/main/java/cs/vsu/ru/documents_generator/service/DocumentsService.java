package cs.vsu.ru.documents_generator.service;

import cs.vsu.ru.documents_generator.data.dto.UserDataDto;
import cs.vsu.ru.documents_generator.data.entity.*;
import cs.vsu.ru.documents_generator.data.mapper.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service
@AllArgsConstructor
public class DocumentsService {
    private EssayMapper essayMapper;
    private ConsentForPersonalDataProcessingMapper consentForPersonalDataProcessingMapper;
    private ConsentForAuthorshipInformationMapper consentForAuthorshipInformationMapper;
    private ListingMapper listingMapper;
    private SupportingRecommendationMapper supportingRecommendationMapper;
    private EssayService essayService;
    private SupportingRecommendationService supportingRecommendationService;
    private ListingService listingService;
    private ConsentForPersonalDataProcessingService consentForPersonalDataProcessingService;
    private ConsentForAuthorshipInformationService consentForAuthorshipInformationService;

    public byte[] generateDocuments(UserDataDto userDataDto, MultipartFile[] programFiles) throws Exception {
        byte[] essay = essayService.generateEssay(essayMapper.dtoToEntity(userDataDto));
        byte[] supportingRecommendation = supportingRecommendationService.generateSupportingRecommendation(
                supportingRecommendationMapper.dtoToEntity(userDataDto));
        byte[] listing = listingService.generateListing(listingMapper.dtoToEntity(userDataDto, programFiles));

        List<ConsentForPersonalDataProcessingEntity> consents = consentForPersonalDataProcessingMapper.dtoToEntity(userDataDto);
        byte[][] consentFiles = new byte[consents.size()][];
        for (int i = 0; i < consents.size(); i++) {
            ConsentForPersonalDataProcessingEntity consent = consents.get(i);
            byte[] consentFile = consentForPersonalDataProcessingService.generateConsentForPersonalDataProcessing(consent);
            consentFiles[i] = consentFile;
        }

        List<ConsentForAuthorshipInformationEntity> consentsForAuthorship = consentForAuthorshipInformationMapper.dtoToEntity(userDataDto);
        byte[][] consentForAuthorshipFiles = new byte[consents.size()][];
        for (int i = 0; i < consents.size(); i++) {
            ConsentForAuthorshipInformationEntity consentForAuthorship = consentsForAuthorship.get(i);
            byte[] consentFile = consentForAuthorshipInformationService.generateConsentForAuthorshipInformation(consentForAuthorship);
            consentForAuthorshipFiles[i] = consentFile;
        }
        return createZipArchive(essay, supportingRecommendation, listing, consentFiles, consentForAuthorshipFiles, consents, userDataDto.getFormat());
    }

    private byte[] createZipArchive(byte[] essay, byte[] supportingRecommendation, byte[] listing,
                                    byte[][] consentsFiles, byte[][] consentsForAuthorshipFiles,
                                    List<ConsentForPersonalDataProcessingEntity> consents, String format)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String extension = "";
        switch (format) {
            case "DOCX (Microsoft Word)":
                extension = ".docx";
                break;
            case "ODT (OpenOffice Writer)":
                extension = ".odt";
                break;
        }
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            addToZipStreamFromResource(zos, "Памятка_авторам" + extension, "documents/Памятка авторам" + extension);
            addToZipStream(zos, "Реферат" + extension, essay);
            addToZipStream(zos, "Обоснование_рекомендации" + extension, supportingRecommendation);
            addToZipStream(zos, "Листинг.docx", listing);

            for (int i = 0; i < consentsFiles.length; i++) {
                addToZipStream(zos, "Согласие 1. " + consents.get(i).getName() + extension, consentsFiles[i]);
                addToZipStream(zos, "Согласие 2. " + consents.get(i).getName() + extension, consentsForAuthorshipFiles[i]);
            }
        }
        return baos.toByteArray();
    }

    private void addToZipStream(ZipOutputStream zos, String fileName, byte[] content) throws IOException {
        ZipEntry entry = new ZipEntry(fileName);
        zos.putNextEntry(entry);
        zos.write(content);
        zos.closeEntry();
    }

    private void addToZipStreamFromResource(ZipOutputStream zos, String fileName, String resourcePath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream != null) {
            ZipEntry entry = new ZipEntry(fileName);
            zos.putNextEntry(entry);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }
            zos.closeEntry();
        } else {
            throw new FileNotFoundException("Resource not found: " + resourcePath);
        }
    }
}
