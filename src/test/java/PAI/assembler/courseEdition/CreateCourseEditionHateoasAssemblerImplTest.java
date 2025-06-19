package PAI.assembler.courseEdition;

import static org.assertj.core.api.Assertions.assertThat;

import PAI.controllerRest.CourseEditionRestController;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.time.LocalDate;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateCourseEditionHateoasAssemblerImplTest {

    private CreateCourseEditionHateoasAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new CreateCourseEditionHateoasAssemblerImpl();
    }

    UUID generatedId = UUID.randomUUID();

    private CourseEditionResponseDTO buildDummyDTO() {
        return new CourseEditionResponseDTO(
                generatedId,
                "MEI",
                UUID.randomUUID(),
                "AP",
                "Advanced Programming",
                LocalDate.now(),
                "PROG2023-COURSE123",
                "AAA");
    }

    @Test
    void toModel_ShouldContainDtoAndCorrectLinks() throws Exception {
        // Arrange
        UUID courseEditionId = UUID.randomUUID();
        UUID schoolYearId = UUID.randomUUID();
        CourseEditionResponseDTO dto = new CourseEditionResponseDTO(
                courseEditionId,
                "PROG",
                schoolYearId,
                "CS101",
                "Computer Science",
                LocalDate.of(2024, 1, 1),
                "ED2024",
                "TEACH123"
        );

        // Act
        EntityModel<CourseEditionResponseDTO> entityModel = assembler.toModel(dto);

        // Assert
        assertThat(entityModel.getContent()).isEqualTo(dto);

        assertThat(entityModel.getLink("self")).isPresent();
        String expectedSelfHref = linkTo(methodOn(CourseEditionRestController.class)
                .getCourseEditionById(courseEditionId))
                .toUri()
                .toString();
        assertThat(entityModel.getLink("self").get().getHref()).isEqualTo(expectedSelfHref);


        assertThat(entityModel.getLink("find-all-course-editions")).isPresent();
        String expectedFindAllHref = linkTo(methodOn(CourseEditionRestController.class)
                .findAllCourseEditions())
                .toUri()
                .toString();
        assertThat(entityModel.getLink("find-all-course-editions").get().getHref()).isEqualTo(expectedFindAllHref);
    }

    @Test
    void toModel_ShouldThrowExceptionWhenDtoIsNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toModel(null);
        });

        assertThat(exception.getMessage()).isEqualTo("CourseEditionResponseDTO cannot be null.");
    }


    @Test
    void toCollectionModel_shouldCreateCollectionModelWithSelfLink() {
        // Arrange
        CourseEditionResponseDTO dto1 = buildDummyDTO();
        CourseEditionResponseDTO dto2 = new CourseEditionResponseDTO(
                generatedId,
                "MAT",
                UUID.randomUUID(),
                "MTH101",
                "Calculus I",
                LocalDate.now(),
                "PROG2024-COURSE456",
                "BBB");

        List<CourseEditionResponseDTO> dtos = List.of(dto1, dto2);

        // Act
        CollectionModel<EntityModel<CourseEditionResponseDTO>> collectionModel =
                assembler.toCollectionModel(dtos);

        // Assert
        assertThat(collectionModel.getContent()).hasSize(2);

        Link selfLink = collectionModel.getLink("self").orElse(null);
        assertThat(selfLink).isNotNull();
        assertThat(selfLink.getHref()).endsWith("/course-editions");

        for (EntityModel<CourseEditionResponseDTO> model : collectionModel.getContent()) {
            assertThat(model.getLink("self")).isPresent();
            assertThat(model.getLink("find-all-course-editions")).isPresent();
        }
    }

    @Test
    void toCollectionModel_ShouldThrowExceptionWhenDtosIsNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toCollectionModel(null);
        });

        assertThat(exception.getMessage()).isEqualTo("CourseEditionResponseDTO list cannot be null.");
    }

}
