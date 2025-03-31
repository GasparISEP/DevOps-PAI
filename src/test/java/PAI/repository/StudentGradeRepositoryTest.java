
package PAI.repository;

import PAI.VOs.*;
import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeListFactory;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentGradeRepositoryTest {

    @Test
    void shouldAddGradeToAStudent() throws Exception {
        // Arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student1, courseEditionID1Double))
                .thenReturn(studentGrade1);

        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student2, courseEditionID1Double))
                .thenReturn(studentGrade2);

        // Act
        boolean result1 = list.addGradeToStudent(grade, dateDouble, student1, courseEditionID1Double);
        boolean result2 = list.addGradeToStudent(grade, dateDouble, student2, courseEditionID1Double);

        // Assert
        assertTrue(result1);
        assertTrue(result2);

    }

    @Test
    void shouldNotAddGradeWhenStudentHasAlreadyGradeAtCertainCourseEdition() throws Exception{
        //arrange
        IStudentGradeFactory IStudentGradeFactoryDouble = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactoryDouble = mock(IStudentGradeListFactory.class);
        StudentGradeRepository studentGradeRepository = new StudentGradeRepository(IStudentGradeFactoryDouble,IStudentGradeListFactoryDouble);

        StudentID studentDouble = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        StudentGrade studentGradeDouble = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when (IStudentGradeFactoryDouble.newGradeStudent(grade,dateDouble,studentDouble,courseEditionID1Double)).thenReturn(studentGradeDouble);

        when(studentGradeDouble.hasThisStudentID(studentDouble)).thenReturn(true);
        when(studentGradeDouble.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);

        ArrayList<StudentGrade> listDouble = mock(ArrayList.class);
        Iterator<StudentGrade> iteratorDouble = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iteratorDouble);
        when(iteratorDouble.hasNext()).thenReturn(true,false);
        when(iteratorDouble.next()).thenReturn(studentGradeDouble);

        //Act I
        boolean firstResult = studentGradeRepository.addGradeToStudent(grade,dateDouble,studentDouble,courseEditionID1Double);
        //assert I
        assertTrue(firstResult);
        //act II
        boolean result = studentGradeRepository.addGradeToStudent(grade,dateDouble,studentDouble,courseEditionID1Double);
        //assert II
        assertFalse(result);
    }

    @Test
    void shouldGradeStudentsAndHave100ApprovalRate() throws Exception {

        // Arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when(grade.knowGrade()).thenReturn(10.0);
        when(grade1.knowGrade()).thenReturn(20.0);

        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student1, courseEditionID1Double)).thenReturn(studentGrade1);
        when(IStudentGradeFactory.newGradeStudent(grade1, dateDouble, student2, courseEditionID1Double)).thenReturn(studentGrade2);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        list.addGradeToStudent(grade, dateDouble, student1, courseEditionID1Double);
        list.addGradeToStudent(grade1, dateDouble, student2, courseEditionID1Double);

        // act
        double approvalRate = list.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(100.0, approvalRate, 0.01);
    }


    @Test
    void shouldGradeStudentsAndHave0ApprovalRate() throws IllegalArgumentException {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(studentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(studentGradeFactory, studentGradeListFactory);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        // act
        double approvalRate = list.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(0.0, approvalRate, 0.01);
    }

    @Test
    void shouldGetAverageGradeOfCourseEditionOf15() throws Exception {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(studentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(studentGradeFactory, studentGradeListFactory);

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when(grade.knowGrade()).thenReturn(10.0);
        when(grade1.knowGrade()).thenReturn(20.0);

        when(studentGradeFactory.newGradeStudent(grade, dateDouble, student1, courseEditionID1Double)).thenReturn(studentGrade1);
        when(studentGradeFactory.newGradeStudent(grade1, dateDouble, student2, courseEditionID1Double)).thenReturn(studentGrade2);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);

        list.addGradeToStudent(grade, dateDouble, student1, courseEditionID1Double);
        list.addGradeToStudent(grade1, dateDouble, student2, courseEditionID1Double);

        // Act
        Double averageGrade = list.getAverageGrade(courseEditionID1Double);

        // Assert
        assertEquals(15, averageGrade, 0.01);
    }

    @Test
    void shouldGetAverageGradeOf0() throws Exception {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(studentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(studentGradeFactory, studentGradeListFactory);
        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when(grade.knowGrade()).thenReturn(0.0);
        when(grade1.knowGrade()).thenReturn(0.0);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(studentGradeFactory.newGradeStudent(grade ,dateDouble, student1, courseEditionID1Double)).thenReturn(studentGrade1);
        when(studentGradeFactory.newGradeStudent(grade1, dateDouble, student2, courseEditionID1Double)).thenReturn(studentGrade2);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);


        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);

        list.addGradeToStudent(grade, dateDouble, student1, courseEditionID1Double);
        list.addGradeToStudent(grade1, dateDouble, student2, courseEditionID1Double);

        // Act
        Double averageGrade = list.getAverageGrade(courseEditionID1Double);

        // Assert
        assertEquals(0, averageGrade, 0.01);

    }

    @Test
    void shouldNotGetAverageGradeOnCourseEditionWithoutStudents() throws IllegalArgumentException {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(studentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(studentGradeFactory, studentGradeListFactory);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        // Act
        Double averageGrade = list.getAverageGrade(courseEditionID1Double);

        // Assert
        assertNull(averageGrade);
    }

    @Test
    void shouldNotAddGradeToAStudentWithFactoryNull() throws IllegalArgumentException {
        // Arrange
        IStudentGradeListFactory studentGradeListFactory = mock(IStudentGradeListFactory.class);

        when(studentGradeListFactory.newArrayList()).thenReturn(new ArrayList<>());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new StudentGradeRepository(null, studentGradeListFactory));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }

    @Test
    void shouldNotAddGradeToAStudentWithListFactoryNull() throws IllegalArgumentException {
        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new StudentGradeRepository(studentGradeFactory, null));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }


    @Test
    void shouldReturnIdWhenStudentGradeExistsInList() throws Exception {
        // Arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        StudentID studentIDDouble = mock(StudentID.class);

        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student1, courseEditionID1Double))
                .thenReturn(studentGrade1);

        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student2, courseEditionID1Double))
                .thenReturn(studentGrade2);

        StudentGradeID studentGradeId = new StudentGradeID(studentIDDouble,courseEditionIDDouble);
        when(studentGrade1.identity()).thenReturn(studentGradeId);

        boolean result1 = list.addGradeToStudent(grade, dateDouble, student1, courseEditionID1Double);
        boolean result2 = list.addGradeToStudent(grade, dateDouble, student2, courseEditionID1Double);

        // Act
        Optional<StudentGradeID> result = list.findIdByStudent(studentGrade1);

        // Assert
        assertTrue(result.isPresent());

    }

    @Test
    void shouldReturnEmptyWhenStudentGradeNotInList() {
        // Arrange
        IStudentGradeFactory factory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory listFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> emptyList = spy(new ArrayList<>());
        when(listFactory.newArrayList()).thenReturn(emptyList);

        StudentGradeRepository repo = new StudentGradeRepository(factory, listFactory);

        StudentGrade studentGradeToSearch = mock(StudentGrade.class);

        // Act
        Optional<StudentGradeID> result = repo.findIdByStudent(studentGradeToSearch);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldSaveStudentGrade() throws Exception {
        // Arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository list = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);

        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student1, courseEditionID1Double))
                .thenReturn(studentGrade1);

        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student2, courseEditionID1Double))
                .thenReturn(studentGrade2);

        // Act
        StudentGrade result1 = list.save(studentGrade1);

        // Assert
        assertEquals(studentGrade1 , result1);

    }

    @Test
    void shouldReturnAllStudentGrades() throws Exception {
        // Arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository repository = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        StudentID student1 = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);

        StudentGrade studentGrade = mock(StudentGrade.class);
        when(IStudentGradeFactory.newGradeStudent(grade, date, student1, courseEditionID))
                .thenReturn(studentGrade);

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
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = new ArrayList<>();
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository repository = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

        // Act & Assert
        assertThrows(IllegalStateException.class, repository::findAll);
    }

    @Test
    void shouldReturnStudentGradeWhenIdExists() {
        // Arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository repository = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

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
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository repository = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

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
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());
        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        StudentGradeRepository repository = new StudentGradeRepository(IStudentGradeFactory, IStudentGradeListFactory);

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



