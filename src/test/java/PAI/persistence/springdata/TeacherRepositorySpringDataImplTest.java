package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.mapper.*;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import PAI.repository.ITeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherRepositorySpringDataImplTest {

    private ITeacherRepositorySpringData iTeacherRepoSpringData;
    private ITeacherMapper teacherMapper;
    private ITeacherIDMapper teacherIDMapper;
    private INIFMapper nifMapper;
    private ITeacherRepository teacherRepository;

    @BeforeEach
    void setUp() {
        // Arrange
        iTeacherRepoSpringData = mock(ITeacherRepositorySpringData.class);
        teacherMapper = mock(TeacherMapperImpl.class);
        teacherIDMapper = mock(TeacherIDMapperImpl.class);
        nifMapper = mock(NIFMapperImpl.class);

        teacherRepository = new TeacherRepositorySpringDataImpl(
                iTeacherRepoSpringData,
                teacherMapper,
                teacherIDMapper,
                nifMapper
        );
    }

    @Test
    void shouldCreateRepositoryWithValidDependencies() {
        //Arrange

        //Act + Assert
        assertNotNull(teacherRepository);
    }

    @Test
    void shouldThrowWhenTeacherRepositorySpringDataIsNull() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new TeacherRepositorySpringDataImpl(null, teacherMapper, teacherIDMapper, nifMapper));
    }

    @Test
    void shouldThrowWhenTeacherMapperIsNull() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new TeacherRepositorySpringDataImpl(iTeacherRepoSpringData, null, teacherIDMapper, nifMapper));
    }

    @Test
    void shouldThrowWhenTeacherIDMapperIsNull() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new TeacherRepositorySpringDataImpl(iTeacherRepoSpringData, teacherMapper, null, nifMapper));
    }

    @Test
    void shouldThrowWhenNIFMapperIsNull() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new TeacherRepositorySpringDataImpl(iTeacherRepoSpringData, teacherMapper, teacherIDMapper, null));
    }

    @Test
    void shouldSaveTeacher () throws Exception {
        // Arrange
        Teacher teacher = mock(Teacher.class);
        TeacherDataModel teacherDataModel = mock(TeacherDataModel.class);

        when(teacherMapper.toDataModel(teacher)).thenReturn(teacherDataModel);
        when(iTeacherRepoSpringData.save(teacherDataModel)).thenReturn(teacherDataModel);
        when(teacherMapper.toDomain(teacherDataModel)).thenReturn(teacher);

        // Act
        Teacher result = teacherRepository.save(teacher);

        // Assert
        assertEquals(teacher, result);
    }

    @Test
    void shouldNotSaveNullTeacher() throws Exception {
        //Act
        Teacher result = teacherRepository.save(null);

        //Assert
        assertNull(result);
    }

    @Test
    void shouldFindAllTeachers() {
        //Arrange
        List<TeacherDataModel> teacherDataModels = List.of(
                mock(TeacherDataModel.class),
                mock(TeacherDataModel.class));

        Teacher teacherDouble1 = mock(Teacher.class);
        Teacher teacherDouble2 = mock(Teacher.class);

        TeacherDataModel teacherDMdouble = mock(TeacherDataModel.class);
        //List<Teacher> teachers = new ArrayList<>();

        when(iTeacherRepoSpringData.findAll()).thenReturn(teacherDataModels);
        when(teacherMapper.toDomain(teacherDataModels.get(0))).thenReturn(teacherDouble1);
        when(teacherMapper.toDomain(teacherDataModels.get(1))).thenReturn(teacherDouble2);

        //Act
        Iterable<Teacher> result = teacherRepository.findAll();
        List<Teacher> teacherList = (List<Teacher>) result;

        //Assert
        assertEquals(2, teacherList.size());
    }

    @Test
    void shouldHandleEmptyTeacherList() {
        // Arrange
        List<TeacherDataModel> teacherDataModels = new ArrayList<>();

        when(iTeacherRepoSpringData.findAll()).thenReturn(teacherDataModels);

        // Act
        Iterable<Teacher> result = teacherRepository.findAll();
        List<Teacher> teacherList = (List<Teacher>) result;

        // Assert
        assertFalse(teacherList.iterator().hasNext());
    }

    @Test
    void shouldCheckContainsOfIdentity() {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        TeacherID teacherIDdouble = mock(TeacherID.class);

        when(teacherIDMapper.toDataModel(teacherIDdouble)).thenReturn(teacherIDDataModelDouble);
        when(iTeacherRepoSpringData.existsById(teacherIDDataModelDouble)).thenReturn(true);

        // Act
        boolean result = teacherRepository.containsOfIdentity(teacherIDdouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldCheckNotContainsOfIdentity() {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        TeacherID teacherIDdouble = mock(TeacherID.class);

        when(teacherIDMapper.toDataModel(teacherIDdouble)).thenReturn(teacherIDDataModelDouble);
        when(iTeacherRepoSpringData.existsById(teacherIDDataModelDouble)).thenReturn(false);

        // Act
        boolean result = teacherRepository.containsOfIdentity(teacherIDdouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherIDIsNull() {
        // Arrange

        // Act
        boolean result = teacherRepository.containsOfIdentity(null);

        // Assert
        assertFalse(result);
    }


    @Test
    void shouldReturnOfIdentity() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel idDataModel = mock(TeacherIDDataModel.class);
        TeacherDataModel teacherDataModel = mock(TeacherDataModel.class);
        Teacher teacher = mock(Teacher.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(idDataModel);
        when(iTeacherRepoSpringData.findById(idDataModel)).thenReturn(Optional.of(teacherDataModel));
        when(teacherMapper.toDomain(teacherDataModel)).thenReturn(teacher);

        // Act
        Optional<Teacher> result = teacherRepository.ofIdentity(teacherID);

        // Assert
        assertEquals(teacher, result.get());
    }

    @Test
    void ofIdentityShouldReturnEmptyWhenNotFound() {
        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel idDataModel = mock(TeacherIDDataModel.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(idDataModel);
        when(iTeacherRepoSpringData.findById(idDataModel)).thenReturn(Optional.empty());

        Optional<Teacher> result = teacherRepository.ofIdentity(teacherID);

        assertTrue(result.isEmpty());
    }

    @Test
    void ofIdentityShouldThrowWhenDomainConversionFailure() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel idDataModel = mock(TeacherIDDataModel.class);
        TeacherDataModel teacherDataModel = mock(TeacherDataModel.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(idDataModel);
        when(iTeacherRepoSpringData.findById(idDataModel)).thenReturn(Optional.of(teacherDataModel));
        when(teacherMapper.toDomain(teacherDataModel)).thenThrow(new RuntimeException("Could not convert Teacher Data Model to Teacher Domain Object."));

        // Act + Assert
        assertThrows(RuntimeException.class, () -> teacherRepository.ofIdentity(teacherID));
    }

    @Test
    void ofIdentityShouldReturnEmptyWhenTeacherIDIsNull() {
        Optional<Teacher> result = teacherRepository.ofIdentity(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnFalseIfDoesNotExistByIDorNIF () {
        // Arrange

        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        NIF nif = mock(NIF.class);
        NIFDataModel nifDataModel = mock(NIFDataModel.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(teacherIDDataModel);
        when(nifMapper.domainToDataModel(nif)).thenReturn(nifDataModel);
        when(iTeacherRepoSpringData.existsByIDorNIF(teacherIDDataModel, nifDataModel)).thenReturn(false);

        // Act
        boolean result = teacherRepository.existsByIDorNIF(teacherID, nif);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfExistsByIDorNIF () {
        // Arrange

        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        NIF nif = mock(NIF.class);
        NIFDataModel nifDataModel = mock(NIFDataModel.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(teacherIDDataModel);
        when(nifMapper.domainToDataModel(nif)).thenReturn(nifDataModel);
        when(iTeacherRepoSpringData.existsByIDorNIF(teacherIDDataModel, nifDataModel)).thenReturn(true);

        // Act
        boolean result = teacherRepository.existsByIDorNIF(teacherID, nif);

        // Assert
        assertTrue(result);
    }

    @Test
    void existsByIDorNIFShouldReturnFalseWhenTeacherIDIsNull() {
        // Arrange
        NIF nif = mock(NIF.class);

        // Act
        boolean result = teacherRepository.existsByIDorNIF(null, nif);

        // Assert
        assertFalse(result);
    }

    @Test
    void existsByIDorNIFShouldReturnFalseWhenNIFIsNull() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);

        // Act
        boolean result = teacherRepository.existsByIDorNIF(teacherID, null);

        // Assert
        assertFalse(result);
    }

    @Test
    void existsByIDorNIFShouldFailToConvertTeacherIDWithMapper () {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        NIF nif = mock(NIF.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(null);
        when(nifMapper.domainToDataModel(nif)).thenReturn(mock(NIFDataModel.class));

        // Assert
        assertFalse(teacherRepository.existsByIDorNIF(teacherID, nif));
    }

    @Test
    void existsByIDorNIFShouldFailToConvertNIFWithMapper () {
        TeacherID teacherID = mock(TeacherID.class);
        NIF nif = mock(NIF.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(mock(TeacherIDDataModel.class));
        when(nifMapper.domainToDataModel(nif)).thenReturn(null);

        // Assert
        assertFalse(teacherRepository.existsByIDorNIF(teacherID, nif));
    }
}