package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudyPlanIDTest {

    @Test
    void testDefaultConstructorCreatesNonNullID() {
        //arrange + act
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        //assert
        assertNotNull(id);
    }

    @Test
    void testEqualsMethodWithDifferentUUID() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        Date implementationDate2 = mock(Date.class);

        //arrange + act
        StudyPlanID id1 = new StudyPlanID(programmeID, implementationDate);
        StudyPlanID id2 = new StudyPlanID(programmeID2, implementationDate2);
        //assert
        assertNotEquals(id1, id2);
    }

    @Test
    void testHashCodeConsistency() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        //arrange
        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        //act
        int hash1 = id.hashCode();
        int hash2 = id.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    void testEqualsSameObject() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        assertEquals(id, id);
    }

    @Test
    void testEqualsWithNull() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);

        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        assertNotEquals(id, null);
    }

    @Test
    void testEqualsWithDifferentClass() {
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);


        StudyPlanID id = new StudyPlanID(programmeID, implementationDate);
        String other = "not a StudyPlanID";
        assertNotEquals(id, other);
    }

    @Test
    void testToString_ReturnsExpectedString() {
        // Arrange
        ProgrammeID mockProgrammeID = mock(ProgrammeID.class);
        Date mockImplementationDate = mock(Date.class);

        when(mockProgrammeID.toString()).thenReturn("ProgrammeID{mock}");
        when(mockImplementationDate.toString()).thenReturn("Date{21-03-2000}");

        StudyPlanID studyPlanID = new StudyPlanID(mockProgrammeID, mockImplementationDate);

        // Act
        String result = studyPlanID.toString();

        // Assert
        assertEquals("StudyPlanID{_programmeID=ProgrammeID{mock}, _implementationDate=Date{21-03-2000}}", result);
    }
}