package PAI.factory;

import PAI.VOs.Description;
import PAI.domain.SchoolYear;

public interface ISchoolYearFactory {

    SchoolYear createSchoolYear(Description description, String startDate, String endDate);
}
