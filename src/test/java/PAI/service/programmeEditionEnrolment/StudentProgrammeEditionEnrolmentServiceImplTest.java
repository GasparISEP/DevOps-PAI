package PAI.service.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.assembler.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentAssemblerImpl;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentProgrammeEditionEnrolmentServiceImplTest {

    private IProgrammeEnrolmentRepository programmeEnrolmentRepository;
    private IProgrammeEditionRepository programmeEditionRepository;
    private StudentProgrammeEditionEnrolmentAssemblerImpl assembler;

    private StudentProgrammeEditionEnrolmentServiceImpl service;

    @BeforeEach
    void setUp() {
        programmeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        assembler = mock(StudentProgrammeEditionEnrolmentAssemblerImpl.class);

        service = new StudentProgrammeEditionEnrolmentServiceImpl(
                programmeEnrolmentRepository,
                programmeEditionRepository,
                assembler
        );
    }

    @Test
    void shouldReturnListOfProgrammeEditionDTOs() throws Exception {
        // Arrange
        StudentID studentID = new StudentID(1500000);
        ProgrammeID programmeID = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("Informática"),
                new Acronym("LEI")
        );
        List<ProgrammeID> programmeIDs = List.of(programmeID);

        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new ProgrammeEditionID(programmeID, schoolYearID),
                programmeID,
                schoolYearID
        );

        List<ProgrammeEdition> editions = List.of(programmeEdition);
        StudentProgrammeEditionEnrolmentDTO dto = new StudentProgrammeEditionEnrolmentDTO("LEI", "Informática", schoolYearID.getSchoolYearID().toString());

        when(programmeEnrolmentRepository.findProgrammesByStudent(studentID)).thenReturn(programmeIDs);
        when(programmeEditionRepository.findByProgrammeIDs(programmeIDs)).thenReturn(editions);
        when(assembler.toDTO(programmeEdition)).thenReturn(dto);

        // Act
        List<StudentProgrammeEditionEnrolmentDTO> result = service.findAvailableProgrammeEditionsForStudent(studentID);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("LEI", result.get(0).getProgrammeAcronym());
        assertEquals("Informática", result.get(0).getProgrammeName());
    }
}
