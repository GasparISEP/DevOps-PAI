package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;

public class SchoolYearFactoryImpl implements ISchoolYearFactory {

    public SchoolYear createSchoolYear(Description description, Date startDate, Date endDate) {

        return new SchoolYear(description, startDate, endDate);

    }
}
