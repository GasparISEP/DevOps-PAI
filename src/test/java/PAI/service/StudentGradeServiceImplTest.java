package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.StudentGrade;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentGradeServiceImplTest {

    @Test
    public void shouldCreatConstructor(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);

        //act
        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl( studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository);

        //assert
        assertNotNull(studentGradeServiceImpl);
    }

    @Test
    public void shouldNotCreatConstructorWithNullFactory(){
        //arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(null, studentGradeRepository,courseEditionEnrolmentRepository));
    }

    @Test
    public void shouldNotCreatConstructorWithNullRepository(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(studentGradeFactory, null,courseEditionEnrolmentRepository));
    }

    @Test
    public void shouldNotCreatConstructorWithNullCourseEditionEnrolmentRepository(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,null));
    }

    @Test
    public void shouldAddNewStudentGrade() throws Exception {
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository);

        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        StudentGrade studentGrade = mock(StudentGrade.class);
        when(courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID,courseEditionID)).thenReturn(true);
        when(studentGradeFactory.newGradeStudent(grade, date, studentID, courseEditionID)).thenReturn(studentGrade);
        when(studentGradeRepository.save(studentGrade)).thenReturn(studentGrade);

        //act
        StudentGrade result = studentGradeServiceImpl.newStudentGrade(grade,date,studentID,courseEditionID);

        //assert

        assertEquals(studentGrade, result);

    }
    @Test
    public void shouldThrowAnExceptionWhenNotEnrolled() throws Exception {
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository);

        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        when(courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID,courseEditionID)).thenReturn(false);
        // assert
        assertThrows(Exception.class, () -> studentGradeServiceImpl.newStudentGrade(grade, date, studentID, courseEditionID));

    }

    @Test
    public void shouldGetAverageGradeOfCourseEditionOf15() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(10.0);
        when(grade1.knowGrade()).thenReturn(20.0);


        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);
        when(studentGrade3.get_grade()).thenReturn(grade);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade3.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(false);

        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2,studentGrade3));

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository);


        // Act
        Double averageGrade = studentGradeServiceImpl.getAverageGrade(courseEditionID1Double);

        // Assert
        assertEquals(15, averageGrade, 0.01);
    }


    @Test
    public void shouldNotGetAverageGradeOfCourseEditionBecauseHave0Students() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);


        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository);


        // Act
        Double averageGrade = studentGradeServiceImpl.getAverageGrade(courseEditionID1Double);

        // Assert
        assertEquals(null, averageGrade);
    }

    @Test
    public void shouldGetApprovalRateOf100() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(10.0);
        when(grade1.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);
        when(studentGrade3.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade3.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(false);

        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3));

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository);


        // Act
        double averageGrade = studentGradeServiceImpl.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(100, averageGrade, 0.01);
    }

    @Test
    public void shouldGetApprovalRateOf50() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(8.0);
        when(grade1.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);
        when(studentGrade3.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade3.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(false);

        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3));

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository);


        // Act
        double averageGrade = studentGradeServiceImpl.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(50, averageGrade, 0.01);
    }

    @Test
    public void shouldNotGetApprovalRateWith0Student() throws Exception {

        // Arrange
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);


        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository);


        // Act
        Double averageGrade = studentGradeServiceImpl.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(0, averageGrade);
    }
}