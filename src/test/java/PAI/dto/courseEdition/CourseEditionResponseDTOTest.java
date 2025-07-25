package PAI.dto.courseEdition;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionResponseDTOTest {

    @Test
    void shouldCreateCourseEditionRequestDTOCorrectly() {
        //arrange
        UUID generatedID = UUID.randomUUID();
        String courseEditionID = "PROG2023-COURSE123";
        String programmeName = "Software Development";
        String programmeAcronym = "MEI";
        UUID schoolYearID = UUID.randomUUID();

        String courseAcronym = "AP";
        String courseName = "Advanced Programming";
        LocalDate studyPlanImplementationDate = LocalDate.now();
        String ruc = "AAA";

        //act
        CourseEditionResponseDTO dto = new CourseEditionResponseDTO(
                generatedID,
                programmeAcronym,
                schoolYearID,
                courseAcronym,
                courseName,
                studyPlanImplementationDate,
                courseEditionID,
                ruc
                );

        //assert
        assertEquals(courseEditionID, dto.courseEditionID());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearID, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanImplementationDate, dto.studyPlanImplementationDate());
    }

}