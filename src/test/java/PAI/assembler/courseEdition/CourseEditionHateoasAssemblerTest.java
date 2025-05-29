package PAI.assembler.courseEdition;
import PAI.dto.courseEdition.DefineRucResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
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
}
