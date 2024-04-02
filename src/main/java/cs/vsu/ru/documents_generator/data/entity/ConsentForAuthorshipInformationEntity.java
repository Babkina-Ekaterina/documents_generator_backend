package cs.vsu.ru.documents_generator.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ConsentForAuthorshipInformationEntity {
    private boolean computerProgramCheckbox;
    private boolean databaseCheckbox1;
    private boolean databaseCheckbox2;
    private String programName;
    private String name;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String citizenship;
    private String address;
    private String description;
    private boolean nameCheckbox;
    private boolean anonymousCheckbox;
    private boolean pseudoNameCheckbox;
    private int year;
}
