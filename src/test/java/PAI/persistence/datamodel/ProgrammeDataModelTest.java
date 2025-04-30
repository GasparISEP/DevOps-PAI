package PAI.persistence.datamodel;

import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
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
    void defaultConstructorInitializesFieldsToDefaults() {
        ProgrammeDataModel dataModel = new ProgrammeDataModel();
        assertNotNull(dataModel);
        assertNull(dataModel.getName());
        assertNull(dataModel.getAcronym());
        assertEquals(0, dataModel.getQuantSemesters());
        assertEquals(0, dataModel.getQuantEcts());
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
        assertEquals(30, programmeDataModel.getQuantEcts());
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
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("name", "ACR");
        ProgrammeDataModel programme = new ProgrammeDataModel(id, "name", "ACR", 6, 30, null, null, null);

        assertFalse(programme.equals(null));
    }

    @Test
    void shouldReturnFalseWhenComparedWithDifferentClass() {
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("name", "ACR");
        ProgrammeDataModel programme = new ProgrammeDataModel(id, "name", "ACR", 6, 30, null, null, null);

        String differentClassObject = "Different Object";

        assertFalse(programme.equals(differentClassObject));
    }

    @Test
    void shouldReturnTrueWhenObjectsAreEqual() {
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("name", "ACR");
        ProgrammeDataModel programme1 = new ProgrammeDataModel(id, "name", "ACR", 6, 30, null, null, null);
        ProgrammeDataModel programme2 = new ProgrammeDataModel(id, "name", "ACR", 6, 30, null, null, null);

        assertTrue(programme1.equals(programme2));
    }

    @Test
    void shouldReturnFalseWhenObjectsAreNotEqual() {
        ProgrammeIDDataModel id1 = new ProgrammeIDDataModel("name1", "ACR1");
        ProgrammeIDDataModel id2 = new ProgrammeIDDataModel("name2", "ACR2");

        ProgrammeDataModel programme1 = new ProgrammeDataModel(id1, "name1", "ACR1", 6, 30, null, null, null);
        ProgrammeDataModel programme2 = new ProgrammeDataModel(id2, "name2", "ACR2", 6, 30, null, null, null);

        assertFalse(programme1.equals(programme2));
    }

    @Test
    void shouldReturnEqualHashCodesForEqualObjects() {
        ProgrammeIDDataModel id = new ProgrammeIDDataModel("name", "ACR");
        ProgrammeDataModel programme1 = new ProgrammeDataModel(id, "name", "ACR", 6, 30, null, null, null);
        ProgrammeDataModel programme2 = new ProgrammeDataModel(id, "name", "ACR", 6, 30, null, null, null);

        assertEquals(programme1.hashCode(), programme2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodesForDifferentObjects() {
        ProgrammeIDDataModel id1 = new ProgrammeIDDataModel("name1", "ACR1");
        ProgrammeIDDataModel id2 = new ProgrammeIDDataModel("name2", "ACR2");

        ProgrammeDataModel programme1 = new ProgrammeDataModel(id1, "name1", "ACR1", 6, 30, null, null, null);
        ProgrammeDataModel programme2 = new ProgrammeDataModel(id2, "name2", "ACR2", 6, 30, null, null, null);

        assertNotEquals(programme1.hashCode(), programme2.hashCode());
    }

    @Test
    void shouldReturnTrueWhenComparedWithItself() {
        ProgrammeIDDataModel programmeID = new ProgrammeIDDataModel("name", "ACR");
        ProgrammeDataModel programme = new ProgrammeDataModel(programmeID, "name", "ACR", 6, 30, null, null, null);

        assertTrue(programme.equals(programme));
    }







}
