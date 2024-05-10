package cs.vsu.ru.documents_generator.data.mapper.documents;

import cs.vsu.ru.documents_generator.data.dto.documents.UserDataDto;
import cs.vsu.ru.documents_generator.data.entity.documents.EssayEntity;
import org.springframework.stereotype.Component;

@Component
public class EssayMapper {
    public EssayEntity dtoToEntity(UserDataDto userDataDto) {
        return new EssayEntity(userDataDto.getProgramName(), userDataDto.getAnnotation(),
                userDataDto.getLanguage(), userDataDto.getProgramSize(), userDataDto.getFormat());
    }
}
