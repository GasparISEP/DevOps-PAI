/*package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;
import PAI.mapper.StudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeRepositorySpringDataTest {

    @Test
    void shouldCreatConstructor(){
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeFactory iStudentGradeFactory = mock(IStudentGradeFactory.class);
        StudentGradeMapper studentGradeMapper = mock(StudentGradeMapper.class);

        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,iStudentGradeFactory,studentGradeMapper);

        //assert
        assertNotNull(studentGradeRepositorySpringData);
    }

    @Test
    void shouldReturnTrueWhenStudentGradeAdded() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeFactory iStudentGradeFactory = mock(IStudentGradeFactory.class);
        StudentGradeMapper studentGradeMapper = mock(StudentGradeMapper.class);

        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,iStudentGradeFactory,studentGradeMapper);

        StudentGrade studentGrade = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        when(iStudentGradeFactory.newGradeStudent(grade,date,studentID,courseEditionID)).thenReturn(studentGrade);
        when(studentGradeMapper.toData(studentGrade)).thenReturn(studentGradeDM);
        when(studentGradeDM.getId()).thenReturn(1L);
        when(iStudentGradeRepositorySpringData.existsById(studentGradeDM.getId())).thenReturn(false);

        //act
        boolean result = studentGradeRepositorySpringData.addGradeToStudent(grade,date,studentID,courseEditionID);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentGradeAlreadyAdded() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeFactory iStudentGradeFactory = mock(IStudentGradeFactory.class);
        StudentGradeMapper studentGradeMapper = mock(StudentGradeMapper.class);

        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,iStudentGradeFactory,studentGradeMapper);

        StudentGrade studentGrade = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        when(iStudentGradeFactory.newGradeStudent(grade,date,studentID,courseEditionID)).thenReturn(studentGrade);
        when(studentGradeMapper.toData(studentGrade)).thenReturn(studentGradeDM);
        when(studentGradeDM.getId()).thenReturn(1L);
        when(iStudentGradeRepositorySpringData.existsById(studentGradeDM.getId())).thenReturn(true);

        //act
        boolean result = studentGradeRepositorySpringData.addGradeToStudent(grade,date,studentID,courseEditionID);

        //assert
        assertFalse(result);
    }

}*/