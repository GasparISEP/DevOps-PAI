package PAI.factory;

import PAI.VOs.Description;
import PAI.domain.SchoolYear;

public class SchoolYearFactoryImpl implements ISchoolYearFactory {

    public SchoolYear createSchoolYear(Description description, String startDate, String endDate) {

        return new SchoolYear(description, startDate, endDate);

    }
}
