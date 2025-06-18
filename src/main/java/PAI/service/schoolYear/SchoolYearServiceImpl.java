package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearIDDescriptionResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public SchoolYear addSchoolYear(Description description, Date startDate, Date endDate) throws Exception {

        if (description == null || startDate == null || endDate == null) {
            throw new Exception("Not possible to create a school year");
        }

        SchoolYear newSchoolYear = schoolYearFactory.createSchoolYear(description, startDate, endDate);

        if (schoolYearRepository.schoolYearExists(newSchoolYear)) {
            throw new Exception("School year already exists.");
        }

        return schoolYearRepository.save(newSchoolYear);
    }

    public SchoolYear addSchoolYearDM(UUID schoolYearID, Description description, Date startDate, Date endDate) throws Exception {

        if (schoolYearID == null || description == null || startDate == null || endDate == null) {
            throw new Exception("Not possible to create a school year");
        }

        SchoolYear newSchoolYear = schoolYearFactory.recreateSchoolYear(schoolYearID, description, startDate, endDate);

        if (schoolYearRepository.schoolYearExists(newSchoolYear)) {
            throw new Exception("School year already exists.");
        }

        return schoolYearRepository.save(newSchoolYear);
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
        try {
            Optional<SchoolYear> schoolYear = schoolYearRepository.getCurrentSchoolYear();
            if (schoolYear.isPresent()) {
                return Optional.of(schoolYear.get().identity());
            }
            return Optional.empty();
        } catch (Exception e) {
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

    public Iterable<CurrentSchoolYearDTO> getAllSchoolYears() {
        Iterable<SchoolYear> schoolYears = schoolYearRepository.findAll();
        List<CurrentSchoolYearDTO> schoolYearDTOs = new ArrayList<>();
        for (SchoolYear schoolYear : schoolYears) {
            CurrentSchoolYearDTO schoolYearDTO = schoolYearMapperDTO.toCurrentSchoolYearDTO(schoolYear);
            schoolYearDTOs.add(schoolYearDTO);
        }
        return schoolYearDTOs;
    }

    @Override
    public Optional<CurrentSchoolYearDTO> getCurrentSchoolYear() {
        Optional<SchoolYear> schoolYear = schoolYearRepository.getCurrentSchoolYear();
        if (schoolYear.isPresent()) {
            return Optional.of(schoolYearMapperDTO.toCurrentSchoolYearDTO(schoolYear.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SchoolYear> getSchoolYearByID(SchoolYearID schoolYearID) {
        return schoolYearRepository.findBySchoolYearID(schoolYearID);
    }

    @Override
    public List<SchoolYearIDDescriptionResponseDTO> getAllSchoolYearsIDDescriptions() {
        Iterable<SchoolYear> schoolYears = schoolYearRepository.findAll();
        List<SchoolYearIDDescriptionResponseDTO> schoolYearIDDescriptionResponseDTOs = new ArrayList<>();
        for (SchoolYear schoolYear : schoolYears) {
            SchoolYearIDDescriptionResponseDTO responseDTO = new SchoolYearIDDescriptionResponseDTO(
                    schoolYear.identity().toString(),
                    schoolYear.getDescription().getDescription());
            schoolYearIDDescriptionResponseDTOs.add(responseDTO);
        }
        return schoolYearIDDescriptionResponseDTOs;
    }

    @Override
    public List<SchoolYear> getSchoolYearsByIDs(List<SchoolYearID> schoolYearIDs) {
        return schoolYearIDs.stream()
                .map(id -> getSchoolYearByID(id).orElseThrow())
                .toList();
    }
}
