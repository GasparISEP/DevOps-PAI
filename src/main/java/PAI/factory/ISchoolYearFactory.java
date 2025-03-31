package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.SchoolYear;

public interface ISchoolYearFactory {

    SchoolYear createSchoolYear(Description description, Date startDate, Date endDate);
}
