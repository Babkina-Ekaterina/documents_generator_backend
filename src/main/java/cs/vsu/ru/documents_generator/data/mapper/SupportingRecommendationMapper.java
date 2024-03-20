package cs.vsu.ru.documents_generator.data.mapper;

import cs.vsu.ru.documents_generator.data.dto.UserDataDto;
import cs.vsu.ru.documents_generator.data.entity.SupportingRecommendationEntity;
import cs.vsu.ru.documents_generator.data.utilModel.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SupportingRecommendationMapper {
    public SupportingRecommendationEntity dtoToEntity(UserDataDto userDataDto) {
        StringBuilder supportingText = new StringBuilder();
        supportingText.append("\tРезультат интеллектуальной деятельности ");
        String programType1 = "";
        String programType2 = "";
        String programType3 = "";
        if (userDataDto.isComputerProgramCheckbox()) {
            programType1 = "Программа для ЭВМ";
            programType2 = "программы для ЭВМ";
            programType3 = "программ для ЭВМ";
        } else if (userDataDto.isDatabaseCheckbox1()) {
            programType1 = "База данных";
            programType2 = "базы данных";
            programType3 = "баз данных (пункт 4 статьи 1259 Кодекса)";
        } else if (userDataDto.isDatabaseCheckbox2()) {
            programType1 = "База данных";
            programType2 = "базы данных";
            programType3 = "баз данных (пункт 3 статьи 1334 Кодекса)";
        }
        supportingText.append(programType1);
        supportingText.append(" «");
        supportingText.append(userDataDto.getProgramName());
        supportingText.append("» разработан на ");


        String faculty = userDataDto.getFaculty();
        String facultyName1 = "";
        String facultyName2 = "";
        String dean = "";
        switch (faculty) {
            case "Факультет компьютерных наук":
                facultyName1 = "факультете компьютерных наук";
                facultyName2 = "факультета компьютерных наук";
                dean = "Крыловецкий А.А.";
                break;
            case "Медико-биологический факультет":
                facultyName1 = "медико-биологическом факультете";
                facultyName2 = "медико-биологического факультета";
                dean = "Попова Т.Н.";
                break;
            case "Факультет географии, геоэкологии и туризма":
                facultyName1 = "факультете географии, геоэкологии и туризма";
                facultyName2 = "факультета географии, геоэкологии и туризма";
                dean = "Куролап С.А.";
                break;
            case "Геологический факультет":
                facultyName1 = "геологическом факультете";
                facultyName2 = "геологического факультета";
                dean = "Ненахов В.М.";
                break;
            case "Факультет журналистики":
                facultyName1 = "факультете журналистики";
                facultyName2 = "факультета журналистики";
                dean = "Тулупов В.В.";
                break;
            case "Исторический факультет":
                facultyName1 = "историческом факультете";
                facultyName2 = "исторического факультета";
                dean = "Глазьев В.Н.";
                break;
            case "Математический факультет":
                facultyName1 = "математическом факультете";
                facultyName2 = "математического факультета";
                dean = "Бурлуцкая М.Ш.";
                break;
            case "Факультет международных отношений":
                facultyName1 = "факультете международных отношений";
                facultyName2 = "факультета международных отношений";
                dean = "Беленов О.Н.";
                break;
            case "Факультет прикладной математики, информатики и механики":
                facultyName1 = "факультете прикладной математики, информатики и механики";
                facultyName2 = "факультета прикладной математики, информатики и механики";
                dean = "Медведев С.Н.";
                break;
            case "Факультет романо-германской филологии":
                facultyName1 = "факультете романо-германской филологии";
                facultyName2 = "факультета романо-германской филологии";
                dean = "Борискина О.О.";
                break;
            case "Химический факультет":
                facultyName1 = "химическом факультете";
                facultyName2 = "химического факультета";
                dean = "Семенов В.Н.";
                break;
            case "Фармацевтический факультет":
                facultyName1 = "фармацевтическом факультете";
                facultyName2 = "фармацевтического факультета";
                dean = "Чупандина Е.Е.";
                break;
            case "Физический факультет":
                facultyName1 = "физическом факультете";
                facultyName2 = "физического факультета";
                dean = "Овчинников О.В.";
                break;
            case "Филологический факультет":
                facultyName1 = "филологическом факультете";
                facultyName2 = "филологического факультета";
                dean = "Грачева Ж.В.";
                break;
            case "Факультет философии и психологии":
                facultyName1 = "факультете философии и психологии";
                facultyName2 = "факультета философии и психологии";
                dean = "Бубнов Ю.А.";
                break;
            case "Экономический факультет":
                facultyName1 = "экономическом факультете";
                facultyName2 = "экономического факультета";
                dean = "Канапухин П.А.";
                break;
            case "Юридический факультет":
                facultyName1 = "юридическом факультете";
                facultyName2 = "юридическорго факультета";
                dean = "Старилов Ю.Н.";
                break;
            default:
                break;
        }

        supportingText.append(facultyName1);
        supportingText.append(" ");
        supportingText.append(userDataDto.getFullAuthors());
        supportingText.append(".\n\tСвидетельство о государственной регистрации ");
        supportingText.append(programType2);
        supportingText.append(" требуется для ");
        supportingText.append(userDataDto.getReason());
        supportingText.append(". \n\tПросим произвести государственную регистрацию в реестре ");
        supportingText.append(programType3);
        supportingText.append(". Оплату государственной пошлины произвести за счет средств ");
        supportingText.append(facultyName2);
        supportingText.append(".");

        List<Author> authors = new ArrayList<>();
        for (String name : userDataDto.getNames()) {
            String[] parts = name.split(" ");
            StringBuilder sb = new StringBuilder();
            sb.append(parts[0]);
            sb.append(" ");
            for (int partIndex = 1; partIndex < parts.length; partIndex++) {
                String part = parts[partIndex];
                if (!part.isEmpty()) {
                    sb.append(part.charAt(0)).append(".");
                }
            }
            authors.add(new Author(sb.toString()));
        }


        return new SupportingRecommendationEntity(faculty, supportingText.toString(), authors, dean);
    }
}
