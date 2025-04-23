package PAI.persistence.springdata;

import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.mapper.SchoolYear.ISchoolYearMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

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
}