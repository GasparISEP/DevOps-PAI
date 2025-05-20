package PAI.dto.SchoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;

public class SchoolYearDTOMapper {

    private ISchoolYearFactory _syFactory;

    public SchoolYearDTOMapper (ISchoolYearFactory syFactory) {
        _syFactory = syFactory;
    }

    public SchoolYear toDomain(SchoolYearDTO syDTO) {

        Description description = new Description(syDTO.getDescription());

        Date startDate = new Date(syDTO.getStartDate());

        Date endDate = new Date(syDTO.getEndDate());

        return _syFactory.createSchoolYear(description, startDate, endDate);
    }
}
