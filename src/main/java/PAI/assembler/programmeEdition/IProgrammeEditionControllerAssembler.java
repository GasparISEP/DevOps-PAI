package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.*;

public interface IProgrammeEditionControllerAssembler {
    ProgrammeEditionRequestServiceDTO toDTO(ProgrammeEditionRequestDTO programmeEditionRequest);
    ProgrammeEditionResponseDTO toResponseDTO(ProgrammeEditionResponseServiceDTO programmeEdition);
    CountStudentsRequestDto toCountDTO(ProgrammeEdition programmeEdition);
    ProgrammeEditionResponseServiceDTO toDTOFromIDs(ProgrammeID programmeID, SchoolYearID schoolYearID);
}
