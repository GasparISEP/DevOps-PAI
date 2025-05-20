package PAI.dto.SchoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;

public class SchoolYearDTOMapper {

    private ISchoolYearFactory _syFactory;

    public SchoolYearDTOMapper (ISchoolYearFactory syFactory) {
        _syFactory = syFactory;
    }
}
