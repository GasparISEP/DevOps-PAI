package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;

import java.util.List;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.SchoolYearDTO;

import java.util.Optional;

public interface ISchoolYearService {

    SchoolYear addSchoolYear(Description description, Date startDate, Date endDate) throws Exception;
    Optional<SchoolYearID> getCurrentSchoolYearID();
    boolean schoolYearExistsById(SchoolYearID schoolYearID);
    List<SchoolYearID> getAllSchoolYearsIDs();
}
