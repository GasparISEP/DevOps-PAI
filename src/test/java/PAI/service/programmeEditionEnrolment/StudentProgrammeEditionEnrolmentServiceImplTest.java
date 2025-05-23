package PAI.service.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.assembler.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentAssemblerImpl;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    private StudentProgrammeEditionEnrolmentServiceImpl service;

    @BeforeEach
    void setUp() {
        programmeEnrolmentRepository = mock(IProgrammeEnrolmentRepository.class);
        programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        programmeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        assembler = mock(StudentProgrammeEditionEnrolmentAssemblerImpl.class);

        service = new StudentProgrammeEditionEnrolmentServiceImpl(
                programmeEnrolmentRepository,
                programmeEditionRepository,
                programmeEditionEnrolmentRepository,
                programmeEditionEnrolmentFactory,
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
    @Test
    void enrolStudentInProgrammeEdition_shouldCreateAndSaveEnrolment() throws Exception {
        // Arrange
        StudentID studentID = new StudentID(1234567);
        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars("Informática"), new Acronym("LEI"));
        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
        ProgrammeEditionID editionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Mocks
        ProgrammeEdition edition = new ProgrammeEdition(editionID, programmeID, schoolYearID);
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
    }

    @Test
    void enrolStudentInProgrammeEdition_shouldThrowIfStudentNotEnrolledInProgramme() {
        // Arrange
        StudentID studentID = new StudentID(1500001);
        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars("Informática"), new Acronym("LEI"));
        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());

        // Mock: o estudante não está inscrito em nenhum programa
        when(programmeEnrolmentRepository.findProgrammesByStudent(studentID))
                .thenReturn(List.of()); // vazio

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                service.enrolStudentInProgrammeEdition(studentID, programmeID, schoolYearID));
    }

    @Test
    void enrolStudentInProgrammeEdition_shouldThrowIfAlreadyEnrolled() throws Exception {
        // Arrange
        StudentID studentID = new StudentID(1500001);
        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars("Informática"), new Acronym("LEI"));
        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
        ProgrammeEditionID editionID = new ProgrammeEditionID(programmeID, schoolYearID);
        ProgrammeEdition edition = new ProgrammeEdition(editionID, programmeID, schoolYearID);
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(editionID, studentID);

        when(programmeEnrolmentRepository.findProgrammesByStudent(studentID))
                .thenReturn(List.of(programmeID));

        when(programmeEditionRepository.findByID(editionID))
                .thenReturn(Optional.of(edition));

        when(programmeEditionEnrolmentRepository.existsByID(enrolmentID))
                .thenReturn(true); // já está inscrito

        // Act + Assert
        assertThrows(IllegalStateException.class, () ->
                service.enrolStudentInProgrammeEdition(studentID, programmeID, schoolYearID));
    }

}
