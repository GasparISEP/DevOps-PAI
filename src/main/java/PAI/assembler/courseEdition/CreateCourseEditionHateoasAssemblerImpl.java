package PAI.assembler.courseEdition;

import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static PAI.utils.ValidationUtils.validateNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CreateCourseEditionHateoasAssemblerImpl
        implements ICreateCourseEditionHateoasAssembler {

    @Override
    public EntityModel<CourseEditionResponseDTO> toModel(CourseEditionResponseDTO dto) {

        validateNotNull(dto, "CourseEditionResponseDTO");

        EntityModel<CourseEditionResponseDTO> courseEditionResponseDTOEntityModel;
        List<Link> iterable = new ArrayList<>();

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .getCourseEditionById(dto.courseEditionGeneratedID()))
                    .withSelfRel();

            iterable.add(link);

        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .findAllCourseEditions())
                    .withRel("find-all-course-editions");

            iterable.add(link);

        } catch (Exception ignored) {}

        courseEditionResponseDTOEntityModel = EntityModel.of(dto, iterable);

        return courseEditionResponseDTOEntityModel;
    }

    @Override
    public CollectionModel<EntityModel<CourseEditionResponseDTO>> toCollectionModel(Iterable<? extends CourseEditionResponseDTO> dtos) {

        validateNotNull(dtos, "CourseEditionResponseDTO list");

        List<EntityModel<CourseEditionResponseDTO>> models = new ArrayList<>();
        dtos.forEach(dto -> models.add(toModel(dto)));

        return CollectionModel.of(models,
                linkTo(methodOn(CourseEditionRestController.class)
                        .findAllCourseEditions())
                        .withSelfRel()
        );
    }

}
