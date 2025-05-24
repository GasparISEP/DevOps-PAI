package PAI.persistence.datamodel.programme;

import PAI.persistence.datamodel.degreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProgrammeDataModelTest {

    @Test
    void shouldCreateProgrammeDataModelConstructor() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        //assert
        assertNotNull(programmeDataModel);
    }

    @Test
    void shouldNotCreateProgrammeDataModelWithNullName() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act + assert
        assertThrows (Exception.class, () -> new ProgrammeDataModel(programmeIDDM, null, "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel));
    }

    @Test
    void shouldNotCreateProgrammeDataModelWithNullAcronym() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act + assert
        assertThrows (Exception.class, () -> new ProgrammeDataModel(programmeIDDM, "name", null, 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel));
    }

    @Test
    void shouldNotCreateProgrammeDataModelWithNullDegreetypeID() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        //act + assert
        assertThrows (Exception.class, () -> new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, null, departmentIDDataModel, teacherIDDataModel));
    }

    @Test
    void shouldNotCreateProgrammeDataModelWithNullDepartmentID() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act + assert
        assertThrows (Exception.class, () -> new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, null, teacherIDDataModel));
    }

    @Test
    void shouldNotCreateProgrammeDataModelWithNullTeacherID() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act + assert
        assertThrows (Exception.class, () -> new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, null));
    }

    @Test
    void shouldNotCreateProgrammeDataModelWithNullProgID() {
        //arrange
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act + assert
        assertThrows (Exception.class, () -> new ProgrammeDataModel(null,"name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel));
    }

    @Test
    void shouldNotCreateProgrammeDataModelWith0QuantSemesters() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act + assert
        assertThrows (Exception.class, () -> new ProgrammeDataModel(programmeIDDM, "name", "ACR", 0, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel));
    }

    @Test
    void shouldNotCreateProgrammeDataModelWith0QuantEcts() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act + assert
        assertThrows (Exception.class, () -> new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 0, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel));
    }

    @Test
    void defaultConstructorInitializesFieldsToDefaults() {
        ProgrammeDataModel dataModel = new ProgrammeDataModel();
        assertNotNull(dataModel);
        assertNull(dataModel.getName());
        assertNull(dataModel.getAcronym());
        assertEquals(0, dataModel.getQuantSemesters());
        assertEquals(0, dataModel.getMaxEcts());
        assertNull(dataModel.getDegreeTypeID());
        assertNull(dataModel.getDepartmentID());
        assertNull(dataModel.getProgrammeDirectorID());
    }

    @Test
    public void getProgrammeDataModelName() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals("name", programmeDataModel.getName());
    }

    @Test
    public void getProgrammeDataModelAcronym() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals("ACR", programmeDataModel.getAcronym());
    }

    @Test
    public void getProgrammeDataModelQuantSemesters() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(6, programmeDataModel.getQuantSemesters());
    }

    @Test
    public void getProgrammeDataModelQuantEcts() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(30, programmeDataModel.getMaxEcts());
    }

    @Test
    public void getProgrammeDataModelDegreeTypeID() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(degreeTypeIDDataModel, programmeDataModel.getDegreeTypeID());
    }

    @Test
    public void getProgrammeDataModelDepartment() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(departmentIDDataModel, programmeDataModel.getDepartmentID());
    }

    @Test
    public void getProgrammeDataModelDirectorID() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(teacherIDDataModel, programmeDataModel.getProgrammeDirectorID());
    }

    @Test
    public void shouldGetProgIDDM() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, degreeTypeIDDataModel, departmentIDDataModel, teacherIDDataModel);


        //assert
        assertEquals(programmeIDDM, programmeDataModel.getProgID());
    }

    @Test
    void shouldReturnTrueWhenProgrammeIDsAreEqual() {
        // arrange
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("Name1", "ACR1");

        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);

        ProgrammeDataModel p1 = new ProgrammeDataModel(id, "Name1", "ACR1", 6, 30, degreeTypeID, departmentID, teacherID);
        ProgrammeDataModel p2 = new ProgrammeDataModel(id, "Name2", "ACR2", 4, 20, degreeTypeID, departmentID, teacherID);

        // act + assert
        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());
    }


    @Test
    void shouldReturnFalseWhenProgrammeIDsAreDifferent() {
        // arrange
        ProgrammeIDDataModel id1 = new ProgrammeIDDataModel("Name1", "ACR1");
        ProgrammeIDDataModel id2 = new ProgrammeIDDataModel("Name2", "ACR2");

        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);

        ProgrammeDataModel p1 = new ProgrammeDataModel(id1, "Name1", "ACR1", 6, 30, degreeTypeID, departmentID, teacherID);
        ProgrammeDataModel p2 = new ProgrammeDataModel(id2, "Name2", "ACR2", 4, 20, degreeTypeID, departmentID, teacherID);

        // act + assert
        assertFalse(p1.equals(p2));
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void shouldReturnFalseWhenComparedWithNull() {
        //arrange
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("name", "ACR");
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);
        ProgrammeDataModel programme = new ProgrammeDataModel(id, "name", "ACR", 6, 30, degreeTypeID, departmentID, teacherID);

        //act+assert
        assertFalse(programme.equals(null));
    }

    @Test
    void shouldReturnFalseWhenComparedWithDifferentClass() {
        //arrange
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("name", "ACR");
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);
        ProgrammeDataModel programme = new ProgrammeDataModel(id, "name", "ACR", 6, 30, degreeTypeID, departmentID, teacherID);

        //act
        String differentClassObject = "Different Object";
        //assert
        assertFalse(programme.equals(differentClassObject));
    }

    @Test
    void shouldReturnTrueWhenObjectsAreEqual() {
        //arrange
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("name", "ACR");
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);
        ProgrammeDataModel programme1 = new ProgrammeDataModel(id, "name", "ACR", 6, 30, degreeTypeID, departmentID, teacherID);
        ProgrammeDataModel programme2 = new ProgrammeDataModel(id, "name", "ACR", 6, 30, degreeTypeID, departmentID, teacherID);

        //act+assert
        assertTrue(programme1.equals(programme2));
    }

    @Test
    void shouldReturnFalseWhenObjectsAreNotEqual() {
        //arrange
        ProgrammeIDDataModel id1 = new ProgrammeIDDataModel("name1", "ACR1");
        ProgrammeIDDataModel id2 = new ProgrammeIDDataModel("name2", "ACR2");
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);
        ProgrammeDataModel programme1 = new ProgrammeDataModel(id1, "name1", "ACR1", 6, 30, degreeTypeID, departmentID, teacherID);
        ProgrammeDataModel programme2 = new ProgrammeDataModel(id2, "name2", "ACR2", 6, 30, degreeTypeID, departmentID, teacherID);

        //act+assert
        assertFalse(programme1.equals(programme2));
    }

    @Test
    void shouldReturnEqualHashCodesForEqualObjects() {
        //arrange
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("name", "ACR");
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);
        ProgrammeDataModel programme1 = new ProgrammeDataModel(id, "name", "ACR", 6, 30, degreeTypeID, departmentID, teacherID);
        ProgrammeDataModel programme2 = new ProgrammeDataModel(id, "name", "ACR", 6, 30, degreeTypeID, departmentID, teacherID);

        //act+assert
        assertEquals(programme1.hashCode(), programme2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodesForDifferentObjects() {
        //arrnage
        ProgrammeIDDataModel id1 = new ProgrammeIDDataModel("name1", "ACR1");
        ProgrammeIDDataModel id2 = new ProgrammeIDDataModel("name2", "ACR2");
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);

        ProgrammeDataModel programme1 = new ProgrammeDataModel(id1, "name1", "ACR1", 6, 30, degreeTypeID, departmentID, teacherID);
        ProgrammeDataModel programme2 = new ProgrammeDataModel(id2, "name2", "ACR2", 6, 30, degreeTypeID, departmentID, teacherID);

        //act+assert
        assertNotEquals(programme1.hashCode(), programme2.hashCode());
    }

    @Test
    void shouldReturnTrueWhenComparedWithItself() {
        //arrange
        ProgrammeIDDataModel programmeID = new ProgrammeIDDataModel("name", "ACR");
        DegreeTypeIDDataModel degreeTypeID = mock(DegreeTypeIDDataModel.class);
        DepartmentIDDataModel departmentID = mock(DepartmentIDDataModel.class);
        TeacherIDDataModel teacherID = mock(TeacherIDDataModel.class);
        ProgrammeDataModel programme = new ProgrammeDataModel(programmeID, "name", "ACR", 6, 30, degreeTypeID, departmentID, teacherID);

        //act+assert
        assertTrue(programme.equals(programme));
    }







}
