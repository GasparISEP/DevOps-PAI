package PAI.persistence.datamodel;

import PAI.VOs.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammeIDDataModelTest {

    @Test
    public void testDefaultConstructor() {
        //Arrange+Act
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel();

        //Assert
        assertNotNull(dataModel);
    }

    @Test
    public void testConstructor() {
        //arrange
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Ola");
        Acronym acronym = new Acronym("OLA");
        ProgrammeID progID = new ProgrammeID(name, acronym);

        //act
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel(progID);

        //assert
        assertNotNull(dataModel);
    }

    @Test
    public void testGetName() {
        //arrange

        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel();

        //act
        String res = dataModel.getName();

        //assert
        assertEquals(res,dataModel.getName());
    }

    @Test
    public void testGetAcronym() {
        //arrange

        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel();

        //act
        String res = dataModel.getAcronym();

        //assert
        assertEquals(res,dataModel.getAcronym());
    }

    @Test
    public void shouldReturnHashCode() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID progID = mock(ProgrammeID.class);

        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("Ola");
        when(acronym.getAcronym()).thenReturn("OLA");
        when(progID.getName()).thenReturn(name);
        when(progID.getAcronym()).thenReturn(acronym);


        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel(progID);

        //act
        int res = dataModel.hashCode();

        //assert
        assertNotNull(res);
    }

    @Test
    public void shouldReturnTrueIfObjectsAreEquals() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        ProgrammeID progIDDouble = mock(ProgrammeID.class);
        when(progIDDouble.getAcronym()).thenReturn(acronym);
        when(progIDDouble.getName()).thenReturn(name);

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel(progIDDouble);
        Object progIDDataModel2 = progIDDataModel;

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueIfTwoProgIDDMAreEquals() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        ProgrammeID progIDDouble = mock(ProgrammeID.class);
        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("Ola");
        when(acronym.getAcronym()).thenReturn("OLA");
        when(progIDDouble.getAcronym()).thenReturn(acronym);
        when(progIDDouble.getName()).thenReturn(name);

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel(progIDDouble);
        ProgrammeIDDataModel progIDDataModel2 = new ProgrammeIDDataModel(progIDDouble);

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertTrue(result);
    }


    @Test
    public void shouldReturnFalseIfTwoProgIDDMAreNotEquals() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        Acronym acronym1 = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);

        ProgrammeID progIDDouble = mock(ProgrammeID.class);
        ProgrammeID progIDDouble2 = mock(ProgrammeID.class);

        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("Sim");
        when(acronym.getAcronym()).thenReturn("SIM");
        when(name1.getnameWithNumbersAndSpecialChars()).thenReturn("Ola");
        when(acronym1.getAcronym()).thenReturn("OLA");

        when(progIDDouble.getAcronym()).thenReturn(acronym);
        when(progIDDouble.getName()).thenReturn(name);

        when(progIDDouble2.getAcronym()).thenReturn(acronym1);
        when(progIDDouble2.getName()).thenReturn(name1);

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel(progIDDouble);
        ProgrammeIDDataModel progIDDataModel2 = new ProgrammeIDDataModel(progIDDouble2);

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfProgIDDMAreNotEqualsWithNull() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        ProgrammeID progIDDouble = mock(ProgrammeID.class);

        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("Sim");
        when(acronym.getAcronym()).thenReturn("SIM");

        when(progIDDouble.getAcronym()).thenReturn(acronym);
        when(progIDDouble.getName()).thenReturn(name);

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel(progIDDouble);
        ProgrammeIDDataModel progIDDataModel2 = null;

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfTwoProgIDDMAreNotEqualsBecauseTheyAreOfDifferentClass() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        ProgrammeID progIDDouble = mock(ProgrammeID.class);

        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("Sim");
        when(acronym.getAcronym()).thenReturn("SIM");

        when(progIDDouble.getAcronym()).thenReturn(acronym);
        when(progIDDouble.getName()).thenReturn(name);

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel(progIDDouble);
        Object progIDDataModel2 = new Object();

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfProgIDDataModelNotEqualsWithDifferentObject() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        ProgrammeID progID = mock(ProgrammeID.class);

        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("Sim");
        when(acronym.getAcronym()).thenReturn("SIM");

        when(progID.getAcronym()).thenReturn(acronym);
        when(progID.getName()).thenReturn(name);

        ProgrammeIDDataModel progIDDM = new ProgrammeIDDataModel(progID);
        ProgrammeIDDataModel progIDDM2 = new ProgrammeIDDataModel();

        // Act
        boolean result = progIDDM.equals(progIDDM2);

        // Assert
        assertFalse(result);
    }

}