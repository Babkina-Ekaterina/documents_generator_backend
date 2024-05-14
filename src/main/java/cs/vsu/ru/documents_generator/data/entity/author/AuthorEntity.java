package cs.vsu.ru.documents_generator.data.entity.author;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login", length = 55)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "address")
    private String address;
    @Column(name = "citizenship")
    private String citizenship;
    @Column(name = "series")
    private String series;
    @Column(name = "number")
    private String number;
    @Column(name = "date_of_issue")
    private String dateOfIssue;
    @Column(name = "issued_by")
    private String issuedBy;
    @Column(name = "department_code")
    private String departmentCode;
}
