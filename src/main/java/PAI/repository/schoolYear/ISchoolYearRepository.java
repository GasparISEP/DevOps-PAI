package PAI.repository.schoolYear;

import PAI.VOs.SchoolYearID;
import PAI.ddd.IRepository;
import PAI.domain.schoolYear.SchoolYear;

import java.util.List;
import java.util.Optional;

public interface ISchoolYearRepository extends IRepository<SchoolYearID, SchoolYear> {

    boolean schoolYearExists(SchoolYear schoolYear);

    Optional<SchoolYear> getCurrentSchoolYear();

    List<SchoolYearID> getAllSchoolYearsIDs();

}
