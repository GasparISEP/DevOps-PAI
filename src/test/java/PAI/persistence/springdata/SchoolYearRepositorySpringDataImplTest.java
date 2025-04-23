package PAI.persistence.springdata;

import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.mapper.SchoolYear.ISchoolYearMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;

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
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(ischoolYearRepositorySpringData.findAll()).thenReturn(List.of(schoolYearDataModel));

        // act
        Iterable<SchoolYear> schoolYears = schoolYearRepositorySpringDataImpl.findAll(schoolYearFactory);

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
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(ischoolYearRepositorySpringData.findAll()).thenReturn(List.of(schoolYearDataModel, schoolYearDataModel2, schoolYearDataModel3));

        // act
        Iterable<SchoolYear> schoolYears = schoolYearRepositorySpringDataImpl.findAll(schoolYearFactory);

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
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(ischoolYearRepositorySpringData.findAll()).thenReturn(List.of());

        // act
        Iterable<SchoolYear> schoolYears = schoolYearRepositorySpringDataImpl.findAll(schoolYearFactory);

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
    void shouldReturnEmptyListIfFactoryIsNull() {

        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ISchoolYearFactory schoolYearFactory = null;
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringDataImpl = new SchoolYearRepositorySpringDataImpl(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        when(schoolYearMapper.toDomain(schoolYearDataModel, schoolYearFactory)).thenThrow(new IllegalArgumentException("School Year DataModel and/or Factory cannot be null"));
        when(ischoolYearRepositorySpringData.findAll()).thenReturn(List.of(schoolYearDataModel));

        // act
        Iterable<SchoolYear> schoolYears = schoolYearRepositorySpringDataImpl.findAll(schoolYearFactory);

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

        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(ischoolYearRepositorySpringData.findCurrentSchoolYear()).thenReturn(Optional.of(schoolYearIDDataModel));
        when(schoolYearIDMapper.toDomain(schoolYearIDDataModel)).thenReturn(schoolYearID);
        //act
        Optional<SchoolYearID> result = schoolYearRepositorySpringDataImpl.getCurrentSchoolYear();
        //assert
        assertEquals(Optional.of(schoolYearID), result);
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
        Optional<SchoolYearID> result = schoolYearRepositorySpringDataImpl.getCurrentSchoolYear();
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
        SchoolYearFactoryImpl schoolYearFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(schoolYearMapper.toDomain(schoolYearDMOpt.get(), schoolYearFactory)).thenReturn(schoolYearClass);
        when(iSchoolYearRepositorySpringData.findById(schoolYearID.toString())).thenReturn(schoolYearDMOpt);
        //Act
        Optional<SchoolYear> schoolYear = schoolYearRepositorySpringData.ofIdentity(schoolYearID, schoolYearFactory);

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
        SchoolYearFactoryImpl schoolYearFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(schoolYearMapper.toDomain(schoolYearDMOpt.get(), schoolYearFactory)).thenReturn(schoolYearClass);
        when(iSchoolYearRepositorySpringData.findById(schoolYearID.toString())).thenReturn(Optional.empty());
        //Act
        Optional<SchoolYear> schoolYear = schoolYearRepositorySpringData.ofIdentity(schoolYearID, schoolYearFactory);

        //Assert
        assertTrue(schoolYear.isEmpty());
    }
    @Test
    void shouldReturnEmptyOptionalOfSchoolYearIfFactoryIsNull() {

        //Arrange
        Optional<SchoolYearDataModel> schoolYearDMOpt = Optional.of(mock(SchoolYearDataModel.class));
        ISchoolYearRepositorySpringData iSchoolYearRepositorySpringData=mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper = mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYearFactoryImpl schoolYearFactory = null;
        SchoolYearRepositorySpringDataImpl schoolYearRepositorySpringData= new SchoolYearRepositorySpringDataImpl(iSchoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        when(schoolYearMapper.toDomain(schoolYearDMOpt.get(), schoolYearFactory)).thenThrow(new IllegalArgumentException("School Year DataModel and/or Factory cannot be null"));
        when(iSchoolYearRepositorySpringData.findById(schoolYearID.toString())).thenReturn(schoolYearDMOpt);
        //Act
        Optional<SchoolYear> schoolYear = schoolYearRepositorySpringData.ofIdentity(schoolYearID, schoolYearFactory);

        //Assert
        assertTrue(schoolYear.isEmpty());
    }

}