package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;

public interface IProgrammeEditionControllerAssembler {
    ProgrammeEditionServiceDTO toDTO(ProgrammeEditionRequestDTO programmeEditionRequest);
    ProgrammeEditionResponseDTO toResponseDTO(ProgrammeEditionServiceDTO programmeEdition);
    CountStudentsDto toCountDTO(ProgrammeEdition programmeEdition);
    ProgrammeEditionServiceDTO toDTOFromIDs(ProgrammeID programmeID, SchoolYearID schoolYearID);
}
