package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.dto.schoolYear.SchoolYearDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolYearServiceImpl implements ISchoolYearService {

    private final ISchoolYearRepository schoolYearRepository;
    private final ISchoolYearFactory schoolYearFactory;
    private final ISchoolYearAssembler schoolYearMapperDTO;

    public SchoolYearServiceImpl(ISchoolYearRepository schoolYearRepository, ISchoolYearFactory schoolYearFactory, ISchoolYearAssembler schoolYearMapperDTO) {
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

     // Validate that a given dependency is not null
    private <T> T validateNotNull(T dependency, String name) {
        if (dependency == null) {
            throw new IllegalArgumentException(name + " cannot be null");
        }
        return dependency;
    }

    @Override
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

    @Override
    public List<SchoolYearID> getAllSchoolYearsIDs() {
        List<SchoolYearID> schoolYearIDs = new ArrayList<>();
        for (SchoolYear schoolYear : schoolYearRepository.findAll()) {
            schoolYearIDs.add(schoolYear.identity());
        }
        return schoolYearIDs;
    }

    public boolean schoolYearExistsById(SchoolYearID schoolYearID) {
        boolean result;
        if (schoolYearID == null) {
            result = false;
        } else {
            result = schoolYearRepository.containsOfIdentity(schoolYearID);
        }
        return result;
    }

}
