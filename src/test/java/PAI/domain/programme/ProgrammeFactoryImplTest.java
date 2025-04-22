package PAI.domain.programme;

import PAI.VOs.*;
import PAI.factory.ProgrammeFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeFactoryImplTest {
    @Test

    void shouldCreatNewProgramme() throws Exception {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        //act
        ProgrammeFactoryImpl factory = new ProgrammeFactoryImpl();
        Programme programme = factory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, department, programmeDirectorID);

        //assert
        assertNotNull(programme);
        assertEquals(name, programme.getProgrammeName());
        assertEquals(acronym, programme.getAcronym());
        assertEquals(quantityOfEcts, programme.getQuantEcts());
        assertEquals(quantityOfSemesters, programme.getQuantSemesters());
        assertEquals(degreeTypeID, programme.getDegreeTypeID());
        assertEquals(department, programme.getDepartment());
        assertEquals(programmeDirectorID, programme.getProgrammeDirectorID());
    }
}
