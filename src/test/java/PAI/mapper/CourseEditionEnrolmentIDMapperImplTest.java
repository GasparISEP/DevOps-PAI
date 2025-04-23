package PAI.mapper;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionEnrolmentIDMapperImplTest {

    // testing constructor

    @Test
    void shouldReturnAnExceptionIfCourseEditionIDMapperInterfaceIsNull (){
        //arrange


        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentIDMapperImpl(null);
        });
        assertEquals("Course Edition ID Mapper Interface cannot be null!", exception.getMessage());
    }

    //testing to Domain method

    @Test
    void shouldReturnACourseEditionEnrolmentID() throws Exception {
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel = mock (CourseEditionEnrolmentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock (CourseEditionIDDataModel.class);

        when(doubleCourseEditionEnrolmentIDDataModel.findStudentID()).thenReturn("1234567");
        when(doubleCourseEditionEnrolmentIDDataModel.findCourseEditionID()).thenReturn(doubleCourseEditionIDDataModel);

        ICourseEditionIDMapper doubleCourseEditionIDMapperInterface = mock(ICourseEditionIDMapper.class);
        CourseEditionID doubleCourseEditionID = mock(CourseEditionID.class);
        when (doubleCourseEditionIDMapperInterface.toDomain(doubleCourseEditionIDDataModel)).thenReturn(doubleCourseEditionID);

        CourseEditionEnrolmentIDMapperImpl ceeIDMapper = new CourseEditionEnrolmentIDMapperImpl(doubleCourseEditionIDMapperInterface);

        //act
        Optional<CourseEditionEnrolmentID> result = ceeIDMapper.toDomain (doubleCourseEditionEnrolmentIDDataModel);

        //assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseEditionEnrolmentIDIsNull() throws Exception {
        //arrange
        ICourseEditionIDMapper doubleCourseEditionIDMapperInterface = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentIDMapperImpl ceeIDMapper = new CourseEditionEnrolmentIDMapperImpl(doubleCourseEditionIDMapperInterface);

        //act
        Optional<CourseEditionEnrolmentID> result = ceeIDMapper.toDomain (null);

        //assert
        assertTrue(result.isEmpty());
    }

    //testing to DataModel method

    @Test
    void shouldReturnACourseEditionEnrolmentIDDataModel() throws Exception {
        //arrange
        CourseEditionEnrolmentID doubleCourseEditionEnrolmentID = mock(CourseEditionEnrolmentID.class);
        CourseEditionID doubleCourseEditionID = mock(CourseEditionID.class);
        StudentID doubleStudentID = mock(StudentID.class);

        when(doubleCourseEditionEnrolmentID.findStudentID()).thenReturn(doubleStudentID);
        when(doubleStudentID.toString()).thenReturn("1234567");
        when(doubleCourseEditionEnrolmentID.findCourseEditionID()).thenReturn(doubleCourseEditionID);

        ICourseEditionIDMapper doubleCourseEditionIDMapperInterface = mock(ICourseEditionIDMapper.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        when(doubleCourseEditionIDMapperInterface.toDataModel(doubleCourseEditionID)).thenReturn(doubleCourseEditionIDDataModel);

        CourseEditionEnrolmentIDMapperImpl ceeIDMapper = new CourseEditionEnrolmentIDMapperImpl(doubleCourseEditionIDMapperInterface);

        //act
        Optional<CourseEditionEnrolmentIDDataModel> result = ceeIDMapper.toDataModel(doubleCourseEditionEnrolmentID);

        //assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseEditionEnrolmentIDDataModelIsNull() throws Exception {
        //arrange
        ICourseEditionIDMapper doubleCourseEditionIDMapperInterface = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentIDMapperImpl ceeIDMapper = new CourseEditionEnrolmentIDMapperImpl(doubleCourseEditionIDMapperInterface);

        //act
        Optional <CourseEditionEnrolmentIDDataModel> result = ceeIDMapper.toDataModel (null);

        //assert
        assertTrue(result.isEmpty());
    }
}