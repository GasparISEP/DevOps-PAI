package PAI.dto.courseInStudyPlan;

import PAI.VOs.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseInStudyPlanResponseDTOTest {

    @Test
    void testValidCourseInStudyPlanResponseDTO() {

        //arrange
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act
        CourseInStudyPlanResponseDTO dto = new CourseInStudyPlanResponseDTO(
                1,
                1,
                "CS101",
                "Computer Science",
                "CS",
                "Informa",
                "2023-09-01",
                4,
                6.0,
                new CourseID(acronym, name),
                new StudyPlanID(programmeID, Date.now())
        );

        // assert
        Set<ConstraintViolation<CourseInStudyPlanResponseDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "DTO should be valid");
    }

    @Test
    void testInvalidCourseInStudyPlanResponseDTO_BlankFields() {

        // arrange
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act
        CourseInStudyPlanResponseDTO dto = new CourseInStudyPlanResponseDTO(
                1,
                1,
                "",
                "",
                "",
                "",
                "",
                4,
                6.0,
                new CourseID(acronym, name),
                new StudyPlanID(programmeID, Date.now())
        );

        // assert
        Set<ConstraintViolation<CourseInStudyPlanResponseDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "DTO should have validation errors");
        assertEquals(4, violations.size(), "There should be 4 violations for blank fields");
    }

    @Test
    void testCourseDurationBusinessRule() {

        // arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlanResponseDTO invalidDto = new CourseInStudyPlanResponseDTO(
                0,
                1,
                "CS101",
                "Computer Science",
                "CS",
                "Informa",
                "2023-09-01",
                0,
                6.0,
                new CourseID(acronym, name),
                new StudyPlanID(programmeID, Date.now())
        );

        CourseInStudyPlanResponseDTO validDto = new CourseInStudyPlanResponseDTO(
                1, // Valid duration
                1,
                "CS101",
                "Computer Science",
                "CS",
                "Informa",
                "2023-09-01",
                4, // Valid credits
                6.0,
                new CourseID(acronym, name),
                new StudyPlanID(programmeID, Date.now())
        );

        // act & assert
        assertTrue(validDto.duration() >= 1, "Duration should be at least 1 semester");
        assertFalse(invalidDto.duration() >= 1, "Invalid duration should fail the business rule");
    }

    @Test
    void testCourseCreditsBusinessRule() {

        // arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlanResponseDTO invalidDto = new CourseInStudyPlanResponseDTO(
                1, // Valid duration
                1,
                "CS101",
                "Computer Science",
                "CS",
                "Informa",
                "2023-09-01",
                4, // Valid duration
                0.0, // Invalid credits
                new CourseID(acronym, name),
                new StudyPlanID(programmeID, Date.now())
        );

        CourseInStudyPlanResponseDTO validDto = new CourseInStudyPlanResponseDTO(
                1, // Valid duration
                1,
                "CS101",
                "Computer Science",
                "CS",
                "Informa",
                "2023-09-01",
                4, // Valid duration
                6.0, // Valid credits
                new CourseID(acronym, name),
                new StudyPlanID(programmeID, Date.now())
        );

        // act & assert
        assertTrue(validDto.credits() > 0, "Credits should be greater than 0");
        assertFalse(invalidDto.credits() > 0, "Invalid credits should fail the business rule");
    }

}