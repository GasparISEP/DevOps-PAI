package PAI.assembler.programme;


import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.Programme.ProgrammeDirectorVOsDTO;

import java.util.List;

public interface IProgrammeDirectorAssembler  {

    ProgrammeDirectorResponseDTO fromDomainToDTO(List<Teacher> teachers);

    ProgrammeDirectorVOsDTO fromDTOToDomain(ProgrammeDirectorVOsDTO dto);

}
