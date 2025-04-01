package PAI.domain.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanDDDTest {

    @Test
    void testStudyPlanCreation() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Act
        StudyPlanDDD studyPlan = new StudyPlanDDD(programmeID, implementationDate, durationInYears);

        // Assert
        assertNotNull(studyPlan);
    }

    @Test
    void testGetStudyPlanIDNotNull() throws Exception {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Act
        StudyPlanDDD studyPlan = new StudyPlanDDD(programmeID, implementationDate, durationInYears);
        StudyPlanID id = studyPlan.getStudyPlanID();

        // Assert
        assertNotNull(id);
    }

    @Test
    void testUniqueStudyPlanIDsForDifferentInstances() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Act
        StudyPlanDDD studyPlan1 = new StudyPlanDDD(programmeID, implementationDate, durationInYears);
        StudyPlanDDD studyPlan2 = new StudyPlanDDD(programmeID, implementationDate, durationInYears);

        // Assert
        assertNotEquals(studyPlan1.getStudyPlanID(), studyPlan2.getStudyPlanID());
    }

    @Test
    void shouldReturnProgrammeID() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        StudyPlanDDD studyPlan1 = new StudyPlanDDD(programmeID, implementationDate, durationInYears);
        // act
        ProgrammeID result = studyPlan1.getProgrammeID();
        // assert
        assertEquals(programmeID, result);
    }
}
