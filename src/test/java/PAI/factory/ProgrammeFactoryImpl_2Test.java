package PAI.factory;

import PAI.VOs.*;
import PAI.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeFactoryImpl_2Test {
    @Test

    void shouldCreatNewProgramme() throws Exception {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters = mock(QuantSemesters.class);
        DegreeType_ID degreeTypeID = mock(DegreeType_ID.class);
        Department department = mock(Department.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        //act
        ProgrammeFactoryImpl_2 factory = new ProgrammeFactoryImpl_2();
        Programme_2 programme = factory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, department, programmeDirectorID);

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
