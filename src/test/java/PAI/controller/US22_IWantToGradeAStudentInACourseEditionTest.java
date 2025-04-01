
package PAI.controller;


import PAI.VOs.*;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ICourseEditionEnrolmentRepository;
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
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble);
        //assert
        assertNotNull(controller);
    }
    @Test
    void shouldNotCreateControllerWhenStudentGradeRepoIsNull() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = null;
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble,courseEditionEnrolmentRepositoryDouble));
    }
    @Test
    void shouldNotCreateControllerWhenCourseEditionEnrollmentIsNull() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);;
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = null;
        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble,iCourseEditionEnrolmentRepositoryDouble));
    }

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInCourseEdition() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble);

        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble);

        //assert
        assertTrue(result);
    }
    @Test
    void shouldAddGradeToAStudentInACourseEdition() throws Exception {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble);

        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(true);
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
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble);

        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(false);
        when(iStudentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble);

        //assert
        assertFalse(result);
    }
}




