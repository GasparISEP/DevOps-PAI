package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;

public interface IProgrammeEditionControllerAssembler {
    ProgrammeEditionDTO toDTO(ProgrammeEditionRequestDTO programmeEditionRequest);
    ProgrammeEditionResponseDTO toResponseDTO(ProgrammeEditionDTO programmeEdition);

    ProgrammeEditionDTO toDTOFromIDs(ProgrammeID programmeID, SchoolYearID schoolYearID);
}
