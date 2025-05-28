package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodHateoasAssemblerImplTest {

    @Test
    void toModel_ShouldReturnEntityModelWithSelfLink() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        String id = "123e4567-e89b-12d3-a456-426614174000";
        String name = "Test Access Method";

        AccessMethodResponseDTO dto = new AccessMethodResponseDTO(id, name);

        // Act
        EntityModel<AccessMethodResponseDTO> model = assembler.toModel(dto);

        // Assert
        assertNotNull(model);
        assertEquals(dto, model.getContent());

        Link selfLink = model.getLink("self").orElse(null);
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