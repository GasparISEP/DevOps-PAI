package PAI.assembler.courseInStudyPlan;

import PAI.controllerRest.CourseInStudyPlanRestController;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CourseInStudyPlanHateoasAssemblerImpl implements ICourseInStudyPlanHateoasAssembler {

    @Override
    public EntityModel<CourseInStudyPlanResponseDTO> toModel(CourseInStudyPlanResponseDTO dto) throws Exception {
        return EntityModel.of(dto,
                linkTo(methodOn(CourseInStudyPlanRestController.class)
                        .getCoursesInStudyPlanByProgrammeID(dto.programmeAcronym()))
                        .withRel("courses-in-study-plan")
        );
    }

    @Override
    public CollectionModel<EntityModel<CourseInStudyPlanResponseDTO>> toCollectionModel(Iterable<CourseInStudyPlanResponseDTO> dtos) throws Exception {
        List<EntityModel<CourseInStudyPlanResponseDTO>> entityModels = StreamSupport.stream(dtos.spliterator(), false)
                .map(dto -> {
                    try {
                        return toModel(dto);
                    } catch (Exception e) {
                        throw new RuntimeException("Error converting to EntityModel", e);
                    }
                })
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(CourseInStudyPlanRestController.class).create(null))
                        .withRel("create-course-in-study-plan")
        );
    }
}