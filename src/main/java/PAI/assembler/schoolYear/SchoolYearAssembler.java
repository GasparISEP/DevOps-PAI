package PAI.assembler.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.CurrentSchoolYearResponseDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
}
