package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionTest {


    //Constructor Tests
    @Test
    void shouldCreateProgrammeEdition() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        // Act
        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        // Assert
        assertNotNull(pE);
    }

    @Test
    void shouldNotCreateProgrammeEditionIfProgrammeEditionIDIsNull() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = null;
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEdition(pEID, pID, sYID, pEDID));

        // Assert
        assertEquals("ProgrammeEditionID cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeEditionIfProgrammeIDIsNull() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = null;
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEdition(pEID, pID, sYID, pEDID));

        // Assert
        assertEquals("ProgrammeID cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeEditionIfSchoolYearIDIsNull() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = null;
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEdition(pEID, pID, sYID, pEDID));

        // Assert
        assertEquals("SchoolYearID cannot be null", exception.getMessage());
    }


    @Test
    void shouldNotCreateProgrammeEditionIfGeneratedIDIsNull() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEdition(pEID, pID, sYID, pEDID));

        // Assert
        assertEquals("ProgrammeEditionGeneratedID cannot be null", exception.getMessage());
    }

    // identity Test
    @Test
    void shouldReturnProgrammeEditionIDWhenIdentityMethodUsed() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        // Act
        ProgrammeEditionID peIDCheck = pE.identity();

        // Assert
        assertEquals(pEID, peIDCheck);
    }


    // sameAS test
    @Test
    void shouldReturnTrueIfProgrammeEditionIsComparedToItSelf() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID,pEDID);

        // Act
        boolean result = pE.sameAs(pE);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsComparedToANull() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID,pEDID);

        // Act
        boolean result = pE.sameAs(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsComparedToADifferentInstanceOfObject() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        // Act
        boolean result = pE.sameAs(sYID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTwoProgrammeEditionHaveTheSameValue() throws Exception {
        // Arrange
        ProgrammeEditionID pEID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID pEID2 = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE1 = new ProgrammeEdition(pEID1, pID, sYID, pEDID);
        ProgrammeEdition pE2 = new ProgrammeEdition(pEID2, pID, sYID, pEDID);

        // Act
        boolean result = pE1.sameAs(pE2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionHaveTheSameSchoolYearIDButDifferentProgrammeID() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID1 = mock(ProgrammeID.class);
        ProgrammeID pID2 = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE1 = new ProgrammeEdition(pEID, pID1, sYID, pEDID);
        ProgrammeEdition pE2 = new ProgrammeEdition(pEID, pID2, sYID, pEDID);

        // Act
        boolean result = pE1.sameAs(pE2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionHaveTheSameProgrammeIDButDifferentSchoolYearID() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID1 = mock(SchoolYearID.class);
        SchoolYearID sYID2 = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE1 = new ProgrammeEdition(pEID, pID, sYID1, pEDID);
        ProgrammeEdition pE2 = new ProgrammeEdition(pEID, pID, sYID2, pEDID);

        // Act
        boolean result = pE1.sameAs(pE2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionIDHaveDifferentProgrammeIDAndSchoolYearID() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID1 = mock(ProgrammeID.class);
        ProgrammeID pID2 = mock(ProgrammeID.class);
        SchoolYearID sYID1 = mock(SchoolYearID.class);
        SchoolYearID sYID2 = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE1 = new ProgrammeEdition(pEID, pID1, sYID1, pEDID);
        ProgrammeEdition pE2 = new ProgrammeEdition(pEID, pID2, sYID2,pEDID);

        // Act
        boolean result = pE1.sameAs(pE2);

        // Assert
        assertFalse(result);
    }


    // findProgrammeIDInProgrammeEdition Test
    @Test
    void shouldReturnProgrammeID() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        // Act
        ProgrammeID pIDCheck = pE.findProgrammeIDInProgrammeEdition();

        // Assert
        assertEquals(pID, pIDCheck);
    }


    // findSchoolYearIDInProgrammeEdition Test
    @Test
    void shouldReturnSchoolYearID() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        // Act
        SchoolYearID sYIDCheck = pE.findSchoolYearIDInProgrammeEdition();

        // Assert
        assertEquals(sYID, sYIDCheck);
    }


    // equals Tests
    @Test
    void shouldReturnTrueIfCompareProgrammeEditionToItSelf() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        // Act
        boolean result = pE.equals(pE);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCompareProgrammeEditionToANull() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        // Act
        boolean result = pE.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCompareProgrammeEditionToADifferentTypeOfObject() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        // Act
        boolean result = pE.equals(pID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCompareProgrammeEditionWithDifferentProgrammeEditionID() throws Exception {
        // Arrange
        ProgrammeEditionID pEID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID pEID2 = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE1 = new ProgrammeEdition(pEID1, pID, sYID, pEDID);
        ProgrammeEdition pE2 = new ProgrammeEdition(pEID2, pID, sYID, pEDID);

        // Act
        boolean result = pE1.equals(pE2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCompareProgrammeEditionWithSameProgrammeEditionID() throws Exception {
        // Arrange
        ProgrammeEditionID pEID1 = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE1 = new ProgrammeEdition(pEID1, pID, sYID, pEDID);
        ProgrammeEdition pE2 = new ProgrammeEdition(pEID1, pID, sYID, pEDID);

        // Act
        boolean result = pE1.equals(pE2);

        // Assert
        assertTrue(result);
    }


    // HashCode Test
    @Test
    void shouldReturnHashCode() throws Exception {
        // Arrange
        ProgrammeID pID = mock(ProgrammeID.class);
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionGeneratedID pEDID = mock(ProgrammeEditionGeneratedID.class);

        ProgrammeEdition pE = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        ProgrammeEdition pE1 = new ProgrammeEdition(pEID, pID, sYID, pEDID);

        ProgrammeEditionID pEID2 = mock(ProgrammeEditionID.class);
        ProgrammeEdition pE2 = new ProgrammeEdition(pEID2, pID, sYID, pEDID);

        // Act
        int result = pE.hashCode();

        // Assert
        assertEquals(result, pE1.hashCode());
        assertNotEquals(result, pE2.hashCode());
    }
}