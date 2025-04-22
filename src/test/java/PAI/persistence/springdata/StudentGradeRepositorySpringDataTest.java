
package PAI.persistence.springdata;

import PAI.domain.StudentGrade;
import PAI.mapper.IStudentGradeIDMapper;
import PAI.mapper.IStudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeRepositorySpringDataTest {

    @Test
    void shouldCreatConstructor(){
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,iStudentGradeMapper,iStudentGradeIDMapper);
        //assert
        assertNotNull(studentGradeRepositorySpringData);
    }

    @Test
    void shouldReturnStudentGradeWhenSaved() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,iStudentGradeMapper,iStudentGradeIDMapper);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        StudentGrade studentGrade = mock(StudentGrade.class);

        when(iStudentGradeMapper.toData(studentGrade)).thenReturn(studentGradeDM);
        when(iStudentGradeRepositorySpringData.save(studentGradeDM)).thenReturn(studentGradeDM);
        //act
        StudentGrade result = studentGradeRepositorySpringData.save(studentGrade);
        //assert
        assertNotNull(result);
    }
    @Test
    void shouldThrowExceptionWhenSaveFailedAndReturnStudentGrade() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,iStudentGradeMapper,iStudentGradeIDMapper);

        StudentGrade studentGrade = mock(StudentGrade.class);

        when (iStudentGradeMapper.toData(studentGrade)).thenThrow(IllegalArgumentException.class);

        //act
        StudentGrade result = studentGradeRepositorySpringData.save(studentGrade);
        //assert
        assertNotNull(result);
    }
    @Test
    void shouldReturnAllStudentGradesWhenFound() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData,iStudentGradeMapper,iStudentGradeIDMapper);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        StudentGrade studentGrade = mock(StudentGrade.class);

        when(iStudentGradeRepositorySpringData.findAll()).thenReturn(List.of(studentGradeDM));
        when(iStudentGradeMapper.toDomain(studentGradeDM)).thenReturn(studentGrade);
        //act
        Iterable<StudentGrade> result = studentGradeRepositorySpringData.findAll();
        //assert
        assertNotNull(result);
    }
    @Test
    void shouldReturnEmptyListWhen()throws Exception{
        // Arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        StudentGradeDM faultyDM = mock(StudentGradeDM.class);

        when(iStudentGradeRepositorySpringData.findAll()).thenReturn(List.of(faultyDM));
        when(iStudentGradeMapper.toDomain(faultyDM)).thenThrow(new RuntimeException("Mapping failed"));

        // Act
        Iterable<StudentGrade> result = studentGradeRepositorySpringData.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }
    @Test
    void shouldReturnGradeStudentWhenIDExists() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        Long id = 1L;
        StudentGrade studentGrade = mock(StudentGrade.class);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        when(iStudentGradeRepositorySpringData.findById(id)).thenReturn(Optional.of(studentGradeDM));
        when(iStudentGradeMapper.toDomain(studentGradeDM)).thenReturn(studentGrade);

        //act
        Optional<StudentGrade> result = studentGradeRepositorySpringData.ofIdentity(id);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnOptionalEmptyWhenIDNotExists() throws Exception {
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        Long id = 1L;
        when(iStudentGradeRepositorySpringData.findById(id)).thenReturn(Optional.empty());

        //act
        Optional<StudentGrade> result = studentGradeRepositorySpringData.ofIdentity(id);
        //assert
        assertEquals(Optional.empty(),result);
    }

    @Test
    void shouldReturnOptionalEmptyWhenStudentWhenMappingFails() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        Long id = 1L;
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        when(iStudentGradeRepositorySpringData.findById(id)).thenReturn(Optional.of(studentGradeDM));
        when(iStudentGradeMapper.toDomain(studentGradeDM)).thenReturn(null);
        //act
        Optional<StudentGrade> result = studentGradeRepositorySpringData.ofIdentity(id);
        //assert
        assertEquals(Optional.empty(),result);
    }

    @Test
    void shouldReturnTrueWhenIDExists(){
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        Long id = 1L;
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        when(iStudentGradeRepositorySpringData.findById(id)).thenReturn(Optional.of(studentGradeDM));
        //act
        boolean result = studentGradeRepositorySpringData.containsOfIdentity(id);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenIDDoesNotExists(){
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringData studentGradeRepositorySpringData = new StudentGradeRepositorySpringData(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        Long id = 1L;
        when(iStudentGradeRepositorySpringData.findById(id)).thenReturn(Optional.empty());
        //act
        boolean result = studentGradeRepositorySpringData.containsOfIdentity(id);
        //assert
        assertFalse(result);
    }

}
