package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodHateoasAssemblerImplTest {

    @Test
    void toModel_ShouldReturnNonNullEntityModel() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        AccessMethodResponseDTO dto = new AccessMethodResponseDTO("123e4567-e89b-12d3-a456-426614174000", "Test Access Method");

        // Act
        EntityModel<AccessMethodResponseDTO> model = assembler.toModel(dto);

        // Assert
        assertNotNull(model);
    }

    @Test
    void toModel_ShouldContainCorrectContent() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        AccessMethodResponseDTO dto = new AccessMethodResponseDTO("123e4567-e89b-12d3-a456-426614174000", "Test Access Method");

        // Act
        EntityModel<AccessMethodResponseDTO> model = assembler.toModel(dto);

        // Assert
        assertEquals(dto, model.getContent());
    }

    @Test
    void toModel_ShouldContainValidSelfLink() {
        // Arrange
        String id = "123e4567-e89b-12d3-a456-426614174000";
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        AccessMethodResponseDTO dto = new AccessMethodResponseDTO(id, "Test Access Method");

        // Act
        EntityModel<AccessMethodResponseDTO> model = assembler.toModel(dto);
        Link selfLink = model.getLink("self").orElse(null);

        // Assert
        assertNotNull(selfLink);
        assertTrue(selfLink.getHref().contains("/access-methods/" + id));
    }


    @Test
    void toModel_ShouldThrowException_WhenDtoIsNull() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toModel(null);
        });

        assertEquals("AccessMethodResponseDTO cannot be null.", exception.getMessage());
    }

}