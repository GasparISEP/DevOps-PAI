package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeIDTest {

    @Test
    void shouldCreateProgrammeID() throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID programmeID = new ProgrammeID(name, acronym);

        //act+assert
        assertNotNull(programmeID);
    }

    @Test
    void shouldntCreateProgrammeIDWithNullName() throws IllegalArgumentException {
        //arrange
        Acronym acronym = mock(Acronym.class);

        //act+assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeID(null,acronym));
    }

    @Test
    void shouldntCreateProgrammeIDWithNullAcronym() throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        //act+assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeID(name,null));
    }

    @Test
    void shouldntCreateProgrammeIDIfBothComponentsAreNull() throws IllegalArgumentException {

        //arrange+act+assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeID(null,null));
    }

    @Test
    void shouldReturnTrueIfSameProgID() throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID progID = new ProgrammeID(name, acronym);
        ProgrammeID progID1 = progID;

        //act+assert
        assertEquals(progID,progID1);
    }

    @Test
    void shouldReturnTrueIfBothProgIDHaveTheSameContent() throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID progID = new ProgrammeID(name, acronym);
        ProgrammeID progID1 = new ProgrammeID(name, acronym);

        //act+assert
        assertEquals(progID,progID1);
    }

    @Test
    void shouldReturnNotEqualsIfProgIDsHaveDifferentContent() {
        //Arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        Acronym acronym1 = mock(Acronym.class);
        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        ProgrammeID programmeID1 = new ProgrammeID(name1, acronym1);
          //Act+Assert
        assertNotEquals(programmeID1, programmeID);
    }

    @Test
    void shouldReturnFalseIfTheProgrammeIDsAreFromDifferentInstances() throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID progID = new ProgrammeID(name, acronym);
        Object o = new Object();
        //act
        boolean result = progID.equals(o);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenOneOfTheProgrammeIDsIsNull() throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID progID = new ProgrammeID(name, acronym);
        ProgrammeID progID1 = null;
        //act
        boolean result = progID.equals(progID1);
        //assert
        assertFalse(result);
    }


    // hashCode Test - non isolated test
    @Test
    void shouldReturnAnImmutableHashCode() throws Exception {
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("bacoco");//mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = new Acronym("POR");//mock(Acronym.class);

        int nameHashCode = name.hashCode();
        int acronymHashCode = acronym.hashCode();

        ProgrammeID progID = new ProgrammeID(name, acronym);

        // Act
        int result = progID.hashCode();

        // Assert
        assertEquals(nameHashCode + acronymHashCode, result);
        assertNotEquals(nameHashCode - acronymHashCode, result);
    }

    // hashCode Test - non isolated test
    @Test
    void shouldReturnAnImmutableHashCodeWithMock() throws Exception {
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        int nameHashCode = name.hashCode();
        int acronymHashCode = acronym.hashCode();

        ProgrammeID progID = new ProgrammeID(name, acronym);

        // Act
        int result = progID.hashCode();

        // Assert
        assertEquals(nameHashCode + acronymHashCode, result);
        assertNotEquals(nameHashCode - acronymHashCode, result);
    }
}