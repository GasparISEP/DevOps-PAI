package PAI.assembler.courseEdition;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        DefineRucResponseDTO dto = mock(DefineRucResponseDTO.class);
        // Act
        EntityModel<DefineRucResponseDTO> result = assembler.toModel(dto);
        String href = result.getLink("define-ruc").orElseThrow().getHref();
        // Assert
        assertTrue(href.contains("/courseeditions/ruc"));
    }

    @Test
    void toCollectionModel_EachEntityShouldHaveRequiredLinks() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        List<CourseEditionResponseDTO> dtos = Arrays.asList(mock(CourseEditionResponseDTO.class));

        // Act
        CollectionModel<EntityModel<CourseEditionResponseDTO>> result = assembler.toCollectionModel(dtos);
        EntityModel<CourseEditionResponseDTO> entityModel = result.getContent().iterator().next();

        // Assert
        assertTrue(entityModel.hasLink("self"));
        assertTrue(entityModel.hasLink("enroll-student"));
        assertTrue(entityModel.hasLink("find-all-course-editions"));
        assertTrue(entityModel.hasLink("approval-rate"));
    }

    @Test
    void toCollectionModel_ShouldReturnCollectionModelWithCorrectNumberOfEntities() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        List<CourseEditionResponseDTO> dtos = Arrays.asList(mock(CourseEditionResponseDTO.class), mock(CourseEditionResponseDTO.class));
        // Act
        CollectionModel<EntityModel<CourseEditionResponseDTO>> result = assembler.toCollectionModel(dtos);
        // Assert
        assertEquals(2, result.getContent().size());
    }

    @Test
    void toCollectionModel_ShouldReturnCollectionModelWithCorrectHref() {
        // Arrange
        CourseEditionHateoasAssembler assembler = new CourseEditionHateoasAssembler();
        List<CourseEditionResponseDTO> dtos = Arrays.asList(mock(CourseEditionResponseDTO.class), mock(CourseEditionResponseDTO.class));
        // Act
        CollectionModel<EntityModel<CourseEditionResponseDTO>> result = assembler.toCollectionModel(dtos);
        EntityModel<CourseEditionResponseDTO> entityModel = result.getContent().iterator().next();
        // Assert
        assertTrue(entityModel.getLink("self").orElseThrow().getHref().contains("/courseeditions"));
        assertTrue(entityModel.getLink("enroll-student").orElseThrow().getHref().contains("/courseeditions/students/0/courses-edition-enrolments"));
        assertTrue(entityModel.getLink("find-all-course-editions").orElseThrow().getHref().contains("/courseeditions"));
        assertTrue(entityModel.getLink("approval-rate").orElseThrow().getHref().contains("/approval-rate"));
    }

}
