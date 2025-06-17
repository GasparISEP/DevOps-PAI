package PAI.assembler.courseEdition;
import PAI.dto.courseEdition.CourseEditionResponseIDDTO;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionRUCHateoasAssemblerTest {
    @Test
    void shouldReturnNonNullEntityModel() {
        // Arrange
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();
        DefineRucResponseDTO dto = mock(DefineRucResponseDTO.class);
        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);
        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldContainGivenDtoAsContent() {
        // Arrange
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();
        DefineRucResponseDTO dto = mock(DefineRucResponseDTO.class);
        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);
        // Assert
        assertEquals(dto, result.getContent());
    }

    @Test
    void shouldHaveExpectedLinks() {
        // Arrange
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();
        UUID courseEditionId = UUID.randomUUID();
        String teacherID = "ABC";
        DefineRucResponseDTO dto = new DefineRucResponseDTO(teacherID, courseEditionId);

        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);

        // Assert
        assertTrue(result.hasLink("get-courseEdition-ruc"), "Link 'get-courseEdition-ruc' should be present");
        assertTrue(result.hasLink("find-all-course-editions"), "Link 'find-all-course-editions' should be present");
        assertTrue(result.hasLink("details"), "Link 'details' should be present");
        assertTrue(result.hasLink("collection"), "Link 'collection' should be present");
    }

    @Test
    void getCourseEditionRucLinkShouldContainCorrectHref() {
        // Arrange
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();
        UUID courseEditionId = UUID.randomUUID();
        String teacherID = "ABC";
        DefineRucResponseDTO dto = new DefineRucResponseDTO(teacherID, courseEditionId);

        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);

        // Assert
        assertTrue(result.hasLink("get-courseEdition-ruc"), "Link 'get-courseEdition-ruc' should be present");
        String href = result.getLink("get-courseEdition-ruc").orElseThrow().getHref();
        assertTrue(href.endsWith("/by-id/" + courseEditionId),
                "HREF should end with '/by-id/{id}'");

    }

    @Test
    void toModel_shouldThrowRuntimeException_whenExceptionOccurs() {
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();

        DefineRucResponseDTO dto = mock(DefineRucResponseDTO.class);
        when(dto.courseEditionGeneratedID()).thenThrow(new RuntimeException("Forced exception"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            assembler.toModel(dto);
        });

        assertEquals("Forced exception", exception.getCause().getMessage());

    }


    @Test
    void toCollectionModel_EachEntityShouldHaveRequiredLinks() {
        // Arrange
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();
        CourseEditionResponseIDDTO dto = mock(CourseEditionResponseIDDTO.class);
        when(dto.programmeAcronym()).thenReturn("TEST");
        when(dto.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto.courseAcronym()).thenReturn("COURSE");
        when(dto.studyPlanImplementationDate()).thenReturn(LocalDate.now());

        List<CourseEditionResponseIDDTO> dtos = Arrays.asList(dto);

        // Act
        CollectionModel<EntityModel<CourseEditionResponseIDDTO>> result = assembler.toCollectionModel(dtos);
        EntityModel<CourseEditionResponseIDDTO> entityModel = result.getContent().iterator().next();

        // Assert
        assertTrue(entityModel.hasLink("approval-rate"));
    }

    @Test
    void toCollectionModel_ShouldReturnCollectionModelWithCorrectNumberOfEntities() {
        // Arrange
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();
        CourseEditionResponseIDDTO dto1 = mock(CourseEditionResponseIDDTO.class);
        CourseEditionResponseIDDTO dto2 = mock(CourseEditionResponseIDDTO.class);

        when(dto1.programmeAcronym()).thenReturn("TEST");
        when(dto1.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto1.courseAcronym()).thenReturn("COURSE");
        when(dto1.studyPlanImplementationDate()).thenReturn(LocalDate.now());

        when(dto2.programmeAcronym()).thenReturn("TEST");
        when(dto2.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto2.courseAcronym()).thenReturn("COURSE");
        when(dto2.studyPlanImplementationDate()).thenReturn(LocalDate.now());

        List<CourseEditionResponseIDDTO> dtos = Arrays.asList(dto1, dto2);

        // Act
        CollectionModel<EntityModel<CourseEditionResponseIDDTO>> result = assembler.toCollectionModel(dtos);

        // Assert
        assertEquals(2, result.getContent().size());
    }

    @Test
    void toCollectionModel_ShouldReturnCollectionModelWithCorrectHref() {
        // Arrange
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();
        CourseEditionResponseIDDTO dto = mock(CourseEditionResponseIDDTO.class);

        when(dto.programmeAcronym()).thenReturn("TEST");
        when(dto.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto.courseAcronym()).thenReturn("COURSE");
        when(dto.studyPlanImplementationDate()).thenReturn(LocalDate.now());

        List<CourseEditionResponseIDDTO> dtos = Arrays.asList(dto);

        // Act
        CollectionModel<EntityModel<CourseEditionResponseIDDTO>> result = assembler.toCollectionModel(dtos);
        EntityModel<CourseEditionResponseIDDTO> entityModel = result.getContent().iterator().next();

        // Assert
        assertTrue(entityModel.getLink("approval-rate").orElseThrow().getHref().contains("/approval-rate"));
    }
    @Test
    void toCollectionModel_ShouldReturnCollectionModelNotNull() {
        // Arrange
        CourseEditionRUCHateoasAssembler assembler = new CourseEditionRUCHateoasAssembler();
        CourseEditionResponseIDDTO dto1 = mock(CourseEditionResponseIDDTO.class);
        CourseEditionResponseIDDTO dto2 = mock(CourseEditionResponseIDDTO.class);
        
        when(dto1.programmeAcronym()).thenReturn("TEST");
        when(dto1.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto1.courseAcronym()).thenReturn("COURSE");
        when(dto1.studyPlanImplementationDate()).thenReturn(LocalDate.now());
        
        when(dto2.programmeAcronym()).thenReturn("TEST");
        when(dto2.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto2.courseAcronym()).thenReturn("COURSE");
        when(dto2.studyPlanImplementationDate()).thenReturn(LocalDate.now());
        
        List<CourseEditionResponseIDDTO> dtos = Arrays.asList(dto1, dto2);
        
        // Act
        CollectionModel<EntityModel<CourseEditionResponseIDDTO>> result = assembler.toCollectionModel(dtos);
        
        // Assert
        assertNotNull(result);
    }

}
