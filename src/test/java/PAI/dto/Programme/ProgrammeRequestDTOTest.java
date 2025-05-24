package PAI.dto.Programme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeRequestDTOTest {

    static class ProgrammeTestData {
        String name = "Engenharia Informática";
        String acronym = "EIN";
        int quantECTS = 30;
        int quantSemesters = 6;
        String degreeTypeID = "123e4567-e89b-12d3-a456-426614174000";
        String departmentID = "DEI";
        String teacherID = "NPS";
    }

    @Test
    void shouldCreateProgrammeRequestDTO() {
        //arrange

        //act
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO();

        //assert
        assertNotNull(programmeRequestDTO);
    }

    @Test
    void shouldCreateProgrammeRequestDTOWithGivenValues () {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();

        //act
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO(data.name, data.acronym, data.quantECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        //assert
        assertNotNull(programmeRequestDTO);
    }

    @Test
    void getNameShouldReturnName() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO(data.name, data.acronym, data.quantECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        String name = programmeRequestDTO.getName();

        //assert
        assertEquals(name, data.name);
    }

    @Test
    void getAcronymShouldReturnAcronym() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO(data.name, data.acronym, data.quantECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        String acronym = programmeRequestDTO.getAcronym();

        //assert
        assertEquals(acronym, data.acronym);
    }

    @Test
    void getQuantECTSShouldReturnIntQuantityOfECTSCredits() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO(data.name, data.acronym, data.quantECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        int quantOfECTS = programmeRequestDTO.getMaxECTS();

        //assert
        assertEquals(quantOfECTS, data.quantECTS);
    }

    @Test
    void getQuantSemestersShouldReturnQuantityOfSemesters() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO(data.name, data.acronym, data.quantECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        int quantOfSemesters = programmeRequestDTO.getQuantSemesters();

        //assert
        assertEquals(quantOfSemesters, data.quantSemesters);
    }

    @Test
    void getDegreeTypeIDShouldReturnDegreeTypeID() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO(data.name, data.acronym, data.quantECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        String degreeTypeID = programmeRequestDTO.getDegreeTypeID();

        //assert
        assertEquals(degreeTypeID, data.degreeTypeID);
    }

    @Test
    void getDepartmentIDShouldReturnDepartmentID() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO(data.name, data.acronym, data.quantECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        String departmentID = programmeRequestDTO.getDepartmentID();

        //assert
        assertEquals(departmentID, data.departmentID);
    }

    @Test
    void getTeacherIDShouldReturnTeacherID() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeRequestDTO programmeRequestDTO = new ProgrammeRequestDTO(data.name, data.acronym, data.quantECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        //act
        String teacherID = programmeRequestDTO.getTeacherID();

        //assert
        assertEquals(teacherID, data.teacherID);
    }
}