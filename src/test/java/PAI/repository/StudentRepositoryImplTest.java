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

class StudentRepositoryImplTest {

    private IStudentListFactory studentListFactory;
    private StudentRepositoryImpl studentRepositoryImpl;
    private Student student;
    private StudentID studentID;
    private NIF studentNIF;

    @BeforeEach
    void setUp() {
        studentListFactory = mock(IStudentListFactory.class);
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);
        student = mock(Student.class);
        studentID = mock(StudentID.class);
        studentNIF = mock(NIF.class);
        when(studentListFactory.newArrayList()).thenReturn(new ArrayList<>());
    }

    @Test
    void shouldThrowExceptionWhenFactoryIsNull() {
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentRepositoryImpl(null);
        });

        // Assert
        assertEquals("Invalid factory argument, null values are not allowed!", exception.getMessage());
    }

    @Test
    void shouldInitializeWithEmptyList() {
        // Act
        StudentRepositoryImpl repository = new StudentRepositoryImpl(studentListFactory);

        // Assert
        assertNotNull(repository);
    }

    @Test
    void shouldSaveStudent() {
        // Act
        Student savedStudent = studentRepositoryImpl.save(student);

        // Assert
        verify(studentListFactory).newArrayList();
        assertEquals(student, savedStudent);
    }

    @Test
    void shouldThrowExceptionWhenListIsEmpty() {
        // Act + Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            studentRepositoryImpl.findAll();
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
        StudentRepositoryImpl repo = new StudentRepositoryImpl(studentListFactory);

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
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);

        when(student.identity()).thenReturn(studentID);
        when(student.sameAs(student)).thenReturn(true);

        // Act
        Optional<Student> result = studentRepositoryImpl.ofIdentity(studentID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(student, result.get());
    }

    @Test
    void shouldReturnEmptyWhenNotFound() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);

        // Act
        Optional<Student> result = studentRepositoryImpl.ofIdentity(studentID);

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
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);

        // Act
        boolean result = studentRepositoryImpl.containsOfIdentity(studentID);

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
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);

        // Act
        boolean result = studentRepositoryImpl.containsOfIdentity(studentID);

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
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);

        // Act
        boolean result = studentRepositoryImpl.existsByStudentIDOrNIF(studentID, studentNIF);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenStudentExistsByNIF() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        when(student.identity()).thenReturn(mock(StudentID.class));
        when(student.hasNIF(studentNIF)).thenReturn(true);
        studentList.add(student);
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);

        // Act
        boolean result = studentRepositoryImpl.existsByStudentIDOrNIF(studentID, studentNIF);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentDoesNotExist() {
        // Arrange
        List<Student> studentList = new ArrayList<>();
        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);

        // Act
        boolean result = studentRepositoryImpl.existsByStudentIDOrNIF(studentID, studentNIF);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenNoStudentMatchesIDOrNIF() {
        // Arrange
        Student otherStudent1 = mock(Student.class);
        Student otherStudent2 = mock(Student.class);

        StudentID otherID1 = mock(StudentID.class);
        StudentID otherID2 = mock(StudentID.class);

        when(otherStudent1.identity()).thenReturn(otherID1);
        when(otherStudent2.identity()).thenReturn(otherID2);

        when(otherStudent1.hasNIF(studentNIF)).thenReturn(false);
        when(otherStudent2.hasNIF(studentNIF)).thenReturn(false);

        List<Student> studentList = new ArrayList<>();
        studentList.add(otherStudent1);
        studentList.add(otherStudent2);

        when(studentListFactory.newArrayList()).thenReturn(studentList);
        studentRepositoryImpl = new StudentRepositoryImpl(studentListFactory);

        // Act
        boolean result = studentRepositoryImpl.existsByStudentIDOrNIF(studentID, studentNIF);

        // Assert
        assertFalse(result);
    }
}