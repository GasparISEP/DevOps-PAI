package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;

import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.dto.programmeEdition.CountStudentsInProgrammeEditionDto;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionServiceTest {
    @Test
    void shouldCreateProgrammeEditionService() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        //act
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler);
        //assert
        assertNotNull(programmeEditionService);
    }

    @Test
    void nullProgrammeEditionFactoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = null;
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler));
    }

    @Test
    void nullProgrammeEditionRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = null;
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler));
    }

    @Test
    void nullProgrammeRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = null;
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler));
    }

    @Test
    void nullSchoolYearRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearRepository schoolYearRepository = null;
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler));
    }

    @Test
    void nullProgrammeEditionEnrollmentRepositoryThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = null;
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler));
    }

    @Test
    void nullProgrammeEditionAssemblerThrowsException() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = null;
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler));
    }

    @Test
    void shouldCreateProgrammeEdition() throws Exception {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository,programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository,programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository,programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository,programmeEditionEnrolmentRepository, programmeEditionAssembler);
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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository, programmeRepository, schoolYearRepository,programmeEditionEnrolmentRepository, programmeEditionAssembler);

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
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository,
                programmeRepository, schoolYearRepository, programmeEditionEnrolmentRepository, programmeEditionAssembler);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        when(programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID1)).thenReturn(List.of());
        //act
        List<ProgrammeEdition> result = programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID1);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllProgrammeEditions_shouldReturnListOfDTOs() {
        // Given
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory,programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);
        ProgrammeEdition pee1= mock(ProgrammeEdition.class);
        ProgrammeEdition pee2= mock(ProgrammeEdition.class);
        CountStudentsInProgrammeEditionDto dto1 = mock(CountStudentsInProgrammeEditionDto.class);
        CountStudentsInProgrammeEditionDto dto2 = mock(CountStudentsInProgrammeEditionDto.class);
        List<ProgrammeEdition> domainList = Arrays.asList(pee1,pee2);
        List<CountStudentsInProgrammeEditionDto> dtoList = Arrays.asList(dto1,dto2);

        when(programmeEditionRepository.findAll()).thenReturn(domainList);
        when(programmeEditionAssembler.toCountStudentsInProgrammeEditionDTOList(domainList)).thenReturn(dtoList);

        // When
        Iterable<CountStudentsInProgrammeEditionDto> result = service.getAllProgrammeEditions();

        // Then
        assertNotNull(result);
        assertEquals(2, ((List<CountStudentsInProgrammeEditionDto>) result).size());
    }

    @Test
    void countTotalNumberOfStudentsInAProgrammeEdition_shouldReturnCorrectCount() throws Exception {
        //arrange
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory,programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);
        CountStudentsInProgrammeEditionDto dto = mock(CountStudentsInProgrammeEditionDto.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrolment pee1= mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment pee2= mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment pee3= mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionID identity = mock(ProgrammeEditionID.class);
        List<ProgrammeEditionEnrolment> enrolments = Arrays.asList(pee1,pee2,pee3
                );
        when(programmeEditionAssembler.CountStudentsInProgrammeEditionDTOtoDomain(dto)).thenReturn(edition);
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
        IProgrammeEditionAssembler programmeEditionAssembler = mock(IProgrammeEditionAssembler.class);
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeEditionFactory factory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeEditionService service = new ProgrammeEditionService(factory,programmeEditionRepository,programmeRepository,schoolYearRepository,programmeEditionEnrolmentRepository,programmeEditionAssembler);
        CountStudentsInProgrammeEditionDto dto = mock(CountStudentsInProgrammeEditionDto.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);
        ProgrammeEditionID identity =mock(ProgrammeEditionID.class);

        when(programmeEditionAssembler.CountStudentsInProgrammeEditionDTOtoDomain(dto)).thenReturn(edition);
        when(edition.identity()).thenReturn(identity);
        when(programmeEditionEnrolmentRepository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(identity)).thenReturn(Collections.emptyList());

        // Act
        int count = service.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        // Arrange
        assertEquals(0, count);
    }
}
