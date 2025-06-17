package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.CourseEditionResponseIDDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.DummyInvocationUtils;
import org.springframework.stereotype.Component;
import PAI.controllerRest.CourseEditionRestController;

import org.springframework.hateoas.server.RepresentationModelAssembler;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CreateCourseEditionHateoasAssemblerImpl implements RepresentationModelAssembler<CourseEditionResponseIDDTO, EntityModel<CourseEditionResponseIDDTO>>, ICreateCourseEditionHateoasAssembler {

    @Override
    public EntityModel<CourseEditionResponseIDDTO> toModel(CourseEditionResponseIDDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(CourseEditionRestController.class)
                        .getCourseEditionById(
                                dto.programmeAcronym(),
                                dto.schoolYearID().toString(),
                                dto.courseAcronym(),
                                dto.courseName(),
                                dto.studyPlanImplementationDate().toString()))
                        .withSelfRel(),

                linkTo(DummyInvocationUtils.methodOn(CourseEditionRestController.class)
                        .findAllCourseEditions())
                        .withRel("find-all-course-editions")
        );
    }

    @Override
    public CollectionModel<EntityModel<CourseEditionResponseIDDTO>> toCollectionModel(List<CourseEditionResponseIDDTO> dtos) {
        List<EntityModel<CourseEditionResponseIDDTO>> models = dtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(models,
                linkTo(DummyInvocationUtils.methodOn(CourseEditionRestController.class).findAllCourseEditions())
                        .withSelfRel()
        );
    }

}

