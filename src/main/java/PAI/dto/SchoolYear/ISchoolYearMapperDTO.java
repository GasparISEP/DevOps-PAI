package PAI.dto.SchoolYear;

import PAI.domain.schoolYear.SchoolYear;

public interface ISchoolYearMapperDTO {

    SchoolYear toDomain(SchoolYearDTO syDTO);
}
