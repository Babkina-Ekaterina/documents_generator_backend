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
        supportingText.append("Результат интеллектуальной деятельности ");
        String programType1 = "";
        String programType2 = "";
        if (userDataDto.isComputerProgramCheckbox()) {
            programType1 = "программа для ЭВМ";
            programType2 = "программ для ЭВМ";
        } else if (userDataDto.isDatabaseCheckbox1()) {
            programType1 = "база данных";
            programType2 = "баз данных (пункт 4 статьи 1259 Кодекса)";
        } else if (userDataDto.isDatabaseCheckbox2()) {
            programType1 = "база данных";
            programType2 = "баз данных (пункт 3 статьи 1334 Кодекса)";
        }
        supportingText.append(programType1);
        supportingText.append(" «");
        supportingText.append(userDataDto.getProgramName());
        supportingText.append("» (авторов: ");
        supportingText.append(userDataDto.getFullAuthors());
        supportingText.append("), создан ");

        String faculty = userDataDto.getFaculty();
        String facultyName1 = "";
        String facultyName2 = "";
        String dean = "";
        switch (faculty) {
            case "Факультет компьютерных наук":
                facultyName1 = "факультетом компьютерных наук";
                facultyName2 = "факультета компьютерных наук";
                dean = "Крыловецкий Александр Абрамович";
                break;
            case "Медико-биологический факультет":
                facultyName1 = "медико-биологическим факультетом";
                facultyName2 = "медико-биологического факультета";
                dean = "Попова Татьяна Николаевна";
                break;
            case "Факультет географии, геоэкологии и туризма":
                facultyName1 = "факультетом географии, геоэкологии и туризма";
                facultyName2 = "факультета географии, геоэкологии и туризма";
                dean = "Куролап Семен Александрович";
                break;
            case "Геологический факультет":
                facultyName1 = "геологическим факультетом";
                facultyName2 = "геологического факультета";
                dean = "Ненахов Виктор Миронович";
                break;
            case "Факультет журналистики":
                facultyName1 = "факультетом журналистики";
                facultyName2 = "факультета журналистики";
                dean = "Тулупов Владимир Васильевич";
                break;
            case "Исторический факультет":
                facultyName1 = "историческим факультетом";
                facultyName2 = "исторического факультета";
                dean = "Глазьев Владимир Николаевич";
                break;
            case "Математический факультет":
                facultyName1 = "математическим факультетом";
                facultyName2 = "математического факультета";
                dean = "Бурлуцкая Мария Шаукатовна";
                break;
            case "Факультет международных отношений":
                facultyName1 = "факультетом международных отношений";
                facultyName2 = "факультета международных отношений";
                dean = "Беленов Олег Николаевич";
                break;
            case "Факультет прикладной математики, информатики и механики":
                facultyName1 = "факультетом прикладной математики, информатики и механики";
                facultyName2 = "факультета прикладной математики, информатики и механики";
                dean = "Медведев Сергей Николаевич";
                break;
            case "Факультет романо-германской филологии":
                facultyName1 = "факультетом романо-германской филологии";
                facultyName2 = "факультета романо-германской филологии";
                dean = "Борискина Ольга Олеговна";
                break;
            case "Химический факультет":
                facultyName1 = "химическим факультетом";
                facultyName2 = "химического факультета";
                dean = "Семенов Виктор Николаевич";
                break;
            case "Фармацевтический факультет":
                facultyName1 = "фармацевтическим факультетом";
                facultyName2 = "фармацевтического факультета";
                dean = "Чупандина Елена Евгеньевна";
                break;
            case "Физический факультет":
                facultyName1 = "физическим факультетом";
                facultyName2 = "физического факультета";
                dean = "Овчинников Олег Владимирович";
                break;
            case "Филологический факультет":
                facultyName1 = "филологическим факультетом";
                facultyName2 = "филологического факультета";
                dean = "Грачева Жанна Владимировна";
                break;
            case "Факультет философии и психологии":
                facultyName1 = "факультетом философии и психологии";
                facultyName2 = "факультета философии и психологии";
                dean = "Бубнов Юрий Александрович";
                break;
            case "Экономический факультет":
                facultyName1 = "экономическим факультетом";
                facultyName2 = "экономического факультета";
                dean = "Канапухин Павел Анатольевич";
                break;
            case "Юридический факультет":
                facultyName1 = "юридическим факультетом";
                facultyName2 = "юридического факультета";
                dean = "Старилов Юрий Николаевич";
                break;
            default:
                break;
        }

        supportingText.append(facultyName1);
        supportingText.append(". ");
        supportingText.append(userDataDto.getReason());
        supportingText.append(" Просим произвести государственную регистрацию в реестре ");
        supportingText.append(programType2);
        supportingText.append(". Оплату государственной пошлины произвести за счет средств ");
        supportingText.append(facultyName2);
        supportingText.append(".");

        List<Author> authors = new ArrayList<>();
        for (String name : userDataDto.getNames()) {
            authors.add(new Author(name));
        }

        return new SupportingRecommendationEntity(faculty, supportingText.toString(), authors, dean, userDataDto.getFormat());
    }
}
