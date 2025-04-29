package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.mapper.NIFMapperImpl;
import PAI.mapper.StudentIDMapperImpl;
import PAI.mapper.StudentMapperImpl;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.StudentDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentRepositoryImplSpringDataTest {

    private IStudentRepositorySpringData repoMock;
    private StudentMapperImpl studentMapperImpl;
    private StudentIDMapperImpl studentIDMapperImpl;
    private NIFMapperImpl nifMapperImpl;

    private StudentRepositorySpringDataImpl repository;

    private Student student;
    private StudentDataModel dataModel;
    private StudentID studentID;
    private StudentIDDataModel studentIDDataModel;
    private NIF nif;
    private NIFDataModel nifDataModel;

    @BeforeEach
    public void setup() {
        repoMock = mock(IStudentRepositorySpringData.class);
        studentMapperImpl = mock(StudentMapperImpl.class);
        studentIDMapperImpl = mock(StudentIDMapperImpl.class);
        nifMapperImpl = mock(NIFMapperImpl.class);

        repository = new StudentRepositorySpringDataImpl(repoMock, studentMapperImpl, studentIDMapperImpl, nifMapperImpl);

        studentID = mock(StudentID.class);
        when(studentID.getUniqueNumber()).thenReturn(1234567);
        studentIDDataModel = mock(StudentIDDataModel.class);
        nif = mock(NIF.class);
        nifDataModel = mock(NIFDataModel.class);

        student = mock(Student.class);
        dataModel = mock(StudentDataModel.class);
    }

    @Test
    public void testConstructorThrowsOnNulls() {
        // Arrange
        // No need for specific mocks for this test.

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringDataImpl(null, studentMapperImpl, studentIDMapperImpl, nifMapperImpl));
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringDataImpl(repoMock, null, studentIDMapperImpl, nifMapperImpl));
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringDataImpl(repoMock, studentMapperImpl, null, nifMapperImpl));
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringDataImpl(repoMock, studentMapperImpl, studentIDMapperImpl, null));
    }

    @Test
    public void testGetStudentByID() throws Exception {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(repoMock.findById(studentIDDataModel)).thenReturn(Optional.of(dataModel));
        when(studentMapperImpl.dataModelToDomain(dataModel)).thenReturn(student);

        // Act
        Optional<Student> result = repository.ofIdentity(studentID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(student, result.get());
    }

    @Test
    public void testSaveReturnsMappedStudent() throws Exception {
        // Arrange
        when(studentMapperImpl.domainToDataModel(student)).thenReturn(dataModel);
        when(repoMock.save(dataModel)).thenReturn(dataModel);
        when(studentMapperImpl.dataModelToDomain(dataModel)).thenReturn(student);

        // Act
        Student result = repository.save(student);

        // Assert
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    public void testSaveThrowsRuntimeExceptionOnMappingFailure() throws Exception {
        // Arrange
        when(studentMapperImpl.domainToDataModel(student)).thenReturn(dataModel);
        when(repoMock.save(dataModel)).thenReturn(dataModel);
        when(studentMapperImpl.dataModelToDomain(dataModel)).thenThrow(new RuntimeException("Could not save student"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.save(student));
        assertTrue(ex.getMessage().contains("Could not save student"));
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<StudentDataModel> dataModels = Arrays.asList(dataModel, dataModel);
        when(repoMock.findAll()).thenReturn(dataModels);
        when(studentMapperImpl.dataModelToDomain(dataModel)).thenReturn(student);

        // Act
        Iterable<Student> result = repository.findAll();
        List<Student> list = (List<Student>) result;

        // Assert
        assertEquals(2, list.size());
    }

    @Test
    public void testOfIdentityDelegatesToGetStudentByID() {
        // Arrange
        StudentRepositorySpringDataImpl spyRepo = spy(repository);
        doReturn(Optional.of(student)).when(spyRepo).ofIdentity(studentID);

        // Act
        Optional<Student> result = spyRepo.ofIdentity(studentID);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    public void testContainsOfIdentity() {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(repoMock.existsById(studentIDDataModel)).thenReturn(true);

        // Act
        boolean result = repository.containsOfIdentity(studentID);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testContainsByStudentIDOrNIF() {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(nifMapperImpl.domainToDataModel(nif)).thenReturn(nifDataModel);
        when(repoMock.existsByStudentIDOrNIF(studentIDDataModel, nifDataModel)).thenReturn(true);

        // Act
        boolean result = repository.existsByStudentIDOrNIF(studentID, nif);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testContainsOfIdentityReturnsFalseWhenStudentDoesNotExist() {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(repoMock.existsById(studentIDDataModel)).thenReturn(false);

        // Act
        boolean result = repository.containsOfIdentity(studentID);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testContainsByStudentIDOrNIFReturnsFalse() {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(nifMapperImpl.domainToDataModel(nif)).thenReturn(nifDataModel);
        when(repoMock.existsByStudentIDOrNIF(studentIDDataModel, nifDataModel)).thenReturn(false);

        // Act
        boolean result = repository.existsByStudentIDOrNIF(studentID, nif);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetStudentByIDThrowsRuntimeExceptionOnMappingError() throws Exception {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(repoMock.findById(studentIDDataModel)).thenReturn(Optional.of(dataModel));
        when(studentMapperImpl.dataModelToDomain(dataModel)).thenThrow(new RuntimeException("Failed to retrieve and map Student by ID"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.ofIdentity(studentID);
        });
        assertTrue(exception.getMessage().contains("Failed to retrieve and map Student by ID"));
    }

    @Test
    public void testFindAllThrowsRuntimeExceptionOnMappingFailure() throws Exception {
        // Arrange
        List<StudentDataModel> dataModels = Collections.singletonList(dataModel);
        when(repoMock.findAll()).thenReturn(dataModels);
        when(studentMapperImpl.dataModelToDomain(dataModel)).thenThrow(new RuntimeException("Mapping fail"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.findAll();
        });
        assertTrue(exception.getMessage().contains("Failed to convert"));
    }

    @Test
    public void testSaveThrowsRuntimeExceptionOnRepositoryFailure() {
        // Arrange
        when(studentMapperImpl.domainToDataModel(student)).thenReturn(dataModel);
        doThrow(new RuntimeException("Could not save student")).when(repoMock).save(dataModel);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.save(student);
        });
        assertTrue(exception.getMessage().contains("Could not save student"));
    }

    @Test
    void shouldReturnFalseWhenExceptionOccursInContainsOfIdentity() {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(mock(StudentIDDataModel.class));
        when(repoMock.existsById(any())).thenThrow(new RuntimeException("Database error"));

        // Act
        boolean result = repository.containsOfIdentity(studentID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnEmptyWhenNoStudentFoundByIdentity() {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(mock(StudentIDDataModel.class));
        when(repoMock.findById(any())).thenReturn(Optional.empty());

        // Act
        Optional<Student> result = repository.ofIdentity(studentID);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnStudentsWhenFindAllSucceeds() throws Exception {
        // Arrange
        StudentDataModel dataModel = mock(StudentDataModel.class);
        List<StudentDataModel> dataModels = List.of(dataModel);
        when(repoMock.findAll()).thenReturn(dataModels);

        when(studentMapperImpl.dataModelToDomain(dataModel)).thenReturn(student);

        // Act
        List<Student> result = repository.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(student, result.get(0));
    }

    @Test
    void shouldThrowExceptionWhenFindAllFailsDueToMapping() throws Exception {
        //Arrange
        StudentDataModel dataModel = mock(StudentDataModel.class);
        List<StudentDataModel> dataModels = List.of(dataModel);
        when(repoMock.findAll()).thenReturn(dataModels);

        when(studentMapperImpl.dataModelToDomain(dataModel)).thenThrow(new RuntimeException("Mapping error"));

        //Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.findAll();
        });

        //Assert
        assertEquals("Failed to convert StudentDataModel to Student", exception.getMessage());
    }

}
