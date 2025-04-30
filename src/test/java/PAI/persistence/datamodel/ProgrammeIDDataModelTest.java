package PAI.persistence.datamodel;

import PAI.VOs.*;
import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
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
        String name = "Ola";
        String acronym = "OLA";

        //act
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel(name, acronym);

        //assert
        assertEquals(name, dataModel.getName());
        assertEquals(acronym, dataModel.getAcronym());
    }

    @Test
    public void testConstructorIsNullWithNullName() {
        //arrange
        String acronym = "OLA";

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeIDDataModel(null,acronym));
    }

    @Test
    public void testConstructorIsNullWithNullAcronym() {
        //arrange
        String name = "name";

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeIDDataModel(name, null));
    }

    @Test
    public void testGettersDefaultsNull() {
        //arrange
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel();
        //act + assert
        assertNull(dataModel.getName());
        assertNull(dataModel.getAcronym());
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

        String nameDM = "Ola";
        String acronymDM = "OLA";
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel(nameDM, acronymDM);

        //act
        int res = dataModel.hashCode();

        //assert
        assertNotNull(res);
    }

    @Test
    public void testHashCodeNonZero() {
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel("Ola", "OLA");
        int hash = dataModel.hashCode();
        assertNotEquals(0, hash);
    }

    @Test
    public void shouldReturnTrueIfObjectsAreEquals() {
        // Arrange

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel("Ola", "OLA");
        Object progIDDataModel2 = progIDDataModel;

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueIfTwoProgIDDMAreEquals() {
        // Arrange
        String name = "Ola";
        String acronym = "OLA";

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel(name, acronym);
        ProgrammeIDDataModel progIDDataModel2 = new ProgrammeIDDataModel(name, acronym);

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfTwoProgIDDMAreNotEquals() {
        // Arrange
        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel("Sim", "SIM");
        ProgrammeIDDataModel progIDDataModel2 = new ProgrammeIDDataModel("Ola", "OLA");

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfProgIDDMAreNotEqualsWithNull() {
        // Arrange
        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel("Sim", "SIM");
        ProgrammeIDDataModel progIDDataModel2 = null;

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfTwoProgIDDMAreNotEqualsBecauseTheyAreOfDifferentClass() {
        // Arrange
        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel("Sim", "SIM");
        Object progIDDataModel2 = new Object();

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfProgIDDataModelNotEqualsWithDifferentObject() {
        // Arrange
        ProgrammeIDDataModel progIDDM = new ProgrammeIDDataModel("Sim", "SIM");
        ProgrammeIDDataModel progIDDM2 = new ProgrammeIDDataModel();

        // Act
        boolean result = progIDDM.equals(progIDDM2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnConsistentHashCode() {
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        ProgrammeDataModel p1 = new ProgrammeDataModel(programmeIDDM, "Engenharia", "ENG", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);
        ProgrammeDataModel p2 = new ProgrammeDataModel(programmeIDDM, "Engenharia", "ENG", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        assertEquals(p1.hashCode(), p2.hashCode());
    }
}