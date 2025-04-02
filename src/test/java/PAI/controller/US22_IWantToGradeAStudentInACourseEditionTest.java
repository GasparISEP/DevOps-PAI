
package PAI.controller;


import PAI.VOs.*;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US22_IWantToGradeAStudentInACourseEditionTest {
    @Test
    void shouldCreateController() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository);
        //assert
        assertNotNull(controller);
    }
    @Test
    void shouldNotCreateControllerWhenStudentGradeRepoIsNull() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = null;
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble,courseEditionEnrolmentRepositoryDouble,studentRepository));
    }
    @Test
    void shouldNotCreateControllerWhenCourseEditionEnrollmentIsNull() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);;
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = null;
        IStudentRepository studentRepository = mock(StudentRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble,iCourseEditionEnrolmentRepositoryDouble,studentRepository));
    }

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInCourseEdition() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository);

        Student student = mock(Student.class);
        StudentID studentID = mock(StudentID.class);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentID));
        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.isStudentEnrolledInCourseEdition(student,courseEditionIDDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIDIsNotFoundAndEnrollInCourseEdition() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository);

        Student student = mock(Student.class);
        StudentID studentID = mock(StudentID.class);


        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.empty());

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.isStudentEnrolledInCourseEdition(student,courseEditionIDDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldAddGradeToAStudentInACourseEdition() throws Exception {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);

        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository);

        Student student = mock(Student.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentIDDouble));

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(true);
        when(iStudentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(gradeDouble,dateDouble,student,courseEditionIDDouble);

        //assert
        assertTrue(result);
    }
    @Test
    void shouldNotAddGradeToAStudentWhenStudentIsNotEnrolledInACourseEdition() throws Exception {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository);

        Student student = mock(Student.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentIDDouble));

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(false);
        when(iStudentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(gradeDouble,dateDouble,student,courseEditionIDDouble);

        //assert
        assertFalse(result);
    }
}




