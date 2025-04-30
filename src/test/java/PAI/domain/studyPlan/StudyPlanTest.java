package PAI.domain.studyPlan;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudyPlanTest {

    @Test
    void testStudyPlanCreation() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        // Act
        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

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
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);


        // Act
        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

        // Assert
        assertNotNull(studyPlanID);
    }

    @Test
    void testUniqueStudyPlanIDForDifferentInstancesOfProgrammeID() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID, implementationDate);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID1, implementationDate);

        // Act
        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID1);
        StudyPlan studyPlan2 = new StudyPlan(programmeID1, implementationDate, durationInYears, quantityOfEcts, studyPlanID2);

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
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID, implementationDate);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID, implementationDate1);

        // Act
        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID1);
        StudyPlan studyPlan2 = new StudyPlan(programmeID, implementationDate1, durationInYears, quantityOfEcts, studyPlanID2);

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
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);
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
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);
        // act
        MaxEcts result = studyPlan1.getQuantityOfEcts();

        // assert
        assertEquals(quantityOfEcts, result);
    }

    @Test
    void shouldReturnDurationInYears() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);
        // act
        DurationInYears result = studyPlan1.getDurationInYears();

        // assert
        assertEquals(durationInYears, result);
    }

    @Test
    void testIdentityReturnsCorrectID() {
        //arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

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
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

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
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);
        StudyPlan studyPlan2 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

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
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate2 = mock(Date.class);
        DurationInYears durationInYears2 = mock(DurationInYears.class);
        MaxEcts quantityOfEcts2 = mock(MaxEcts.class);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID2, implementationDate2);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);
        StudyPlan studyPlan2 = new StudyPlan(programmeID2, implementationDate2, durationInYears2, quantityOfEcts2, studyPlanID2);

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
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

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
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan studyPlan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

        Object other = new Object();

        //act + assert
        assertFalse(studyPlan.sameAs(other));
    }

    @Test
    void constructorShouldThrowWhenProgrammeIDIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlan(null, mock(Date.class), mock(DurationInYears.class), mock(MaxEcts.class), mock(StudyPlanID.class));
        });
    }

    @Test
    void constructorShouldThrowWhenImplementationDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlan(mock(ProgrammeID.class), null, mock(DurationInYears.class), mock(MaxEcts.class), mock(StudyPlanID.class));
        });
    }

    @Test
    void constructorShouldThrowWhenDurationInYearsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlan(mock(ProgrammeID.class), mock(Date.class), null, mock(MaxEcts.class), mock(StudyPlanID.class));
        });
    }

    @Test
    void constructorShouldThrowWhenQuantityOfEctsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlan(mock(ProgrammeID.class), mock(Date.class), mock(DurationInYears.class), null, mock(StudyPlanID.class));
        });
    }

    @Test
    void constructorShouldThrowWhenStudyPlanIDIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlan(mock(ProgrammeID.class), mock(Date.class), mock(DurationInYears.class), mock(MaxEcts.class), null);
        });
    }

    @Test
    void testEqualsReturnsTrueForSameStudyPlanID() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan plan1 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);
        StudyPlan plan2 = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

        assertEquals(plan1, plan2);
    }

    @Test
    void testEqualsReturnsFalseForNull() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan plan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

        assertNotEquals(plan, null);
    }

    @Test
    void testEqualsReturnsFalseForDifferentClass() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);
        MaxEcts quantityOfEcts = mock(MaxEcts.class);
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        StudyPlan plan = new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);

        assertNotEquals(plan, new Object());
    }
}