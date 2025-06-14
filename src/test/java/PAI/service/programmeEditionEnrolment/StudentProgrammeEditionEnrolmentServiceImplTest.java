package PAI.service.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.assembler.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentAssemblerImpl;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentProgrammeEditionEnrolmentServiceImplTest {

    private IProgrammeEnrolmentRepository programmeEnrolmentRepository;
    private IProgrammeEditionRepository programmeEditionRepository;
    private IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository;
    private IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory;
    private StudentProgrammeEditionEnrolmentAssemblerImpl assembler;
    private IProgrammeEditionControllerAssembler programmeEditionControllerAssembler;
    private ISchoolYearRepository schoolYearRepository;
    private IProgrammeRepository programmeRepository;

    private StudentProgrammeEditionEnrolmentServiceImpl service;

    @BeforeEach
    void setUp() {
        programmeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        programmeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        assembler = mock(StudentProgrammeEditionEnrolmentAssemblerImpl.class);
        programmeEditionControllerAssembler = mock(IProgrammeEditionControllerAssembler.class);
        schoolYearRepository = mock(ISchoolYearRepository.class);
        programmeRepository = mock(IProgrammeRepository.class);

        service = new StudentProgrammeEditionEnrolmentServiceImpl(
                programmeEnrolmentRepository,
                programmeEditionRepository,
                programmeEditionEnrolmentRepository,
                programmeEditionEnrolmentFactory,
                assembler,
                programmeEditionControllerAssembler,
                schoolYearRepository,
                programmeRepository

                );
    }

    @Test
    void shouldReturnListOfProgrammeEditionDTOs() {
        try {
            // Arrange
            StudentID studentID = new StudentID(1500000);
            ProgrammeID programmeID = new ProgrammeID(
                    new Acronym("LEI")
            );
            List<ProgrammeID> programmeIDs = List.of(programmeID);

            SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
            ProgrammeEditionGeneratedID programmeEditionGeneratedID = new ProgrammeEditionGeneratedID(UUID.randomUUID());
            ProgrammeEdition programmeEdition = new ProgrammeEdition(
                    new ProgrammeEditionID(programmeID, schoolYearID),
                    programmeID,
                    schoolYearID,
                    programmeEditionGeneratedID
            );

            List<ProgrammeEdition> editions = List.of(programmeEdition);
            StudentProgrammeEditionEnrolmentDTO dto = new StudentProgrammeEditionEnrolmentDTO("LEI", schoolYearID.getSchoolYearID().toString());

            when(programmeEnrolmentRepository.findProgrammesByStudent(studentID)).thenReturn(programmeIDs);
            when(programmeEditionRepository.findByProgrammeIDs(programmeIDs)).thenReturn(editions);
            when(assembler.toDTO(programmeEdition)).thenReturn(dto);

            // Act
            List<StudentProgrammeEditionEnrolmentDTO> result = service.findAvailableProgrammeEditionsForStudent(studentID);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("LEI", result.get(0).getProgrammeAcronym());
        } catch (Exception e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    void enrolStudentInProgrammeEdition_shouldCreateAndSaveEnrolment() {
        try {
            // Arrange
            StudentID studentID = new StudentID(1234567);
            ProgrammeID programmeID = new ProgrammeID(new Acronym("LEI"));
            SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
            ProgrammeEditionID editionID = new ProgrammeEditionID(programmeID, schoolYearID);
            ProgrammeEditionGeneratedID programmeEditionGeneratedID = new ProgrammeEditionGeneratedID(UUID.randomUUID());

            ProgrammeEdition edition = new ProgrammeEdition(editionID, programmeID, schoolYearID, programmeEditionGeneratedID);

            ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(editionID, studentID);
            ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

            when(programmeEnrolmentRepository.findProgrammesByStudent(studentID))
                    .thenReturn(List.of(programmeID));
            when(programmeEditionRepository.findByID(editionID))
                    .thenReturn(Optional.of(edition));
            when(programmeEditionEnrolmentRepository.existsByID(enrolmentID))
                    .thenReturn(false);
            when(programmeEditionEnrolmentFactory.create(enrolmentID, studentID, editionID))
                    .thenReturn(enrolment);

            // Act
            service.enrolStudentInProgrammeEdition(studentID, programmeID, schoolYearID);

            // Assert
            verify(programmeEditionEnrolmentRepository).save(enrolment);
        } catch (Exception e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    void enrolStudentInProgrammeEdition_shouldThrowIfStudentNotEnrolledInProgramme() {
        StudentID studentID = new StudentID(1500001);
        ProgrammeID programmeID = new ProgrammeID(new Acronym("LEI"));
        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());

        when(programmeEnrolmentRepository.findProgrammesByStudent(studentID))
                .thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () ->
                service.enrolStudentInProgrammeEdition(studentID, programmeID, schoolYearID));
    }

    @Test
    void enrolStudentInProgrammeEdition_shouldThrowIfAlreadyEnrolled() {
        try {
            StudentID studentID = new StudentID(1500001);
            ProgrammeID programmeID = new ProgrammeID(new Acronym("LEI"));
            SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
            ProgrammeEditionID editionID = new ProgrammeEditionID(programmeID, schoolYearID);
            ProgrammeEditionGeneratedID programmeEditionGeneratedID = new ProgrammeEditionGeneratedID(UUID.randomUUID());
            ProgrammeEdition edition = new ProgrammeEdition(editionID, programmeID, schoolYearID,programmeEditionGeneratedID);
            ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(editionID, studentID);

            when(programmeEnrolmentRepository.findProgrammesByStudent(studentID))
                    .thenReturn(List.of(programmeID));
            when(programmeEditionRepository.findByID(editionID))
                    .thenReturn(Optional.of(edition));
            when(programmeEditionEnrolmentRepository.existsByID(enrolmentID))
                    .thenReturn(true);

            assertThrows(IllegalStateException.class, () ->
                    service.enrolStudentInProgrammeEdition(studentID, programmeID, schoolYearID));
        } catch (Exception e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    void enrolStudentInProgrammeEdition_shouldThrowIfProgrammeEditionNotFound() {
        try {
            StudentID studentID = new StudentID(1500001);
            ProgrammeID programmeID = new ProgrammeID(
                    new Acronym("LEI")
            );
            SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

            when(programmeEnrolmentRepository.findProgrammesByStudent(studentID))
                    .thenReturn(List.of(programmeID));
            when(programmeEditionRepository.findByID(programmeEditionID))
                    .thenReturn(Optional.empty());

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                    service.enrolStudentInProgrammeEdition(studentID, programmeID, schoolYearID));

            assertEquals("ProgrammeEdition not found.", exception.getMessage());
        } catch (Exception e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }

    @Test
    void shouldReturnLocalDate_whenProgrammeEnrolmentExists() {
        // Arrange
        ProgrammeEnrolmentGeneratedID generatedID = mock(ProgrammeEnrolmentGeneratedID.class);
        LocalDate expectedDate = LocalDate.of(2024, 9, 15);

        ProgrammeEnrolment enrolment = mock(ProgrammeEnrolment.class);
        Date dateVO = mock(Date.class);

        when(programmeEnrolmentRepository.findByGeneratedID(generatedID)).thenReturn(Optional.of(enrolment));
        when(enrolment.getDate()).thenReturn(dateVO);
        when(dateVO.getLocalDate()).thenReturn(expectedDate);

        // Act
        LocalDate result = service.findDateByProgrammeEnrolmentGeneratedID(generatedID);

        // Assert
        assertEquals(expectedDate, result);
    }

    @Test
    void shouldReturnProgrammeID_whenProgrammeEnrolmentExists() {
        // Arrange
        ProgrammeEnrolmentGeneratedID generatedID = mock(ProgrammeEnrolmentGeneratedID.class);
        ProgrammeEnrolment enrolment = mock(ProgrammeEnrolment.class);
        ProgrammeID expectedProgrammeID = mock(ProgrammeID.class);

        when(programmeEnrolmentRepository.findByGeneratedID(generatedID))
                .thenReturn(Optional.of(enrolment));
        when(enrolment.getProgrammeID()).thenReturn(expectedProgrammeID);

        // Act
        ProgrammeID result = service.findProgrammeIDByProgrammeEnrolmentGeneratedID(generatedID);

        // Assert
        assertEquals(expectedProgrammeID, result);
    }

    @Test
    void shouldReturnProgrammeEditionIDs_whenValidProgrammeIDAndDateProvided() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        LocalDate date = LocalDate.of(2025, 6, 11);

        ProgrammeEditionID editionID = mock(ProgrammeEditionID.class);
        when(programmeEditionRepository.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeID, date))
                .thenReturn(List.of(editionID));

        // Act
        List<ProgrammeEditionID> result = service.getAvailableProgrammeEditions(programmeID, date);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(editionID, result.get(0));
    }

    @Test
    void findStudentIDByProgrammeEnrolmentGeneratedID_WhenEnrolmentExists_ReturnsStudentID() {
        // Arrange
        ProgrammeEnrolmentGeneratedID generatedID = new ProgrammeEnrolmentGeneratedID(UUID.randomUUID());
        ProgrammeEnrolment domain = mock(ProgrammeEnrolment.class);
        StudentID expectedSID = new StudentID(1234567);


        when(programmeEnrolmentRepository.findByGeneratedID(generatedID))
                .thenReturn(Optional.of(domain));
        when(domain.getStudentID()).thenReturn(expectedSID);

        // Act
        StudentID actualSID = service.findStudentIDByProgrammeEnrolmentGeneratedID(generatedID);

        // Assert
        assertEquals(expectedSID, actualSID);
    }

    @Test
    void findStudentIDByProgrammeEnrolmentGeneratedID_WhenEnrolmentNotFound_ThrowsNoSuchElementException() {
        // Arrange
        ProgrammeEnrolmentGeneratedID generatedID = new ProgrammeEnrolmentGeneratedID(UUID.randomUUID());
        when(programmeEnrolmentRepository.findByGeneratedID(generatedID))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () ->
                service.findStudentIDByProgrammeEnrolmentGeneratedID(generatedID)
        );
    }

}
