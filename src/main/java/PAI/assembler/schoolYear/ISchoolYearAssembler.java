package PAI.assembler.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.SchoolYearDTO;

public interface ISchoolYearAssembler {

    SchoolYear toDomain(SchoolYearDTO syDTO);
    Description toDescription(SchoolYearDTO syDTO);
    Date toEndDate(SchoolYearDTO syDTO);
    Date toStartDate(SchoolYearDTO syDTO);
    SchoolYearDTO toDTO(SchoolYear sy);
}
