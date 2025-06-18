package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionHATEOASAssemblerTest {

    private final CourseEditionHATEOASAssembler assembler = new CourseEditionHATEOASAssembler();

    private CourseEditionResponseDTO createSampleDTO() {
        return new CourseEditionResponseDTO(
                UUID.randomUUID(),
                "CS",
                UUID.randomUUID(),
                "CS101",
                "Intro to CS",
                LocalDate.of(2023, 9, 1),
                null,
                null
        );
    }

    @Test
    void toModel_shouldContainSelfLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("self").isPresent(), "'self' link should be present");
        assertEquals(dto, model.getContent());
    }

    @Test
    void toModel_shouldContainCreateCourseEditionLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("create-course-edition").isPresent());
    }

    @Test
    void toModel_shouldContainAverageGradeLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("average-grade").isPresent());
    }

    @Test
    void toModel_shouldContainNumberOfStudentsLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("Number-of-students").isPresent());
    }

    @Test
    void toModel_shouldContainApprovalRateLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("approval-rate").isPresent());
    }

    @Test
    void toModel_shouldContainEnrolStudentInCourseEditionLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("enrol-student-in-course-edition").isPresent());
    }

    @Test
    void toModel_shouldContainRemoveStudentEnrolmentLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("remove-student-enrolment").isPresent());
    }

    @Test
    void toModel_shouldContainDefineRucLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("define-ruc").isPresent());
    }

    @Test
    void toModel_shouldContainGradeStudentLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("grade-student").isPresent());
    }

    @Test
    void toModel_shouldContainSchoolYearLink() {
        CourseEditionResponseDTO dto = createSampleDTO();
        EntityModel<CourseEditionResponseDTO> model = assembler.toModel(dto);

        assertTrue(model.getLink("school-year").isPresent());
    }
}
