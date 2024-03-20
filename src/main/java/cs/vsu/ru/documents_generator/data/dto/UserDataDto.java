package cs.vsu.ru.documents_generator.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDataDto {
    private String programName;
    private String annotation;
    private String implement;
    private String language;
    private String programSize;

    private List<String> names;
    private List<String> addresses;
    private List<String> series;
    private List<String> numbers;
    private List<String> datesOfIssue;
    private List<String> issuedBys;

    private boolean computerProgramCheckbox;
    private boolean databaseCheckbox1;
    private boolean databaseCheckbox2;
    private List<String> datesOfBirth;
    private List<String> citizenships;
    private List<String> descriptions;
    private List<Boolean> nameCheckboxes;
    private List<Boolean> anonymousCheckboxes;
    private List<Boolean> pseudoNameCheckboxes;

    private String faculty;
    private String fullAuthors;
    private String reason;
}