package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.repository.ISchoolYearRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolYearServiceImpl implements ISchoolYearService {

    private final ISchoolYearRepository schoolYearRepository;
    private final ISchoolYearFactory schoolYearFactory;

    public SchoolYearServiceImpl(ISchoolYearRepository schoolYearRepository, ISchoolYearFactory schoolYearFactory) {
        this.schoolYearRepository = validateNotNull(schoolYearRepository, "schoolYearRepository");
        this.schoolYearFactory = validateNotNull(schoolYearFactory, "schoolYearFactory");
    }

    // Create and save a new school year if it does not already exist
    @Override
    public boolean addSchoolYear(Description description, Date startDate, Date endDate) throws Exception {
        SchoolYear newSchoolYear = schoolYearFactory.createSchoolYear(description, startDate, endDate);

        if (schoolYearRepository.schoolYearExists(newSchoolYear)) {
            throw new Exception("School year already exists.");
        }

        schoolYearRepository.save(newSchoolYear);
        return true;
    }

     // Validate that a given dependency is not null
    private <T> T validateNotNull(T dependency, String name) {
        if (dependency == null) {
            throw new IllegalArgumentException(name + " cannot be null");
        }
        return dependency;
    }

    public Optional<SchoolYearID> getCurrentSchoolYearID() {
        try{
            Optional<SchoolYear> schoolYear = schoolYearRepository.getCurrentSchoolYear();
            if (schoolYear.isPresent()) {
                return Optional.of(schoolYear.get().identity());
            }
            return Optional.empty();
        }catch (Exception e) {
            return Optional.empty();
        }
    }
}
