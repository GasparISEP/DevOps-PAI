package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.assembler.programmeEdition.IProgrammeEditionServiceAssembler;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;

import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.dto.programmeEdition.CountStudentsRequestDto;
import PAI.dto.programmeEdition.ProgrammeEditionRequestServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionServiceTest {
    @Test
    void shouldCreateProgrammeEditionService() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService= mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler, schoolYearService);
        //assert
        assertNotNull(programmeEditionService);
    }

    @Test
    void nullProgrammeEditionFactoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = null;
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService));
    }

    @Test
    void nullProgrammeEditionRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = null;
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () ->new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService));
    }

    @Test
    void nullProgrammeRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = null;
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService));
    }

    @Test
    void nullSchoolYearServiceThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () ->  new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, null));
    }

    @Test
    void nullProgrammeEditionEnrollmentRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = null;
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService));
    }

    @Test
    void nullProgrammeEditionAssemblerThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = null;
        //act + assert
        assertThrows(IllegalArgumentException.class, () ->new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService));
    }

    @Test
    void shouldCreateProgrammeEdition() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(mock(ProgrammeEdition.class));
        //act
        ProgrammeEdition programmeEdition = programmeEditionService.createProgrammeEdition(programmeID, schoolYearID);
        //assert
        assertNotNull(programmeEdition);
    }

    @Test
    void shouldThrowExceptionIfProgrammeIdIsNull() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = null;
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionService.createProgrammeEdition(programmeID, schoolYearID));
    }

    @Test
    void shouldThrowExceptionIfSchoolYearIdIsNull() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = null;
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionService.createProgrammeEdition(programmeID, schoolYearID));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIdIsNullAtProgrammeEditionCreationIsolationTest() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = null;
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID)).thenThrow(IllegalArgumentException.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionService.createProgrammeEdition(programmeID, schoolYearID));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIdIsNullAtProgrammeEditionCreation() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = null;
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionService.createProgrammeEdition(programmeID, schoolYearID));
    }

    @Test
    void shouldSaveProgrammeEdition() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionRepository.containsOfIdentity(programmeEditionID)).thenReturn(false);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionRepository.save(programmeEdition)).thenReturn(programmeEdition);
        //act
        Optional<ProgrammeEdition> programmeEditionSaved = programmeEditionService.saveProgrammeEdition(programmeEdition);
        //assert
        assertTrue(programmeEditionSaved.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyWhenProgrammeEditionIsNull() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeEdition programmeEdition = null;
        //act
        Optional<ProgrammeEdition> programmeEditionSaved = programmeEditionService.saveProgrammeEdition(programmeEdition);
        //assert
        assertFalse(programmeEditionSaved.isPresent());
        assertEquals(Optional.empty(), programmeEditionSaved);
    }

    @Test
    void shouldReturnOptionalEmptyWhenProgrammeEditionIsAlreadyRegistered() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionRepository.containsOfIdentity(programmeEdition.identity())).thenReturn(true);

        //act
        Optional<ProgrammeEdition> programmeEditionSaved = programmeEditionService.saveProgrammeEdition(programmeEdition);
        //assert
        assertFalse(programmeEditionSaved.isPresent());
        assertEquals(Optional.empty(), programmeEditionSaved);
    }

    @Test
    void shouldReturnOptionalEmptyWhenProgrammeEditionSavedIsNull() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionRepository.containsOfIdentity(programmeEditionID)).thenReturn(false);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionRepository.save(programmeEdition)).thenReturn(null);
        //act
        Optional<ProgrammeEdition> programmeEditionSaved = programmeEditionService.saveProgrammeEdition(programmeEdition);
        //assert
        assertFalse(programmeEditionSaved.isPresent());
        assertEquals(Optional.empty(), programmeEditionSaved);
    }

    @Test
    void shouldReturnEmptyListWhenProgrammeIDIsNull() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);
        ProgrammeID programmeID = null;
        //act
        List<ProgrammeEdition> result = programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnListWithProgrammeEditions() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2 = mock(ProgrammeEdition.class);

        when(programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID))
                .thenReturn(List.of(programmeEdition1, programmeEdition2));
        //act
        List<ProgrammeEdition> result = programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID);

        // assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(programmeEdition1));
        assertTrue(result.contains(programmeEdition2));
    }

    @Test
    void shouldReturnEmptyListWithProgrammeEditions() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        when(programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID1)).thenReturn(List.of());
        //act
        List<ProgrammeEdition> result = programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID1);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void countTotalNumberOfStudentsInAProgrammeEdition_shouldReturnCorrectCount() throws Exception {
        //arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);
        CountStudentsRequestDto dto = mock(CountStudentsRequestDto.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrolment pee1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment pee2 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment pee3 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionID identity = mock(ProgrammeEditionID.class);
        List<ProgrammeEditionEnrolment> enrolments = Arrays.asList(pee1, pee2, pee3
        );
        when(programmeEditionAssembler.countStudentsInProgrammeEditionDTOtoDomain(dto)).thenReturn(edition);
        when(edition.identity()).thenReturn(identity);
        when(programmeEditionEnrolmentRepository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(identity)).thenReturn(enrolments);

        // act
        int count = service.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        // assert
        assertEquals(3, count);
    }


    @Test
    void countTotalNumberOfStudentsInAProgrammeEdition_shouldReturnZeroIfNoEnrolments() throws Exception {
        // Assert
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);
        CountStudentsRequestDto dto = mock(CountStudentsRequestDto.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionID identity = mock(ProgrammeEditionID.class);

        when(programmeEditionAssembler.countStudentsInProgrammeEditionDTOtoDomain(dto)).thenReturn(edition);
        when(edition.identity()).thenReturn(identity);
        when(programmeEditionEnrolmentRepository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(identity)).thenReturn(Collections.emptyList());

        // Act
        int count = service.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        // Arrange
        assertEquals(0, count);
    }

    @Test
    void shouldCreateProgrammeEditionAndSaveWhenValid() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearService schoolYearService= mock(ISchoolYearService.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        ProgrammeEdition storedEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID storedProgrammeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeID storedProgrammeID = mock(ProgrammeID.class);
        SchoolYearID storedSchoolYearID = mock(SchoolYearID.class);

        ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO = mock(ProgrammeEditionRequestServiceDTO.class);
        ProgrammeEditionResponseServiceDTO programmeEditionResponseServiceDTO = mock (ProgrammeEditionResponseServiceDTO.class);

        when(programmeEditionAssembler.toProgrammeID(programmeEditionRequestServiceDTO)).thenReturn(programmeID);
        when(schoolYearService.getCurrentSchoolYearID()).thenReturn(Optional.ofNullable(schoolYearID));
        when(programmeRepository.containsOfIdentity(programmeID)).thenReturn(true);
        when(schoolYearRepository.containsOfIdentity(schoolYearID)).thenReturn(true);
        when(factory.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(programmeEdition);
        when(programmeEditionRepository.containsOfIdentity(programmeEdition.identity())).thenReturn(false);
        when(programmeEditionRepository.save(programmeEdition)).thenReturn(storedEdition);
        when(storedEdition.identity()).thenReturn(storedProgrammeEditionID);
        when(storedProgrammeEditionID.getProgrammeID()).thenReturn(storedProgrammeID);
        when(storedProgrammeEditionID.getSchoolYearID()).thenReturn(storedSchoolYearID);
        when(programmeEditionAssembler.toServiceResponseDTO(storedEdition.identity().getProgrammeID(), storedEdition.identity().getSchoolYearID())).thenReturn(programmeEditionResponseServiceDTO);
        // Act
        ProgrammeEditionResponseServiceDTO result = service.createProgrammeEditionAndSave(programmeEditionRequestServiceDTO);

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionIfSchoolYearDoesNotExists() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock (ISchoolYearService.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        ProgrammeEdition storedEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID storedProgrammeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeID storedProgrammeID = mock(ProgrammeID.class);
        SchoolYearID storedSchoolYearID = mock(SchoolYearID.class);

        ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO = mock(ProgrammeEditionRequestServiceDTO.class);
        ProgrammeEditionResponseServiceDTO programmeEditionResponseServiceDTO = mock (ProgrammeEditionResponseServiceDTO.class);

        when(programmeEditionAssembler.toProgrammeID(programmeEditionRequestServiceDTO)).thenReturn(programmeID);
        when(schoolYearService.getCurrentSchoolYearID()).thenReturn(Optional.empty());
        when(programmeRepository.containsOfIdentity(programmeID)).thenReturn(true);
        when(factory.createProgrammeEdition(programmeID, storedSchoolYearID)).thenReturn(programmeEdition);
        when(programmeEditionRepository.containsOfIdentity(programmeEdition.identity())).thenReturn(false);
        when(programmeEditionRepository.save(programmeEdition)).thenReturn(storedEdition);
        when(storedEdition.identity()).thenReturn(storedProgrammeEditionID);
        when(storedProgrammeEditionID.getProgrammeID()).thenReturn(storedProgrammeID);
        when(storedProgrammeEditionID.getSchoolYearID()).thenReturn(storedSchoolYearID);
        when(programmeEditionAssembler.toServiceResponseDTO(storedEdition.identity().getProgrammeID(), storedEdition.identity().getSchoolYearID())).thenReturn(programmeEditionResponseServiceDTO);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createProgrammeEditionAndSave(programmeEditionRequestServiceDTO);
        });
    }

    @Test
    void shouldThrowExceptionIfProgrammeNotExists() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearService schoolYearService = mock (ISchoolYearService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        ProgrammeEdition storedEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID storedProgrammeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeID storedProgrammeID = mock(ProgrammeID.class);
        SchoolYearID storedSchoolYearID = mock(SchoolYearID.class);

        ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO = mock(ProgrammeEditionRequestServiceDTO.class);
        ProgrammeEditionResponseServiceDTO programmeEditionResponseServiceDTO = mock(ProgrammeEditionResponseServiceDTO.class);

        when(programmeEditionAssembler.toProgrammeID(programmeEditionRequestServiceDTO)).thenReturn(programmeID);
        when(schoolYearService.getCurrentSchoolYearID()).thenReturn(Optional.ofNullable(schoolYearID));
        when(programmeRepository.containsOfIdentity(programmeID)).thenReturn(false);
        when(schoolYearRepository.containsOfIdentity(schoolYearID)).thenReturn(true);
        when(factory.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(programmeEdition);
        when(programmeEditionRepository.containsOfIdentity(programmeEdition.identity())).thenReturn(false);
        when(programmeEditionRepository.save(programmeEdition)).thenReturn(storedEdition);
        when(storedEdition.identity()).thenReturn(storedProgrammeEditionID);
        when(storedProgrammeEditionID.getProgrammeID()).thenReturn(storedProgrammeID);
        when(storedProgrammeEditionID.getSchoolYearID()).thenReturn(storedSchoolYearID);
        when(programmeEditionAssembler.toServiceResponseDTO(storedEdition.identity().getProgrammeID(), storedEdition.identity().getSchoolYearID())).thenReturn(programmeEditionResponseServiceDTO);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createProgrammeEditionAndSave(programmeEditionRequestServiceDTO);
        });
    }

    @Test
    void shouldThrowExceptionIfEditionAlreadyExists() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearService schoolYearService = mock (ISchoolYearService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler, schoolYearService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        ProgrammeEdition storedEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID storedProgrammeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeID storedProgrammeID = mock(ProgrammeID.class);
        SchoolYearID storedSchoolYearID = mock(SchoolYearID.class);

        ProgrammeEditionResponseServiceDTO programmeEditionResponseServiceDTO = mock(ProgrammeEditionResponseServiceDTO.class);
        ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO = mock(ProgrammeEditionRequestServiceDTO.class);

        when(programmeEditionAssembler.toProgrammeID(programmeEditionRequestServiceDTO)).thenReturn(programmeID);
        when(schoolYearService.getCurrentSchoolYearID()).thenReturn(Optional.ofNullable(schoolYearID));
        when(programmeRepository.containsOfIdentity(programmeID)).thenReturn(true);
        when(schoolYearRepository.containsOfIdentity(schoolYearID)).thenReturn(true);
        when(factory.createProgrammeEdition(programmeID, schoolYearID)).thenReturn(programmeEdition);
        when(programmeEditionRepository.containsOfIdentity(programmeEdition.identity())).thenReturn(true);
        when(programmeEditionRepository.save(programmeEdition)).thenReturn(storedEdition);
        when(storedEdition.identity()).thenReturn(storedProgrammeEditionID);
        when(storedProgrammeEditionID.getProgrammeID()).thenReturn(storedProgrammeID);
        when(storedProgrammeEditionID.getSchoolYearID()).thenReturn(storedSchoolYearID);
        when(programmeEditionAssembler.toServiceResponseDTO(storedEdition.identity().getProgrammeID(), storedEdition.identity().getSchoolYearID())).thenReturn(programmeEditionResponseServiceDTO);

        //act + assert
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createProgrammeEditionAndSave(programmeEditionRequestServiceDTO);
        });
    }

    @Test
    void shouldReturnAllProgrammeEditions() {
        // Arrange
        IProgrammeEditionServiceAssembler assembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepo = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeRepository programmeRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository editionRepo = mock(IProgrammeEditionRepository.class);

        ProgrammeEditionService service = new ProgrammeEditionService(factory, editionRepo, programmeRepo, enrolmentRepo, assembler, schoolYearService);

        ProgrammeEdition edition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2 = mock(ProgrammeEdition.class);
        Iterable<ProgrammeEdition> iterable = List.of(edition1, edition2);

        when(editionRepo.findAll()).thenReturn(iterable);

        // Act
        List<ProgrammeEdition> result = service.findAllProgrammeEditions();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(edition1));
        assertTrue(result.contains(edition2));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIdConversionFails() {
        // Arrange
        IProgrammeEditionServiceAssembler assembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionRepository editionRepo = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepo = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, editionRepo, programmeRepo, enrolmentRepo, assembler, schoolYearService);

        ProgrammeEditionRequestServiceDTO dto = mock(ProgrammeEditionRequestServiceDTO.class);
        when(assembler.toProgrammeID(dto)).thenThrow(new IllegalArgumentException("Invalid programme ID"));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> service.createProgrammeEditionAndSave(dto));
    }

    @Test
    void countTotalNumberOfStudentsInAProgrammeEdition_shouldHandleLargeNumberOfEnrolments() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler assembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepo = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeRepository programmeRepo = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository editionRepo = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, editionRepo, programmeRepo, enrolmentRepo, assembler, schoolYearService);

        CountStudentsRequestDto dto = mock(CountStudentsRequestDto.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionID editionID = mock(ProgrammeEditionID.class);

        List<ProgrammeEditionEnrolment> enrolments = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            enrolments.add(mock(ProgrammeEditionEnrolment.class));
        }

        when(assembler.countStudentsInProgrammeEditionDTOtoDomain(dto)).thenReturn(edition);
        when(edition.identity()).thenReturn(editionID);
        when(enrolmentRepo.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(editionID)).thenReturn(enrolments);

        // Act
        int count = service.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        // Assert
        assertEquals(1000, count);
    }

    @Test
    void shouldHandleExceptionWhenFindAllProgrammeEditionsFails() {
        // Arrange
        IProgrammeEditionServiceAssembler assembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionRepository editionRepo = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepo = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, editionRepo, programmeRepo, enrolmentRepo, assembler, schoolYearService);

        when(editionRepo.findAll()).thenThrow(new RuntimeException("Database failure"));

        // Act + Assert
        assertThrows(RuntimeException.class, () -> service.findAllProgrammeEditions());
    }
}
