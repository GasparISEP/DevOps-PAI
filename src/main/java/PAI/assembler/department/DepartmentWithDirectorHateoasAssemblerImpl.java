package PAI.assembler.department;
import PAI.controllerRest.DepartmentRestController;
import PAI.dto.department.DepartmentWithDirectorDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DepartmentWithDirectorHateoasAssemblerImpl implements IDepartmentWithDirectorHateaosAssembler {

    @Override
    public EntityModel<DepartmentWithDirectorDTO> toModel(DepartmentWithDirectorDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(DepartmentRestController.class)
                        .getDepartmentById(dto.id()))
                        .withRel("self"),

                linkTo(methodOn(DepartmentRestController.class)
                        .getAllDepartments())
                        .withRel("all-departments"),

                linkTo(methodOn(DepartmentRestController.class)
                        .updateDepartmentDirector(dto.id(), null)) // null só para criar o link
                        .withRel("update-director")
                        .withType("PUT")
        );
    }
}
