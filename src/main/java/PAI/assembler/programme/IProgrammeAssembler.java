package PAI.assembler.programme;

import PAI.VOs.ProgrammeID;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeIDResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;

import java.util.List;

public interface IProgrammeAssembler {

    ProgrammeVOsDTO fromDTOToDomain(ProgrammeDTO programmeDTO);

    ProgrammeDTO fromDomainToDTO(Programme programme);

    ProgrammeIDDTO toDTO(ProgrammeID programmeID);

    ProgrammeIDResponseDTO toResponseDTO(ProgrammeIDDTO programmeIDDTO);

    List<ProgrammeIDDTO> toListOfDTOs(List<ProgrammeID> list);
}
