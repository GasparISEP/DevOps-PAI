package PAI.assembler.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;

/**
 * Assembler responsible for converting between REST-level DTOs and application-level command/service DTOs
 * in the context of {@link CourseInStudyPlan} operations.
 *
 * This is used exclusively at the API/controller layer.
 */
public interface ICourseInStudyPlanAssembler {

    /**
     * Converts a {@link CourseInStudyPlanRequestDTO} received from a client into a domain-level command
     * used by the application service layer.
     *
     * @param request the client-facing request DTO
     * @return a {@link CourseInStudyPlanCommand} for internal service use
     * @throws NullPointerException if the requestDTO is null
     */
    CourseInStudyPlanCommand toCommand(CourseInStudyPlanRequestDTO request) throws Exception;

    /**
     * Converts a service-level DTO into a response DTO to be returned to the client.
     *
     * @param courseInStudyPlanServiceDTO the result from the service layer
     * @return a {@link CourseInStudyPlanResponseDTO} suitable for API response
     * @throws NullPointerException if the serviceDTO is null
     */
    CourseInStudyPlanResponseDTO toDTO(CourseInStudyPlanServiceDTO courseInStudyPlanServiceDTO);

    CourseInStudyPlanResponseDTO toDTOfromDomain(CourseInStudyPlan course);
}
