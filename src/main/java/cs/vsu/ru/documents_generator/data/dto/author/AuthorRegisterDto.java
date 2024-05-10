package cs.vsu.ru.documents_generator.data.dto.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRegisterDto {
    private String login;
    private String password;
    private String name;
    private String dateOfBirth;
    private String address;
    private String citizenship;
    private String series;
    private String number;
    private String dateOfIssue;
    private String issuedBy;
}
