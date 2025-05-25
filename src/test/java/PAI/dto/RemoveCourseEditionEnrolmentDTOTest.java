package PAI.dto;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RemoveCourseEditionEnrolmentDTOTest {

    @Test
    void shouldCreateCourseEditionIdDTO() throws Exception {
        //arrange
        //ProgrammeEditionID
        String programmeName = "programmeName";
        String programmeAcronym = "LEI";
        UUID schoolYearId = UUID.randomUUID();
        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars(programmeName),  new Acronym(programmeAcronym));
        SchoolYearID schoolYearID = new SchoolYearID(schoolYearId);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        //CourseInStudyPlan
        String courseAcronym = "SAS";
        String courseName = "Desenvolvimento de Software";
        String studyPlanProgrammeName = "studyPlanProgrammeName";
        String studyPlanProgrammeAcronym  = "LEI1";
        PAI.VOs.Date studyPlanImplementationDate = new Date("01-10-2024");
        CourseID courseID = new CourseID(new Acronym(courseAcronym), new Name(courseName));
        ProgrammeID programmeID2 = new ProgrammeID(new NameWithNumbersAndSpecialChars(studyPlanProgrammeName),  new Acronym(studyPlanProgrammeAcronym));
        StudyPlanID studyPlanID = new StudyPlanID(programmeID2, studyPlanImplementationDate);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        StudentID studentID = new StudentID(1241924);

        //act
        RemoveCourseEditionEnrolmentDTO testRemoveCourseEditionEnrolmentDTO = new RemoveCourseEditionEnrolmentDTO(courseEditionID, studentID);
        //assert
        assertNotNull(testRemoveCourseEditionEnrolmentDTO);
    }
}