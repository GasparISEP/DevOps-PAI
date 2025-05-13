package PAI.persistence.springdata.schoolYear;

import PAI.VOs.SchoolYearID;
import PAI.domain.schoolYear.SchoolYear;
import PAI.mapper.schoolYear.ISchoolYearMapper;
import PAI.mapper.schoolYear.ISchoolYearIDMapper;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import PAI.repository.schoolYear.ISchoolYearRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearRepositorySpringDataImplTest {

    // Tests with isolation

    @Test
    void shouldCreateConstructor() {
        //arrange

        ISchoolYearRepositorySpringData schoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        //act
        SchoolYearRepositorySpringDataImpl schoolYearRepository = new SchoolYearRepositorySpringDataImpl(schoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        //assert
        assertNotNull(schoolYearRepository);
    }

    @Test
    void shouldThrowExceptionSpringDataRepositoryIsNull() {
        //arrange

        ISchoolYearRepositorySpringData schoolYearRepositorySpringData = null;
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        // act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            SchoolYearRepositorySpringDataImpl schoolYearRepository = new SchoolYearRepositorySpringDataImpl(schoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        });
        assertEquals(exception.getMessage(), "Spring Data Repository or SchoolYear/SchoolYearID mappers cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearMapperIsNull() {

        //arrange
        ISchoolYearRepositorySpringData schoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = null;
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        // act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            SchoolYearRepositorySpringDataImpl schoolYearRepository = new SchoolYearRepositorySpringDataImpl(schoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        });
        assertEquals(exception.getMessage(), "Spring Data Repository or SchoolYear/SchoolYearID mappers cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDMapperIsNull() {

        //arrange
        ISchoolYearRepositorySpringData schoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = null;

        // act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            SchoolYearRepositorySpringDataImpl schoolYearRepository = new SchoolYearRepositorySpringDataImpl(schoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        });
        assertEquals(exception.getMessage(), "Spring Data Repository or SchoolYear/SchoolYearID mappers cannot be null");
    }

    @Test
    void shouldReturnSchoolYearWhenSaved() {

        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);

        when(schoolYearMapper.toDataModel(schoolYear)).thenReturn(schoolYearDataModel);
        when(ischoolYearRepositorySpringData.save(schoolYearDataModel)).thenReturn(schoolYearDataModel);
        //act
        SchoolYear result = schoolYearRepositorySpringDataImpl.save(schoolYear);
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionIfSchoolYearIsNull() {

        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYear schoolYear = null;
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);

        when(schoolYearMapper.toDataModel(schoolYear)).thenReturn(schoolYearDataModel);
        when(ischoolYearRepositorySpringData.save(schoolYearDataModel)).thenReturn(schoolYearDataModel);

        // act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            SchoolYear result = schoolYearRepositorySpringDataImpl.save(schoolYear);

        });
        assertEquals(exception.getMessage(), "SchoolYear cannot be null");

    }

    @Test
    void shouldReturnListOfOneSchoolYearIfSchoolYearExists() {

        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(ischoolYearRepositorySpringData.findAll()).thenReturn(List.of(schoolYearDataModel));

        // act
        Iterable<SchoolYear> schoolYears = schoolYearRepositorySpringDataImpl.findAll();

        //assert
        Iterator<SchoolYear> it = schoolYears.iterator();
        int count = 0;
        while (it.hasNext()) {
            count++;
            it.next();
        }
        assertEquals(1, count);
    }

    @Test
    void shouldReturnListOfThreeSchoolYearsIfThreeSchoolYearsExist() {

        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);
        SchoolYearDataModel schoolYearDataModel2 = mock(SchoolYearDataModel.class);
        SchoolYearDataModel schoolYearDataModel3 = mock(SchoolYearDataModel.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(ischoolYearRepositorySpringData.findAll()).thenReturn(List.of(schoolYearDataModel, schoolYearDataModel2, schoolYearDataModel3));

        // act
        Iterable<SchoolYear> schoolYears = schoolYearRepositorySpringDataImpl.findAll();

        //assert
        Iterator<SchoolYear> it = schoolYears.iterator();
        int count = 0;
        while (it.hasNext()) {
            count++;
            it.next();
        }
        assertEquals(3, count);
    }

    @Test
    void shouldReturnEmptyListIfNoSchoolYearsExist() {

        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(ischoolYearRepositorySpringData.findAll()).thenReturn(List.of());

        // act
        Iterable<SchoolYear> schoolYears = schoolYearRepositorySpringDataImpl.findAll();

        //assert
        Iterator<SchoolYear> it = schoolYears.iterator();
        int count = 0;
        while (it.hasNext()) {
            count++;
            it.next();
        }
        assertEquals(0, count);
    }


    @Test
    void shouldReturnOptionalSchoolYearIdForCurrentSchoolYear() {
        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearDataModel schoolYearDataModel=mock(SchoolYearDataModel.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        when(ischoolYearRepositorySpringData.findCurrentSchoolYear()).thenReturn(Optional.of(schoolYearDataModel));
        when(schoolYearMapper.toDomain(schoolYearDataModel)).thenReturn(schoolYear);
        //act
        Optional<SchoolYear> result = schoolYearRepositorySpringDataImpl.getCurrentSchoolYear();
        //assert
        assertEquals(Optional.of(schoolYear), result);
    }

    @Test
    void shouldReturnEmptyOptionalIfRepositoryIsEmptyOrThereISNotCurrentSchoolYear() {
        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(ischoolYearRepositorySpringData.findCurrentSchoolYear()).thenReturn(Optional.empty());
        //act
        Optional<SchoolYear> result = schoolYearRepositorySpringDataImpl.getCurrentSchoolYear();
        assertEquals(Optional.empty(), result);
    }

    @Test
    void shouldReturnOptionalOfSchoolYear() {

        //Arrange
        Optional<SchoolYearDataModel> schoolYearDMOpt = Optional.of(mock(SchoolYearDataModel.class));
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYear schoolYearClass=mock(SchoolYear.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearIDDataModel schoolYearIDDataModel=mock(SchoolYearIDDataModel.class);

        when(schoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(schoolYearMapper.toDomain(schoolYearDMOpt.get())).thenReturn(schoolYearClass);
        when(iSchoolYearRepositorySpringData.findById(schoolYearIDDataModel)).thenReturn(schoolYearDMOpt);
        //Act
        Optional<SchoolYear> schoolYear = schoolYearRepositorySpringData.ofIdentity(schoolYearID);

        //Assert
        assertFalse(schoolYear.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalOfSchoolYearIfNoSchoolYearIsFound() {

        //Arrange
        Optional<SchoolYearDataModel> schoolYearDMOpt = Optional.of(mock(SchoolYearDataModel.class));
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYear schoolYearClass=mock(SchoolYear.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearIDDataModel schoolYearIDDataModel=mock(SchoolYearIDDataModel.class);

        when(schoolYearMapper.toDomain(schoolYearDMOpt.get())).thenReturn(schoolYearClass);
        when(iSchoolYearRepositorySpringData.findById(schoolYearIDDataModel)).thenReturn(Optional.empty());
        //Act
        Optional<SchoolYear> schoolYear = schoolYearRepositorySpringData.ofIdentity(schoolYearID);

        //Assert
        assertTrue(schoolYear.isEmpty());
    }

    @Test
    void shouldReturnTrueIfSchoolYearIDIsFound() {

        //Arrange
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearIDDataModel schoolYearIDDataModel=mock(SchoolYearIDDataModel.class);

        when(schoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(iSchoolYearRepositorySpringData.existsById(schoolYearIDDataModel)).thenReturn(true);

        //Act
        boolean result = schoolYearRepositorySpringData.containsOfIdentity(schoolYearID);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearIDIsNotFound() {

        //Arrange
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearIDDataModel schoolYearIDDataModel=mock(SchoolYearIDDataModel.class);

        when(iSchoolYearRepositorySpringData.existsById(schoolYearIDDataModel)).thenReturn(false);

        //Act
        boolean result = schoolYearRepositorySpringData.containsOfIdentity(schoolYearID);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearIDIsNull() {

        //Arrange
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearID schoolYearID = null;
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        //Act
        boolean result = schoolYearRepositorySpringData.containsOfIdentity(schoolYearID);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAllSchoolYearIDs() {
        // arrange
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearRepository schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);


        SchoolYearID id1 = mock(SchoolYearID.class);
        SchoolYearID id2 = mock(SchoolYearID.class);
        SchoolYearID id3 = mock(SchoolYearID.class);

        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);
        SchoolYear schoolYear3 = mock(SchoolYear.class);

        when(schoolYear1.identity()).thenReturn(id1);
        when(schoolYear2.identity()).thenReturn(id2);
        when(schoolYear3.identity()).thenReturn(id3);
        SchoolYearDataModel schoolYearDataModel1=mock(SchoolYearDataModel.class);
        SchoolYearDataModel schoolYearDataModel2=mock(SchoolYearDataModel.class);
        SchoolYearDataModel schoolYearDataModel3=mock(SchoolYearDataModel.class);

        when(iSchoolYearRepositorySpringData.findAll()).thenReturn(List.of(schoolYearDataModel1, schoolYearDataModel2, schoolYearDataModel3));
        when(schoolYearMapper.toDomain(schoolYearDataModel1)).thenReturn(schoolYear1);
        when(schoolYearMapper.toDomain(schoolYearDataModel2)).thenReturn(schoolYear2);
        when(schoolYearMapper.toDomain(schoolYearDataModel3)).thenReturn(schoolYear3);

        // act
        List<SchoolYearID> result = schoolYearRepositorySpringDataImpl.getAllSchoolYearsIDs();

        // assert
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList(id1, id2, id3)));
    }

    @Test
    void shouldReturnEmptyListWhenNoSchoolYearsExist() {
        // arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearRepository schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(schoolYearRepository.findAll()).thenReturn(List.of());

        // act
        List<SchoolYearID> result = schoolYearRepositorySpringDataImpl.getAllSchoolYearsIDs();

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueIfSchoolYearIsFoundInRepository() {

        // Arrange
        SchoolYear inputSchoolYear = mock(SchoolYear.class);
        SchoolYear foundSchoolYear = mock(SchoolYear.class);

        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);

        when(inputSchoolYear.identity()).thenReturn(schoolYearID);
        when(schoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(iSchoolYearRepositorySpringData.findById(schoolYearIDDataModel)).thenReturn(Optional.of(schoolYearDataModel));
        when(schoolYearMapper.toDomain(schoolYearDataModel)).thenReturn(foundSchoolYear);
        when(foundSchoolYear.isSameSchoolYear(inputSchoolYear)).thenReturn(true);

        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData =
                new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        // Act
        boolean result = schoolYearRepositorySpringData.schoolYearExists(inputSchoolYear);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearsAreDifferent() {

        // Arrange
        SchoolYear inputSchoolYear = mock(SchoolYear.class);
        SchoolYear foundSchoolYear = mock(SchoolYear.class);

        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);

        when(inputSchoolYear.identity()).thenReturn(schoolYearID);
        when(schoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(iSchoolYearRepositorySpringData.findById(schoolYearIDDataModel)).thenReturn(Optional.of(schoolYearDataModel));
        when(schoolYearMapper.toDomain(schoolYearDataModel)).thenReturn(foundSchoolYear);
        when(foundSchoolYear.isSameSchoolYear(inputSchoolYear)).thenReturn(false);

        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData =
                new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        // Act
        boolean result = schoolYearRepositorySpringData.schoolYearExists(inputSchoolYear);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfFindByIDReturnsEmptyDataModel() {

        //Arrange
        SchoolYear schoolYear=mock(SchoolYear.class);
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        Optional <SchoolYearDataModel> schoolYearDataModelOpt=Optional.of(mock(SchoolYearDataModel.class));
        when(schoolYear.identity()).thenReturn(schoolYearID);

        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearIDDataModel schoolYearIDDataModel=mock(SchoolYearIDDataModel.class);
        when(schoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(schoolYear.isSameSchoolYear(schoolYear)).thenReturn(true);
        when(iSchoolYearRepositorySpringData.findById(schoolYearIDDataModel)).thenReturn(Optional.empty());
        when(schoolYearMapper.toDomain(schoolYearDataModelOpt.get())).thenReturn(schoolYear);

        //Act
        boolean result = schoolYearRepositorySpringData.schoolYearExists(schoolYear);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearIsNull() {

        //Arrange
        SchoolYear schoolYear=null;
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        //Act
        boolean result = schoolYearRepositorySpringData.schoolYearExists(schoolYear);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearIsNotFound() {

        //Arrange
        SchoolYear schoolYear=mock(SchoolYear.class);
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        Optional <SchoolYearDataModel> schoolYearDataModelOpt=Optional.of(mock(SchoolYearDataModel.class));
        when(schoolYear.identity()).thenReturn(schoolYearID);

        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearIDDataModel schoolYearIDDataModel=mock(SchoolYearIDDataModel.class);
        when(schoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(schoolYear.isSameSchoolYear(schoolYear)).thenReturn(true);
        when(iSchoolYearRepositorySpringData.findById(schoolYearIDDataModel)).thenReturn(Optional.empty());
        when(schoolYearMapper.toDomain(schoolYearDataModelOpt.get())).thenReturn(schoolYear);

        //Act
        boolean result = schoolYearRepositorySpringData.schoolYearExists(schoolYear);

        //Assert
        assertFalse(result);
    }

}