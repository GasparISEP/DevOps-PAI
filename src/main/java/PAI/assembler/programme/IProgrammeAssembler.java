package PAI.assembler.programme;

import PAI.VOs.ProgrammeID;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeRequestDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;

public interface IProgrammeAssembler {

    ProgrammeVOsDTO fromDTOToDomain (ProgrammeRequestDTO programmeRequestDTO);

    ProgrammeResponseDTO fromDomainToDTO (Programme programme, String degreeTypeName, String departmentName, String teacherName);

    ProgrammeIDDTO toDTO (ProgrammeID programmeID);
}
