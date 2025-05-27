package PAI.assembler.department;

import PAI.dto.department.DepartmentDTO;
import org.springframework.hateoas.EntityModel;

public interface IDepartmentHateoasAssembler {

    EntityModel<DepartmentDTO> toModel(DepartmentDTO dto);


}
