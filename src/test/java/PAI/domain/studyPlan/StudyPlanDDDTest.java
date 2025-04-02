package PAI.domain.studyPlan;

import PAI.VOs.*;
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
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        // Act
        StudyPlanDDD studyPlan = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);

        // Assert
        assertNotNull(studyPlan);
    }

    @Test
    void testGetStudyPlanIDNotNull() throws Exception {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);


        // Act
        StudyPlanDDD studyPlan = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanID id = studyPlan.getStudyPlanID();

        // Assert
        assertNotNull(id);
    }

    @Test
    void testUniqueStudyPlanIDForDifferentInstancesOfProgrammeID() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);


        ProgrammeID programmeID1 = mock(ProgrammeID.class);


        // Act
        StudyPlanDDD studyPlan1 = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanDDD studyPlan2 = new StudyPlanDDD(programmeID1, implementationDate, durationInYears, quantityOfEcts);

        // Assert
        assertNotEquals(studyPlan1.getStudyPlanID(), studyPlan2.getStudyPlanID());
    }

    @Test
    void testUniqueStudyPlanIDForDifferentInstancesOfDate() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        Date implementationDate1 = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);




        // Act
        StudyPlanDDD studyPlan1 = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanDDD studyPlan2 = new StudyPlanDDD(programmeID, implementationDate1, durationInYears, quantityOfEcts);

        // Assert
        assertNotEquals(studyPlan1.getStudyPlanID(), studyPlan2.getStudyPlanID());
    }

    @Test
    void shouldReturnProgrammeID() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        StudyPlanDDD studyPlan1 = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);
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
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        StudyPlanDDD studyPlan1 = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);
        // act
        QuantEcts result = studyPlan1.getQuantityOfEcts();
        // assert
        assertEquals(quantityOfEcts, result);
    }

    @Test
    void testIdentityReturnsCorrectID() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        StudyPlanDDD studyPlan = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //act + assert
        assertEquals(id, studyPlan.identity());
    }

    @Test
    void testSameAsReturnsTrueForSameObject() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        StudyPlanDDD studyPlan = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //act + assert
        assertTrue(studyPlan.sameAs(studyPlan));
    }

    @Test
    void testSameAsReturnsTrueForEqualID() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        StudyPlanDDD studyPlan1 = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanDDD studyPlan2 = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //act + assert
        assertTrue(studyPlan1.sameAs(studyPlan2));
    }

    @Test
    void testSameAsReturnsFalseForDifferentID() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate2 = mock(Date.class);
        DurationInYears durationInYears2 = mock(DurationInYears.class);
        QuantEcts quantityOfEcts2 = mock(QuantEcts.class);

        StudyPlanDDD studyPlan1 = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);
        StudyPlanDDD studyPlan2 = new StudyPlanDDD(programmeID2, implementationDate2, durationInYears2, quantityOfEcts2);

        //act + assert
        assertFalse(studyPlan1.sameAs(studyPlan2));
    }

    @Test
    void testSameAsReturnsFalseForNull() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        StudyPlanDDD studyPlan = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);

        //act + assert
        assertFalse(studyPlan.sameAs(null));
    }

    @Test
    void testSameAsReturnsFalseForDifferentType() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);

        StudyPlanDDD studyPlan = new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);

        Object other = new Object();

        //act + assert
        assertFalse(studyPlan.sameAs(other));
    }
}
