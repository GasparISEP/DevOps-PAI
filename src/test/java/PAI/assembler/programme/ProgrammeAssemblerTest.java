package PAI.assembler.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeIDResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeAssemblerTest {

    @Test
    void shouldCreateProgrammeVOsDTOFromProgrammeDTO() {
        //arrange
        ProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeDTO programmeDTODouble = mock(ProgrammeDTO.class);

        String name = "Data Science";
        String acronym = "DSD";
        int maxECTS = 30;
        int quantSemesters = 6;
        String degreeTypeID = "123e4567-e89b-12d3-a456-426614174000";
        String departmentID = "DEI";
        String teacherID = "AAA";

        when(programmeDTODouble.name()).thenReturn(name);
        when(programmeDTODouble.acronym()).thenReturn(acronym);
        when(programmeDTODouble.maxECTS()).thenReturn(maxECTS);
        when(programmeDTODouble.quantSemesters()).thenReturn(quantSemesters);
        when(programmeDTODouble.degreeTypeID()).thenReturn(degreeTypeID);
        when(programmeDTODouble.departmentID()).thenReturn(departmentID);
        when(programmeDTODouble.teacherID()).thenReturn(teacherID);

        //act
        ProgrammeVOsDTO result = programmeAssembler.fromDTOToDomain(programmeDTODouble);

        //assert
        assertAll(

                () -> assertEquals(name, result.name().getNameWithNumbersAndSpecialChars()),
                () -> assertEquals(acronym, result.acronym().getAcronym()),
                () -> assertEquals(maxECTS, result.maxEcts().getMaxEcts()),
                () -> assertEquals(quantSemesters, result.quantSemesters().getQuantityOfSemesters()),
                () -> assertEquals(degreeTypeID, result.degreeTypeID().getDTID()),
                () -> assertEquals(departmentID, result.departmentID().getAcronym().getAcronym()),
                () -> assertEquals(teacherID, result.teacherID().getTeacherAcronym().getAcronym())
        );
    }

    @Test
    void shouldCreateProgrammeDTOFromProgramme() {
        //arrange
        ProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        Programme programmeDouble = mock(Programme.class);

        NameWithNumbersAndSpecialChars nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronymDouble1 = mock(Acronym.class);
        MaxEcts maxEctsDouble = mock(MaxEcts.class);
        QuantSemesters quantSemestersDouble = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeIDDouble = mock(DegreeTypeID.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronymDouble = mock(DepartmentAcronym.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronymDouble = mock(TeacherAcronym.class);


        when(programmeDouble.getProgrammeName()).thenReturn(nameDouble);
        when(nameDouble.getNameWithNumbersAndSpecialChars()).thenReturn("Data Science");
        when(programmeDouble.getAcronym()).thenReturn(acronymDouble1);
        when(acronymDouble1.getAcronym()).thenReturn("DSD");
        when(programmeDouble.getMaxEcts()).thenReturn(maxEctsDouble);
        when(maxEctsDouble.getMaxEcts()).thenReturn(30);
        when(programmeDouble.getQuantSemesters()).thenReturn(quantSemestersDouble);
        when(quantSemestersDouble.getQuantityOfSemesters()).thenReturn(6);
        when(programmeDouble.getDegreeTypeID()).thenReturn(degreeTypeIDDouble);
        when(degreeTypeIDDouble.getDTID()).thenReturn("DTID");
        when(programmeDouble.getDepartment()).thenReturn(departmentIDDouble);
        when(departmentIDDouble.getAcronym()).thenReturn(departmentAcronymDouble);
        when(departmentAcronymDouble.getAcronym()).thenReturn("AAA");
        when(programmeDouble.getProgrammeDirectorID()).thenReturn(teacherID);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronymDouble);
        when(teacherAcronymDouble.getAcronym()).thenReturn("ABC");

        //act
        ProgrammeDTO programmeDTO = programmeAssembler.fromDomainToDTO(programmeDouble);

        //assert
        assertAll(
                () -> assertEquals("Data Science", programmeDTO.name()),
                () -> assertEquals("DSD", programmeDTO.acronym()),
                () -> assertEquals(30, programmeDTO.maxECTS()),
                () -> assertEquals(6, programmeDTO.quantSemesters()),
                () -> assertEquals("DTID", programmeDTO.degreeTypeID()),
                () -> assertEquals("AAA", programmeDTO.departmentID()),
                () -> assertEquals("ABC", programmeDTO.teacherID())
        );
    }

    @Test
    void shouldCreateProgrammeIDDTOFromProgrammeID() {
        // Arrange
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        when(programmeIDDouble.getProgrammeAcronym()).thenReturn("CSE");

        // Act
        ProgrammeIDDTO programmeIDDTO = programmeAssembler.toDTO(programmeIDDouble);

        // Assert
        assertNotNull(programmeIDDTO);
        assertEquals(programmeIDDTO.acronym(), "CSE");
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIDIsNull() {
        // Arrange
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeID programmeIDDouble = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            programmeAssembler.toDTO(programmeIDDouble);
        });
    }

    @Test
    void shouldCreateProgrammeIDResponseDTOFromProgrammeIDDTO() {
        // Arrange
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);
        when(programmeIDDTO.acronym()).thenReturn("CSE");

        // Act
        ProgrammeIDResponseDTO result = programmeAssembler.toResponseDTO(programmeIDDTO);

        // Assert
        assertNotNull(result);
        assertEquals(result.acronym(), "CSE");
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIDDTOIsNull() {
        // Arrange
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeIDDTO programmeIDDTO = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            programmeAssembler.toResponseDTO(programmeIDDTO);
        });
    }
}