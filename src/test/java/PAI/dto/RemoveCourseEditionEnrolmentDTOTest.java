package PAI.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RemoveCourseEditionEnrolmentDTOTest {

    @Test
    void shouldCreateCourseEditionIdDTO(){
        //arrange
        //ProgrammeEditionID
        String programmeName = "programmeName";
        String programmeAcronym = "LEI";
        UUID schoolYearId = UUID.randomUUID();

        //CourseInStudyPlan
        String courseAcronym = "SAS";
        String courseName = "Desenvolvimento de Software";
        String studyPlanProgrammeName = "studyPlanProgrammeName";
        String studyPlanProgrammeAcronym  = "LEI1";
        String studyPlanImplementationDate = "01-10-2024";

        int studentID = 1241924;

        //act
        RemoveCourseEditionEnrolmentDTO testRemoveCourseEditionEnrolmentDTO = new RemoveCourseEditionEnrolmentDTO(programmeName, programmeAcronym, schoolYearId, courseAcronym, courseName, studyPlanProgrammeName, studyPlanProgrammeAcronym, studyPlanImplementationDate, studentID);
        //assert
        assertNotNull(testRemoveCourseEditionEnrolmentDTO);
    }
}