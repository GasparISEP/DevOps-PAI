package PAI.assembler.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.CurrentSchoolYearResponseDTO;
import PAI.dto.schoolYear.SchoolYearCEDTO;
import PAI.dto.schoolYear.SchoolYearDTO;

public interface ISchoolYearAssembler {

    SchoolYear toDomain(SchoolYearDTO syDTO);
    Description toDescription(SchoolYearDTO syDTO);
    Date toEndDate(SchoolYearDTO syDTO);
    Date toStartDate(SchoolYearDTO syDTO);
    SchoolYearDTO toDTO(SchoolYear sy);
    CurrentSchoolYearDTO toCurrentSchoolYearDTO(SchoolYear sy);
    CurrentSchoolYearResponseDTO toResponseDTO(CurrentSchoolYearDTO sy);
    SchoolYearID fromStringToSchoolYearID(String id);
    SchoolYearCEDTO toCEDTO(SchoolYear sy);
    Iterable<SchoolYearCEDTO> toCEDTOs(Iterable<SchoolYear> schoolYears);
}
