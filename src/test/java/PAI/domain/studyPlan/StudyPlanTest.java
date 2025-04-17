package PAI.domain.studyPlan;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanTest {

    @Test
    void testStudyPlanCreation() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        // Act
        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);

        // Assert
        assertNotNull(studyPlan);
    }

    @Test
    void testGetStudyPlanIDNotNull() throws Exception {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);


        // Act
        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanID id = studyPlan.identity();

        // Assert
        assertNotNull(id);
    }

    @Test
    void testUniqueStudyPlanIDForDifferentInstancesOfProgrammeID() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);


        ProgrammeID programmeID1 = mock(ProgrammeID.class);


        // Act
        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlan studyPlan2 = new StudyPlan(programmeID1, implementationDate, durationInYears, quantityOfEcts);

        // Assert
        assertNotEquals(studyPlan1.identity(), studyPlan2.identity());
    }

    @Test
    void testUniqueStudyPlanIDForDifferentInstancesOfDate() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        Date implementationDate1 = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        // Act
        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlan studyPlan2 = new StudyPlan(programmeID, implementationDate1, durationInYears, quantityOfEcts);

        // Assert
        assertNotEquals(studyPlan1.identity(), studyPlan2.identity());
    }

    @Test
    void shouldReturnProgrammeID() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
        // act
        ProgrammeID result = studyPlan1.getProgrammeID();
        // assert
        assertEquals(programmeID, result);
    }

    @Test
    void shouldReturnQuantityOfEcts() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
        // act
        MaxEcts result = studyPlan1.getQuantityOfEcts();

        // assert
        assertEquals(quantityOfEcts, result);
    }

    @Test
    void testIdentityReturnsCorrectID() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //act + assert
        assertEquals(id, studyPlan.identity());
    }

    @Test
    void testSameAsReturnsTrueForSameObject() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //act + assert
        assertTrue(studyPlan.sameAs(studyPlan));
    }

    @Test
    void testSameAsReturnsTrueForEqualID() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlan studyPlan2 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //act + assert
        assertTrue(studyPlan1.sameAs(studyPlan2));
    }

    @Test
    void testSameAsReturnsFalseForDifferentID() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate2 = mock(Date.class);
        DurationInYears durationInYears2 = mock(DurationInYears.class);
        MaxEcts quantityOfEcts2 = mock(MaxEcts.class);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlan studyPlan2 = new StudyPlan(programmeID2, implementationDate2, durationInYears2, quantityOfEcts2);

        //act + assert
        assertFalse(studyPlan1.sameAs(studyPlan2));
    }

    @Test
    void testSameAsReturnsFalseForNull() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //act + assert
        assertFalse(studyPlan.sameAs(null));
    }

    @Test
    void testSameAsReturnsFalseForDifferentType() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);

        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);

        Object other = new Object();

        //act + assert
        assertFalse(studyPlan.sameAs(other));
    }
}