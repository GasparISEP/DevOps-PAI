package PAI.assembler.schoolYear;

import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ISchoolYearHateoasAssembler extends RepresentationModelAssembler<CurrentSchoolYearDTO, EntityModel<CurrentSchoolYearDTO>> {

    EntityModel<CurrentSchoolYearDTO> toModel(CurrentSchoolYearDTO dto);
}
