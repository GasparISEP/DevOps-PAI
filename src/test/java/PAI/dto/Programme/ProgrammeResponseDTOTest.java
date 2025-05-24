package PAI.dto.Programme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeResponseDTOTest {

    static class ProgrammeTestData {
        String name = "Data Science";
        String acronym = "DSD";
        int maxECTS = 30;
        int quantSemesters = 6;
        String degreeTypeName = "Master";
        String departmentName = "Astronomy";
        String teacherName = "AAA";
    }

    @Test
    void shouldCreateProgrammeResponseDTO() {
        //arrange

        //act
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO();

        //assert
        assertNotNull(programmeResponseDTO);
    }

    @Test
    void shouldCreateProgrammeResponseDTOWithGivenValues() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();

        //act
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeName, data.departmentName, data.teacherName);

        //assert
        assertNotNull(programmeResponseDTO);
    }

    @Test
    void getNameShouldReturnName() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeName, data.departmentName, data.teacherName);

        //act
        String result = programmeResponseDTO.getName();

        //assert
        assertEquals(result, data.name);
    }

    @Test
    void getAcronymShouldReturnAcronym() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeName, data.departmentName, data.teacherName);

        //act
        String result = programmeResponseDTO.getAcronym();

        //assert
        assertEquals(result, data.acronym);
    }

    @Test
    void getQuantECTSShouldReturnQuantityOfECTSCredits() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeName, data.departmentName, data.teacherName);

        //act
        int result = programmeResponseDTO.getMaxECTS();

        //assert
        assertEquals(result, data.maxECTS);
    }

    @Test
    void getQuantSemestersShouldReturnQuantityOfSemesters() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeName, data.departmentName, data.teacherName);

        //act
        int result = programmeResponseDTO.getQuantSemesters();

        //assert
        assertEquals(result, data.quantSemesters);
    }

    @Test
    void getDegreeTypeNameShouldReturnDegreeTypeName() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeName, data.departmentName, data.teacherName);

        //act
        String result = programmeResponseDTO.getDegreeTypeName();

        //assert
        assertEquals(result, data.degreeTypeName);
    }

    @Test
    void getDepartmentNameShouldReturnDepartmentName() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeName, data.departmentName, data.teacherName);

        //act
        String result = programmeResponseDTO.getDepartmentName();

        //assert
        assertEquals(result, data.departmentName);
    }

    @Test
    void getTeacherNameShouldReturnTeacherName() {
        //arrange
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeResponseDTO programmeResponseDTO = new ProgrammeResponseDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeName, data.departmentName, data.teacherName);

        //act
        String result = programmeResponseDTO.getTeacherName();

        //assert
        assertEquals(result, data.teacherName);
    }
}