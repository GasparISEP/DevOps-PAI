package PAI.assembler.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanHateoasAssemblerImplTest {

    private final CourseInStudyPlanHateoasAssemblerImpl assembler = new CourseInStudyPlanHateoasAssemblerImpl();

    @Test
    void toModel_shouldCreateEntityModelWithLink() throws Exception {
        // Arrange
        CourseInStudyPlanResponseDTO dto = mock(CourseInStudyPlanResponseDTO.class);
        when(dto.programmeAcronym()).thenReturn("LEI");

        // Act
        EntityModel<CourseInStudyPlanResponseDTO> model = assembler.toModel(dto);

        // Assert
        assertNotNull(model);
        assertEquals(dto, model.getContent());
        assertTrue(model.getLinks().hasLink("courses-in-programme"));

        String href = model.getLink("courses-in-programme")
                .orElseThrow(() -> new AssertionError("Link 'courses-in-programme' not found"))
                .getHref();

        assertTrue(href.contains("LEI"));    }

    @Test
    void toCollectionModel_shouldCreateCollectionWithLinks() throws Exception {
        // Arrange
        CourseInStudyPlanResponseDTO dto1 = mock(CourseInStudyPlanResponseDTO.class);
        when(dto1.programmeAcronym()).thenReturn("LEI");

        CourseInStudyPlanResponseDTO dto2 = mock(CourseInStudyPlanResponseDTO.class);
        when(dto2.programmeAcronym()).thenReturn("LEI");

        List<CourseInStudyPlanResponseDTO> dtos = List.of(dto1, dto2);

        // Act
        CollectionModel<EntityModel<CourseInStudyPlanResponseDTO>> collection = assembler.toCollectionModel(dtos);

        // Assert
        assertNotNull(collection);
        assertEquals(2, collection.getContent().size());
        assertTrue(collection.getLinks().hasLink("create-course"));
    }
}