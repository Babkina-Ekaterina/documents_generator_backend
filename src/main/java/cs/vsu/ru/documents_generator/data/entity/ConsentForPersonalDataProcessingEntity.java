package cs.vsu.ru.documents_generator.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ConsentForPersonalDataProcessingEntity {
    private String programName;
    private String name;
    private String address;
    private String series;
    private String number;
    private String dateOfIssue;
    private String issuedBy;
    private String format;
}
