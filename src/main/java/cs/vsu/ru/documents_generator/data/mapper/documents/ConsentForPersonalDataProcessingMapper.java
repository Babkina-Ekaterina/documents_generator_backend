package cs.vsu.ru.documents_generator.data.mapper.documents;

import cs.vsu.ru.documents_generator.data.dto.documents.UserDataDto;
import cs.vsu.ru.documents_generator.data.entity.documents.ConsentForPersonalDataProcessingEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsentForPersonalDataProcessingMapper {
    public List<ConsentForPersonalDataProcessingEntity> dtoToEntity(UserDataDto userDataDto) {
        List<ConsentForPersonalDataProcessingEntity> result = new ArrayList<>();
        int documentNumber = userDataDto.getNames().size();
        for (int documentIndex = 0; documentIndex < documentNumber; documentIndex++) {
            result.add(new ConsentForPersonalDataProcessingEntity(userDataDto.getProgramName(),
                    userDataDto.getNames().get(documentIndex), userDataDto.getAddresses().get(documentIndex),
                    userDataDto.getSeries().get(documentIndex), userDataDto.getNumbers().get(documentIndex),
                    userDataDto.getDatesOfIssue().get(documentIndex), userDataDto.getIssuedBys().get(documentIndex),
                    userDataDto.getFormat()));
        }
        return result;
    }
}
