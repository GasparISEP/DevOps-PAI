package PAI.assembler.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ICourseInStudyPlanHateoasAssembler {

    EntityModel<CourseInStudyPlanResponseDTO> toModel(CourseInStudyPlanResponseDTO dto);

    CollectionModel<EntityModel<CourseInStudyPlanResponseDTO>> toCollectionModel(Iterable<CourseInStudyPlanResponseDTO> dtos);
}
