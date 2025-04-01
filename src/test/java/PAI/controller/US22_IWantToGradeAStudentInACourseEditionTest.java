
package PAI.controller;


import PAI.VOs.*;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.StudentGradeRepository;
import PAI.repository.CourseEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US22_IWantToGradeAStudentInACourseEditionTest {
    @Test
    void gradeStudentInRepository () {
        //arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        //act
        US22_IWantToGradeAStudentInACourseEdition G1 = mock(US22_IWantToGradeAStudentInACourseEdition.class);
        //assert
        assertNotNull(G1);
    }

    @Test
    void iWantToGradeAStudentInACourseEdition () throws Exception {
        //arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);
        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn(mockGradeList);

        CourseEditionEnrolmentFactoryImpl courseEditionEnrolmentFactoryImpl = mock (CourseEditionEnrolmentFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = mock(CourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = mock(US22_IWantToGradeAStudentInACourseEdition.class);

        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        StudentGrade studentGrade1 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when(controller.iWantToGradeAStudent(grade, dateDouble, studentIDDouble, courseEditionID1Double)).thenReturn(true);
        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, studentIDDouble, courseEditionID1Double)).thenReturn(studentGrade1);
        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(studentIDDouble);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEditionID1Double);
        //when(enrollment1.isEnrollmentActive()).thenReturn(true);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrolment(studentIDDouble, courseEditionID1Double)).thenReturn(enrollment1);
        when(enrollmentRepository.enrolStudentInACourseEdition(studentIDDouble, courseEditionID1Double)).thenReturn(true);

        //act
        enrollmentRepository.enrolStudentInACourseEdition(studentIDDouble, courseEditionID1Double);
        boolean result = controller.iWantToGradeAStudent(grade,dateDouble,studentIDDouble,courseEditionID1Double);

        //assert
        assertTrue(result);
    }


    @Test
    void iWantToCheckIfStudentIsNotEnrolledInCourseEdition () throws Exception {
        //arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn((mockGradeList));

        CourseEditionEnrolmentFactoryImpl courseEditionEnrolmentFactoryImpl = mock (CourseEditionEnrolmentFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = mock(CourseEditionEnrolmentRepository.class);

        US22_IWantToGradeAStudentInACourseEdition controller1 = mock(US22_IWantToGradeAStudentInACourseEdition.class);

        StudentID studentID1Souble = mock(StudentID.class);
        StudentID studentID2Double = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(studentID1Souble);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEditionID1Double);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrolment(studentID1Souble, courseEditionID1Double)).thenReturn(enrollment1);

        enrollmentRepository.enrolStudentInACourseEdition(studentID1Souble, courseEditionID1Double);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        Date dateDouble = mock(Date.class);


        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, studentID1Souble, courseEditionID1Double)).thenReturn(studentGrade1);
        when(IStudentGradeFactory.newGradeStudent(grade1, dateDouble, studentID2Double, courseEditionID1Double)).thenReturn(studentGrade2);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(controller1.isStudentEnrolledInCourseEdition(studentID1Souble, courseEditionID1Double)).thenReturn(false);

        // act
        Boolean result = controller1.isStudentEnrolledInCourseEdition(studentID2Double,courseEditionID1Double);

        //assert
        assertFalse(result);
    }


    @Test
    void iWantoToGradeAStudentInCourseEditionNotEnrolledInCourseEdition () throws Exception {

        //arrange
        IStudentGradeFactory IStudentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn((mockGradeList));

        CourseEditionEnrolmentFactoryImpl courseEditionEnrolmentFactoryImpl = mock (CourseEditionEnrolmentFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = mock(CourseEditionEnrolmentRepository.class);

        StudentGradeRepository studentGradeRepository = mock(StudentGradeRepository.class);

        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(studentGradeRepository,enrollmentRepository);

        StudentID studentID1Souble = mock(StudentID.class);
        StudentID studentID2Double = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(studentID1Souble);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEditionID1Double);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrolment(studentID1Souble, courseEditionID1Double)).thenReturn(enrollment1);

        enrollmentRepository.enrolStudentInACourseEdition(studentID1Souble, courseEditionID1Double);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);


        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, studentID1Souble, courseEditionID1Double)).thenReturn(studentGrade1);
        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(controller1.isStudentEnrolledInCourseEdition(studentID1Souble, courseEditionID1Double)).thenReturn(false);


        // act
        boolean result = controller1.iWantToGradeAStudent(grade,dateDouble,studentID2Double,courseEditionID1Double);

        //assert
        assertFalse(result);
    }



    @Test
    void iWantToGradeAStudentInACourseEditionWithNullGradeStudentRepo () throws IllegalArgumentException {

        //arrange
        StudentGradeRepository studentGradeRepository1 = null;
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository1 = mock(CourseEditionEnrolmentRepository.class);


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US22_IWantToGradeAStudentInACourseEdition(studentGradeRepository1, courseEditionEnrolmentRepository1);
        });
        assertEquals("Repository cannot be null", exception.getMessage());

    }

    @Test
    void iWantToGradeAStudentInACourseEditionWithNullCourseEditionEnrollRepo () throws IllegalArgumentException {

        //arrange
        IStudentGradeListFactory IStudentGradeListFactory = mock(IStudentGradeListFactory.class);

        List<StudentGrade> mockGradeList = spy(new ArrayList<>());

        when(IStudentGradeListFactory.newArrayList()).thenReturn((mockGradeList));

        StudentGradeRepository list = mock(StudentGradeRepository.class);
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository1 = null;


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US22_IWantToGradeAStudentInACourseEdition(list, courseEditionEnrolmentRepository1);
        });
        assertEquals("Repository cannot be null", exception.getMessage());

    }

    @Test
    public void shouldReturnTrueWithGradeStudentIfGradeStudentWasAddedSuccessfully() throws Exception{

        //arrange

        StudentGradeRepository studentGradeRepositoryDouble = mock(StudentGradeRepository.class);
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(CourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(studentGradeRepositoryDouble, courseEditionEnrolmentRepositoryDouble);

        StudentID studentID1Souble = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        StudentGrade studentGradeDouble = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when(courseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID1Souble,courseEditionID1Double)).thenReturn(true);
        when(studentGradeRepositoryDouble.addGradeToStudent(grade,dateDouble,studentID1Souble,courseEditionID1Double)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(grade,dateDouble,studentID1Souble,courseEditionID1Double);

        //assert

        assertTrue(result);
    }
}




