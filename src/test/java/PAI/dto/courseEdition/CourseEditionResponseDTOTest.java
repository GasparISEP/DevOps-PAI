package PAI.dto.courseEdition;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionResponseDTOTest {

    @Test
    void shouldCreateCourseEditionRequestDTOCorrectly() {
        //arrange
        String courseEditionID = "PROG2023-COURSE123";
        String programmeName = "Software Development";
        String programmeAcronym = "MEI";
        UUID schoolYearID = UUID.randomUUID();

        String courseAcronym = "AP";
        String courseName = "Advanced Programming";
        Date studyPlanImplementationDate = new Date();

        //act
        CourseEditionResponseDTO dto = new CourseEditionResponseDTO(
                courseEditionID,
                programmeName,
                programmeAcronym,
                schoolYearID,
                courseAcronym,
                courseName,
                studyPlanImplementationDate
        );

        //assert
        assertEquals(courseEditionID, dto.courseEditionID());
        assertEquals(programmeName, dto.programmeName());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearID, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanImplementationDate, dto.studyPlanImplementationDate());
    }

}