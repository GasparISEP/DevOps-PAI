
package PAI.controller;


import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.domain.StudentGrade;
import PAI.service.IStudentGradeService;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US22_IWantToGradeAStudentInACourseEditionControllerTest {
    @Test
    void shouldCreateController() {
        //arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);
        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeService);
        //act + assert
        assertNotNull(controller);
    }

    @Test
    void shouldNotCreateControllerWhenServiceIsNull() {
        //arrange
        IStudentGradeService studentGradeService = null;

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US22_IWantToGradeAStudentInACourseEditionController(studentGradeService));
    }

    @Test
    void shoudStudentGrade() throws Exception {
        //arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);
        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeService);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Student student = mock(Student.class);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGrade studentGrade = mock(StudentGrade.class);

        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(student.identity()).thenReturn(studentID);

        when(studentGradeService.newStudentGrade(grade, date, studentID, courseEditionID)).thenReturn(studentGrade);

        //act
        Optional<StudentGrade> result = controller.registerStudentGrade(grade,date,student,courseEdition);

        //assert
        assertTrue(result.isPresent());
        assertEquals(studentGrade,result.get());
    }

    @Test
    void shouldNotAddGradeWhenCourseEditionIsNull() throws Exception {
        //arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);
        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeService);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Student student = mock(Student.class);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = null;
        StudentGrade studentGrade = mock(StudentGrade.class);

        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(student.identity()).thenReturn(studentID);

        when(studentGradeService.newStudentGrade(grade, date, studentID, courseEditionID)).thenReturn(studentGrade);

        //act
        Optional<StudentGrade> result = controller.registerStudentGrade(grade,date,student,courseEdition);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotAddGradeWhenStudentIsNull() throws Exception {
        //arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);
        US22_IWantToGradeAStudentInACourseEditionController controller = new US22_IWantToGradeAStudentInACourseEditionController(studentGradeService);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        Student student = mock(Student.class);

        StudentID studentID = null;
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGrade studentGrade = mock(StudentGrade.class);

        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(student.identity()).thenReturn(studentID);

        when(studentGradeService.newStudentGrade(grade, date, studentID, courseEditionID)).thenReturn(studentGrade);

        //act
        Optional<StudentGrade> result = controller.registerStudentGrade(grade,date,student,courseEdition);

        //assert
        assertTrue(result.isEmpty());

    }


}




