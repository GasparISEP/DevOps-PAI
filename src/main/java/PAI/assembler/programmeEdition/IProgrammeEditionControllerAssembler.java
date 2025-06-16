package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.programmeEdition.*;

import java.util.List;

public interface IProgrammeEditionControllerAssembler {
    ProgrammeEditionIdDto toIdDto(ProgrammeEditionID id);

    ProgrammeEditionRequestServiceDTO toServiceDTOFromRequestDTO(ProgrammeEditionRequestDTO programmeEditionRequest);

    ProgrammeEditionResponseDTO toResponseDTOFromServiceDTO(ProgrammeEditionResponseServiceDTO responseDTO);

    List<ProgrammeEditionResponseDTO> toResponseDTOList(ProgrammeEditionResponseServiceDTO responseDTO);

    ProgrammeEditionWithNameAndDescriptionResponseDTO toProgrammeEditionIdResponseDto(Programme programme, SchoolYear schoolYear);

    RequestServiceDto toRequestServiceDtoFromIDs(String programmeAcronym, String schoolYearID);
}