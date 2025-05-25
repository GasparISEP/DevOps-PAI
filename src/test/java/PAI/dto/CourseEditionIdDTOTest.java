package PAI.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionIdDTOTest {

    @Test
    void shouldCreateCourseEditionIdDTO() {
        //arrange
        String programmeName = "programmeName";
        String programmeAcronym = "programmeAcronym";
        UUID schoolYearId = UUID.randomUUID();

        String courseAcronym = "courseAcronym";
        String courseName = "courseName";
        String studyPlanProgrammeName = "studyPlanProgrammeName";
        String studyPlanProgrammeAcronym  = "studyPlanProgrammeAcronym";
        Date studyPlanImplementationDate = new Date();
        //act
        CourseEditionIdDTO testCourseEditionIdDTO = new CourseEditionIdDTO(programmeName, programmeAcronym, schoolYearId, courseAcronym, courseName, studyPlanProgrammeName, studyPlanProgrammeAcronym, studyPlanImplementationDate);
        //assert
        assertNotNull(testCourseEditionIdDTO);
        assertEquals(programmeName, testCourseEditionIdDTO.programmeName());
        assertEquals(programmeAcronym, testCourseEditionIdDTO.programmeAcronym());
        assertEquals(schoolYearId, testCourseEditionIdDTO.schoolYearId());
        assertEquals(courseAcronym, testCourseEditionIdDTO.courseAcronym());
        assertEquals(courseName, testCourseEditionIdDTO.courseName());
        assertEquals(studyPlanProgrammeName, testCourseEditionIdDTO.studyPlanProgrammeName());
        assertEquals(studyPlanProgrammeAcronym, testCourseEditionIdDTO.studyPlanProgrammeAcronym());
        assertEquals(studyPlanImplementationDate, testCourseEditionIdDTO.studyPlanImplementationDate());
    }

}