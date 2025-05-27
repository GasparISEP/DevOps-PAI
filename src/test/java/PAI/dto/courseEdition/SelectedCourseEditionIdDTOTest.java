package PAI.dto.courseEdition;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SelectedCourseEditionIdDTOTest {

    @Test
    void shouldCreateCourseEditionReferenceDTO() {
        //arrange
        String programmeName = "Software Development";
        String programmeAcronym = "MEI";
        UUID schoolYearID = UUID.randomUUID();

        String courseAcronym = "AP";
        String courseName = "Advanced Programming";
        LocalDate studyPlanImplementationDate = LocalDate.now();

        //act
        SelectedCourseEditionIdDTO referenceDto = new SelectedCourseEditionIdDTO(
                programmeName,
                programmeAcronym,
                schoolYearID,
                courseAcronym,
                courseName,
                studyPlanImplementationDate
        );

        //assert
        assertEquals(programmeName, referenceDto.programmeName());
        assertEquals(programmeAcronym, referenceDto.programmeAcronym());
        assertEquals(schoolYearID, referenceDto.schoolYearID());
        assertEquals(courseAcronym, referenceDto.courseAcronym());
        assertEquals(courseName, referenceDto.courseName());
        assertEquals(studyPlanImplementationDate, referenceDto.studyPlanImplementationDate());
    }
}