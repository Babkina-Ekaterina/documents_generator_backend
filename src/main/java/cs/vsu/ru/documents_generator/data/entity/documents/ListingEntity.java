package cs.vsu.ru.documents_generator.data.entity.documents;

import cs.vsu.ru.documents_generator.data.utilModel.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ListingEntity {
    private String type;
    private String programName;
    private List<Author> authors;
    private int year;
    private MultipartFile[] programFiles;
    private String format;
}
