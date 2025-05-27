package PAI.dto.Programme;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeVOsDTOTest {

    @Test
    void shouldCreateProgrammeVOsDTOWithValidValues() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);

        //act
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(name, acronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        //assert
        assertEquals(name, programmeVOsDTO.name());
        assertEquals(acronym, programmeVOsDTO.acronym());
        assertEquals(maxEcts, programmeVOsDTO.maxEcts());
        assertEquals(quantSemesters, programmeVOsDTO.quantSemesters());
        assertEquals(degreeTypeID, programmeVOsDTO.degreeTypeID());
        assertEquals(departmentID, programmeVOsDTO.departmentID());
        assertEquals(teacherID, programmeVOsDTO.teacherID());
    }

}