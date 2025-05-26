package PAI.assembler.programme;

import PAI.domain.programme.Programme;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeDirectorRequestDTO;
import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.Programme.ProgrammeDirectorVOsDTO;

import java.util.List;

public interface IProgrammeDirectorAssembler  {

    ProgrammeDirectorResponseDTO fromDomainToDTO(List<Programme> programmes, List<Teacher> teachers);

    ProgrammeDirectorVOsDTO fromDTOToDomain(ProgrammeDirectorRequestDTO dto);
}
