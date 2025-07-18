package PAI.persistence.springdata.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.mapper.NIFMapperImpl;
import PAI.mapper.student.StudentIDMapperImpl;
import PAI.mapper.student.StudentMapperImpl;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.student.StudentDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
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
        when(nif.getNIF()).thenReturn(String.valueOf(123123123));
        nifDataModel = mock(NIFDataModel.class);

        student = mock(Student.class);
        dataModel = mock(StudentDataModel.class);
    }

    @Test
    public void testConstructorThrowsOnNullsIfStudentRepositorySpringDataIsNull() {

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringDataImpl(null, studentMapperImpl, studentIDMapperImpl, nifMapperImpl));
    }

    @Test
    public void testConstructorThrowsOnNullsIfStudentMapperIsNull() {

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringDataImpl(repoMock, null, studentIDMapperImpl, nifMapperImpl));
    }

    @Test
    public void testConstructorThrowsOnNullsIfStudentIDMapperImplIsNull() {

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringDataImpl(repoMock, studentMapperImpl, null, nifMapperImpl));
    }

    @Test
    public void testConstructorThrowsOnNullsIfNifMapperImplIsNull() {

        // Act & Assert
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
        assertEquals(Optional.of(student), result);
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
        when(nif.getCountry()).thenReturn(mock(Country.class));


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
        assertEquals(List.of(student), result);
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

    @Test
    void testExistsByStudentIDOrNIFReturnsFalseWhenNeitherExists() {
        // Arrange
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(nif.getNIF()).thenReturn("123123123");
        when(nif.getCountry()).thenReturn(mock(Country.class));
        when(nif.getCountry().toString()).thenReturn("PT");
        when(repoMock.existsByStudentID(studentIDDataModel)).thenReturn(false);
        when(repoMock.existsByNIF_NifNumberAndNIF_NifCountry("123123123", "PT")).thenReturn(false);

        // Act
        boolean result = repository.existsByStudentIDOrNIF(studentID, nif);

        // Assert
        assertFalse(result);
    }

    @Test
    void testExistsByStudentIDOrNIFThrowsWhenStudentIDExists() {
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(nif.getNIF()).thenReturn("123123123");
        when(nif.getCountry()).thenReturn(mock(Country.class));
        when(nif.getCountry().toString()).thenReturn("PT");
        when(repoMock.existsByStudentID(studentIDDataModel)).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            repository.existsByStudentIDOrNIF(studentID, nif);
        });
        assertEquals("StudentID already exists!", ex.getMessage());
    }

    @Test
    void testExistsByStudentIDOrNIFThrowsWhenNIFExists() {
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(nif.getNIF()).thenReturn("123123123");
        when(nif.getCountry()).thenReturn(mock(Country.class));
        when(nif.getCountry().toString()).thenReturn("PT");
        when(repoMock.existsByStudentID(studentIDDataModel)).thenReturn(false);
        when(repoMock.existsByNIF_NifNumberAndNIF_NifCountry("123123123", "PT")).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            repository.existsByStudentIDOrNIF(studentID, nif);
        });
        assertEquals("NIF already exists!", ex.getMessage());
    }

    @Test
    void testLastStudentIDReturnsCorrectValueWhenGreaterThanZero() {
        // Arrange
        when(repoMock.lastStudentID()).thenReturn(1122334);

        // Act
        int result = repository.lastStudentID();

        // Assert
        assertEquals(1122334, result);
    }

    @Test
    void testLastStudentIDReturnsFallbackWhenNull() {
        // Arrange
        when(repoMock.lastStudentID()).thenReturn(null);

        // Act
        int result = repository.lastStudentID();

        // Assert
        assertEquals(1000000, result);
    }


}
