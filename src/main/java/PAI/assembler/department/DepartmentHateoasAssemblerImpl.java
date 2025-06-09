package PAI.assembler.department;

import PAI.controllerRest.DepartmentRestController;
import PAI.dto.department.DepartmentDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DepartmentHateoasAssemblerImpl implements IDepartmentHateoasAssembler {

    @Override
    public EntityModel<DepartmentDTO> toModel(DepartmentDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(DepartmentRestController.class)
                        .getDepartmentById(dto.id()))
                        .withSelfRel(),

                linkTo(methodOn(DepartmentRestController.class)
                        .getAllDepartments())
                        .withRel("all")
        );
    }
}

