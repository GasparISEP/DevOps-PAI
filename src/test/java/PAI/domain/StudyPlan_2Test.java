package PAI.domain;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlan_2Test {

    @Test
    void testStudyPlanCreation() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Act
        StudyPlan_2 studyPlan = new StudyPlan_2(programmeID, implementationDate, durationInYears);

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
        StudyPlan_2 studyPlan = new StudyPlan_2(programmeID, implementationDate, durationInYears);
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
        StudyPlan_2 studyPlan1 = new StudyPlan_2(programmeID, implementationDate, durationInYears);
        StudyPlan_2 studyPlan2 = new StudyPlan_2(programmeID, implementationDate, durationInYears);

        // Assert
        assertNotEquals(studyPlan1.getStudyPlanID(), studyPlan2.getStudyPlanID());
    }
}
