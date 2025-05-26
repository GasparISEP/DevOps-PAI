package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsInProgrammeEditionDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;

import java.util.List;

public interface IProgrammeEditionAssembler {
    CountStudentsInProgrammeEditionDto toCountStudentsInProgrammeEditionDTO(ProgrammeEdition programmeEdition);
    ProgrammeEdition CountStudentsInProgrammeEditionDTOtoDomain(CountStudentsInProgrammeEditionDto dto) throws Exception;
    Iterable<CountStudentsInProgrammeEditionDto> toCountStudentsInProgrammeEditionDTOList(Iterable<ProgrammeEdition> editions);
    SchoolYearID toSchoolYearID(ProgrammeEditionDTO programmeEditionDTO);
    ProgrammeID toProgrammeID(ProgrammeEditionDTO programmeEditionDTO);
    ProgrammeEditionDTO toDTO(ProgrammeID programmeID, SchoolYearID schoolYearID);
}
