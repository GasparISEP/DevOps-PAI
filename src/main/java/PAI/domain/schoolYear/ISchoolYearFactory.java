package PAI.domain.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;

import java.util.UUID;

public interface ISchoolYearFactory {

    SchoolYear createSchoolYear(Description description, Date startDate, Date endDate);
    SchoolYear recreateSchoolYear(UUID uuid, Description description, Date startDate, Date endDate);
}
