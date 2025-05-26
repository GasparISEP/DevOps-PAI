package PAI.dto.courseEdition;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidation_WhenAllFieldsAreValid() {
        //arrange
        String programmeName = "Software Development";
        String programmeAcronym = "MEI";
        UUID schoolYearID = UUID.randomUUID();

        String courseAcronym = "AP";
        String courseName = "Advanced Programming";
        LocalDate studyPlanImplementationDate = LocalDate.now();

        //act
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                programmeName,
                programmeAcronym,
                schoolYearID,
                courseAcronym,
                courseName,
                studyPlanImplementationDate
        );

        //assert
        assertEquals(programmeName, dto.programmeName());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearID, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanImplementationDate, dto.studyPlanImplementationDate());
    }

    @Test
    void shouldFail_WhenProgrammeNameIsBlank() {
        //arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "",
                "SE",
                UUID.randomUUID(),
                "CS101",
                "Intro to Programming",
                LocalDate.now()
        );

        //act
        Set<ConstraintViolation<CourseEditionRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("programmeName") &&
                v.getMessage().equals("Programme name cannot be blank")));
    }

    @Test
    void shouldFail_WhenProgrammeAcronymIsBlank() {
        //arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "Software Engineering",
                "",
                UUID.randomUUID(),
                "CS101",
                "Intro to Programming",
                LocalDate.now()
        );

        //act
        Set<ConstraintViolation<CourseEditionRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("programmeAcronym") &&
                v.getMessage().equals("Programme acronym cannot be blank")));
    }

    @Test
    void shouldFail_WhenSchoolYearIDIsNull() {
        //arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "Software Engineering",
                "SE",
                null,
                "CS101",
                "Intro to Programming",
                LocalDate.now()
        );

        //act
        Set<ConstraintViolation<CourseEditionRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("schoolYearID") &&
                v.getMessage().equals("School Year ID is required")));
    }

    @Test
    void shouldFail_WhenCourseAcronymIsBlank() {
        //arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "Software Engineering",
                "SE",
                UUID.randomUUID(),
                "",
                "Intro to Programming",
                LocalDate.now()
        );

        //act
        Set<ConstraintViolation<CourseEditionRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("courseAcronym") &&
                v.getMessage().equals("Course acronym cannot be blank")));
    }

    @Test
    void shouldFail_WhenCourseNameIsBlank() {
        //arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "Software Engineering",
                "SE",
                UUID.randomUUID(),
                "CS101",
                "",
                LocalDate.now()
        );

        //act
        Set<ConstraintViolation<CourseEditionRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("courseName") &&
                v.getMessage().equals("Course name cannot be blank")));
    }

    @Test
    void shouldFail_WhenStudyPlanImplementationDateIsNull() {
        //arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "Software Engineering",
                "SE",
                UUID.randomUUID(),
                "CS101",
                "Intro to Programming",
                null
        );

        //act
        Set<ConstraintViolation<CourseEditionRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("studyPlanImplementationDate") &&
                v.getMessage().equals("Study plan implementation date is required")));
    }
}