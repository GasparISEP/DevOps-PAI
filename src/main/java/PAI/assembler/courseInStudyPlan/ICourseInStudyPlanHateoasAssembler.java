package PAI.assembler.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

/**
 * Assembler responsible for creating HATEOAS-compliant representations of {@link CourseInStudyPlanResponseDTO}
 * objects. Converts DTOs into {@link EntityModel} and {@link CollectionModel} instances enriched with links
 * pointing to related REST resources.
 *
 * This enables REST clients to navigate the API dynamically through provided hyperlinks.
 */
public interface ICourseInStudyPlanHateoasAssembler {

    /**
     * Converts a single {@link CourseInStudyPlanResponseDTO} into an {@link EntityModel} with HATEOAS links.
     *
     * @param dto the response DTO to wrap, must not be null
     * @return an {@link EntityModel} wrapping the DTO enriched with relevant links
     * @throws Exception if building the links fails
     */
    EntityModel<CourseInStudyPlanResponseDTO> toModel(CourseInStudyPlanResponseDTO dto) throws Exception;

    /**
     * Converts an iterable of {@link CourseInStudyPlanResponseDTO} into a {@link CollectionModel} of
     * {@link EntityModel} instances, each enriched with HATEOAS links. Also adds a top-level link
     * to the create operation.
     *
     * @param dtos iterable collection of response DTOs, must not be null
     * @return a {@link CollectionModel} containing {@link EntityModel} instances with HATEOAS links
     * @throws Exception if building any of the links fails
     */
    CollectionModel<EntityModel<CourseInStudyPlanResponseDTO>> toCollectionModel(Iterable<CourseInStudyPlanResponseDTO> dtos) throws Exception;
}
