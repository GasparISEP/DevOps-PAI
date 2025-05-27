package PAI.assembler.programmeEdition;

import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEditionControllerAssembler implements IProgrammeEditionControllerAssembler{

    @Override
    public ProgrammeEditionDTO toDTO(ProgrammeEditionRequestDTO programmeEditionRequest) {
        if (programmeEditionRequest == null){
            throw new IllegalArgumentException("ProgrammeEditionRequestDTO cannot be null");
        }
        return new ProgrammeEditionDTO(programmeEditionRequest.programme(),programmeEditionRequest.schoolYear());
    }

    @Override
    public ProgrammeEditionResponseDTO toResponseDTO(ProgrammeEditionDTO programmeEdition) {
        if (programmeEdition == null){
            throw new IllegalArgumentException("ProgrammeEditionDTO cannot be null");
        }
      return new ProgrammeEditionResponseDTO(programmeEdition.programme(),programmeEdition.schoolYear());
    }
}
