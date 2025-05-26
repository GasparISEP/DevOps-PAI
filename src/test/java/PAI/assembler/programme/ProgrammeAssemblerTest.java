package PAI.assembler.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeAssemblerTest {

    @Test
    void shouldCreateProgrammeVOsDTOFromProgrammeRequestDTO() throws Exception {
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

                () -> assertEquals(name, result.getName().getnameWithNumbersAndSpecialChars()),
                () -> assertEquals(acronym, result.getAcronym().getAcronym()),
                () -> assertEquals(maxECTS, result.getMaxEcts().getMaxEcts()),
                () -> assertEquals(quantSemesters, result.getQuantSemesters().getQuantityOfSemesters()),
                () -> assertEquals(degreeTypeID, result.getDegreeTypeID().getDTID()),
                () -> assertEquals(departmentID, result.getDepartmentID().getAcronym().getAcronym()),
                () -> assertEquals(teacherID, result.getTeacherID().getTeacherAcronym().getAcronym())
        );
    }

    @Test
    void shouldCreateProgrammeResponseDTOFromProgramme() {
        //arrange
        ProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        Programme programmeDouble = mock(Programme.class);

        NameWithNumbersAndSpecialChars nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronymDouble = mock(Acronym.class);
        MaxEcts maxEctsDouble = mock(MaxEcts.class);
        QuantSemesters quantSemestersDouble = mock(QuantSemesters.class);
        String degreeTypeName = "Master";
        String departmentName = "Astronomy";
        String teacherName = "AAA";

        when(programmeDouble.getProgrammeName()).thenReturn(nameDouble);
        when(nameDouble.getnameWithNumbersAndSpecialChars()).thenReturn("Data Science");
        when(programmeDouble.getAcronym()).thenReturn(acronymDouble);
        when(acronymDouble.getAcronym()).thenReturn("DSD");
        when(programmeDouble.getMaxEcts()).thenReturn(maxEctsDouble);
        when(maxEctsDouble.getMaxEcts()).thenReturn(30);
        when(programmeDouble.getQuantSemesters()).thenReturn(quantSemestersDouble);
        when(quantSemestersDouble.getQuantityOfSemesters()).thenReturn(6);

        //act
        ProgrammeResponseDTO programmeResponseDTO = programmeAssembler.fromDomainToDTO(programmeDouble, degreeTypeName, departmentName, teacherName);

        //assert
        assertAll(
                () -> assertEquals("Data Science", programmeResponseDTO.getName()),
                () -> assertEquals("DSD", programmeResponseDTO.getAcronym()),
                () -> assertEquals(30, programmeResponseDTO.getMaxECTS()),
                () -> assertEquals(6, programmeResponseDTO.getQuantSemesters()),
                () -> assertEquals("Master", programmeResponseDTO.getDegreeTypeName()),
                () -> assertEquals("Astronomy", programmeResponseDTO.getDepartmentName()),
                () -> assertEquals("AAA", programmeResponseDTO.getTeacherName())
        );
    }

    @Test
    void shouldCreateProgrammeIDDTOFromProgrammeID() {
        // Arrange
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        when(programmeIDDouble.getProgrammeName()).thenReturn("Computer Science");
        when(programmeIDDouble.getProgrammeAcronym()).thenReturn("CSE");

        // Act
        ProgrammeIDDTO programmeIDDTO = programmeAssembler.toDTO(programmeIDDouble);

        // Assert
        assertNotNull(programmeIDDTO);
        assertEquals(programmeIDDTO.name(), "Computer Science");
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

}