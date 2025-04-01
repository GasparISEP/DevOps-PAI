
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
    void shouldCreateController() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(CourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, courseEditionEnrolmentRepositoryDouble);
        //assert
        assertNotNull(controller);
    }
    @Test
    void shouldNotCreateControllerWhenStudentGradeRepoIsNull() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = null;
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(CourseEditionEnrolmentRepository.class);
        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble,courseEditionEnrolmentRepositoryDouble));
    }
    @Test
    void shouldNotCreateControllerWhenCourseEditionEnrollmentIsNull() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);;
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = null;
        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble,courseEditionEnrolmentRepositoryDouble));
    }

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInCourseEdition() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(CourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, courseEditionEnrolmentRepositoryDouble);

        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(courseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble);

        //assert
        assertTrue(result);
    }
    @Test
    void shouldAddGradeToAStudentInACourseEdition() throws Exception {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(CourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, courseEditionEnrolmentRepositoryDouble);

        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(courseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(true);
        when(iStudentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble);

        //assert
        assertTrue(result);
    }
    @Test
    void shouldNotAddGradeToAStudentWhenStudentIsNotEnrolledInACourseEdition() throws Exception {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(CourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, courseEditionEnrolmentRepositoryDouble);

        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(courseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(false);
        when(iStudentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble);

        //assert
        assertFalse(result);
    }



}




