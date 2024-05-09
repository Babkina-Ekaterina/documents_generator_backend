package cs.vsu.ru.documents_generator.data.mapper;

import cs.vsu.ru.documents_generator.data.dto.UserDataDto;
import cs.vsu.ru.documents_generator.data.entity.EssayEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EssayMapper {
    public EssayEntity dtoToEntity(UserDataDto userDataDto) {
        return new EssayEntity(userDataDto.getProgramName(), userDataDto.getAnnotation(),
                userDataDto.getLanguage(), userDataDto.getProgramSize(), userDataDto.getFormat());
    }
}
