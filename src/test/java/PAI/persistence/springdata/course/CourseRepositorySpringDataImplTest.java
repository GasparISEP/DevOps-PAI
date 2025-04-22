package PAI.persistence.springdata.course;

import PAI.VOs.CourseID;
import PAI.domain.course.Course;
import PAI.mapper.Course.ICourseMapper;
import PAI.mapper.CourseID.ICourseIDMapper;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseRepositorySpringDataImplTest {

    @Test
    void shouldReturnNotNullCourseRepository() {
        // Arrange
        ICourseIDMapper iCourseIDMapper = mock(ICourseIDMapper.class);
        ICourseMapper iCourseMapper = mock(ICourseMapper.class);
        ICourseRepositorySpringData iCourseRepository = mock(ICourseRepositorySpringData.class);

        // Act
        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepository, iCourseMapper, iCourseIDMapper);

        // Assert
        assertNotNull(courseRepositorySpringData);
    }

    @Test
    void shouldReturnAllCoursesFindAllMethod() {
        // Arrange
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        courseRepositorySpringData.findAll();

        // Assert
        verify(iCourseRepositoryDouble).findAll();
    }

    @Test
    void shouldReturnCoursesWhenRepositoryReturnsValidData() throws Exception {
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseDataModel dataModel1 = mock(CourseDataModel.class);
        CourseDataModel dataModel2 = mock(CourseDataModel.class);

        when(iCourseRepositoryDouble.findAll()).thenReturn(List.of(dataModel1, dataModel2));
        when(iCourseMapperDouble.toDomain(dataModel1)).thenReturn(mock(Course.class));
        when(iCourseMapperDouble.toDomain(dataModel2)).thenReturn(mock(Course.class));

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        Iterable<Course> result = courseRepositorySpringData.findAll();

        assertNotNull(result);
        assertEquals(2, ((List<Course>) result).size());
        verify(iCourseMapperDouble).toDomain(dataModel1);
        verify(iCourseMapperDouble).toDomain(dataModel2);
    }

    @Test
    void shouldReturnSavedCourseForSaveMethod() throws Exception {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        Course courseDouble = mock(Course.class);
        CourseDataModel courseDataModel = mock(CourseDataModel.class);

        when(iCourseMapperDouble.toDataModel(courseDouble)).thenReturn(courseDataModel);
        when(iCourseRepositoryDouble.save(courseDataModel)).thenReturn(courseDataModel);
        when(iCourseMapperDouble.toDomain(courseDataModel)).thenReturn(courseDouble);

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        Course result = courseRepositorySpringData.save(courseDouble);

        // Assert
        assertNotNull(result);
        verify(iCourseRepositoryDouble).save(courseDataModel);
    }

    @Test
    void shouldReturnNonEmptyOptionalOfIdentity() throws Exception {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseID courseIDDouble = mock(CourseID.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        Course courseDouble = mock(Course.class);
        CourseDataModel courseDataModel = mock(CourseDataModel.class);

        when(iCourseIDMapperDouble.toDataModel(courseIDDouble)).thenReturn(courseIDDataModel);
        when(iCourseRepositoryDouble.findById(courseIDDataModel)).thenReturn(Optional.of(courseDataModel));
        when(iCourseMapperDouble.toDomain(courseDataModel)).thenReturn(courseDouble);

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        Optional<Course> result = courseRepositorySpringData.ofIdentity(courseIDDouble);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(courseDouble, result.get());
        assertNotNull(result);
        verify(iCourseIDMapperDouble).toDataModel(courseIDDouble);
    }

    @Test
    void shouldReturnTrueIfContainsOfIdentity() {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseID courseIDDouble = mock(CourseID.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);

        when(iCourseIDMapperDouble.toDataModel(courseIDDouble)).thenReturn(courseIDDataModel);
        when(iCourseRepositoryDouble.existsById(courseIDDataModel)).thenReturn(true);

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        boolean result = courseRepositorySpringData.containsOfIdentity(courseIDDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNotContainsOfIdentity() {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseID courseIDDouble = mock(CourseID.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);

        when(iCourseIDMapperDouble.toDataModel(courseIDDouble)).thenReturn(courseIDDataModel);
        when(iCourseRepositoryDouble.existsById(courseIDDataModel)).thenReturn(false);

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        boolean result = courseRepositorySpringData.containsOfIdentity(courseIDDouble);

        // Assert
        assertFalse(result);
    }
}