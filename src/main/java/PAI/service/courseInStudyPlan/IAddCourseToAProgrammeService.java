package PAI.service.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.exception.*;

public interface IAddCourseToAProgrammeService {

    /**
     * Adds a course to a programme's current study plan.
     *
     * @param command validated command object
     * @return DTO of created course in study plan
     * @throws IllegalArgumentException if command is null
     * @throws NotFoundException if no study plan is found for the given programme
     * @throws AlreadyExistsException if the course already exists in the study plan
     * @throws CreditsExceededException if study plan ECTS limit is exceeded
     */
    CourseInStudyPlanServiceDTO addCourseToAProgramme(CourseInStudyPlanCommand command) throws Exception;
}