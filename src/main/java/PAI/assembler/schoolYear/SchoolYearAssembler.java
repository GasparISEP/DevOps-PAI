package PAI.assembler.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.CurrentSchoolYearResponseDTO;
import PAI.dto.schoolYear.SchoolYearCEDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class SchoolYearAssembler implements ISchoolYearAssembler {

    private ISchoolYearFactory _syFactory;

    public SchoolYearAssembler(ISchoolYearFactory syFactory) {
        _syFactory = syFactory;
    }

    @Override
    public SchoolYear toDomain(SchoolYearDTO syDTO) {

        Description description = new Description(syDTO.getDescription());

        Date startDate = new Date(syDTO.getStartDate());

        Date endDate = new Date(syDTO.getEndDate());

        return _syFactory.createSchoolYear(description, startDate, endDate);
    }

    public SchoolYearDTO toDTO(SchoolYear sy) {
        String description = sy.getDescription().getDescription();
        String startDate = sy.getStartDate().getLocalDate().toString();
        String endDate = sy.getEndDate().getLocalDate().toString();

        return new SchoolYearDTO(description,startDate,endDate);
    }

    @Override
    public SchoolYearCEDTO toCEDTO(SchoolYear sy) {
        String id = sy.identity().toString();
        String description = sy.getDescription().getDescription();
        String startDate = sy.getStartDate().getLocalDate().toString();
        String endDate = sy.getEndDate().getLocalDate().toString();

        return new SchoolYearCEDTO(id,description,startDate,endDate);
    }

    @Override
    public Iterable<SchoolYearCEDTO> toCEDTOs(Iterable<SchoolYear> schoolYears) {
        if (schoolYears == null) {
            return Collections.emptyList();
        }
        List<SchoolYearCEDTO> listDTO = new ArrayList<>();
        for (SchoolYear schoolYear: schoolYears) {
            SchoolYearCEDTO responseDTO = toCEDTO(schoolYear);
            listDTO.add(responseDTO);
        }
        return listDTO;
    }

    @Override
    public CurrentSchoolYearDTO toCurrentSchoolYearDTO(SchoolYear sy) {
        if (sy == null) {
            throw new IllegalArgumentException("SchoolYear cannot be null");
        }
        String id = sy.identity().getSchoolYearID().toString();
        String description = sy.getDescription().getDescription();
        LocalDate startDate = sy.getStartDate().getLocalDate();
        LocalDate endDate = sy.getEndDate().getLocalDate();
        return new CurrentSchoolYearDTO(id,description,startDate,endDate);
    }

    @Override
    public CurrentSchoolYearResponseDTO toResponseDTO(CurrentSchoolYearDTO sy) {
        if (sy == null) {
            throw new IllegalArgumentException("CurrentSchoolYearDTO cannot be null");
        }
        return  new CurrentSchoolYearResponseDTO(sy.id(),sy.description(),sy.startDate(),sy.endDate());
    }

    public Description toDescription(SchoolYearDTO syDTO) {
        return new Description(syDTO.getDescription());
    }

    public Date toEndDate(SchoolYearDTO syDTO) {
        return new Date(syDTO.getEndDate());
    }

    public Date toStartDate(SchoolYearDTO syDTO) {
        return new Date(syDTO.getStartDate());
    }

    public SchoolYearID fromStringToSchoolYearID(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("SchoolYearID cannot be null or blank");
        }
        UUID uuid = UUID.fromString(id);
        return new SchoolYearID(uuid);
    }
}
