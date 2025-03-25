package PAI.factory;

import PAI.domain.SchoolYear;

public interface ISchoolYearFactory {

    SchoolYear createSchoolYear(String description, String startDate, String endDate);
}
