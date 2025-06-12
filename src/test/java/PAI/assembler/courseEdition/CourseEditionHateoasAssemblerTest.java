package PAI.assembler.courseEdition;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionHateoasAssemblerTest {
    @Test
    void shouldReturnNonNullEntityModel() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        DefineRucResponseDTO dto = mock(DefineRucResponseDTO.class);
        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);
        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldContainGivenDtoAsContent() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        DefineRucResponseDTO dto = mock(DefineRucResponseDTO.class);
        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);
        // Assert
        assertEquals(dto, result.getContent());
    }

    @Test
    void shouldHaveDefineRucLink() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        DefineRucResponseDTO dto = mock(DefineRucResponseDTO.class);
        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);
        // Assert
        assertTrue(result.hasLink("define-ruc"));
    }

    @Test
    void defineRucLinkShouldContainCorrectHref() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        UUID courseEditionId = UUID.randomUUID();
        String teacherID = "ABC";
        DefineRucResponseDTO dto = new DefineRucResponseDTO(teacherID, courseEditionId);

        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);

        // Assert
        assertTrue(result.hasLink("define-ruc"), "Link with rel 'define-ruc' should be present");

        String defineRucHref = result.getLink("define-ruc").orElseThrow().getHref();
        assertTrue(defineRucHref.endsWith("/courseeditions/" + courseEditionId + "/ruc"),
                "HREF should end with '/courseeditions/{id}/ruc'");
    }

    @Test
    void toCollectionModel_EachEntityShouldHaveRequiredLinks() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        CourseEditionResponseDTO dto = mock(CourseEditionResponseDTO.class);
        when(dto.programmeAcronym()).thenReturn("TEST");
        when(dto.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto.courseAcronym()).thenReturn("COURSE");
        when(dto.studyPlanImplementationDate()).thenReturn(LocalDate.now());
        
        List<CourseEditionResponseDTO> dtos = Arrays.asList(dto);

        // Act
        CollectionModel<EntityModel<CourseEditionResponseDTO>> result = assembler.toCollectionModel(dtos);
        EntityModel<CourseEditionResponseDTO> entityModel = result.getContent().iterator().next();

        // Assert
        assertTrue(entityModel.hasLink("approval-rate"));
    }

    @Test
    void toCollectionModel_ShouldReturnCollectionModelWithCorrectNumberOfEntities() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        CourseEditionResponseDTO dto1 = mock(CourseEditionResponseDTO.class);
        CourseEditionResponseDTO dto2 = mock(CourseEditionResponseDTO.class);
        
        when(dto1.programmeAcronym()).thenReturn("TEST");
        when(dto1.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto1.courseAcronym()).thenReturn("COURSE");
        when(dto1.studyPlanImplementationDate()).thenReturn(LocalDate.now());
        
        when(dto2.programmeAcronym()).thenReturn("TEST");
        when(dto2.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto2.courseAcronym()).thenReturn("COURSE");
        when(dto2.studyPlanImplementationDate()).thenReturn(LocalDate.now());
        
        List<CourseEditionResponseDTO> dtos = Arrays.asList(dto1, dto2);
        
        // Act
        CollectionModel<EntityModel<CourseEditionResponseDTO>> result = assembler.toCollectionModel(dtos);
        
        // Assert
        assertEquals(2, result.getContent().size());
    }

    @Test
    void toCollectionModel_ShouldReturnCollectionModelWithCorrectHref() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        CourseEditionResponseDTO dto = mock(CourseEditionResponseDTO.class);
        
        when(dto.programmeAcronym()).thenReturn("TEST");
        when(dto.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto.courseAcronym()).thenReturn("COURSE");
        when(dto.studyPlanImplementationDate()).thenReturn(LocalDate.now());
        
        List<CourseEditionResponseDTO> dtos = Arrays.asList(dto);
        
        // Act
        CollectionModel<EntityModel<CourseEditionResponseDTO>> result = assembler.toCollectionModel(dtos);
        EntityModel<CourseEditionResponseDTO> entityModel = result.getContent().iterator().next();
        
        // Assert
        assertTrue(entityModel.getLink("approval-rate").orElseThrow().getHref().contains("/approval-rate"));
    }

    @Test
    void toCollectionModel_ShouldReturnCollectionModelNotNull() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        CourseEditionResponseDTO dto1 = mock(CourseEditionResponseDTO.class);
        CourseEditionResponseDTO dto2 = mock(CourseEditionResponseDTO.class);
        
        when(dto1.programmeAcronym()).thenReturn("TEST");
        when(dto1.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto1.courseAcronym()).thenReturn("COURSE");
        when(dto1.studyPlanImplementationDate()).thenReturn(LocalDate.now());
        
        when(dto2.programmeAcronym()).thenReturn("TEST");
        when(dto2.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto2.courseAcronym()).thenReturn("COURSE");
        when(dto2.studyPlanImplementationDate()).thenReturn(LocalDate.now());
        
        List<CourseEditionResponseDTO> dtos = Arrays.asList(dto1, dto2);
        
        // Act
        CollectionModel<EntityModel<CourseEditionResponseDTO>> result = assembler.toCollectionModel(dtos);
        
        // Assert
        assertNotNull(result);
    }
}
