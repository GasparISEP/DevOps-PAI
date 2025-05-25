package PAI.dto.courseEdition;

import PAI.dto.RemoveCourseEditionEnrolmentDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void shouldCreateCourseEditionRequestDTOCorrectly() {
        //arrange
        String programmeName = "Software Development";
        String programmeAcronym = "MEI";
        UUID schoolYearID = UUID.randomUUID();

        String courseAcronym = "AP";
        String courseName = "Advanced Programming";
        String studyPlanProgrammeName = "Software Development";
        String studyPlanProgrammeAcronym = "MEI";
        Date studyPlanImplementationDate = new Date();

        //act
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                programmeName,
                programmeAcronym,
                schoolYearID,
                courseAcronym,
                courseName,
                studyPlanProgrammeName,
                studyPlanProgrammeAcronym,
                studyPlanImplementationDate
        );

        //assert
        assertEquals(programmeName, dto.programmeName());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearID, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanProgrammeName, dto.studyPlanProgrammeName());
        assertEquals(studyPlanProgrammeAcronym, dto.studyPlanProgrammeAcronym());
        assertEquals(studyPlanImplementationDate, dto.studyPlanImplementationDate());
    }

    @Test
    void validationFailsWhenFieldsAreNullOrBlank () {
        //arrange
        CourseEditionRequestDTO invalidDTO = new CourseEditionRequestDTO(
                "",
                "",
                null,
                "",
                "",
                "",
                "",
                null
        );

        //act
        Set<ConstraintViolation<CourseEditionRequestDTO>> violations = validator.validate(invalidDTO);

        //assert
        assertEquals(8, violations.size());
    }
}