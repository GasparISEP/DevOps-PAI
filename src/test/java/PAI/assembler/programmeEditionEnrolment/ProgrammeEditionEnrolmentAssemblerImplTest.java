package PAI.assembler.programmeEditionEnrolment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PAI.VOs.*;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;

public class ProgrammeEditionEnrolmentAssemblerImplTest {

    private ISchoolYearRepository schoolYearRepository;
    private ProgrammeEditionEnrolmentAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        schoolYearRepository = mock(ISchoolYearRepository.class);
        assembler = new ProgrammeEditionEnrolmentAssemblerImpl(schoolYearRepository);
    }

    @Test
    void toDtoList_ShouldConvertEnrolmentsToDtoList() throws Exception {
        // Arrange
        List<ProgrammeEditionID> programmeEditionIDs = new ArrayList<>();
        StudentID studentId = new StudentID(1000001);
        
        // Create test data
        ProgrammeID programmeId = new ProgrammeID(
            new NameWithNumbersAndSpecialChars("Test Programme"),
            new Acronym("TP")
        );
        SchoolYearID schoolYearId = new SchoolYearID(UUID.randomUUID());
        ProgrammeEditionID programmeEditionId = new ProgrammeEditionID(programmeId, schoolYearId);
        programmeEditionIDs.add(programmeEditionId);

        // Mock school year repository response
        SchoolYear schoolYear = mock(SchoolYear.class);
        Description description = new Description("2024-2025");
        when(schoolYear.getDescription()).thenReturn(description);
        when(schoolYearRepository.findBySchoolYearID(schoolYearId)).thenReturn(java.util.Optional.of(schoolYear));

        // Act
        List<ProgrammeEditionEnrolmentDetailDto> result = assembler.toDtoList(programmeEditionIDs, studentId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void toDtoList_ShouldThrowException_WhenEnrolmentsIsNull() {
        // Act & Assert
        StudentID studentId = mock(StudentID.class);    
        Exception exception = assertThrows(Exception.class, () -> {
            assembler.toDtoList(null, studentId);
        });
        
        assertEquals("Programme edition enrolment is null", exception.getMessage());
    }

    @Test
    void toDtoList_ShouldReturnEmptyList_WhenEnrolmentsIsEmpty() throws Exception {
        // Arrange
        List<ProgrammeEditionID> emptyList = new ArrayList<>();
        StudentID studentId = mock(StudentID.class);

        // Act
        List<ProgrammeEditionEnrolmentDetailDto> result = assembler.toDtoList(emptyList, studentId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
