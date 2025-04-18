package PAI.persistence.datamodel;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProgrammeDataModelTest {

    private ProgrammeDataModel registerProgrammeDataModel() {
        // Arrange
        Programme programme = mock(Programme.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantSemesters semesters = mock(QuantSemesters.class);
        QuantEcts ects = mock(QuantEcts.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID directorID = mock(TeacherID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);

        // Mocks for ProgrammeID
        when(programme.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getAcronym()).thenReturn(acronym);
        when(programmeID.getName()).thenReturn(name);

        // Mocking the methods of Acronym and Name
        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("Engenharia Informática");
        when(acronym.getAcronym()).thenReturn("EI");

        // Mocks for Programme attributes
        when(programme.getProgrammeName()).thenReturn(name);
        when(programme.getAcronym()).thenReturn(acronym);
        when(programme.getQuantSemesters()).thenReturn(semesters);
        when(semesters.getQuantityOfSemesters()).thenReturn(6);

        when(programme.getQuantEcts()).thenReturn(ects);
        when(ects.getQuantEcts()).thenReturn(180);

        when(programme.getDegreeTypeID()).thenReturn(degreeTypeID);
        when(degreeTypeID.getDTID()).thenReturn("MSC");

        when(programme.getDepartment()).thenReturn(departmentID);

        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEP");

        when(programme.getProgrammeDirectorID()).thenReturn(directorID);
        when(directorID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("DIR01");

        return new ProgrammeDataModel(programme);
    }

    @Test
    public void defaultConstructorInitializesFieldsToDefaults() {
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
    public void createProgrammeDataModel()  {
        // Act
        ProgrammeDataModel programmeDataModel = registerProgrammeDataModel();

        // Assert
        assertNotNull(programmeDataModel);
    }

    @Test
    public void getProgrammeDataModelName() {
        // Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        // Assert
        assertEquals("Engenharia Informática", dataModel.getName());  // Using mocked value
    }

    @Test
    public void getProgrammeDataModelAcronym() {
        // Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        // Assert
        assertEquals("EI", dataModel.getAcronym());
    }

    @Test
    public void getProgrammeDataModelQuantSemesters() {
        // Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        // Assert
        assertEquals(6, dataModel.getQuantSemesters());
    }

    @Test
    public void getProgrammeDataModelQuantEcts() {
        // Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        // Assert
        assertEquals(180, dataModel.getQuantEcts());
    }

    @Test
    public void getProgrammeDataModelDegreeTypeID() {
        // Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        // Assert
        assertEquals("MSC", dataModel.getDegreeTypeID());
    }

    @Test
    public void getProgrammeDataModelDepartment() {
        // Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        // Assert
        assertEquals("DEP", dataModel.getDepartmentID());
    }

    @Test
    public void getProgrammeDataModelDirectorID() {
        // Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        // Assert
        assertEquals("DIR01", dataModel.getProgrammeDirectorID());
    }
}
