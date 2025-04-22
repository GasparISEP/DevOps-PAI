package PAI.persistence.datamodel;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
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

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, "id",departmentIDDataModel, teacherIDDataModel);

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

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, "id", departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals("name", programmeDataModel.getName());
    }

    @Test
    public void getProgrammeDataModelAcronym() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, "id", departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals("ACR", programmeDataModel.getAcronym());
    }

    @Test
    public void getProgrammeDataModelQuantSemesters() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, "id", departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(6, programmeDataModel.getQuantSemesters());
    }

    @Test
    public void getProgrammeDataModelQuantEcts() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, "id", departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(30, programmeDataModel.getQuantEcts());
    }

    @Test
    public void getProgrammeDataModelDegreeTypeID() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, "id", departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals("id", programmeDataModel.getDegreeTypeID());
    }

    @Test
    public void getProgrammeDataModelDepartment() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, "id", departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(departmentIDDataModel, programmeDataModel.getDepartmentID());
    }

    @Test
    public void getProgrammeDataModelDirectorID() {
        //arrange
        ProgrammeIDDataModel programmeIDDM = mock(ProgrammeIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        //act
        ProgrammeDataModel programmeDataModel = new ProgrammeDataModel(programmeIDDM, "name", "ACR", 6, 30, "id", departmentIDDataModel, teacherIDDataModel);

        // Assert
        assertEquals(teacherIDDataModel, programmeDataModel.getProgrammeDirectorID());
    }
}
