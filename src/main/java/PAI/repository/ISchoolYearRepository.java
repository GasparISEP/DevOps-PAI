package PAI.repository;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.ddd.IRepository;
import PAI.domain.SchoolYear;

import java.util.List;
import java.util.Optional;

public interface ISchoolYearRepository extends IRepository<SchoolYearID, SchoolYear> {

    boolean addSchoolYear(Description description, Date startDate, Date endDate) throws Exception;

    boolean schoolYearExists(SchoolYear schoolYear);

    Optional<SchoolYear> getCurrentSchoolYear();

    List<SchoolYear> getAllSchoolYears();

    List<SchoolYearID> getAllSchoolYearsIDs();

    boolean schoolYearExistsByID(SchoolYearID schoolYear);
}
