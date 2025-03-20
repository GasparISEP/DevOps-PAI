package PAI.factory;

import PAI.domain.SchoolYear;

public interface SchoolYearFactory {

    SchoolYear createSchoolYear(String description, String startDate, String endDate);
}
