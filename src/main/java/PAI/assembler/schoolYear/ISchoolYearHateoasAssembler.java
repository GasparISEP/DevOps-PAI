package PAI.assembler.schoolYear;

import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import org.springframework.hateoas.EntityModel;

public interface ISchoolYearHateoasAssembler {
    EntityModel<CurrentSchoolYearDTO> toModel(CurrentSchoolYearDTO dto);
}
