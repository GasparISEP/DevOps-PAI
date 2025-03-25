package PAI.factory;

import PAI.domain.SchoolYear;

public class SchoolYearFactoryImpl implements ISchoolYearFactory {

    public SchoolYear createSchoolYear(String description, String startDate, String endDate) {

        return new SchoolYear(description, startDate, endDate);

    }
}
