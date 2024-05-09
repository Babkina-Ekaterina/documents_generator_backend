package cs.vsu.ru.documents_generator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs.vsu.ru.documents_generator.data.dto.UserDataDto;
import cs.vsu.ru.documents_generator.service.DocumentsService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
@AllArgsConstructor
@CrossOrigin(origins = {"https://documents-generator-frontend.onrender.com"}, allowCredentials = "true")
public class DocumentsController {
    private DocumentsService documentsService;

    @PostMapping("/generate")
    public ResponseEntity<Resource> generateDocuments(@RequestParam("userDataDto") String userDataDtoJson,
                                                      @RequestParam("programFiles") MultipartFile[] programFiles) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDataDto userDataDto = objectMapper.readValue(userDataDtoJson, UserDataDto.class);
        byte[] reportContent = documentsService.generateDocuments(userDataDto, programFiles);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("Documents.zip")
                                .build().toString())
                .body(resource);
    }
}