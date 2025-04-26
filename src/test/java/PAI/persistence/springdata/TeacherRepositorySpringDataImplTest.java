package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.mapper.*;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TeacherRepositorySpringDataImplTest {

    private ITeacherRepositorySpringData iTeacherRepoSpringData;
    private ITeacherMapper teacherMapper;
    private ITeacherIDMapper teacherIDMapper;
    private INIFMapper nifMapper;
    private TeacherRepositorySpringDataImpl teacherRepository;

    @BeforeEach
    void setUp() {
        iTeacherRepoSpringData = mock(ITeacherRepositorySpringData.class);
        teacherMapper = mock(TeacherMapperImpl.class);
        teacherIDMapper = mock(TeacherIDMapperImpl.class);
        nifMapper = mock(NIFMapper.class);

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
    void shouldGetTeacherByID() throws Exception {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel idDataModel = mock(TeacherIDDataModel.class);
        TeacherDataModel teacherDataModel = mock(TeacherDataModel.class);
        Teacher teacher = mock(Teacher.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(idDataModel);
        when(iTeacherRepoSpringData.findById(idDataModel.toString())).thenReturn(Optional.of(teacherDataModel));
        when(teacherMapper.toDomain(teacherDataModel)).thenReturn(teacher);

        // Act
        Optional<Teacher> result = teacherRepository.getTeacherByID(teacherID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(teacher, result.get());
    }

    @Test
    void shouldReturnEmptyWhenTeacherNotFoundByID() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel idDataModel = mock(TeacherIDDataModel.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(idDataModel);
        when(iTeacherRepoSpringData.findById(idDataModel.toString())).thenReturn(Optional.empty());

        // Act
        Optional<Teacher> result = teacherRepository.getTeacherByID(teacherID);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldHandleMappingExceptionWhenGettingTeacherByID() throws Exception {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel idDataModel = mock(TeacherIDDataModel.class);
        TeacherDataModel teacherDataModel = mock(TeacherDataModel.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(idDataModel);
        when(iTeacherRepoSpringData.findById(idDataModel.toString())).thenReturn(Optional.of(teacherDataModel));
        when(teacherMapper.toDomain(teacherDataModel)).thenThrow(new Exception("Mapping failed"));

        // Act + Assert
        assertThrows(RuntimeException.class, () -> teacherRepository.getTeacherByID(teacherID));
    }


    @Test
    void shouldSaveTeacher() {
        // Arrange
        Teacher teacher = mock(Teacher.class);
        TeacherDataModel teacherDataModel = mock(TeacherDataModel.class);

        when(teacherMapper.toDataModel(teacher)).thenReturn(teacherDataModel);
        when(iTeacherRepoSpringData.save(teacherDataModel)).thenReturn(teacherDataModel);

        // Act
        Teacher result = teacherRepository.save(teacher);

        // Assert
        assertEquals(teacher, result);
        verify(iTeacherRepoSpringData).save(teacherDataModel);
    }

    @Test
    void shouldNotSaveNullTeacher() {
        //Arrange

        //Act
        Teacher result = teacherRepository.save(null);

        //Assert
        assertNull(result);
    }

    @Test
    void shouldFindAllTeachers() throws Exception {
        //Arrange
        List<TeacherDataModel> dataModels = new ArrayList<>();
        dataModels.add(mock(TeacherDataModel.class));
        dataModels.add(mock(TeacherDataModel.class));

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(iTeacherRepoSpringData.findAll()).thenReturn(dataModels);
        when(teacherMapper.toDomain(dataModels.get(0))).thenReturn(teacher1);
        when(teacherMapper.toDomain(dataModels.get(1))).thenReturn(teacher2);

        //Act
        Iterable<Teacher> result = teacherRepository.findAll();

        //Assert
        List<Teacher> resultList = new ArrayList<>();
        result.forEach(resultList::add);

        assertEquals(2, resultList.size());
        assertTrue(resultList.contains(teacher1));
        assertTrue(resultList.contains(teacher2));
    }

    @Test
    void shouldHandleEmptyTeacherList() {
        // Arrange
        List<TeacherDataModel> dataModels = new ArrayList<>();

        when(iTeacherRepoSpringData.findAll()).thenReturn(dataModels);

        // Act
        Iterable<Teacher> result = teacherRepository.findAll();

        // Assert
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldHandleMappingExceptionInFindAll() throws Exception {
        // Arrange
        List<TeacherDataModel> dataModels = new ArrayList<>();
        dataModels.add(mock(TeacherDataModel.class));

        when(iTeacherRepoSpringData.findAll()).thenReturn(dataModels);
        when(teacherMapper.toDomain(any())).thenThrow(new Exception("Mapping failed"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> teacherRepository.findAll());
    }

    @Test
    void shouldCheckContainsOfIdentity() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym acronym = mock(TeacherAcronym.class);

        when(teacherID.getTeacherAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("ABC");
        when(iTeacherRepoSpringData.existsById("ABC")).thenReturn(true);

        // Act
        boolean result = teacherRepository.containsOfIdentity(teacherID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldCheckNotContainsOfIdentity() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym acronym = mock(TeacherAcronym.class);

        when(teacherID.getTeacherAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("CBA");
        when(iTeacherRepoSpringData.existsById("CBA")).thenReturn(false);

        // Act
        boolean result = teacherRepository.containsOfIdentity(teacherID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnOfIdentity() throws Exception {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherIDDataModel idDataModel = mock(TeacherIDDataModel.class);
        TeacherDataModel teacherDataModel = mock(TeacherDataModel.class);
        Teacher teacher = mock(Teacher.class);

        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(idDataModel);
        when(iTeacherRepoSpringData.findById(idDataModel.toString())).thenReturn(Optional.of(teacherDataModel));
        when(teacherMapper.toDomain(teacherDataModel)).thenReturn(teacher);

        // Act
        Optional<Teacher> result = teacherRepository.ofIdentity(teacherID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(teacher, result.get());
    }

    // [Temporary] method added only because in order to implement ITeacherRepository this class needs this method
    @Test
    void shouldReturnEmptyWhenRegisteringTeacher() throws Exception {
        // Arrange
        TeacherAcronym acronym = new TeacherAcronym("MSA");
        Name name = new Name("John Doe");
        Email email = new Email("john.doe@example.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351","912345678");
        AcademicBackground academicBackground = new AcademicBackground("PhD in AI");
        Street street = new Street("Main Street");
        PostalCode postalCode = new PostalCode("1000-000");
        Location location = new Location("Lisbon");
        DepartmentAcronym dptAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dptAcronym);

        // Act
        Optional<TeacherID> result = teacherRepository.registerTeacher(
                acronym, name, email, nif, phoneNumber,
                academicBackground, street, postalCode,
                location, country, departmentID
        );

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnFalseIfDoesNotExistsByIDorNIF () {
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

//    @Test
//    void existsByIDorNIFShouldFailToConvertIDWithMapper () {
//        // Arrange
//
//        // Act
//
//        // Assert
//
//    }

//    @Test
//    void existsByIDorNIFShouldFailToConvertNIFWithMapper () {
//        // Arrange
//
//        // Act
//
//        // Assert
//
//    }
}