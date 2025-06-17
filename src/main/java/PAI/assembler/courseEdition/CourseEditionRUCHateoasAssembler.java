package PAI.assembler.courseEdition;
import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.courseEdition.CourseEditionResponseIDDTO;
import PAI.dto.courseEdition.DefineRucRequestDTO;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;

@Component
public class CourseEditionRUCHateoasAssembler implements RepresentationModelAssembler<DefineRucResponseDTO, EntityModel<DefineRucResponseDTO>>, ICourseEditionRUCHateoasAssembler {

    @Override
    public EntityModel<DefineRucResponseDTO> toModel(DefineRucResponseDTO dto) {
        try {

            return EntityModel.of(dto,
                    linkTo(methodOn(CourseEditionRestController.class)
                            .getCourseEditionById(dto.courseEditionGeneratedID()))
                            .withRel("get-courseEdition-ruc"),
                    linkTo(methodOn(CourseEditionRestController.class)
                            .findAllCourseEditions())
                            .withRel("find-all-course-editions"),

                    // UI links for frontend (React)
                    Link.of("/course-editions/by-id/" + dto.courseEditionGeneratedID()).withRel("details"),
                    Link.of("/course-editions").withRel("collection")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CollectionModel<EntityModel<CourseEditionResponseIDDTO>> toCollectionModel(List<CourseEditionResponseIDDTO> courseEditionResponseDTOs) {
        List<EntityModel<CourseEditionResponseIDDTO>> listOfCourseEditionResponseDtosWithHypermedia = courseEditionResponseDTOs.stream()
            .map(dto -> EntityModel.of(dto,
                linkTo(methodOn(CourseEditionRestController.class)
                            .getCourseEditionApprovalRate(dto.programmeAcronym(), dto.schoolYearID().toString(), dto.courseAcronym(), dto.courseName(), dto.studyPlanImplementationDate().toString()))
                            .withRel("approval-rate")
            ))
            .collect(Collectors.toList());

        return CollectionModel.of(listOfCourseEditionResponseDtosWithHypermedia);
    }
}

