package cs.vsu.ru.documents_generator.data.mapper;

import cs.vsu.ru.documents_generator.data.dto.UserDataDto;
import cs.vsu.ru.documents_generator.data.entity.ConsentForPersonalDataProcessingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
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
