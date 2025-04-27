
package PAI.repository;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeListFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentGradeRepositoryImplTest {

    @Test
    void shouldCreateConstructor(){
        //arrange
        IStudentGradeListFactory iStudentGradeListFactory = mock(IStudentGradeListFactory.class);
        StudentGradeRepositoryImpl studentGradeRepositoryImpl = new StudentGradeRepositoryImpl(iStudentGradeListFactory);
        //assert
        assertNotNull(studentGradeRepositoryImpl);
    }

    @Test
    void shouldNotAddGradeToAStudentWithListFactoryNull() throws IllegalArgumentException {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new StudentGradeRepositoryImpl( null));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }

    @Test
    void shouldSaveStudentGrade() throws Exception {
        // Arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);
        StudentGradeRepositoryImpl list = new StudentGradeRepositoryImpl(IStudentGradeListFactory);
        StudentGrade studentGrade1 = mock(StudentGrade.class);
        // Act
        StudentGrade result1 = list.save(studentGrade1);
        // Assert
        assertEquals(studentGrade1 , result1);
    }

    @Test
    void shouldReturnAllStudentGrades() throws Exception {
        // Arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);
        StudentGradeRepositoryImpl repository = new StudentGradeRepositoryImpl(IStudentGradeListFactory);
        StudentGrade studentGrade = mock(StudentGrade.class);
        // Act
        repository.save(studentGrade);
        Iterable<StudentGrade> result = repository.findAll();
        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        assertEquals(studentGrade, result.iterator().next());
    }

    @Test
    void shouldThrowExceptionWhenListIsEmpty() {
        // Arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = new ArrayList<>();
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);
        StudentGradeRepositoryImpl repository = new StudentGradeRepositoryImpl(IStudentGradeListFactory);
        // Act & Assert
        assertThrows(IllegalStateException.class, repository::findAll);
    }

    @Test
    void shouldReturnStudentGradeWhenIdExists() {
        // Arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepositoryImpl repository = new StudentGradeRepositoryImpl(IStudentGradeListFactory);

        StudentGradeID gradeID = mock(StudentGradeID.class);
        StudentGrade studentGrade = mock(StudentGrade.class);
        when(studentGrade.identity()).thenReturn(gradeID);
        repository.save(studentGrade);
        // Act
        Optional<StudentGrade> result = repository.ofIdentity(gradeID);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(studentGrade, result.get());
    }

    @Test
    void shouldReturnTrueWhenIdExists() {
        // Arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);
        StudentGradeRepositoryImpl repository = new StudentGradeRepositoryImpl(IStudentGradeListFactory);
        StudentGradeID gradeID = mock(StudentGradeID.class);
        StudentGrade studentGrade = mock(StudentGrade.class);
        when(studentGrade.identity()).thenReturn(gradeID);
        repository.save(studentGrade);
        // Act
        boolean result = repository.containsOfIdentity(gradeID);
        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenIdDoesNotExist() {
        // Arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);
        StudentGradeRepositoryImpl repository = new StudentGradeRepositoryImpl(IStudentGradeListFactory);
        StudentGradeID existingID = mock(StudentGradeID.class);
        StudentGradeID otherID = mock(StudentGradeID.class);
        StudentGrade studentGrade = mock(StudentGrade.class);
        when(studentGrade.identity()).thenReturn(existingID);
        repository.save(studentGrade);
        // Act
        boolean result = repository.containsOfIdentity(otherID);
        // Assert
        assertFalse(result);
    }
}



