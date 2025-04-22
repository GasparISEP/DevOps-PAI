
package PAI.persistence.springdata;

import PAI.domain.StudentGrade;
import PAI.mapper.StudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeRepositorySpringDataTest {

    @Test
    void shouldCreatConstructor(){
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        StudentGradeMapper studentGradeMapper = mock(StudentGradeMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,studentGradeMapper);
        //assert
        assertNotNull(studentGradeRepositorySpringData);
    }

    @Test
    void shouldReturnTrueWhenStudentGradeIsSaved() throws Exception{
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        StudentGradeMapper studentGradeMapper = mock(StudentGradeMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,studentGradeMapper);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        StudentGrade studentGrade = mock(StudentGrade.class);

        when(studentGradeMapper.toData(studentGrade)).thenReturn(studentGradeDM);
        when(iStudentGradeRepositorySpringData.save(studentGradeDM)).thenReturn(studentGradeDM);
        //act
        StudentGrade result = studentGradeRepositorySpringData.save(studentGrade);
        //assert
        assertNotNull(result);
    }
}
