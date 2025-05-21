package PAI.dto.schoolYear;

import PAI.domain.schoolYear.SchoolYear;

public interface ISchoolYearMapperDTO {

    SchoolYear toDomain(SchoolYearDTO syDTO);
}
