package PAI.persistence.springdata;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.mapper.IProgrammeEditionEnrolmentIDMapper;
import PAI.mapper.IProgrammeEditionEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentRepositorySpringDataTest {

    private IProgrammeEditionEnrolmentRepositorySpringData repoSpringData;
    private IProgrammeEditionEnrolmentMapper mapper;
    private IProgrammeEditionEnrolmentIDMapper idMapper;
    private ProgrammeEditionEnrolmentRepositorySpringData repository;

    @BeforeEach
    void setUp() {
        repoSpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        mapper = mock(IProgrammeEditionEnrolmentMapper.class);
        idMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        repository = new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, mapper, idMapper);
    }

    @Test
    void testConstructorThrowsIfAnyDependencyIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringData(null, mapper, idMapper));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, null, idMapper));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, mapper, null));
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

    @Test
    void shouldReturnListOfProgrammeEditionsThatStudentIsEnrolled() {
        // Arrange
        StudentID studentId = mock(StudentID.class);
        ProgrammeEditionID programmeEditionId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionId2 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolmentDataModel dataModel1 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolmentDataModel dataModel2 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment enrolment1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolment2 = mock(ProgrammeEditionEnrolment.class);

        // Mock the expected behavior for the mapper
        when(mapper.toDomain(dataModel1)).thenReturn(Optional.of(enrolment1));
        when(mapper.toDomain(dataModel2)).thenReturn(Optional.of(enrolment2));

        // Mock the expected behavior for the domain entities
        when(enrolment1.findStudentInProgrammeEdition()).thenReturn(studentId);
        when(enrolment1.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionId1);

        when(enrolment2.findStudentInProgrammeEdition()).thenReturn(studentId);
        when(enrolment2.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionId2);

        // Mock the repository return with data models
        when(repoSpringData.findAll()).thenReturn(Arrays.asList(dataModel1, dataModel2));

        // Act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionsThatStudentIsEnrolled(studentId);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The result list should contain 2 programme editions");
        assertTrue(result.contains(programmeEditionId1), "The result should contain programmeEditionId1");
        assertTrue(result.contains(programmeEditionId2), "The result should contain programmeEditionId2");
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

}
