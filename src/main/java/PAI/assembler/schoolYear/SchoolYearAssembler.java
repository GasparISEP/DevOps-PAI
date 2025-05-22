package PAI.assembler.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.SchoolYearDTO;
import org.springframework.stereotype.Component;

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
