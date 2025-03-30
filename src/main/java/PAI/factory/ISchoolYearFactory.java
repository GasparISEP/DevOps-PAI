package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;

public interface ISchoolYearFactory {

    SchoolYear createSchoolYear(SchoolYearID schoolYearID, Description description, Date startDate, Date endDate);
}
