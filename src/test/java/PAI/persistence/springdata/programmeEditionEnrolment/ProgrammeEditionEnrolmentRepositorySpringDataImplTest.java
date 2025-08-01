package PAI.persistence.springdata.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.programmeEditionEnrolment.IProgrammeEditionEnrolmentIDMapper;
import PAI.mapper.programmeEditionEnrolment.IProgrammeEditionEnrolmentMapper;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentRepositorySpringDataImplTest {

    private IProgrammeEditionEnrolmentRepositorySpringData repoSpringData;
    private IProgrammeEditionEnrolmentMapper mapper;
    private IProgrammeEditionEnrolmentIDMapper idMapper;
    private ProgrammeEditionEnrolmentRepositorySpringDataImpl repository;
    private IStudentIDMapper studentIdMapper;
    private IProgrammeEditionIdMapper programmeEditionIdMapper;

    @BeforeEach
    void setUp() {
        repoSpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        mapper = mock(IProgrammeEditionEnrolmentMapper.class);
        idMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        studentIdMapper = mock(IStudentIDMapper.class);
        programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(repoSpringData, mapper, idMapper, studentIdMapper, programmeEditionIdMapper);
    }

    @Test
    void testConstructorThrowsIfAnyDependencyIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringDataImpl(null, mapper, idMapper, studentIdMapper, programmeEditionIdMapper));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringDataImpl(repoSpringData, null, idMapper, studentIdMapper, programmeEditionIdMapper));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringDataImpl(repoSpringData, mapper, null, studentIdMapper, programmeEditionIdMapper));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringDataImpl(repoSpringData, mapper, idMapper, null, programmeEditionIdMapper));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringDataImpl(repoSpringData, mapper, idMapper, studentIdMapper, null));
    }

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgrammeEdition() {
        // Arrange
        StudentID studentId = mock(StudentID.class);
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentDataModel dataModel = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        when(repoSpringData.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(Optional.of(enrolment));
        when(enrolment.hasSameStudent(studentId)).thenReturn(true);
        when(enrolment.hasSameProgrammeEdition(programmeEditionId)).thenReturn(true);

        // Act
        boolean result = repository.isStudentEnrolledInThisProgrammeEdition(studentId, programmeEditionId);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgrammeEdition() {
        // Arrange
        StudentID studentId = mock(StudentID.class);
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentDataModel dataModel = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        when(repoSpringData.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(Optional.of(enrolment));
        when(enrolment.hasSameStudent(studentId)).thenReturn(false);

        // Act
        boolean result = repository.isStudentEnrolledInThisProgrammeEdition(studentId, programmeEditionId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNull() {
        // Arrange
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);

        // Act
        boolean result = repository.isStudentEnrolledInThisProgrammeEdition(null, programmeEditionId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsNull() {
        // Arrange
        StudentID studentId = mock(StudentID.class);

        // Act
        boolean result = repository.isStudentEnrolledInThisProgrammeEdition(studentId, null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionAndStudentAreNull() {
        // Act
        boolean result = repository.isStudentEnrolledInThisProgrammeEdition(null, null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfMapperFailsToConvertToDomain() {
        // Arrange
        StudentID studentId = mock(StudentID.class);
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentDataModel dataModel = mock(ProgrammeEditionEnrolmentDataModel.class);

        when(repoSpringData.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> repository.isStudentEnrolledInThisProgrammeEdition(studentId, programmeEditionId));
    }

    // testing find Programme Edition ID's that student is enrolled (active status)

    @Test
    void shouldReturnListOfProgrammeEditionsThatStudentIsEnrolled() {
        // Arrange
        IProgrammeEditionEnrolmentRepositorySpringData doublePeeRepositorySpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper doublePeeMapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper doublePeeIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper doubleStudentIdMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper doublePeIDMapper = mock(IProgrammeEditionIdMapper.class);
        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(doublePeeRepositorySpringData,
                doublePeeMapper, doublePeeIDMapper, doubleStudentIdMapper, doublePeIDMapper);

        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID doubleProgrammeEditionId2 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolmentDataModel doubleDataModel1 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolmentDataModel doubleDataModel2 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment doubleEnrolment1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment doubleEnrolment2 = mock(ProgrammeEditionEnrolment.class);

        when(doublePeeMapper.toDomain(doubleDataModel1)).thenReturn(Optional.of(doubleEnrolment1));
        when(doublePeeMapper.toDomain(doubleDataModel2)).thenReturn(Optional.of(doubleEnrolment2));

        when(doubleEnrolment1.findStudentInProgrammeEdition()).thenReturn(doubleStudentId);
        when(doubleEnrolment1.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId1);
        when(doubleEnrolment1.isEnrolmentActive()).thenReturn(true);

        when(doubleEnrolment2.findStudentInProgrammeEdition()).thenReturn(doubleStudentId);
        when(doubleEnrolment2.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId2);
        when(doubleEnrolment2.isEnrolmentActive()).thenReturn(true);

        when(doublePeeRepositorySpringData.findAll()).thenReturn(List.of(doubleDataModel1, doubleDataModel2));

        // Act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId);

        // Assert
        assertEquals(2, result.size());

    }

    @Test
    void should_return_a_empty_list_when_student_is_not_enrolled_in_any_programmeEdition (){
        // arrange
        IProgrammeEditionEnrolmentRepositorySpringData doublePeeRepositorySpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper doublePeeMapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper doublePeeIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper doubleStudentIdMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper doublePeIDMapper = mock(IProgrammeEditionIdMapper.class);
        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(doublePeeRepositorySpringData,
                doublePeeMapper, doublePeeIDMapper, doubleStudentIdMapper, doublePeIDMapper);

        StudentID doubleStudentId = mock(StudentID.class);
        StudentID doubleStudentId2 = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId1 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolmentDataModel doubleDataModel1 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment doubleEnrolment1 = mock(ProgrammeEditionEnrolment.class);

        when(doublePeeMapper.toDomain(doubleDataModel1)).thenReturn(Optional.of(doubleEnrolment1));

        when(doubleEnrolment1.findStudentInProgrammeEdition()).thenReturn(doubleStudentId);
        when(doubleEnrolment1.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId1);
        when(doubleEnrolment1.isEnrolmentActive()).thenReturn(true);

        when(doublePeeRepositorySpringData.findAll()).thenReturn(List.of(doubleDataModel1));

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId2);

        // assert
        assertEquals(0, result.size());
    }

    @Test
    void should_return_an_exception_if_is_not_possible_map_to_dataModel (){
        // arrange
        IProgrammeEditionEnrolmentRepositorySpringData doublePeeRepositorySpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper doublePeeMapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper doublePeeIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper doubleStudentIdMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper doublePeIDMapper = mock(IProgrammeEditionIdMapper.class);
        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(doublePeeRepositorySpringData,
                doublePeeMapper, doublePeeIDMapper, doubleStudentIdMapper, doublePeIDMapper);

        StudentID doubleStudentId = mock(StudentID.class);

        ProgrammeEditionEnrolmentDataModel doubleDataModel1 = mock(ProgrammeEditionEnrolmentDataModel.class);

        when(doublePeeMapper.toDomain(doubleDataModel1)).thenReturn(Optional.empty());

        when(doublePeeRepositorySpringData.findAll()).thenReturn(List.of(doubleDataModel1));

        // act & assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            repository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId);;
        });
        assertEquals("Could not map data model to domain", exception.getMessage());
    }

    @Test
    void should_return_a_empty_list_when_student_is_enrolled_in_programmeEdition_with_inactive_status (){
        // arrange
        IProgrammeEditionEnrolmentRepositorySpringData doublePeeRepositorySpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper doublePeeMapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper doublePeeIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper doubleStudentIdMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper doublePeIDMapper = mock(IProgrammeEditionIdMapper.class);
        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(doublePeeRepositorySpringData,
                doublePeeMapper, doublePeeIDMapper, doubleStudentIdMapper, doublePeIDMapper);

        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId1 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolmentDataModel doubleDataModel1 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment doubleEnrolment1 = mock(ProgrammeEditionEnrolment.class);

        when(doublePeeMapper.toDomain(doubleDataModel1)).thenReturn(Optional.of(doubleEnrolment1));

        when(doubleEnrolment1.findStudentInProgrammeEdition()).thenReturn(doubleStudentId);
        when(doubleEnrolment1.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId1);
        when(doubleEnrolment1.isEnrolmentActive()).thenReturn(false);

        when(doublePeeRepositorySpringData.findAll()).thenReturn(List.of(doubleDataModel1));

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId);

        // assert
        assertEquals(0, result.size());
    }

    @Test
    void testSaveCoversSuccessAndFailure() {
        // Arrange
        ProgrammeEditionEnrolment domain = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolmentDataModel model = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolmentDataModel saved = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment mapped = mock(ProgrammeEditionEnrolment.class);

        // SUCCESS CASE
        when(mapper.toDataModel(domain)).thenReturn(Optional.of(model));
        when(repoSpringData.save(model)).thenReturn(saved);
        when(mapper.toDomain(saved)).thenReturn(Optional.of(mapped));

        // Act
        ProgrammeEditionEnrolment result = repository.save(domain);

        // Assert
        assertEquals(mapped, result);

        // FAILURE CASES
        // Case 1: Mapper returns empty
        when(mapper.toDataModel(domain)).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> repository.save(domain), "Expected IllegalStateException when data model is empty");

        // Case 2: Mapper returns valid data model, but it cannot be converted back to domain object
        when(mapper.toDataModel(domain)).thenReturn(Optional.of(model));
        when(mapper.toDomain(saved)).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> repository.save(domain), "Expected IllegalStateException when domain conversion fails");

        // Case 3: Null input
        assertThrows(IllegalArgumentException.class, () -> repository.save(null), "Expected IllegalArgumentException when input is null");
    }


    @Test
    void testOfIdentityCoversAllCases() {
        // Arrange
        ProgrammeEditionEnrolmentID id = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentIDDataModel idModel = mock(ProgrammeEditionEnrolmentIDDataModel.class);
        ProgrammeEditionEnrolmentDataModel dataModel = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment domain = mock(ProgrammeEditionEnrolment.class);

        // SUCCESS CASES
        when(idMapper.toDataModel(id)).thenReturn(Optional.of(idModel));
        when(repoSpringData.findById(idModel)).thenReturn(Optional.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(Optional.of(domain));

        // Act
        Optional<ProgrammeEditionEnrolment> result = repository.ofIdentity(id);

        // Assert
        assertTrue(result.isPresent(), "Expected result to be present");
        assertEquals(domain, result.get(), "Expected domain object to match");

        // FAILURE CASES
        // Case 1: Mapper fails to convert data model to domain object
        when(mapper.toDomain(dataModel)).thenReturn(Optional.empty());
        assertTrue(repository.ofIdentity(id).isEmpty(), "Expected result to be empty when mapping fails");

        // Case 2: Repo does not find the ID in the data model
        when(repoSpringData.findById(idModel)).thenReturn(Optional.empty());
        assertTrue(repository.ofIdentity(id).isEmpty(), "Expected result to be empty when ID is not found");

        // Case 3: ID cannot be converted to data model
        when(idMapper.toDataModel(id)).thenReturn(Optional.empty());
        assertTrue(repository.ofIdentity(id).isEmpty(), "Expected result to be empty when ID conversion fails");

        // Case 4: Null input should return empty result
        assertTrue(repository.ofIdentity(null).isEmpty(), "Expected result to be empty when ID is null");
    }

    @Test
    void testFindAllCoversValidAndInvalid() {
        // Arrange
        ProgrammeEditionEnrolmentDataModel m1 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolmentDataModel m2 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment d1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment d2 = mock(ProgrammeEditionEnrolment.class);

        // SUCCESS CASE
        when(repoSpringData.findAll()).thenReturn(Arrays.asList(m1, m2));  // Repo returns a list of data models
        when(mapper.toDomain(m1)).thenReturn(Optional.of(d1));
        when(mapper.toDomain(m2)).thenReturn(Optional.of(d2));

        // Act
        List<ProgrammeEditionEnrolment> result = repository.findAll();

        // Assert
        assertEquals(List.of(d1, d2), result, "Expected the domain objects to match");

        // FAILURE CASE: Mapper fails to convert the data model to domain object
        when(mapper.toDomain(m1)).thenReturn(Optional.empty());  // Mapper returns empty for m1
        assertThrows(IllegalStateException.class, repository::findAll, "Expected IllegalStateException when mapping fails");
    }


    @Test
    void testContainsOfIdentityCoversAll() {
        // Arrange
        ProgrammeEditionEnrolmentID id = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentIDDataModel idModel = mock(ProgrammeEditionEnrolmentIDDataModel.class);

        // Case 1: ID can be successfully converted and found in the repo
        when(idMapper.toDataModel(id)).thenReturn(Optional.of(idModel));
        when(repoSpringData.existsById(idModel)).thenReturn(true);  // Repo finds the ID

        // Act
        boolean result = repository.containsOfIdentity(id);

        // Assert
        assertTrue(result, "Expected result to be true when ID exists");

        // Case 2: ID exists but repo cannot find it
        when(repoSpringData.existsById(idModel)).thenReturn(false);
        result = repository.containsOfIdentity(id);

        // Assert
        assertFalse(result, "Expected result to be false when ID does not exist");

        // Case 3: ID cannot be converted to data model
        when(idMapper.toDataModel(id)).thenReturn(Optional.empty());
        result = repository.containsOfIdentity(id);

        // Assert
        assertFalse(result, "Expected result to be false when ID conversion fails");

        // Case 4: Null input
        assertFalse(repository.containsOfIdentity(null), "Expected result to be false when ID is null");
    }

    @Test
    void shouldGetAllProgrammeEditionEnrolmentsBySpecificProgrammeEditionID() throws Exception {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionEnrolmentDataModel programmeEditionEnrolmentDataModel = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);
        List<ProgrammeEditionEnrolmentDataModel> allProgrammeEditionEnrolment = List.of(programmeEditionEnrolmentDataModel);
        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(repoSpringData.findAllById_ProgrammeEditionIdDataModel(programmeEditionIdDataModel)).thenReturn(allProgrammeEditionEnrolment);
        when(mapper.toDomain(programmeEditionEnrolmentDataModel)).thenReturn(Optional.of(programmeEditionEnrolment));

        // act
        List<ProgrammeEditionEnrolment> result = repository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEditionID);
        // assert
        assertEquals(List.of(programmeEditionEnrolment), result);
    }

    @Test
    void shouldReturnEmptyListIfNoProgrammeEditionEnrolmentFind() throws Exception {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        List<ProgrammeEditionEnrolmentDataModel> allProgrammeEditionEnrolment = List.of();
        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(repoSpringData.findAllById_ProgrammeEditionIdDataModel(programmeEditionIdDataModel)).thenReturn(allProgrammeEditionEnrolment);

        // act
        List<ProgrammeEditionEnrolment> result = repository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEditionID);
        // assert
        assertEquals(List.of(), result);
    }

    @Test
    void shouldReturnCountWhenProgrammeEditionIdsProvided() {
        // Arrange
        IProgrammeEditionEnrolmentRepositorySpringData peeSpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper peeMapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper peeIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);

        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                peeSpringData, peeMapper, peeIDMapper, studentIDMapper, programmeEditionIdMapper
        );

        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(programmeID.getProgrammeAcronym()).thenReturn("DEI");

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(schoolYearID.toString()).thenReturn("2023-2024");

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionID.getProgrammeID()).thenReturn(programmeID);
        when(programmeEditionID.getSchoolYearID()).thenReturn(schoolYearID);

        List<ProgrammeEditionID> ids = List.of(programmeEditionID);

        Object[] dbRow = new Object[]{"DEI", "2023-2024", 7};
        when(peeSpringData.countEnrolledByAcronymAndSchoolYear(anyList(), anyList()))
                .thenReturn(List.<Object[]>of(dbRow));

        // Act
        int count = repository.countEnrolledStudentsByProgrammeEditionIds(ids);

        // Assert
        assertEquals(7, count);

        ArgumentCaptor<List<String>> acronymsCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List<String>> yearsCaptor = ArgumentCaptor.forClass(List.class);
        verify(peeSpringData).countEnrolledByAcronymAndSchoolYear(acronymsCaptor.capture(), yearsCaptor.capture());

        assertEquals(List.of("DEI"), acronymsCaptor.getValue());
        assertEquals(List.of("2023-2024"), yearsCaptor.getValue());
    }

    @Test
    void shouldReturnZeroWhenThereAreNoEnrollmentsAssociatedToAnyOfTheProgrammeEditiions() {
        // Arrange
        IProgrammeEditionEnrolmentRepositorySpringData peeSpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper peeMapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper peeIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);

        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                peeSpringData, peeMapper, peeIDMapper, studentIDMapper, programmeEditionIdMapper
        );

        ProgrammeID p1 = mock(ProgrammeID.class);
        when(p1.getProgrammeAcronym()).thenReturn("DEI");
        SchoolYearID s1 = mock(SchoolYearID.class);
        when(s1.toString()).thenReturn("2023-2024");
        ProgrammeEditionID id1 = mock(ProgrammeEditionID.class);
        when(id1.getProgrammeID()).thenReturn(p1);
        when(id1.getSchoolYearID()).thenReturn(s1);

        List<ProgrammeEditionID> ids = List.of(id1);

        // Simula resultado de outra edição que não está na lista
        Object[] unrelatedResult = new Object[]{"XYZ", "2022-2023", 15};
        when(peeSpringData.countEnrolledByAcronymAndSchoolYear(anyList(), anyList()))
                .thenReturn(List.<Object[]>of(unrelatedResult));

        // Act
        int count = repository.countEnrolledStudentsByProgrammeEditionIds(ids);

        // Assert
        assertEquals(0, count);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionIdsIsNull() {
        // Arrange
        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                mock(IProgrammeEditionEnrolmentRepositorySpringData.class),
                mock(IProgrammeEditionEnrolmentMapper.class),
                mock(IProgrammeEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(IProgrammeEditionIdMapper.class)
        );

        // Act
        int count = repository.countEnrolledStudentsByProgrammeEditionIds(null);

        // Assert
        assertEquals(0, count);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionIdsIsEmpty() {
        // Arrange
        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                mock(IProgrammeEditionEnrolmentRepositorySpringData.class),
                mock(IProgrammeEditionEnrolmentMapper.class),
                mock(IProgrammeEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(IProgrammeEditionIdMapper.class)
        );

        // Act
        int count = repository.countEnrolledStudentsByProgrammeEditionIds(List.of());

        // Assert
        assertEquals(0, count);
    }

    @Test
    void shouldIgnoreInvalidPairsAndSumOnlyMatchingResults() {
        // Arrange
        IProgrammeEditionEnrolmentRepositorySpringData peeSpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper peeMapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper peeIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);

        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                peeSpringData, peeMapper, peeIDMapper, studentIDMapper, programmeEditionIdMapper
        );

        ProgrammeID p1 = mock(ProgrammeID.class);
        when(p1.getProgrammeAcronym()).thenReturn("DEI");
        SchoolYearID s1 = mock(SchoolYearID.class);
        when(s1.toString()).thenReturn("2023-2024");
        ProgrammeEditionID id1 = mock(ProgrammeEditionID.class);
        when(id1.getProgrammeID()).thenReturn(p1);
        when(id1.getSchoolYearID()).thenReturn(s1);

        ProgrammeID p2 = mock(ProgrammeID.class);
        when(p2.getProgrammeAcronym()).thenReturn("LEI");
        SchoolYearID s2 = mock(SchoolYearID.class);
        when(s2.toString()).thenReturn("2024-2025");
        ProgrammeEditionID id2 = mock(ProgrammeEditionID.class);
        when(id2.getProgrammeID()).thenReturn(p2);
        when(id2.getSchoolYearID()).thenReturn(s2);

        List<ProgrammeEditionID> ids = List.of(id1, id2);

        Object[] row1 = new Object[]{"DEI", "2023-2024", 3}; // válido
        Object[] row2 = new Object[]{"LEI", "2024-2025", 2}; // válido
        Object[] row3 = new Object[]{"XYZ", "9999", 100};    // inválido
        when(peeSpringData.countEnrolledByAcronymAndSchoolYear(anyList(), anyList()))
                .thenReturn(List.of(row1, row2, row3));

        // Act
        int count = repository.countEnrolledStudentsByProgrammeEditionIds(ids);

        // Assert
        assertEquals(5, count); // 3 + 2, ignora o "XYZ_9999"
    }

    @Test
    void shouldReturnProgrammeEditionEnrolmentWhenFound() throws Exception{
        // arrange
        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        ProgrammeEditionEnrolmentDataModel peeDataModel = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment pee = mock(ProgrammeEditionEnrolment.class);

        IProgrammeEditionEnrolmentRepositorySpringData springDataRepo = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper mapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper idMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);

        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo, mapper, idMapper, studentIDMapper, programmeEditionIDMapper
        );

        when(studentIDMapper.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(programmeEditionIDMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(springDataRepo.findById_StudentIdDataModelAndId_ProgrammeEditionIdDataModel(studentIDDataModel, programmeEditionIdDataModel))
                .thenReturn(Optional.of(peeDataModel));
        when(mapper.toDomain(peeDataModel)).thenReturn(Optional.of(pee));

        // act
        Optional<ProgrammeEditionEnrolment> result = repository.findByStudentAndProgrammeEdition(studentID, programmeEditionID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(pee, result.get());
    }

    @Test
    void shouldReturnEmptyWhenNoProgrammeEditionEnrolmentFound() throws Exception {
        // arrange
        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        IProgrammeEditionEnrolmentRepositorySpringData springDataRepo = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        IProgrammeEditionEnrolmentMapper mapper = mock(IProgrammeEditionEnrolmentMapper.class);
        IProgrammeEditionEnrolmentIDMapper idMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);

        ProgrammeEditionEnrolmentRepositorySpringDataImpl repository = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo, mapper, idMapper, studentIDMapper, programmeEditionIDMapper
        );

        when(studentIDMapper.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(programmeEditionIDMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(springDataRepo.findById_StudentIdDataModelAndId_ProgrammeEditionIdDataModel(studentIDDataModel, programmeEditionIdDataModel))
                .thenReturn(Optional.empty());

        // act
        Optional<ProgrammeEditionEnrolment> result = repository.findByStudentAndProgrammeEdition(studentID, programmeEditionID);

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getInternalSet_shouldReturnMappedEnrolments() {
        // Arrange
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolmentDataModel dataModel = mock(ProgrammeEditionEnrolmentDataModel.class);

        IProgrammeEditionEnrolmentRepositorySpringData springDataRepo = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        when(springDataRepo.findAll()).thenReturn(List.of(dataModel));

        IProgrammeEditionEnrolmentMapper mapper = mock(IProgrammeEditionEnrolmentMapper.class);
        when(mapper.toDomain(dataModel)).thenReturn(Optional.of(enrolment));

        ProgrammeEditionEnrolmentRepositorySpringDataImpl repo = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo, mapper, mock(IProgrammeEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class), mock(IProgrammeEditionIdMapper.class)
        );

        // Act
        Set<ProgrammeEditionEnrolment> result = repo.getInternalSet();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(enrolment));
    }

    @Test
    void getInternalSet_shouldIgnoreUnmappableDataModels() {
        // Arrange
        ProgrammeEditionEnrolmentDataModel dataModel1 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolmentDataModel dataModel2 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        IProgrammeEditionEnrolmentRepositorySpringData springDataRepo = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        when(springDataRepo.findAll()).thenReturn(List.of(dataModel1, dataModel2));

        IProgrammeEditionEnrolmentMapper mapper = mock(IProgrammeEditionEnrolmentMapper.class);
        when(mapper.toDomain(dataModel1)).thenReturn(Optional.empty());
        when(mapper.toDomain(dataModel2)).thenReturn(Optional.of(enrolment));

        ProgrammeEditionEnrolmentRepositorySpringDataImpl repo = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo, mapper, mock(IProgrammeEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class), mock(IProgrammeEditionIdMapper.class)
        );

        // Act
        Set<ProgrammeEditionEnrolment> result = repo.getInternalSet();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(enrolment));
    }

    @Test
    void getInternalSet_shouldThrowRuntimeExceptionOnFailure() {
        // Arrange
        IProgrammeEditionEnrolmentRepositorySpringData springDataRepo = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        when(springDataRepo.findAll()).thenThrow(new RuntimeException("Simulated DB error"));

        ProgrammeEditionEnrolmentRepositorySpringDataImpl repo = new ProgrammeEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo, mock(IProgrammeEditionEnrolmentMapper.class),
                mock(IProgrammeEditionEnrolmentIDMapper.class), mock(IStudentIDMapper.class),
                mock(IProgrammeEditionIdMapper.class)
        );

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, repo::getInternalSet);
        assertEquals("Error retrieving the set of programme edition enrolments", thrown.getMessage());
    }


}
