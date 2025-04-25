
package PAI.controller;


import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.repository.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US22_IWantToGradeAStudentInACourseEditionControllerTest {
    @Test
    void shouldCreateController() {
        //arrange
        StudentGradeRepository StudentGradeRepositoryDouble = mock(StudentGradeRepository.class);
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(StudentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);
        //assert
        assertNotNull(controller);
    }
    @Test
    void shouldNotCreateControllerWhenStudentGradeRepoIsNull() {
        //arrange
        StudentGradeRepository studentGradeRepositorytudentGradeRepositoryDouble = null;
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEditionController(studentGradeRepositorytudentGradeRepositoryDouble,courseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository));
    }
    @Test
    void shouldNotCreateControllerWhenCourseEditionEnrollmentIsNull() {
        //arrange
        StudentGradeRepository studentGradeRepository = mock(StudentGradeRepository.class);;
        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = null;
        IStudentRepository studentRepository = mock(StudentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEditionController(studentGradeRepository,iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository));
    }
//    @Test
//    void shouldReturnTrueIfStudentIsEnrolledInCourseEdition() {
//        //arrange
//        StudentGradeRepository studentGradeRepositoryDouble = mock(StudentGradeRepository.class);
//        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
//        IStudentRepository studentRepository = mock(StudentRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
//        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);
//
//        Student student = mock(Student.class);
//        StudentID studentID = mock(StudentID.class);
//        CourseEdition courseEdition_DDDDouble = mock(CourseEdition.class);
//        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
//
//        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentID));
//        when(courseEditionRepository.findIdByCourseEdition(courseEdition_DDDDouble)).thenReturn(Optional.of(courseEditionIDDouble));
//        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID,courseEditionIDDouble)).thenReturn(true);
//
//        //act
//        boolean result = controller.isStudentEnrolledInCourseEdition(student, courseEdition_DDDDouble);
//
//        //assert
//        assertTrue(result);
//    }

//    @Test
//    void shouldReturnFalseIfStudentIDIsNotFoundAndEnrollInCourseEdition() {
//        //arrange
//        StudentGradeRepository studentGradeRepositoryDouble = mock(StudentGradeRepository.class);
//        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
//        IStudentRepository studentRepository = mock(StudentRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
//        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);
//
//        Student student = mock(Student.class);
//        StudentID studentID = mock(StudentID.class);
//        CourseEdition courseEdition_DDDDouble = mock(CourseEdition.class);
//        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
//
//        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.empty());
//        when(courseEditionRepository.findIdByCourseEdition(courseEdition_DDDDouble)).thenReturn(Optional.of(courseEditionIDDouble));
//
//        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID,courseEditionIDDouble)).thenReturn(true);
//
//        //act
//        boolean result = controller.isStudentEnrolledInCourseEdition(student, courseEdition_DDDDouble);
//
//        //assert
//        assertFalse(result);
//    }
//    @Test
//    void shouldReturnFalseIfCourseEditionIDNotFoundAndEnrollInCourseEdition() {
//        //arrange
//        StudentGradeRepository studentGradeRepositoryDouble = mock(StudentGradeRepository.class);
//        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
//        IStudentRepository studentRepository = mock(StudentRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
//        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);
//
//        Student student = mock(Student.class);
//        StudentID studentID = mock(StudentID.class);
//        CourseEdition courseEdition_DDDDouble = mock(CourseEdition.class);
//        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
//
//        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentID));
//        when(courseEditionRepository.findIdByCourseEdition(courseEdition_DDDDouble)).thenReturn(Optional.empty());
//
//        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentID,courseEditionIDDouble)).thenReturn(true);
//
//        //act
//        boolean result = controller.isStudentEnrolledInCourseEdition(student, courseEdition_DDDDouble);
//
//        //assert
//        assertFalse(result);
//    }

//    @Test
//    void shouldAddGradeToAStudentInACourseEdition() throws Exception {
//        //arrange
//        StudentGradeRepository studentGradeRepositoryDouble = mock(StudentGradeRepository.class);
//        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
//        IStudentRepository studentRepository = mock(StudentRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
//
//        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);
//
//        Student student = mock(Student.class);
//        CourseEdition courseEdition_DDDDouble = mock(CourseEdition.class);
//        StudentID studentIDDouble = mock(StudentID.class);
//        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
//        Grade gradeDouble = mock(Grade.class);
//        Date dateDouble = mock(Date.class);
//
//        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentIDDouble));
//        when(courseEditionRepository.findIdByCourseEdition(courseEdition_DDDDouble)).thenReturn(Optional.of(courseEditionIDDouble));
//
//        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(true);
//        when(studentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);
//
//        //act
//        boolean result = controller.registerStudentGrade(gradeDouble,dateDouble,student, courseEdition_DDDDouble);
//
//        //assert
//        assertTrue(result);
//    }
//    @Test
//    void shouldNotAddGradeToAStudentWhenStudentIsNotEnrolledInACourseEdition() throws Exception {
//        //arrange
//        StudentGradeRepository studentGradeRepositoryDouble = mock(StudentGradeRepository.class);
//        ICourseEditionEnrolmentRepository iCourseEditionEnrolmentRepositoryDouble = mock(ICourseEditionEnrolmentRepository.class);
//        IStudentRepository studentRepository = mock(StudentRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
//        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeRepositoryDouble, iCourseEditionEnrolmentRepositoryDouble,studentRepository,courseEditionRepository);
//
//        Student student = mock(Student.class);
//        CourseEdition courseEdition_DDDDouble = mock(CourseEdition.class);
//        StudentID studentIDDouble = mock(StudentID.class);
//        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
//        Grade gradeDouble = mock(Grade.class);
//        Date dateDouble = mock(Date.class);
//
//        when(studentRepository.findIdByStudent(student)).thenReturn(Optional.of(studentIDDouble));
//        when(courseEditionRepository.findIdByCourseEdition(courseEdition_DDDDouble)).thenReturn(Optional.of(courseEditionIDDouble));
//
//
//        when(iCourseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentIDDouble,courseEditionIDDouble)).thenReturn(false);
//        when(studentGradeRepositoryDouble.addGradeToStudent(gradeDouble,dateDouble,studentIDDouble,courseEditionIDDouble)).thenReturn(true);
//
//        //act
//        boolean result = controller.registerStudentGrade(gradeDouble,dateDouble,student, courseEdition_DDDDouble);
//
//        //assert
//        assertFalse(result);
//    }

}




