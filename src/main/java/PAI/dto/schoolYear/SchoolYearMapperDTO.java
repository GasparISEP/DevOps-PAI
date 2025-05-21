package PAI.dto.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import org.springframework.stereotype.Component;

@Component
public class SchoolYearMapperDTO implements ISchoolYearMapperDTO {

    private ISchoolYearFactory _syFactory;

    public SchoolYearMapperDTO (ISchoolYearFactory syFactory) {
        _syFactory = syFactory;
    }

    @Override
    public SchoolYear toDomain(SchoolYearDTO syDTO) {

        Description description = new Description(syDTO.getDescription());

        Date startDate = new Date(syDTO.getStartDate());

        Date endDate = new Date(syDTO.getEndDate());

        return _syFactory.createSchoolYear(description, startDate, endDate);
    }

    public Description toDescription(SchoolYearDTO syDTO) {
        return new Description(syDTO.getDescription());
    }

    public Date toEndDate(SchoolYearDTO syDTO) {
        return new Date(syDTO.getEndDate());
    }
}
