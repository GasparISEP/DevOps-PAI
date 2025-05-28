package PAI.assembler.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionIdDto;

public interface IProgrammeEditionAssembler {
    CountStudentsDto toCountStudentsInProgrammeEditionDTO(ProgrammeEdition programmeEdition);
    ProgrammeEdition CountStudentsInProgrammeEditionDTOtoDomain(CountStudentsDto dto) throws Exception;
    SchoolYearID toSchoolYearID(ProgrammeEditionDTO programmeEditionDTO);
    ProgrammeID toProgrammeID(ProgrammeEditionDTO programmeEditionDTO);
    ProgrammeEditionDTO toDTO(ProgrammeID programmeID, SchoolYearID schoolYearID);
    ProgrammeEditionID toProgrammeEditionID(ProgrammeEditionIdDto programmeEditionIdDto) throws Exception;
}
