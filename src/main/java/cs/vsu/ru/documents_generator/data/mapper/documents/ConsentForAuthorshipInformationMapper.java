package cs.vsu.ru.documents_generator.data.mapper.documents;

import cs.vsu.ru.documents_generator.data.dto.documents.UserDataDto;
import cs.vsu.ru.documents_generator.data.entity.documents.ConsentForAuthorshipInformationEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class ConsentForAuthorshipInformationMapper {
    public List<ConsentForAuthorshipInformationEntity> dtoToEntity(UserDataDto userDataDto) {
        List<ConsentForAuthorshipInformationEntity> result = new ArrayList<>();
        int documentNumber = userDataDto.getNames().size();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int documentIndex = 0; documentIndex < documentNumber; documentIndex++) {
            String[] birthInformation = userDataDto.getDatesOfBirth().get(documentIndex).split("\\.");
            result.add(new ConsentForAuthorshipInformationEntity(
                    userDataDto.isComputerProgramCheckbox(), userDataDto.isDatabaseCheckbox1(),
                    userDataDto.isDatabaseCheckbox2(), userDataDto.getProgramName(),
                    userDataDto.getNames().get(documentIndex), birthInformation[0], birthInformation[1],
                    birthInformation[2], userDataDto.getCitizenships().get(documentIndex),
                    userDataDto.getAddresses().get(documentIndex), userDataDto.getDescriptions().get(documentIndex),
                    userDataDto.getNameCheckboxes().get(documentIndex),
                    userDataDto.getAnonymousCheckboxes().get(documentIndex),
                    userDataDto.getPseudoNameCheckboxes().get(documentIndex), year, userDataDto.getFormat()));
        }
        return result;
    }
}
