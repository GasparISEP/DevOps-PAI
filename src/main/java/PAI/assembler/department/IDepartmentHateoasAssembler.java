package PAI.assembler.department;

import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.DepartmentWithDirectorDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface IDepartmentHateoasAssembler extends RepresentationModelAssembler<DepartmentDTO, EntityModel<DepartmentDTO>> {

    EntityModel<DepartmentDTO> toModel(DepartmentDTO dto);

    CollectionModel<EntityModel<DepartmentWithDirectorDTO>> toDiretorCollectionModel(Iterable<DepartmentWithDirectorDTO> dtos);
}
