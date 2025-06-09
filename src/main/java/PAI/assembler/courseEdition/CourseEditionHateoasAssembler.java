package PAI.assembler.courseEdition;
import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;

@Component
public class CourseEditionHateoasAssembler implements RepresentationModelAssembler<DefineRucResponseDTO, EntityModel<DefineRucResponseDTO>>, ICourseEditionHateoasAssembler {

    @Override
    public EntityModel<DefineRucResponseDTO> toModel(DefineRucResponseDTO dto) {
        try {
            return EntityModel.of(dto,
                    linkTo(methodOn(CourseEditionRestController.class)
                            .defineRucForCourseEdition(null))
                            .withRel("define-ruc")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CollectionModel<EntityModel<CourseEditionResponseDTO>> toCollectionModel(List<CourseEditionResponseDTO> courseEditionResponseDTOs) {
        List<EntityModel<CourseEditionResponseDTO>> listOfCourseEditionResponseDtosWithHypermedia = courseEditionResponseDTOs.stream()
            .map(dto -> EntityModel.of(dto,
                linkTo(methodOn(CourseEditionRestController.class)
                    .getCourseEditionsByProgrammeEditionID(null))
                    .withSelfRel(),
                linkTo(methodOn(CourseEditionRestController.class)
                    .enrolStudentInCourseEdition(0, null))
                    .withRel("enroll-student"),
                linkTo(methodOn(CourseEditionRestController.class)
                    .findAllCourseEditions())
                    .withRel("find-all-course-editions"))
            )
            .collect(Collectors.toList());

        return CollectionModel.of(listOfCourseEditionResponseDtosWithHypermedia);
    }
}

