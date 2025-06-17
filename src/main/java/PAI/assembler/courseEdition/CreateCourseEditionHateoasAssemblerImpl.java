package PAI.assembler.courseEdition;

import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CreateCourseEditionHateoasAssemblerImpl
        implements ICreateCourseEditionHateoasAssembler {

    @Override
    public EntityModel<CourseEditionResponseDTO> toModel(CourseEditionResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(CourseEditionRestController.class)
                        .getCourseEditionById(dto.courseEditionGeneratedID()))
                        .withSelfRel(),

                linkTo(methodOn(CourseEditionRestController.class)
                        .findAllCourseEditions())
                        .withRel("find-all-course-editions")
        );
    }

    @Override
    public CollectionModel<EntityModel<CourseEditionResponseDTO>> toCollectionModel(Iterable<? extends CourseEditionResponseDTO> dtos) {
        List<EntityModel<CourseEditionResponseDTO>> models = new ArrayList<>();
        dtos.forEach(dto -> models.add(toModel(dto)));

        return CollectionModel.of(models,
                linkTo(methodOn(CourseEditionRestController.class)
                        .findAllCourseEditions())
                        .withSelfRel()
        );
    }

}
