package PAI.dto.courseEdition;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionResponseIDDTOTest {

    @Test
    void shouldCreateCourseEditionResponseIDDTOCorrectly() {
        // Arrange
        String programmeAcronym = "MEI";
        UUID schoolYearID = UUID.randomUUID();
        String courseAcronym = "AP";
        String courseName = "Advanced Programming";
        LocalDate studyPlanImplementationDate = LocalDate.now();
        String courseEditionID = "PROG2023-COURSE123";

        // Act
        CourseEditionResponseIDDTO dto = new CourseEditionResponseIDDTO(
                programmeAcronym,
                schoolYearID,
                courseAcronym,
                courseName,
                studyPlanImplementationDate,
                courseEditionID
        );

        // Assert
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearID, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanImplementationDate, dto.studyPlanImplementationDate());
        assertEquals(courseEditionID, dto.courseEditionID());
    }

}