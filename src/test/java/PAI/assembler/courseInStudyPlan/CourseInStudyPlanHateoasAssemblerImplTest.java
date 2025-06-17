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
        assertTrue(model.getLinks().hasLink("courses-in-study-plan"));

        String href = model.getLink("courses-in-study-plan")
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
        assertTrue(collection.getLinks().hasLink("create-course-in-study-plan"));
    }

    @Test
    void toModel_shouldThrowNullPointerException_whenDtoIsNull() {
        assertThrows(NullPointerException.class, () -> assembler.toModel(null));
    }

    @Test
    void toCollectionModel_shouldThrowNullPointerException_whenDtosIsNull() {
        assertThrows(NullPointerException.class, () -> assembler.toCollectionModel(null));
    }

    @Test
    void toCollectionModel_shouldThrowIllegalStateException_whenToModelThrows() {
        CourseInStudyPlanHateoasAssemblerImpl assemblerWithException = new CourseInStudyPlanHateoasAssemblerImpl() {
            @Override
            public EntityModel<CourseInStudyPlanResponseDTO> toModel(CourseInStudyPlanResponseDTO dto) throws Exception {
                throw new Exception("Forced exception");
            }
        };

        CourseInStudyPlanResponseDTO dto = mock(CourseInStudyPlanResponseDTO.class);
        List<CourseInStudyPlanResponseDTO> dtos = List.of(dto);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> assemblerWithException.toCollectionModel(dtos));

        assertTrue(exception.getMessage().contains("Error converting to EntityModel"));
    }
}