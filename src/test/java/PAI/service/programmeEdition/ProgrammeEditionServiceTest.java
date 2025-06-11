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
import PAI.dto.programmeEdition.RequestServiceDto;
import PAI.dto.programmeEdition.ProgrammeEditionRequestServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,programmeRepository,programmeEditionAssembler, schoolYearService,programmeEditionEnrolmentService);
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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,
                programmeRepository, programmeEditionAssembler, schoolYearService,programmeEditionEnrolmentService));
    }

    @Test
    void nullProgrammeEditionRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = null;
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () ->new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService,programmeEditionEnrolmentService));
    }

    @Test
    void nullProgrammeRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = null;
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService));
    }

    @Test
    void nullSchoolYearServiceThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () ->  new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, null, programmeEditionEnrolmentService));
    }

    @Test
    void nullProgrammeEditionEnrollmentServiceThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, null));
    }

    @Test
    void nullProgrammeEditionAssemblerThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = null;
        //act + assert
        assertThrows(IllegalArgumentException.class, () ->new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService));
    }

    @Test
    void shouldCreateProgrammeEdition() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);
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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        when(programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID1)).thenReturn(List.of());
        //act
        List<ProgrammeEdition> result = programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID1);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void countTotalNumberOfStudentsInAProgrammeEdition_shouldReturnCorrectCount() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);

        ProgrammeEditionService service = new ProgrammeEditionService(
                programmeEditionFactory,
                programmeEditionRepository,
                programmeRepository,
                programmeEditionAssembler,
                schoolYearService,
                programmeEditionEnrolmentService
        );

        RequestServiceDto dto = mock(RequestServiceDto.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);

        when(programmeEditionAssembler.toProgrammeEditionFromRequestServiceDTO(dto)).thenReturn(edition);
        when(edition.identity()).thenReturn(programmeEditionId);

        when(programmeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEditionId)).thenReturn(3);

        // Act
        int count = service.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        // Assert
        assertEquals(3, count, "Expected count of students enrolled in programme edition to be 3");
    }



    @Test
    void countTotalNumberOfStudentsInAProgrammeEdition_shouldReturnZeroIfNoEnrolments() throws Exception {
        // Assert
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);
        RequestServiceDto dto = mock(RequestServiceDto.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionID identity = mock(ProgrammeEditionID.class);

        when(programmeEditionAssembler.toProgrammeEditionFromRequestServiceDTO(dto)).thenReturn(edition);
        when(edition.identity()).thenReturn(identity);
        when(programmeEditionEnrolmentService.totalStudentsInProgrammeEdition(identity)).thenReturn(0);

        // Act
        int count = service.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        // Arrange
        assertEquals(0, count);
    }

    @Test
    void shouldCreateProgrammeEditionAndSaveWhenValid() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearService schoolYearService= mock(ISchoolYearService.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository, programmeEditionAssembler,
                schoolYearService, programmeEditionEnrolmentService);

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
        when(programmeEditionAssembler.toServiceResponseDTOFromIDs(storedEdition.identity().getProgrammeID(), storedEdition.identity().getSchoolYearID())).thenReturn(programmeEditionResponseServiceDTO);
        // Act
        ProgrammeEditionResponseServiceDTO result = service.createProgrammeEditionAndSave(programmeEditionRequestServiceDTO);

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionIfSchoolYearDoesNotExists() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock (ISchoolYearService.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        when(programmeEditionAssembler.toServiceResponseDTOFromIDs(storedEdition.identity().getProgrammeID(), storedEdition.identity().getSchoolYearID())).thenReturn(programmeEditionResponseServiceDTO);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createProgrammeEditionAndSave(programmeEditionRequestServiceDTO);
        });
    }

    @Test
    void shouldThrowExceptionIfProgrammeNotExists() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearService schoolYearService = mock (ISchoolYearService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        when(programmeEditionAssembler.toServiceResponseDTOFromIDs(storedEdition.identity().getProgrammeID(), storedEdition.identity().getSchoolYearID())).thenReturn(programmeEditionResponseServiceDTO);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createProgrammeEditionAndSave(programmeEditionRequestServiceDTO);
        });
    }

    @Test
    void shouldThrowExceptionIfEditionAlreadyExists() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearService schoolYearService = mock (ISchoolYearService.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, programmeEditionRepository, programmeRepository,
                programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);

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
        when(programmeEditionAssembler.toServiceResponseDTOFromIDs(storedEdition.identity().getProgrammeID(), storedEdition.identity().getSchoolYearID())).thenReturn(programmeEditionResponseServiceDTO);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeRepository programmeRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository editionRepo = mock(IProgrammeEditionRepository.class);

        ProgrammeEditionService service = new ProgrammeEditionService(factory, editionRepo, programmeRepo, assembler, schoolYearService,programmeEditionEnrolmentService);

        ProgrammeEdition edition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2 = mock(ProgrammeEdition.class);

        when(editionRepo.findAll()).thenReturn(List.of(edition1, edition2));

        ProgrammeEditionResponseServiceDTO responseDTO1 = mock(ProgrammeEditionResponseServiceDTO.class);
        ProgrammeEditionResponseServiceDTO responseDTO2 = mock(ProgrammeEditionResponseServiceDTO.class);

        when(assembler.toServiceResponseDTOFromIDs(any(), any()))
                .thenReturn(responseDTO1)
                .thenReturn(responseDTO2);

        // Act
        List<ProgrammeEditionResponseServiceDTO> result = service.findAllProgrammeEditions();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(responseDTO1));
        assertTrue(result.contains(responseDTO2));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIdConversionFails() {
        // Arrange
        IProgrammeEditionServiceAssembler assembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionRepository editionRepo = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, editionRepo, programmeRepo, assembler, schoolYearService, programmeEditionEnrolmentService);

        ProgrammeEditionRequestServiceDTO dto = mock(ProgrammeEditionRequestServiceDTO.class);
        when(assembler.toProgrammeID(dto)).thenThrow(new IllegalArgumentException("Invalid programme ID"));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> service.createProgrammeEditionAndSave(dto));
    }

    @Test
    void countTotalNumberOfStudentsInAProgrammeEdition_shouldHandleLargeNumberOfEnrolments() throws Exception {
        // Arrange
        IProgrammeEditionServiceAssembler assembler = mock(IProgrammeEditionServiceAssembler.class);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeRepository programmeRepo = mock(IProgrammeRepository.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository editionRepo = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, editionRepo, programmeRepo, assembler, schoolYearService, programmeEditionEnrolmentService);

        RequestServiceDto dto = mock(RequestServiceDto.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionID editionID = mock(ProgrammeEditionID.class);

        List<ProgrammeEditionEnrolment> enrolments = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            enrolments.add(mock(ProgrammeEditionEnrolment.class));
        }

        when(assembler.toProgrammeEditionFromRequestServiceDTO(dto)).thenReturn(edition);
        when(edition.identity()).thenReturn(editionID);
        when(programmeEditionEnrolmentService.totalStudentsInProgrammeEdition(editionID)).thenReturn(1000);

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
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory, editionRepo, programmeRepo, assembler, schoolYearService, programmeEditionEnrolmentService);

        when(editionRepo.findAll()).thenThrow(new RuntimeException("Database failure"));

        // Act + Assert
        assertThrows(RuntimeException.class, service::findAllProgrammeEditions);
    }
}
