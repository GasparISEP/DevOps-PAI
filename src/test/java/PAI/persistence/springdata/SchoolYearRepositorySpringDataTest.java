package PAI.persistence.springdata;

import PAI.domain.SchoolYear;
import PAI.domain.StudentGrade;
import PAI.mapper.IStudentGradeIDMapper;
import PAI.mapper.IStudentGradeMapper;
import PAI.mapper.SchoolYear.ISchoolYearMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.repository.ISchoolYearRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearRepositorySpringDataTest {

    // Tests with isolation

    @Test
    void shouldCreateConstructor(){
        //arrange

        ISchoolYearRepositorySpringData schoolYearRepositorySpringData = mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper =mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper =mock(ISchoolYearIDMapper.class);

        //act
        SchoolYearRepositorySpringData schoolYearRepository = new SchoolYearRepositorySpringData(schoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        //assert
        assertNotNull(schoolYearRepository);
    }

    @Test
    void shouldThrowExceptionSpringDataRepositoryIsNull(){
        //arrange

        ISchoolYearRepositorySpringData schoolYearRepositorySpringData = null;
        ISchoolYearMapper schoolYearMapper =mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper =mock(ISchoolYearIDMapper.class);

        // act + assert
        Exception exception=assertThrows(Exception.class, () -> {
            SchoolYearRepositorySpringData schoolYearRepository = new SchoolYearRepositorySpringData(schoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        });
        assertEquals(exception.getMessage(),"Spring Data Repository or SchoolYear/SchoolYearID mappers cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearMapperIsNull(){

        //arrange
        ISchoolYearRepositorySpringData schoolYearRepositorySpringData =  mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper =null;
        ISchoolYearIDMapper schoolYearIDMapper =mock(ISchoolYearIDMapper.class);

        // act + assert
        Exception exception=assertThrows(Exception.class, () -> {
            SchoolYearRepositorySpringData schoolYearRepository = new SchoolYearRepositorySpringData(schoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        });
        assertEquals(exception.getMessage(),"Spring Data Repository or SchoolYear/SchoolYearID mappers cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDMapperIsNull(){

        //arrange
        ISchoolYearRepositorySpringData schoolYearRepositorySpringData =  mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper =mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = null;

        // act + assert
        Exception exception=assertThrows(Exception.class, () -> {
            SchoolYearRepositorySpringData schoolYearRepository = new SchoolYearRepositorySpringData(schoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);

        });
        assertEquals(exception.getMessage(),"Spring Data Repository or SchoolYear/SchoolYearID mappers cannot be null");
    }

    @Test
    void shouldReturnSchoolYearWhenSaved() {

        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData =  mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper =mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper =mock(ISchoolYearIDMapper.class);
        SchoolYear schoolYear=mock(SchoolYear.class);
        SchoolYearRepositorySpringData schoolYearRepositorySpringData = new SchoolYearRepositorySpringData(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearDataModel schoolYearDataModel =mock(SchoolYearDataModel.class);

        when(schoolYearMapper.toDataModel(schoolYear)).thenReturn(schoolYearDataModel);
        when(ischoolYearRepositorySpringData.save(schoolYearDataModel)).thenReturn(schoolYearDataModel);
        //act
        SchoolYear result = schoolYearRepositorySpringData.save(schoolYear);
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionIfSchoolYearIsNull() {

        //arrange
        ISchoolYearRepositorySpringData ischoolYearRepositorySpringData =  mock(ISchoolYearRepositorySpringData.class);
        ISchoolYearMapper schoolYearMapper =mock(ISchoolYearMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper =mock(ISchoolYearIDMapper.class);
        SchoolYear schoolYear=null;
        SchoolYearRepositorySpringData schoolYearRepositorySpringData = new SchoolYearRepositorySpringData(ischoolYearRepositorySpringData, schoolYearMapper, schoolYearIDMapper);
        SchoolYearDataModel schoolYearDataModel =mock(SchoolYearDataModel.class);

        when(schoolYearMapper.toDataModel(schoolYear)).thenReturn(schoolYearDataModel);
        when(ischoolYearRepositorySpringData.save(schoolYearDataModel)).thenReturn(schoolYearDataModel);

        // act + assert
        Exception exception=assertThrows(Exception.class, () -> {
            SchoolYear result = schoolYearRepositorySpringData.save(schoolYear);

        });
        assertEquals(exception.getMessage(),"SchoolYear cannot be null");

    }
}