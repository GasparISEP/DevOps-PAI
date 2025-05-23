package PAI.dto.Programme;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeVOsDTOTest {

    static class ProgrammeTestData {
        NameWithNumbersAndSpecialChars nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronymDouble = mock(Acronym.class);
        MaxEcts maxEctsDouble = mock(MaxEcts.class);
        QuantSemesters quantSemestersDouble = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
    }

    @Test
    void shouldCreateProgrammeVOsDTOWithTheGivenValues () {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();

        //act
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(data.nameDouble, data.acronymDouble, data.maxEctsDouble, data.quantSemestersDouble, data.degreeTypeID, data.departmentID, data.teacherID);

        //assert
        assertNotNull(programmeVOsDTO);

    }

    @Test
    void getNameShouldReturnName() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(data.nameDouble, data.acronymDouble, data.maxEctsDouble, data.quantSemestersDouble, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        NameWithNumbersAndSpecialChars result = programmeVOsDTO.getName();

        //assert
        assertEquals(result, data.nameDouble);
    }

    @Test
    void getAcronymShouldReturnAcronym() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(data.nameDouble, data.acronymDouble, data.maxEctsDouble, data.quantSemestersDouble, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        Acronym result = programmeVOsDTO.getAcronym();

        //assert
        assertEquals(result, data.acronymDouble);
    }

    @Test
    void getQuantEctsShouldReturnQuantityOfECTSCredits() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(data.nameDouble, data.acronymDouble, data.maxEctsDouble, data.quantSemestersDouble, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        MaxEcts result = programmeVOsDTO.getMaxEcts();

        //assert
        assertEquals(result, data.maxEctsDouble);
    }

    @Test
    void getQuantSemestersShouldReturnQuantityOfSemesters() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(data.nameDouble, data.acronymDouble, data.maxEctsDouble, data.quantSemestersDouble, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        QuantSemesters result = programmeVOsDTO.getQuantSemesters();

        //assert
        assertEquals(result, data.quantSemestersDouble);
    }

    @Test
    void getDegreeTypeIDShouldReturnDegreeTypeID() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(data.nameDouble, data.acronymDouble, data.maxEctsDouble, data.quantSemestersDouble, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        DegreeTypeID result = programmeVOsDTO.getDegreeTypeID();

        //assert
        assertEquals(result, data.degreeTypeID);
    }

    @Test
    void getDepartmentIDShouldReturnDepartmentID() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(data.nameDouble, data.acronymDouble, data.maxEctsDouble, data.quantSemestersDouble, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        DepartmentID result = programmeVOsDTO.getDepartmentID();

        //assert
        assertEquals(result, data.departmentID);
    }

    @Test
    void getTeacherIDShouldReturnTeacherID() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeVOsDTO programmeVOsDTO = new ProgrammeVOsDTO(data.nameDouble, data.acronymDouble, data.maxEctsDouble, data.quantSemestersDouble, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        TeacherID result = programmeVOsDTO.getTeacherID();

        //assert
        assertEquals(result, data.teacherID);
    }
}