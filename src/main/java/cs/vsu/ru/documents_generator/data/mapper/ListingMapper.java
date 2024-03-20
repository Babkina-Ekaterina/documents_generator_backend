package cs.vsu.ru.documents_generator.data.mapper;

import cs.vsu.ru.documents_generator.data.dto.UserDataDto;
import cs.vsu.ru.documents_generator.data.utilModel.Author;
import cs.vsu.ru.documents_generator.data.entity.ListingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@AllArgsConstructor
public class ListingMapper {
    public ListingEntity dtoToEntity(UserDataDto userDataDto, MultipartFile[] programFiles) {
        String type = "";
        if (userDataDto.isComputerProgramCheckbox()) {
            type = "ПРОГРАММА ДЛЯ ЭВМ";
        } else if (userDataDto.isDatabaseCheckbox1()) {
            type = "БАЗА ДАННЫХ, ГОСУДАРСТВЕННАЯ РЕГИСТРАЦИЯ КОТОРОЙ ОСУЩЕСТВЛЯЕТСЯ В СООТВЕТСТВИИ С ПУНКТОМ 4 СТАТЬИ 1259 КОДЕКСА";
        } else if (userDataDto.isDatabaseCheckbox2()) {
            type = "БАЗА ДАННЫХ, ГОСУДАРСТВЕННАЯ РЕГИСТРАЦИЯ КОТОРОЙ ОСУЩЕСТВЛЯЕТСЯ В СООТВЕТСТВИИ С ПУНКТОМ 3 СТАТЬИ 1334 КОДЕКСА";
        }

        List<Author> authors = new ArrayList<>();
        for (String name: userDataDto.getNames()) {
            String[] parts = name.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int partIndex = 1; partIndex < parts.length; partIndex++) {
                String part = parts[partIndex];
                if (!part.isEmpty()) {
                    sb.append(part.charAt(0)).append(".");
                }
            }
            sb.append(" ");
            sb.append(parts[0]);
            authors.add(new Author(sb.toString()));
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);

        return new ListingEntity(type, userDataDto.getProgramName(), authors, year, programFiles);
    }
}
