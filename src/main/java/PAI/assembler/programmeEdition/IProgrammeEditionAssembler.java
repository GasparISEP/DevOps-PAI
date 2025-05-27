package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;

public interface IProgrammeEditionAssembler {
    CountStudentsDto toCountStudentsInProgrammeEditionDTO(ProgrammeEdition programmeEdition);
    ProgrammeEdition CountStudentsInProgrammeEditionDTOtoDomain(CountStudentsDto dto) throws Exception;
    Iterable<CountStudentsDto> toCountStudentsInProgrammeEditionDTOList(Iterable<ProgrammeEdition> editions);
    SchoolYearID toSchoolYearID(ProgrammeEditionDTO programmeEditionDTO);
    ProgrammeID toProgrammeID(ProgrammeEditionDTO programmeEditionDTO);
    ProgrammeEditionDTO toDTO(ProgrammeID programmeID, SchoolYearID schoolYearID);
}
