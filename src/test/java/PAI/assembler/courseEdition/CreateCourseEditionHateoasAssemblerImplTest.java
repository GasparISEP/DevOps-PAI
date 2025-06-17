package PAI.assembler.courseEdition;

import static org.assertj.core.api.Assertions.assertThat;

import PAI.assembler.courseEdition.CreateCourseEditionHateoasAssemblerImpl;
import PAI.dto.courseEdition.CourseEditionResponseIDDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateCourseEditionHateoasAssemblerImplTest {

    private CreateCourseEditionHateoasAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new CreateCourseEditionHateoasAssemblerImpl();
    }

    private CourseEditionResponseIDDTO buildDummyDTO() {
        return new CourseEditionResponseIDDTO(
                "MEI",
                UUID.randomUUID(),
                "AP",
                "Advanced Programming",
                LocalDate.now(),
                "PROG2023-COURSE123");
    }

    @Test
    void toModel_shouldCreateEntityModelWithCorrectLinks() {
        // Arrange
        CourseEditionResponseIDDTO dto = buildDummyDTO();

        // Act
        EntityModel<CourseEditionResponseIDDTO> model = assembler.toModel(dto);

        // Assert
        assertThat(model.getContent()).isEqualTo(dto);

        Link selfLink = model.getLink("self").orElse(null);
        Link findAllLink = model.getLink("find-all-course-editions").orElse(null);

        assertThat(selfLink).isNotNull();
        assertThat(findAllLink).isNotNull();

        String href = selfLink.getHref();
        assertThat(href).contains("programmeAcronym=" + dto.programmeAcronym());
        assertThat(href).contains("schoolYearId=" + dto.schoolYearID());
        assertThat(href).contains("courseAcronym=" + dto.courseAcronym());
        assertThat(href).contains("courseName=" + dto.courseName().replace(" ", "%20"));
        assertThat(href).contains("localDate=" + dto.studyPlanImplementationDate());

        assertThat(findAllLink.getHref()).endsWith("/course-editions");
    }

    @Test
    void toCollectionModel_shouldCreateCollectionModelWithSelfLink() {
        // Arrange
        CourseEditionResponseIDDTO dto1 = buildDummyDTO();
        CourseEditionResponseIDDTO dto2 = new CourseEditionResponseIDDTO(
                "MAT",
                UUID.randomUUID(),
                "MTH101",
                "Calculus I",
                LocalDate.now(),
                "PROG2024-COURSE456");

        List<CourseEditionResponseIDDTO> dtos = List.of(dto1, dto2);

        // Act
        CollectionModel<EntityModel<CourseEditionResponseIDDTO>> collectionModel = assembler.toCollectionModel(dtos);

        // Assert
        assertThat(collectionModel.getContent()).hasSize(2);

        Link selfLink = collectionModel.getLink("self").orElse(null);
        assertThat(selfLink).isNotNull();
        assertThat(selfLink.getHref()).endsWith("/course-editions");

        for (EntityModel<CourseEditionResponseIDDTO> model : collectionModel.getContent()) {
            assertThat(model.getLink("self")).isPresent();
        }
    }
}
