package PAI.assembler.programme;

import PAI.VOs.ProgrammeID;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;

public interface IProgrammeAssembler {

    ProgrammeVOsDTO fromDTOToDomain (ProgrammeDTO programmeDTO);

    ProgrammeResponseDTO fromDomainToDTO (Programme programme, String degreeTypeName, String departmentName, String teacherName);

    ProgrammeIDDTO toDTO (ProgrammeID programmeID);
}
