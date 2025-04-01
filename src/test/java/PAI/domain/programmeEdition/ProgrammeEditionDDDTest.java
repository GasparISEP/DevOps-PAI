package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionDDDTest {

    //Constructor Tests
    @Test
    void shouldCreateProgrammeEdition() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);

        // Act
        ProgrammeEditionDDD PEDDD = new ProgrammeEditionDDD(pEID, pID, sYID);

        // Assert
        assertNotNull(PEDDD);
    }

    @Test
    void shouldNotCreateProgrammeEditionIfProgrammeEditionIDIsNull() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = null;
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionDDD(pEID, pID, sYID));

        // Assert
        assertEquals("ProgrammeEditionID cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeEditionIfProgrammeIDIsNull() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = null;
        SchoolYearID sYID = mock(SchoolYearID.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionDDD(pEID, pID, sYID));

        // Assert
        assertEquals("ProgrammeID cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeEditionIfSchoolYearIDIsNull() throws Exception{
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionDDD(pEID, pID, sYID));

        // Assert
        assertEquals("SchoolYearID cannot be null", exception.getMessage());
    }

    // identity Test
    @Test
    void shouldReturnNullWhenIdentityMethodUsed() throws Exception {
        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionDDD PEDDD = new ProgrammeEditionDDD(pEID, pID, sYID);

        // Act
        ProgrammeEditionID peIDCheck = PEDDD.identity();

        // Assert
        assertNull(peIDCheck);
    }

    @Test
    void shouldReturnFalseWhenSameAsMethodUsed() throws Exception {

        // Arrange
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionDDD PEDDD = new ProgrammeEditionDDD(pEID, pID, sYID);

        // Act
        boolean result = PEDDD.sameAs(PEDDD);

        // Assert
        assertFalse(result);
    }
}