package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearIDDescriptionResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISchoolYearService {

    SchoolYear addSchoolYear(Description description, Date startDate, Date endDate) throws Exception;
    SchoolYear addSchoolYearDM(UUID schoolYearID, Description description, Date startDate, Date endDate) throws Exception;
    Optional<SchoolYearID> getCurrentSchoolYearID();
    boolean schoolYearExistsById(SchoolYearID schoolYearID);
    List<SchoolYearID> getAllSchoolYearsIDs();
    Iterable<CurrentSchoolYearDTO> getAllSchoolYears();
    Optional<CurrentSchoolYearDTO> getCurrentSchoolYear();
    Optional<SchoolYear> getSchoolYearByID(SchoolYearID schoolYearID);
    List<SchoolYearIDDescriptionResponseDTO> getAllSchoolYearsIDDescriptions();
    List<SchoolYear> getSchoolYearsByIDs(List<SchoolYearID> schoolYearIDs);
}
