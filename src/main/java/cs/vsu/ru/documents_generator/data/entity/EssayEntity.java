package cs.vsu.ru.documents_generator.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EssayEntity {
    private String programName;
    private String annotation;
    private String implement;
    private String language;
    private String programSize;
}
