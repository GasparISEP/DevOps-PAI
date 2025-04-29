package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;

import java.util.List;
import java.util.Optional;

public interface ISchoolYearService {

    boolean addSchoolYear (Description description, Date StartDate, Date EndDate) throws Exception;
    Optional<SchoolYearID> getCurrentSchoolYearID();
    List<SchoolYearID> getAllSchoolYearsIDs();
}
