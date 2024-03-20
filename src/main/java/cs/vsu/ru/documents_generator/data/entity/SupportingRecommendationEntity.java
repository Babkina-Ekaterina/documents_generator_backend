package cs.vsu.ru.documents_generator.data.entity;

import cs.vsu.ru.documents_generator.data.utilModel.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SupportingRecommendationEntity {
    private String faculty;
    private String supportingText;
    private List<Author> authors;
    private String dean;
}
