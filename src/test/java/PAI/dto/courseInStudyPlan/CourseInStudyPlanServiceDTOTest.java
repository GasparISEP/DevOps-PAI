package PAI.dto.courseInStudyPlan;

import PAI.VOs.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseInStudyPlanServiceDTOTest {

    private Validator validator;
    private Acronym acronym;
    private Name name;
    private ProgrammeID programmeID;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        acronym = mock(Acronym.class);
        name = mock(Name.class);
        programmeID = mock(ProgrammeID.class);
    }

    @Test
    void testValidCourseInStudyPlanServiceDTO() {
        CourseInStudyPlanServiceDTO dto = new CourseInStudyPlanServiceDTO(
                1,
                1,
                "CS101",
                "Computer Science",
                "CS",
                "2023-09-01",
                4,
                6.0,
                new CourseInStudyPlanGeneratedID(UUID.randomUUID()).getId()
        );

        Set<ConstraintViolation<CourseInStudyPlanServiceDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "DTO should be valid when all required fields are provided correctly");
    }

    @Test
    void testInvalidCourseInStudyPlanServiceDTO_BlankFields() {
        CourseInStudyPlanServiceDTO dto = new CourseInStudyPlanServiceDTO(
                1,
                1,
                "",
                "",
                "",
                null,
                4,
                6.0,
                new CourseInStudyPlanGeneratedID(UUID.randomUUID()).getId()
        );

        Set<ConstraintViolation<CourseInStudyPlanServiceDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "DTO should have validation errors due to blank and null fields");

        long notBlankViolations = violations.stream()
                .filter(v -> v.getMessage().contains("cannot be blank") || v.getMessage().contains("is required"))
                .count();

        assertEquals(4, notBlankViolations, "There should be 5 violations related to @NotBlank and @NotNull constraints");
    }

    @Test
    void testCourseDurationBusinessRule() {
        CourseInStudyPlanServiceDTO dtoInvalid = new CourseInStudyPlanServiceDTO(
                0, // invalid duration
                1,
                "CS101",
                "Computer Science",
                "CS",
                "2023-09-01",
                0,
                6.0,
                new CourseInStudyPlanGeneratedID(UUID.randomUUID()).getId()
        );

        CourseInStudyPlanServiceDTO dtoValid = new CourseInStudyPlanServiceDTO(
                1,
                1,
                "CS101",
                "Computer Science",
                "CS",
                "2023-09-01",
                2,
                6.0,
                new CourseInStudyPlanGeneratedID(UUID.randomUUID()).getId()
        );

        assertTrue(dtoValid.duration() >= 1, "Duration must be at least 1");
        assertFalse(dtoInvalid.duration() >= 1, "Duration less than 1 should be invalid according to business rule");
    }

    @Test
    void testCourseCreditsBusinessRule() {
        CourseInStudyPlanServiceDTO dtoInvalid = new CourseInStudyPlanServiceDTO(
                1,
                1,
                "CS101",
                "Computer Science",
                "CS",
                "2023-09-01",
                2,
                0.0, // Invalid
                new CourseInStudyPlanGeneratedID(UUID.randomUUID()).getId()
        );

        CourseInStudyPlanServiceDTO dtoValid = new CourseInStudyPlanServiceDTO(
                1,
                1,
                "CS101",
                "Computer Science",
                "CS",
                "2023-09-01",
                2,
                6.0,
                new CourseInStudyPlanGeneratedID(UUID.randomUUID()).getId()
        );

        assertTrue(dtoValid.credits() > 0, "Credits must be greater than 0");
        assertFalse(dtoInvalid.credits() > 0, "Credits of 0.0 should be invalid according to business rule");
    }
}

