package PAI.assembler.programmeEdition;

import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsInProgrammeEditionDto;

import java.util.List;

public interface IProgrammeEditionAssembler {
    CountStudentsInProgrammeEditionDto toCountStudentsInProgrammeEditionDTO(ProgrammeEdition programmeEdition);
    ProgrammeEdition CountStudentsInProgrammeEditionDTOtoDomain(CountStudentsInProgrammeEditionDto dto) throws Exception;
    Iterable<CountStudentsInProgrammeEditionDto> toCountStudentsInProgrammeEditionDTOList(Iterable<ProgrammeEdition> editions);
}
