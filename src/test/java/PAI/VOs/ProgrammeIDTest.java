package PAI.VOs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeIDTest {

    private Acronym _acronymDouble;
    private ProgrammeID _programmeId;

    @BeforeEach
    void constructorSetUp(){
        _acronymDouble = mock(Acronym.class);
        _programmeId = new ProgrammeID(_acronymDouble);
    }

    @Test
    void shouldCreateProgrammeID() throws IllegalArgumentException {
        //arrange
        ProgrammeID programmeID = new ProgrammeID(_acronymDouble);

        //act+assert
        assertNotNull(programmeID);
    }

    @Test
    void shouldntCreateProgrammeIDWithNullAcronym() throws IllegalArgumentException {
        //arrange

        //act+assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeID(null));
    }

    @Test
    void shouldReturnTrueIfSameProgrammeIDObject() throws IllegalArgumentException {
        //arrange

        //act+assert
        assertEquals(_programmeId, _programmeId);
    }

    @Test
    void shouldReturnTrueIfSameProgID() throws IllegalArgumentException {
        //arrange
        ProgrammeID programmeId2 = new ProgrammeID(_acronymDouble);

        //act+assert
        assertEquals(_programmeId, programmeId2);
    }

    @Test
    void shouldReturnNotEqualsIfProgIDsHaveDifferentContent() {
        //Arrange
        Acronym acronymDouble2 = mock(Acronym.class);
        ProgrammeID programmeId2 = new ProgrammeID(acronymDouble2);

        //Act+Assert
        assertNotEquals(_programmeId, programmeId2);
    }

    @Test
    void shouldReturnFalseIfTheProgrammeIDsAreFromDifferentInstances() throws IllegalArgumentException {
        //arrange
        Object anotherObject = new Object();

        //act
        boolean result = _programmeId.equals(anotherObject);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenOneOfTheProgrammeIDsIsNull() throws IllegalArgumentException {
        //arrange
        ProgrammeID progID1 = null;

        //act
        boolean result = _programmeId.equals(progID1);

        //assert
        assertFalse(result);
    }


    // hashCode Test - non isolated test
    @Test
    void shouldReturnAnImmutableHashCode() throws Exception {

        int acronymHashCode = _acronymDouble.hashCode();

        // Act
        int result = _programmeId.hashCode();

        // Assert
        assertEquals(acronymHashCode, result);
    }

    // hashCode Test - non isolated test
    @Test
    void shouldReturnAnImmutableHashCodeWithMock() throws Exception {

        int acronymHashCode = _acronymDouble.hashCode();

        // Act
        int result = _programmeId.hashCode();

        // Assert
        assertEquals(acronymHashCode, result);
    }

    @Test
    void shouldGetAcronym() {
        //arrange

        //act
        Acronym result = _programmeId.getAcronym();

        //assert
        assertEquals(result, _acronymDouble);
    }

    @Test
    void shouldGetProgrammeAcronymString() {
        //arrange
        String acronymString = "IPM";
        Acronym acronym = new Acronym(acronymString);
        ProgrammeID programmeID = new ProgrammeID(acronym);

        //act
        String result = programmeID.getProgrammeAcronym();

        //assert
        assertEquals(acronymString, result);
    }
}