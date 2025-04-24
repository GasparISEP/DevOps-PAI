package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;

public interface ISchoolYearService {

    boolean addSchoolYear (Description description, Date StartDate, Date EndDate) throws Exception;
}
