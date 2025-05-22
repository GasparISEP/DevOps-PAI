package PAI.service;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.ISchoolYearMapperDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import org.springframework.stereotype.Service;

@Service
public class CreateSchoolYearService {

    private final ISchoolYearRepository schoolYearRepository;
    private final ISchoolYearFactory schoolYearFactory;
    private final ISchoolYearMapperDTO schoolYearMapperDTO;

    public CreateSchoolYearService(ISchoolYearRepository schoolYearRepository, ISchoolYearFactory schoolYearFactory, ISchoolYearMapperDTO schoolYearMapperDTO) {
        this.schoolYearRepository = validateNotNull(schoolYearRepository, "schoolYearRepository");
        this.schoolYearFactory = validateNotNull(schoolYearFactory, "schoolYearFactory");
        this.schoolYearMapperDTO = validateNotNull(schoolYearMapperDTO, "schoolYearMapper");
    }

    // Create and save a new school year if it does not already exist
    public SchoolYearDTO addSchoolYear(String description, String startDate, String endDate) throws Exception {

        if (description == null || startDate == null || endDate == null) {
            throw new Exception("Not possible to create a school year");
        }

        Description description1 = new Description(description);

        Date startDate1 = new Date(startDate);

        Date endDate1 = new Date(endDate);

        SchoolYear newSchoolYear = schoolYearFactory.createSchoolYear(description1, startDate1, endDate1);

        if (schoolYearRepository.schoolYearExists(newSchoolYear)) {
            throw new Exception("School year already exists.");
        }

        return schoolYearMapperDTO.toDTO(schoolYearRepository.save(newSchoolYear));
    }

    private <T> T validateNotNull(T dependency, String name) {
        if (dependency == null) {
            throw new IllegalArgumentException(name + " cannot be null");
        }
        return dependency;
    }
}
