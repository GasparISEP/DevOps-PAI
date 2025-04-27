
package PAI.persistence.springdata;

import PAI.VOs.StudentGradeID;
import PAI.domain.StudentGrade;
import PAI.mapper.IStudentGradeIDMapper;
import PAI.mapper.IStudentGradeMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeRepositoryImplSpringDataTest {

    @Test
    void shouldCreatConstructor(){
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData,iStudentGradeMapper,iStudentGradeIDMapper);
        //assert
        assertNotNull(studentGradeRepositorySpringDataImpl);
    }

    @Test
    void shouldReturnStudentGradeWhenSaved() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData,iStudentGradeMapper,iStudentGradeIDMapper);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        StudentGrade studentGrade = mock(StudentGrade.class);

        when(iStudentGradeMapper.toData(studentGrade)).thenReturn(studentGradeDM);
        when(iStudentGradeRepositorySpringData.save(studentGradeDM)).thenReturn(studentGradeDM);
        //act
        StudentGrade result = studentGradeRepositorySpringDataImpl.save(studentGrade);
        //assert
        assertNotNull(result);
    }
    @Test
    void shouldThrowExceptionWhenSaveFailedAndReturnStudentGrade() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData,iStudentGradeMapper,iStudentGradeIDMapper);

        StudentGrade studentGrade = mock(StudentGrade.class);

        when (iStudentGradeMapper.toData(studentGrade)).thenThrow(IllegalArgumentException.class);

        //act
        StudentGrade result = studentGradeRepositorySpringDataImpl.save(studentGrade);
        //assert
        assertNotNull(result);
    }
    @Test
    void shouldReturnAllStudentGradesWhenFound() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData,iStudentGradeMapper,iStudentGradeIDMapper);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        StudentGrade studentGrade = mock(StudentGrade.class);

        when(iStudentGradeRepositorySpringData.findAll()).thenReturn(List.of(studentGradeDM));
        when(iStudentGradeMapper.toDomain(studentGradeDM)).thenReturn(studentGrade);
        //act
        Iterable<StudentGrade> result = studentGradeRepositorySpringDataImpl.findAll();
        //assert
        assertNotNull(result);
    }
    @Test
    void shouldReturnEmptyListWhen()throws Exception{
        // Arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        StudentGradeDM faultyDM = mock(StudentGradeDM.class);

        when(iStudentGradeRepositorySpringData.findAll()).thenReturn(List.of(faultyDM));
        when(iStudentGradeMapper.toDomain(faultyDM)).thenThrow(new RuntimeException("Mapping failed"));

        // Act
        Iterable<StudentGrade> result = studentGradeRepositorySpringDataImpl.findAll();

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
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        StudentGrade studentGrade = mock(StudentGrade.class);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        when(iStudentGradeIDMapper.toDataModel(studentGradeID)).thenReturn(studentGradeIDDataModel);
        when(iStudentGradeRepositorySpringData.findById(studentGradeIDDataModel)).thenReturn(Optional.of(studentGradeDM));
        when(iStudentGradeMapper.toDomain(studentGradeDM)).thenReturn(studentGrade);

        //act
        Optional<StudentGrade> result = studentGradeRepositorySpringDataImpl.ofIdentity(studentGradeID);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnOptionalEmptyWhenIDNotExists() throws Exception {
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        when(iStudentGradeIDMapper.toDataModel(studentGradeID)).thenReturn(studentGradeIDDataModel);
        when(iStudentGradeRepositorySpringData.findById(studentGradeIDDataModel)).thenReturn(Optional.empty());
        //act
        Optional<StudentGrade> result = studentGradeRepositorySpringDataImpl.ofIdentity(studentGradeID);
        //assert
        assertEquals(Optional.empty(),result);
    }
    @Test
    void shouldReturnOptionalEmptyFailsToGetBackToDomain() throws Exception {
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        StudentGrade studentGrade = mock(StudentGrade.class);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        when(iStudentGradeIDMapper.toDataModel(studentGradeID)).thenReturn(studentGradeIDDataModel);
        when(iStudentGradeRepositorySpringData.findById(studentGradeIDDataModel)).thenReturn(Optional.of(studentGradeDM));
        when(iStudentGradeMapper.toDomain(studentGradeDM)).thenThrow(new RuntimeException());
        //act
        Optional<StudentGrade> result = studentGradeRepositorySpringDataImpl.ofIdentity(studentGradeID);
        //assert
        assertEquals(Optional.empty(),result);
    }
    @Test
    void shouldReturnTrueGradeStudentIDWhenIDExists() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        StudentGrade studentGrade = mock(StudentGrade.class);
        StudentGradeDM studentGradeDM = mock(StudentGradeDM.class);
        when(iStudentGradeIDMapper.toDataModel(studentGradeID)).thenReturn(studentGradeIDDataModel);
        when(iStudentGradeRepositorySpringData.findById(studentGradeIDDataModel)).thenReturn(Optional.of(studentGradeDM));
        when(iStudentGradeMapper.toDomain(studentGradeDM)).thenReturn(studentGrade);

        //act
        boolean result = studentGradeRepositorySpringDataImpl.containsOfIdentity(studentGradeID);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentGradeIDDoesNotExists() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        when(iStudentGradeIDMapper.toDataModel(studentGradeID)).thenReturn(studentGradeIDDataModel);
        when(iStudentGradeRepositorySpringData.findById(studentGradeIDDataModel)).thenReturn(Optional.empty());
        //act
        boolean result = studentGradeRepositorySpringDataImpl.containsOfIdentity(studentGradeID);

        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenFailsToGetBackToDomain() throws Exception{
        //arrange
        IStudentGradeRepositorySpringData iStudentGradeRepositorySpringData = mock(IStudentGradeRepositorySpringData.class);
        IStudentGradeMapper iStudentGradeMapper = mock(IStudentGradeMapper.class);
        IStudentGradeIDMapper iStudentGradeIDMapper = mock(IStudentGradeIDMapper.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeRepositorySpringDataImpl studentGradeRepositorySpringDataImpl = new StudentGradeRepositorySpringDataImpl(iStudentGradeRepositorySpringData, iStudentGradeMapper, iStudentGradeIDMapper);
        when(iStudentGradeIDMapper.toDataModel(studentGradeID)).thenThrow(new RuntimeException());
        //act
        boolean result = studentGradeRepositorySpringDataImpl.containsOfIdentity(studentGradeID);

        //assert
        assertFalse(result);
    }


}