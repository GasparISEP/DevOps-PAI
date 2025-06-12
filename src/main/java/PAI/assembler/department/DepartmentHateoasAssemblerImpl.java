package PAI.assembler.department;

import PAI.controllerRest.DepartmentRestController;
import PAI.controllerRest.TeacherRestController;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.DepartmentWithDirectorDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public CollectionModel<EntityModel<DepartmentWithDirectorDTO>> toDiretorCollectionModel(Iterable<DepartmentWithDirectorDTO> dtos) {

        List<EntityModel<DepartmentWithDirectorDTO>> entityModels = new ArrayList<>();

        for (DepartmentWithDirectorDTO dto : dtos) {
            entityModels.add(toEntityModel(dto));
        }

        CollectionModel<EntityModel<DepartmentWithDirectorDTO>> collectionModel = CollectionModel.of(entityModels);

        collectionModel.add(linkTo(methodOn(DepartmentRestController.class)
                .getAllDepartments())
                .withSelfRel());

        return collectionModel;
    }

    private EntityModel<DepartmentWithDirectorDTO> toEntityModel(DepartmentWithDirectorDTO dto) {
        EntityModel<DepartmentWithDirectorDTO> model = EntityModel.of(dto);

        model.add(linkTo(methodOn(DepartmentRestController.class).getDepartmentById(dto.id()))
                .withSelfRel());

        if (hasValidDirector(dto)) {
            model.add(linkTo(methodOn(TeacherRestController.class).getTeacherById(dto.teacherID()))
                    .withRel("director"));
        }

        return model;
    }

    private boolean hasValidDirector(DepartmentWithDirectorDTO dto) {
        return dto.teacherID() != null && !"No Director Assigned".equals(dto.teacherID());
    }
}

