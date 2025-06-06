package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

    @Test
    void toModel_shouldHaveSelfLink() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        AccessMethodResponseDTO dto = new AccessMethodResponseDTO("id1", "Name1");

        // Act
        EntityModel<AccessMethodResponseDTO> model = assembler.toModel(dto);

        // Assert
        assertTrue(model.hasLink("self"));
        assertEquals("/access-methods/id1", model.getLink("self").get().getHref());
    }

    @Test
    void toModel_shouldHaveCollectionLink() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        AccessMethodResponseDTO dto = new AccessMethodResponseDTO("id1", "Name1");

        // Act
        EntityModel<AccessMethodResponseDTO> model = assembler.toModel(dto);

        // Assert
        assertTrue(model.hasLink("collection"));
        assertEquals("/access-methods", model.getLink("collection").get().getHref());
    }

    @Test
    void toModel_shouldThrowExceptionWhenDtoIsNull() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toModel(null);
        });

        assertEquals("AccessMethodResponseDTO cannot be null.", thrown.getMessage());
    }

    @Test
    void toCollectionModel_shouldReturnNonNullCollectionModel() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        List<AccessMethodResponseDTO> dtos = List.of(
                new AccessMethodResponseDTO("id1", "Name1"),
                new AccessMethodResponseDTO("id2", "Name2")
        );

        // Act
        CollectionModel<EntityModel<AccessMethodResponseDTO>> collectionModel = assembler.toCollectionModel(dtos);

        // Assert
        assertNotNull(collectionModel);
    }

    @Test
    void toCollectionModel_shouldHaveCorrectNumberOfElements() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        List<AccessMethodResponseDTO> dtos = List.of(
                new AccessMethodResponseDTO("id1", "Name1"),
                new AccessMethodResponseDTO("id2", "Name2")
        );

        // Act
        CollectionModel<EntityModel<AccessMethodResponseDTO>> collectionModel = assembler.toCollectionModel(dtos);

        // Assert
        assertEquals(2, collectionModel.getContent().size());
    }

    @Test
    void toCollectionModel_shouldItemsHaveSelfAndCollectionLinks() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        List<AccessMethodResponseDTO> dtos = List.of(
                new AccessMethodResponseDTO("id1", "Name1"),
                new AccessMethodResponseDTO("id2", "Name2")
        );

        // Act
        CollectionModel<EntityModel<AccessMethodResponseDTO>> collectionModel = assembler.toCollectionModel(dtos);

        // Assert
        collectionModel.getContent().forEach(entityModel -> {
            assertTrue(entityModel.hasLink("self"));
            assertTrue(entityModel.hasLink("collection"));
        });
    }

    @Test
    void toCollectionModel_shouldCollectionModelHaveSelfLink() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        List<AccessMethodResponseDTO> dtos = List.of(
                new AccessMethodResponseDTO("id1", "Name1"),
                new AccessMethodResponseDTO("id2", "Name2")
        );

        // Act
        CollectionModel<EntityModel<AccessMethodResponseDTO>> collectionModel = assembler.toCollectionModel(dtos);

        // Assert
        assertTrue(collectionModel.hasLink("self"));
        assertEquals("/access-methods", collectionModel.getLink("self").get().getHref());
    }

    @Test
    void toCollectionModel_emptyListShouldReturnEmptyCollection() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        List<AccessMethodResponseDTO> emptyList = List.of();

        // Act
        CollectionModel<EntityModel<AccessMethodResponseDTO>> collectionModel = assembler.toCollectionModel(emptyList);

        // Assert
        assertNotNull(collectionModel);
        assertTrue(collectionModel.getContent().isEmpty());
    }

    @Test
    void toCollectionModel_emptyListShouldHaveSelfLink() {
        // Arrange
        AccessMethodHateoasAssemblerImpl assembler = new AccessMethodHateoasAssemblerImpl();
        List<AccessMethodResponseDTO> emptyList = List.of();

        // Act
        CollectionModel<EntityModel<AccessMethodResponseDTO>> collectionModel = assembler.toCollectionModel(emptyList);

        // Assert
        assertTrue(collectionModel.hasLink("self"));
        assertEquals("/access-methods", collectionModel.getLink("self").get().getHref());
    }



}