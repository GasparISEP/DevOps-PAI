package PAI.persistence.springdata;

import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;
import PAI.mapper.StudentGradeMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentGradeRepositorySpringDataTest {
    @Test
    void shouldCreatConstuctor(){
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeFactory iStudentGradeFactory = mock(IStudentGradeFactory.class);
        StudentGradeMapper studentGradeMapper = mock(StudentGradeMapper.class);

        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,iStudentGradeFactory,studentGradeMapper);

        //assert

        assertNotNull(studentGradeRepositorySpringData);

    }
  
}