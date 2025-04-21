package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.mapper.NIFMapper;
import PAI.mapper.StudentIDMapper;
import PAI.mapper.StudentMapper;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.StudentDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentRepositorySpringDataTest {

    private IStudentRepositorySpringData repoMock;
    private StudentMapper studentMapper;
    private StudentIDMapper studentIDMapper;
    private NIFMapper nifMapper;

    private StudentRepositorySpringData repository;

    private Student student;
    private StudentDataModel dataModel;
    private StudentID studentID;
    private StudentIDDataModel studentIDDataModel;
    private NIF nif;
    private NIFDataModel nifDataModel;

    @BeforeEach
    public void setup() {
        repoMock = mock(IStudentRepositorySpringData.class);
        studentMapper = mock(StudentMapper.class);
        studentIDMapper = mock(StudentIDMapper.class);
        nifMapper = mock(NIFMapper.class);

        repository = new StudentRepositorySpringData(repoMock, studentMapper, studentIDMapper, nifMapper);

        studentID = new StudentID(1234567);
        studentIDDataModel = new StudentIDDataModel(1234567);
        nif = new NIF("123456789", new Country("Portugal"));
        nifDataModel = new NIFDataModel("123456789", "Portugal");

        student = mock(Student.class);
        dataModel = mock(StudentDataModel.class);
    }

    @Test
    public void testConstructorThrowsOnNulls() {
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringData(null, studentMapper, studentIDMapper, nifMapper));
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringData(repoMock, null, studentIDMapper, nifMapper));
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringData(repoMock, studentMapper, null, nifMapper));
        assertThrows(IllegalArgumentException.class, () -> new StudentRepositorySpringData(repoMock, studentMapper, studentIDMapper, null));
    }

    @Test
    public void testGetStudentByID() throws Exception {
        when(repoMock.findById(1234567)).thenReturn(Optional.of(dataModel));
        when(studentMapper.dataModelToDomain(dataModel)).thenReturn(student);

        Optional<Student> result = repository.getStudentByID(studentID);
        assertTrue(result.isPresent());
        assertEquals(student, result.get());
    }

    @Test
    public void testFindIdByStudent() {
        when(studentMapper.domainToDataModel(student)).thenReturn(dataModel);
        when(dataModel.getStudentID()).thenReturn(studentIDDataModel);
        when(studentIDMapper.dataModelToDomain(studentIDDataModel)).thenReturn(studentID);

        Optional<StudentID> result = repository.findIdByStudent(student);
        assertTrue(result.isPresent());
        assertEquals(studentID, result.get());
    }

    @Test
    public void testSave() {
        when(studentMapper.domainToDataModel(student)).thenReturn(dataModel);
        when(repoMock.save(dataModel)).thenReturn(dataModel);

        Student result = repository.save(student);
        assertEquals(student, result);
    }

    @Test
    public void testFindAll() throws Exception {
        List<StudentDataModel> dataModels = Arrays.asList(dataModel, dataModel);
        when(repoMock.findAll()).thenReturn(dataModels);
        when(studentMapper.dataModelToDomain(dataModel)).thenReturn(student);

        Iterable<Student> result = repository.findAll();
        List<Student> list = (List<Student>) result;
        assertEquals(2, list.size());
    }

    @Test
    public void testOfIdentityDelegatesToGetStudentByID() {
        StudentRepositorySpringData spyRepo = spy(repository);
        doReturn(Optional.of(student)).when(spyRepo).getStudentByID(studentID);

        Optional<Student> result = spyRepo.ofIdentity(studentID);
        assertTrue(result.isPresent());
    }

    @Test
    public void testContainsOfIdentity() {
        when(repoMock.existsById(1234567)).thenReturn(true);

        boolean result = repository.containsOfIdentity(studentID);
        assertTrue(result);
    }

    @Test
    public void testContainsByStudentIDOrNIF() {
        when(studentIDMapper.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(nifMapper.domainToDataModel(nif)).thenReturn(nifDataModel);
        when(repoMock.existsByStudentIDOrNIF(studentIDDataModel, nifDataModel)).thenReturn(true);

        boolean result = repository.existsByStudentIDOrNIF(studentID, nif);
        assertTrue(result);
    }

    @Test
    public void testContainsOfIdentityReturnsFalseWhenStudentDoesNotExist() {
        when(repoMock.existsById(1234567)).thenReturn(false);

        boolean result = repository.containsOfIdentity(studentID);
        assertFalse(result);
    }

    @Test
    public void testContainsByStudentIDOrNIFReturnsFalse() {
        when(studentIDMapper.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(nifMapper.domainToDataModel(nif)).thenReturn(nifDataModel);
        when(repoMock.existsByStudentIDOrNIF(studentIDDataModel, nifDataModel)).thenReturn(false);

        boolean result = repository.existsByStudentIDOrNIF(studentID, nif);
        assertFalse(result);
    }

    @Test
    public void testGetStudentByIDThrowsRuntimeExceptionOnMappingError() throws Exception {
        when(repoMock.findById(1234567)).thenReturn(Optional.of(dataModel));
        when(studentMapper.dataModelToDomain(dataModel)).thenThrow(new RuntimeException("Mapping failed"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.getStudentByID(studentID);
        });

        assertTrue(exception.getMessage().contains("Mapping error"));
    }

    @Test
    public void testFindAllThrowsRuntimeExceptionOnMappingFailure() throws Exception {
        List<StudentDataModel> dataModels = Collections.singletonList(dataModel);
        when(repoMock.findAll()).thenReturn(dataModels);
        when(studentMapper.dataModelToDomain(dataModel)).thenThrow(new RuntimeException("Mapping fail"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.findAll();
        });

        assertTrue(exception.getMessage().contains("Failed to convert"));
    }

}
