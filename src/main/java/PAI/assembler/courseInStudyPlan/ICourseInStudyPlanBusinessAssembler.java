package PAI.assembler.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;

/**
 * Assembler responsible for converting the domain entity {@link CourseInStudyPlan} into
 * a service-level DTO {@link CourseInStudyPlanServiceDTO} used within the application layer.
 *
 * This class operates at the service layer, facilitating the transfer of domain data
 * into simpler objects tailored for internal business logic and manipulation.
 */
public interface ICourseInStudyPlanBusinessAssembler {

    /**
     * Converts a {@link CourseInStudyPlan} domain entity into a {@link CourseInStudyPlanServiceDTO}.
     *
     * @param courseInStudyPlan the domain entity to convert, must not be null
     * @return a {@link CourseInStudyPlanServiceDTO} containing data extracted from the entity for service layer use
     * @throws NullPointerException if the provided entity is null
     */
    CourseInStudyPlanServiceDTO toDTO(CourseInStudyPlan courseInStudyPlan);

}
