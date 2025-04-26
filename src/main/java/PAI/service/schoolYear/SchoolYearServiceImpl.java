package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.repository.ISchoolYearRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolYearServiceImpl implements ISchoolYearService {

    private final ISchoolYearRepository schoolYearRepository;
    private final ISchoolYearFactory schoolYearFactory;

    public SchoolYearServiceImpl(ISchoolYearRepository schoolYearRepository, ISchoolYearFactory schoolYearFactory) {
        if (schoolYearRepository == null) {
            throw new IllegalArgumentException("schoolYearRepository cannot be null");
        }
        if (schoolYearFactory == null) {
            throw new IllegalArgumentException("schoolYearFactory cannot be null");
        }
        this.schoolYearRepository = schoolYearRepository;
        this.schoolYearFactory = schoolYearFactory;
    }

    //create and save a new school year if it does not already exist
    @Override
    public boolean addSchoolYear(Description description, Date startDate, Date endDate) throws Exception {
        SchoolYear newSchoolYear = schoolYearFactory.createSchoolYear(description, startDate, endDate);

        if (schoolYearRepository.containsOfIdentity(newSchoolYear.identity())) {
            throw new Exception("School year already exists.");
        }

        schoolYearRepository.save(newSchoolYear);
        return true;
    }
}
