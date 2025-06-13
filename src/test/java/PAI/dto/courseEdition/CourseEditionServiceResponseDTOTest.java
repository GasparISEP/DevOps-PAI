package PAI.dto.courseEdition;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionServiceResponseDTOTest {

    @Test
    void shouldCreateCourseEditionServiceResponseDTOCorrectly() {
        // Arrange
        UUID generatedID = UUID.randomUUID();
        String programmeAcronym = "SE";
        UUID schoolYearID = UUID.randomUUID();
        String courseAcronym = "CS101";
        String courseName = "Intro to Programming";
        LocalDate studyPlanImplementationDate = LocalDate.of(2025, 5, 25);
        String courseEditionID = "PROG2023-COURSE123";

        // Act
        CourseEditionServiceResponseDTO dto = new CourseEditionServiceResponseDTO(
                generatedID,
                programmeAcronym,
                schoolYearID,
                courseAcronym,
                courseName,
                studyPlanImplementationDate,
                courseEditionID
        );

        // Assert
        assertEquals(generatedID, dto.courseEditionGeneratedID());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearID, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanImplementationDate, dto.studyPlanImplementationDate());
        assertEquals(courseEditionID, dto.courseEditionID());
    }



}