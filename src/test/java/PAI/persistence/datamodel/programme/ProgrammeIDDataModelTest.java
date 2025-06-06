package PAI.persistence.datamodel.programme;

import PAI.VOs.*;
import PAI.persistence.datamodel.degreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
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
        String acronym = "OLA";

        //act
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel(acronym);

        //assert
        assertEquals(acronym, dataModel.getAcronym());
    }

    @Test
    public void testConstructorIsNullWithNullAcronym() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeIDDataModel(null));
    }

    @Test
    public void testGettersDefaultsNull() {
        //arrange
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel();
        //act + assert
        assertNull(dataModel.getAcronym());
    }

    @Test
    public void shouldReturnHashCode() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID progID = mock(ProgrammeID.class);

        when(name.getNameWithNumbersAndSpecialChars()).thenReturn("Ola");
        when(acronym.getAcronym()).thenReturn("OLA");
        when(progID.getAcronym()).thenReturn(acronym);
        
        String acronymDM = "OLA";
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel(acronymDM);

        //act
        int res = dataModel.hashCode();

        //assert
        assertNotNull(res);
    }

    @Test
    public void testHashCodeNonZero() {
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel("OLA");
        int hash = dataModel.hashCode();
        assertNotEquals(0, hash);
    }

    @Test
    public void shouldReturnTrueIfObjectsAreEquals() {
        // Arrange

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel("OLA");
        Object progIDDataModel2 = progIDDataModel;

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueIfTwoProgIDDMAreEquals() {
        // Arrange
        String acronym = "OLA";

        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel(acronym);
        ProgrammeIDDataModel progIDDataModel2 = new ProgrammeIDDataModel(acronym);

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfTwoProgIDDMAreNotEquals() {
        // Arrange
        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel("SIM");
        ProgrammeIDDataModel progIDDataModel2 = new ProgrammeIDDataModel("OLA");

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfProgIDDMAreNotEqualsWithNull() {
        // Arrange
        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel("SIM");
        ProgrammeIDDataModel progIDDataModel2 = null;

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfTwoProgIDDMAreNotEqualsBecauseTheyAreOfDifferentClass() {
        // Arrange
        ProgrammeIDDataModel progIDDataModel = new ProgrammeIDDataModel("SIM");
        Object progIDDataModel2 = new Object();

        // Act
        boolean result = progIDDataModel.equals(progIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfProgIDDataModelNotEqualsWithDifferentObject() {
        // Arrange
        ProgrammeIDDataModel progIDDM = new ProgrammeIDDataModel("SIM");
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