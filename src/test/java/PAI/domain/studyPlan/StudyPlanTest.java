package PAI.domain.studyPlan;

import PAI.VOs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanTest {

    private ProgrammeID programmeID;
    private ProgrammeID programmeID2;
    private Date startDate;
    private DurationInYears durationInYears;
    private MaxEcts maxEcts;
    private StudyPlanID studyPlanID;
    private StudyPlanID studyPlanID2;
    private StudyPlan studyPlan1;
    private StudyPlan studyPlan2;
    private StudyPlan studyPlan3;

    @BeforeEach
    void createDoubles() {
        programmeID = mock(ProgrammeID.class);
        programmeID2 = mock(ProgrammeID.class);
        startDate = mock(Date.class);
        durationInYears = mock(DurationInYears.class);
        maxEcts = mock(MaxEcts.class);
        studyPlanID = new StudyPlanID(programmeID, startDate);
        studyPlanID2 = new StudyPlanID(programmeID2, startDate);
    }

    private void createStudyPlans() {
        studyPlan1 = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);
        studyPlan2 = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);
        studyPlan3 = new StudyPlan(programmeID2, startDate, durationInYears, maxEcts, studyPlanID2);
    }

    @Test
    void testStudyPlanCreation() {
        // Arrange
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        // Act
        StudyPlan studyPlan = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);

        // Assert
        assertNotNull(studyPlan);
    }

    @Test
    void testGetStudyPlanIDNotNull() throws Exception {
        // Arrange
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        // Act
        StudyPlan studyPlan = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);

        // Assert
        assertNotNull(studyPlanID);
    }

    @Test
    void testUniqueStudyPlanIDForDifferentInstancesOfProgrammeID() {
        // Arrange
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID, startDate);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID1, startDate);

        // Act
        StudyPlan studyPlan1 = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID1);
        StudyPlan studyPlan2 = new StudyPlan(programmeID1, startDate, durationInYears, maxEcts, studyPlanID2);

        // Assert
        assertNotEquals(studyPlan1.identity(), studyPlan2.identity());
    }

    @Test
    void testUniqueStudyPlanIDForDifferentInstancesOfDate() {
        // Arrange
        Date implementationDate1 = mock(Date.class);
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID, startDate);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID, implementationDate1);

        // Act
        StudyPlan studyPlan1 = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID1);
        StudyPlan studyPlan2 = new StudyPlan(programmeID, implementationDate1, durationInYears, maxEcts, studyPlanID2);

        // Assert
        assertNotEquals(studyPlan1.identity(), studyPlan2.identity());
    }

    @Test
    void shouldReturnProgrammeID() {
        // arrange
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);
        // act
        ProgrammeID result = studyPlan1.getProgrammeID();
        // assert
        assertEquals(programmeID, result);
    }

    @Test
    void shouldReturnMaxEcts() {
        // arrange
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);
        // act
        MaxEcts result = studyPlan1.getMaxEcts();

        // assert
        assertEquals(maxEcts, result);
    }

    @Test
    void shouldReturnDurationInYears() {
        // arrange
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        StudyPlan studyPlan1 = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);
        // act
        DurationInYears result = studyPlan1.getDurationInYears();

        // assert
        assertEquals(durationInYears, result);
    }

    @Test
    void testIdentityReturnsCorrectID() {
        //arrange
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        StudyPlanID id = new StudyPlanID(programmeID, startDate);
        StudyPlan studyPlan = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);

        //act + assert
        assertEquals(id, studyPlan.identity());
    }

    @Test
    void testSameAsReturnsTrueForSameObject() {
        //arrange
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        StudyPlan studyPlan = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);

        //act + assert
        assertTrue(studyPlan.sameAs(studyPlan));
    }

    @Test
    void testSameAsReturnsTrueForEqualID() {
        //arrange
        createStudyPlans();

        //act + assert
        assertTrue(studyPlan1.sameAs(studyPlan2));
    }

    @Test
    void testSameAsReturnsFalseForDifferentID() {
        //arrange
        createStudyPlans();

        //act + assert
        assertFalse(studyPlan1.sameAs(studyPlan3));
    }

    @Test
    void testSameAsReturnsFalseForNull() {
        //arrange
        StudyPlan studyPlan = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);

        //act + assert
        assertFalse(studyPlan.sameAs(null));
    }

    @Test
    void testSameAsReturnsFalseForDifferentType() {
        //arrange
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        StudyPlan studyPlan = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);

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
    void constructorShouldThrowWhenMaxEctsIsNull() {
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
        createStudyPlans();

        assertEquals(studyPlan1, studyPlan2);
    }

    @Test
    void testEqualsReturnsFalseForNull() {
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        StudyPlan plan = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);

        assertNotEquals(plan, null);
    }

    @Test
    void testEqualsReturnsFalseForDifferentClass() {
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);

        StudyPlan plan = new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);

        assertNotEquals(plan, new Object());
    }

    @Test
    void testEqualsReturnsFalseForDifferentStudyPlanID() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        ProgrammeID otherProgrammeID = mock(ProgrammeID.class);
        Date otherImplementationDate = mock(Date.class);

        DurationInYears duration = mock(DurationInYears.class);
        MaxEcts ects = mock(MaxEcts.class);

        StudyPlanID id1 = new StudyPlanID(programmeID, implementationDate);
        StudyPlanID id2 = new StudyPlanID(otherProgrammeID, otherImplementationDate);

        StudyPlan plan1 = new StudyPlan(programmeID, implementationDate, duration, ects, id1);
        StudyPlan plan2 = new StudyPlan(otherProgrammeID, otherImplementationDate, duration, ects, id2);

        assertNotEquals(plan1, plan2);
    }

    @Test
    void shouldReturnStartDateWhenGetStartDateIsCalled() {
        // Arrange
        createStudyPlans();

        // Act
        Date result = studyPlan1.getStartDate();

        // Assert
        assertSame(result, startDate);
    }
}