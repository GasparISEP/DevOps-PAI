package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.SchoolYear;

import java.util.UUID;

public interface ISchoolYearFactory {

    SchoolYear createSchoolYear(Description description, Date startDate, Date endDate);
    SchoolYear createSchoolYear(UUID uuid, Description description, Date startDate, Date endDate);
}
