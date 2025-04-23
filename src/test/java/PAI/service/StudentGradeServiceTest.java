package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        when(studentGradeFactory.newGradeStudent(grade, date, studentID, courseEditionID))
                .thenReturn(expected);
        when(studentGradeRepository.save(expected)).thenReturn(expected);

        //act
        StudentGrade actual = studentGradeService.newStudentGrade(grade,date,studentID,courseEditionID);

        //assert

        assertEquals(expected, actual);

    }

}