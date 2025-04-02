
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
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);
        //assert
        assertNotNull(controller);
    }
    @Test
    void shouldNotCreateControllerWhenStudentGradeRepoIsNull() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = null;
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble,courseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository));
    }
    @Test
    void shouldNotCreateControllerWhenCourseEditionEnrollmentIsNull() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);;
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = null;
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble,iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository));
    }
    @Test
    void shouldReturnTrueIfStudentIsEnrolledInCourseEdition() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);

        Student student = mock(Student.class);
        StudentID studentID = mock(StudentID.class);
        CourseEdition_2 courseEdition_2Double = mock(CourseEdition_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentID));
        when(courseEditionRepository.findIdByCourseEdition(courseEdition_2Double)).thenReturn(Optional.of(courseEditionIDDouble));
        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.isStudentEnrolledInCourseEdition(student,courseEdition_2Double);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIDIsNotFoundAndEnrollInCourseEdition() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);

        Student student = mock(Student.class);
        StudentID studentID = mock(StudentID.class);
        CourseEdition_2 courseEdition_2Double = mock(CourseEdition_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.empty());
        when(courseEditionRepository.findIdByCourseEdition(courseEdition_2Double)).thenReturn(Optional.of(courseEditionIDDouble));

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.isStudentEnrolledInCourseEdition(student,courseEdition_2Double);

        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseIfCourseEditionIDNotFoundAndEnrollInCourseEdition() {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);

        Student student = mock(Student.class);
        StudentID studentID = mock(StudentID.class);
        CourseEdition_2 courseEdition_2Double = mock(CourseEdition_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentID));
        when(courseEditionRepository.findIdByCourseEdition(courseEdition_2Double)).thenReturn(Optional.empty());

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.isStudentEnrolledInCourseEdition(student,courseEdition_2Double);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldAddGradeToAStudentInACourseEdition() throws Exception {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);

        Student student = mock(Student.class);
        CourseEdition_2 courseEdition_2Double = mock(CourseEdition_2.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentIDDouble));
        when(courseEditionRepository.findIdByCourseEdition(courseEdition_2Double)).thenReturn(Optional.of(courseEditionIDDouble));

        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(true);
        when(iStudentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(gradeDouble,dateDouble,student,courseEdition_2Double);

        //assert
        assertTrue(result);
    }
    @Test
    void shouldNotAddGradeToAStudentWhenStudentIsNotEnrolledInACourseEdition() throws Exception {
        //arrange
        IStudentGradeRepository iStudentGradeRepositoryDouble = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(iStudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);

        Student student = mock(Student.class);
        CourseEdition_2 courseEdition_2Double = mock(CourseEdition_2.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentIDDouble));
        when(courseEditionRepository.findIdByCourseEdition(courseEdition_2Double)).thenReturn(Optional.of(courseEditionIDDouble));


        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(false);
        when(iStudentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);

        //act
        boolean result = controller.iWantToGradeAStudent(gradeDouble,dateDouble,student,courseEdition_2Double);

        //assert
        assertFalse(result);
    }

}




