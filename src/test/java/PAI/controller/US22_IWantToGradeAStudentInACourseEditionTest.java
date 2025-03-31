
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

        StudentID student1 = mock(StudentID.class);
        Student student = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        StudentGrade studentGrade1 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when(controller.iWantToGradeAStudent(grade, dateDouble, student1, courseEdition1)).thenReturn(true);
        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student1, courseEdition1)).thenReturn(studentGrade1);
        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(student);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);
        when(enrollment1.isEnrollmentActive()).thenReturn(true);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrolment(student, courseEdition1)).thenReturn(enrollment1);
        when(enrollmentRepository.enrolStudentInACourseEdition(student, courseEdition1)).thenReturn(true);

        //act
        enrollmentRepository.enrolStudentInACourseEdition(student, courseEdition1);
        boolean result = controller.iWantToGradeAStudent(grade,dateDouble,student1,courseEdition1);

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

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        Student student = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(student);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrolment(student, courseEdition1)).thenReturn(enrollment1);

        enrollmentRepository.enrolStudentInACourseEdition(student, courseEdition1);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        Date dateDouble = mock(Date.class);


        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student1, courseEdition1)).thenReturn(studentGrade1);
        when(IStudentGradeFactory.newGradeStudent(grade1, dateDouble, student2, courseEdition1)).thenReturn(studentGrade2);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(studentGrade2.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(controller1.isStudentEnrolledInCourseEdition(student1, courseEdition1)).thenReturn(false);

        // act
        Boolean result = controller1.isStudentEnrolledInCourseEdition(student2,courseEdition1);

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

        StudentID student1 = mock(StudentID.class);
        StudentID student2 = mock(StudentID.class);
        Student student = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(student);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrolment(student, courseEdition1)).thenReturn(enrollment1);

        enrollmentRepository.enrolStudentInACourseEdition(student, courseEdition1);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);


        when(IStudentGradeFactory.newGradeStudent(grade, dateDouble, student1, courseEdition1)).thenReturn(studentGrade1);
        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(controller1.isStudentEnrolledInCourseEdition(student1, courseEdition1)).thenReturn(false);


        // act
        boolean result = controller1.iWantToGradeAStudent(grade,dateDouble,student2,courseEdition1);

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

        StudentID studentDouble = mock(StudentID.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        StudentGrade studentGradeDouble = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        when(courseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentDouble,courseEditionDouble)).thenReturn(true);
        when(studentGradeRepositoryDouble.addGradeToStudent(grade,dateDouble,studentDouble,courseEditionDouble)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(grade,dateDouble,studentDouble,courseEditionDouble);

        //assert

        assertTrue(result);
    }
}




