package PAI.assembler.department;

import PAI.dto.department.DepartmentDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface IDepartmentHateoasAssembler extends RepresentationModelAssembler<DepartmentDTO, EntityModel<DepartmentDTO>> {

    EntityModel<DepartmentDTO> toModel(DepartmentDTO dto);

}
