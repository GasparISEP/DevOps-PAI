package PAI.repository;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.factory.IStudentListFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentRepositoryTest {

    private IStudentListFactory studentListFactory;
    private StudentRepository studentRepository;
    private Student student;
    private StudentID studentID;
    private NIF studentNIF;

    @BeforeEach
    void setUp() {
        studentListFactory = mock(IStudentListFactory.class);
        studentRepository = new StudentRepository(studentListFactory);
        student = mock(Student.class);
        studentID = mock(StudentID.class);
        studentNIF = mock(NIF.class);
        when(studentListFactory.newArrayList()).thenReturn(new ArrayList<>());
    }

    @Test
    void shouldThrowExceptionWhenFactoryIsNull() {
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentRepository(null);
        });

        // Assert
        assertEquals("Invalid factory argument, null values are not allowed!", exception.getMessage());
    }

    @Test
    void shouldInitializeWithEmptyList() {
        // Act
        StudentRepository repository = new StudentRepository(studentListFactory);

        // Assert
        assertNotNull(repository);
    }

    @Test
    void shouldSaveStudent() {
        // Act
        Student savedStudent = studentRepository.save(student);

        // Assert
        verify(studentListFactory).newArrayList();
        assertEquals(student, savedStudent);
    }

    @Test
    void shouldThrowExceptionWhenListIsEmpty() {
        // Act + Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            studentRepository.findAll();
        });

        // Assert
        assertEquals("Student List is currently empty.", exception.getMessage());
    }

    @Test
    void shouldReturnStudentsWhenListIsNotEmpty() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        StudentRepository repo = new StudentRepository(studentListFactory);

        // Act
        Iterable<Student> students = repo.findAll();

        // Assert
        assertNotNull(students);
        assertTrue(students.iterator().hasNext());
    }

    @Test
    void shouldReturnStudentWhenFound() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepository = new StudentRepository(studentListFactory);

        when(student.identity()).thenReturn(studentID);
        when(student.sameAs(student)).thenReturn(true);

        // Act
        Optional<Student> result = studentRepository.ofIdentity(studentID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(student, result.get());
    }

    @Test
    void shouldReturnEmptyWhenNotFound() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepository = new StudentRepository(studentListFactory);

        // Act
        Optional<Student> result = studentRepository.ofIdentity(studentID);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueWhenStudentExists() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        when(student.identity()).thenReturn(studentID);
        studentList.add(student);
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepository = new StudentRepository(studentListFactory);

        // Act
        boolean result = studentRepository.containsOfIdentity(studentID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentDoesNotExist2() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        Student anotherStudent = mock(Student.class);
        StudentID anotherID = mock(StudentID.class);

        when(anotherStudent.identity()).thenReturn(anotherID);
        studentList.add(anotherStudent);
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepository = new StudentRepository(studentListFactory);

        // Act
        boolean result = studentRepository.containsOfIdentity(studentID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenStudentExistsByID() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        when(student.identity()).thenReturn(studentID);
        studentList.add(student);
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepository = new StudentRepository(studentListFactory);

        // Act
        boolean result = studentRepository.existsByStudentIDOrNIF(studentID, studentNIF);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenStudentExistsByNIF() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        when(student.identity()).thenReturn(mock(StudentID.class));
        when(student.getStudentNIF()).thenReturn(studentNIF);
        studentList.add(student);
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepository = new StudentRepository(studentListFactory);

        // Act
        boolean result = studentRepository.existsByStudentIDOrNIF(studentID, studentNIF);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentDoesNotExist() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepository = new StudentRepository(studentListFactory);

        // Act
        boolean result = studentRepository.existsByStudentIDOrNIF(studentID, studentNIF);

        // Assert
        assertFalse(result);
    }
}