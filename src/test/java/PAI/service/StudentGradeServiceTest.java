package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeListFactory;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.StudentGradeRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentGradeServiceTest {

    @Test
    public void shouldCreatConstructor(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);

        //act
        StudentGradeService studentGradeService = new StudentGradeService( studentGradeFactory, studentGradeRepository);

        //assert
        assertNotNull(studentGradeService);
    }

    @Test
    public void shouldNotCreatConstructorWithNullFactory(){
        //arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeService(null, studentGradeRepository));
    }

    @Test
    public void shouldNotCreatConstructorWithNullRepository(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeService(studentGradeFactory, null));
    }

    @Test
    public void shouldCreateNewStudentGrade() throws Exception {
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        StudentGradeService studentGradeService = new StudentGradeService(studentGradeFactory, studentGradeRepository);

        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        StudentGrade expected = new StudentGrade(grade, date, studentID, courseEditionID);
        studentGradeRepository.save(expected);
        when(studentGradeFactory.newGradeStudent(grade, date, studentID, courseEditionID))
                .thenReturn(expected);
        when(studentGradeRepository.save(expected)).thenReturn(expected);

        //act
        StudentGrade actual = studentGradeService.newStudentGrade(grade,date,studentID,courseEditionID);

        //assert

        assertEquals(expected, actual);

    }

    @Test
    public void shouldGetAverageGradeOfCourseEditionOf15() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(10.0);
        when(grade1.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);

        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2));

        StudentGradeService studentGradeService = new StudentGradeService(studentGradeFactory, studentGradeRepository);


        // Act
        Double averageGrade = studentGradeService.getAverageGrade(courseEditionID1Double);

        // Assert
        assertEquals(15, averageGrade, 0.01);
    }

    @Test
    public void shouldNotGetAverageGradeOfCourseEditionBecauseHave0Students() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);


        StudentGradeService studentGradeService = new StudentGradeService(studentGradeFactory, studentGradeRepository);


        // Act
        Double averageGrade = studentGradeService.getAverageGrade(courseEditionID1Double);

        // Assert
        assertEquals(null, averageGrade);
    }

    @Test
    public void shouldGetApprovalRateOf100() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(10.0);
        when(grade1.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);

        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2));

        StudentGradeService studentGradeService = new StudentGradeService(studentGradeFactory, studentGradeRepository);


        // Act
        double averageGrade = studentGradeService.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(100, averageGrade, 0.01);
    }

    @Test
    public void shouldNotGetApprovalRateWith0Student() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);


        StudentGradeService studentGradeService = new StudentGradeService(studentGradeFactory, studentGradeRepository);


        // Act
        Double averageGrade = studentGradeService.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(0, averageGrade);
    }
}