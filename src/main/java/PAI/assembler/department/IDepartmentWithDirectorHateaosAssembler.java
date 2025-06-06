package PAI.assembler.department;

import PAI.dto.department.DepartmentWithDirectorDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface IDepartmentWithDirectorHateaosAssembler extends RepresentationModelAssembler<DepartmentWithDirectorDTO, EntityModel<DepartmentWithDirectorDTO>> {

    EntityModel<DepartmentWithDirectorDTO> toModel(DepartmentWithDirectorDTO dto);
}
