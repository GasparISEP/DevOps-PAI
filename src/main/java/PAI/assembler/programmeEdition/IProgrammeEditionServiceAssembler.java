package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.RequestServiceDto;
import PAI.dto.programmeEdition.ProgrammeEditionRequestServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;

import java.util.List;

public interface IProgrammeEditionServiceAssembler {
    ProgrammeEditionResponseServiceDTO toResponseServiceDTOFromProgrammeEdition(ProgrammeEdition programmeEdition);
    ProgrammeEdition toProgrammeEditionFromRequestServiceDTO(RequestServiceDto dto) throws Exception;
    ProgrammeID toProgrammeID(ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO);
    ProgrammeEditionResponseServiceDTO toServiceResponseDTOFromIDs(ProgrammeID programmeID, SchoolYearID schoolYearID);
  //  ProgrammeEditionID toProgrammeEditionID(ProgrammeEditionIdDto programmeEditionIdDto) throws Exception;
    List<ProgrammeEditionResponseServiceDTO> toServiceResponseDTOList(List<ProgrammeEdition> programmeEditions);
}
