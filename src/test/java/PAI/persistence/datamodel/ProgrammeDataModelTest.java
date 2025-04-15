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


        when(programme.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getName()).thenReturn(new NameWithNumbersAndSpecialChars("Engenharia Informática"));
        when(programmeID.getAcronym()).thenReturn(new Acronym("EI"));

        when(programme.getProgrammeName()).thenReturn(name);
        when(name.toString()).thenReturn("Engenharia Informática");

        when(programme.getAcronym()).thenReturn(acronym);
        when(acronym.toString()).thenReturn("EI");

        when(programme.getQuantSemesters()).thenReturn(semesters);
        when(semesters.getQuantityOfSemesters()).thenReturn(6);

        when(programme.getQuantEcts()).thenReturn(ects);
        when(ects.getQuantEcts()).thenReturn(180);

        when(programme.getDegreeTypeID()).thenReturn(degreeTypeID);
        when(degreeTypeID.toString()).thenReturn("MSc");

        when(programme.getDepartment()).thenReturn(departmentID);
        when(departmentID.toString()).thenReturn("DEP");

        when(programme.getProgrammeDirectorID()).thenReturn(directorID);
        when(directorID.toString()).thenReturn("DIR01");

        return new ProgrammeDataModel(programme);
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
        assertEquals("Engenharia Informática", dataModel.getName());  // Usando o valor mockado
    }

    @Test
    public void getProgrammeDataModelAcronym() {
        //Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        //Assert
        assertEquals("EI", dataModel.getAcronym());
    }

    @Test
    public void getProgrammeDataModelQuantSemesters() {
        //Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        //Assert
        assertEquals(6, dataModel.getQuantSemesters());
    }

    @Test
    public void getProgrammeDataModelQuantEcts() {
        //Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        //Assert
        assertEquals(180, dataModel.getQuantEcts());
    }

    @Test
    public void getProgrammeDataModelDegreeTypeID() {
        //Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        //Assert
        assertEquals("MSc", dataModel.getDegreeTypeID());
    }

    @Test
    public void getProgrammeDataModelDepartment() {
        //Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        //Assert
        assertEquals("DEP", dataModel.getDepartmentID());
    }

    @Test
    public void getProgrammeDataModelDirectorID() {
        //Act
        ProgrammeDataModel dataModel = registerProgrammeDataModel();

        //Assert
        assertEquals("DIR01", dataModel.getProgrammeDirectorID());
    }
}
