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
    void shouldThrowExceptionWhenCourseRepositoryIsNull() {
        // Arrange
        ICourseIDMapper iCourseIDMapper = mock(ICourseIDMapper.class);
        ICourseMapper iCourseMapper = mock(ICourseMapper.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CourseRepositorySpringDataImpl(null, iCourseMapper, iCourseIDMapper));
        assertEquals("iCourseRepositorySpringData must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCourseMapperIsNull() {
        // Arrange
        ICourseIDMapper iCourseIDMapper = mock(ICourseIDMapper.class);
        ICourseRepositorySpringData iCourseRepository = mock(ICourseRepositorySpringData.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CourseRepositorySpringDataImpl(iCourseRepository, null, iCourseIDMapper));
        assertEquals("iCourseMapper must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCourseIDMapperIsNull() {
        // Arrange
        ICourseMapper iCourseMapper = mock(ICourseMapper.class);
        ICourseRepositorySpringData iCourseRepository = mock(ICourseRepositorySpringData.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CourseRepositorySpringDataImpl(iCourseRepository, iCourseMapper, null));
        assertEquals("iCourseIDMapper must not be null", exception.getMessage());
    }

    @Test
    void shouldReturnCoursesWhenRepositoryReturnsValidData() throws Exception {
        // Arrange
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);

        Course courseDouble = mock(Course.class);
        CourseDataModel courseDataModel = mock(CourseDataModel.class);
        CourseDataModel courseDataModel2 = mock(CourseDataModel.class);


        when(iCourseRepositoryDouble.findAll()).thenReturn(List.of(courseDataModel, courseDataModel2));
        when(iCourseMapperDouble.toDomain(courseDataModel)).thenReturn(courseDouble);
        when(iCourseMapperDouble.toDomain(courseDataModel2)).thenReturn(courseDouble);
        when(iCourseMapperDouble.toDomain(List.of(courseDataModel, courseDataModel2))).thenReturn(List.of(courseDouble, courseDouble));

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        Iterable<Course> result = courseRepositorySpringData.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(((List<Course>) result).isEmpty());
        assertEquals(2, ((List<Course>) result).size());
    }

    @Test
    void shouldReturnNullIfEntityIsNull() throws Exception {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);
        CourseDataModel courseDataModel = mock(CourseDataModel.class);

        Course courseDouble = null;

        when(iCourseMapperDouble.toDataModel(courseDouble)).thenReturn(courseDataModel);
        when(iCourseRepositoryDouble.save(courseDataModel)).thenReturn(courseDataModel);
        when(iCourseMapperDouble.toDomain(courseDataModel)).thenReturn(courseDouble);

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);
        // Act
        Course result = courseRepositorySpringData.save(courseDouble);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullIfCourseDataModelIsNull() throws Exception {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);
        Course courseDouble = mock(Course.class);

        when(iCourseMapperDouble.toDataModel(courseDouble)).thenThrow(new NullPointerException());

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        Course result = courseRepositorySpringData.save(courseDouble);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNotNullSavedCourseForSaveMethod() throws Exception {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseID courseIDDouble = mock(CourseID.class);
        CourseIDDataModel courseIDDataModelDouble = mock(CourseIDDataModel.class);
        Course courseDouble = mock(Course.class);
        CourseDataModel courseDataModel = mock(CourseDataModel.class);

        when(iCourseMapperDouble.toDataModel(courseDouble)).thenReturn(courseDataModel);
        when(iCourseRepositoryDouble.save(courseDataModel)).thenReturn(courseDataModel);
        when(iCourseMapperDouble.toDomain(courseDataModel)).thenReturn(courseDouble);
        when(courseDouble.identity()).thenReturn(courseIDDouble);
        when(iCourseIDMapperDouble.toDataModel(courseIDDouble)).thenReturn(courseIDDataModelDouble);
        when(iCourseRepositoryDouble.existsById(courseIDDataModelDouble)).thenReturn(false);

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        Course result = courseRepositorySpringData.save(courseDouble);

        // Assert
        assertNotNull(result);
        verify(iCourseRepositoryDouble).save(courseDataModel);
    }

    @Test
    void shouldReturnNullNOTSavedCourseForSaveMethod() throws Exception {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseID courseIDDouble = mock(CourseID.class);
        CourseIDDataModel courseIDDataModelDouble = mock(CourseIDDataModel.class);
        Course courseDouble = mock(Course.class);
        CourseDataModel courseDataModel = mock(CourseDataModel.class);

        when(iCourseMapperDouble.toDataModel(courseDouble)).thenReturn(courseDataModel);
        when(iCourseRepositoryDouble.save(courseDataModel)).thenReturn(courseDataModel);
        when(iCourseMapperDouble.toDomain(courseDataModel)).thenReturn(courseDouble);
        when(courseDouble.identity()).thenReturn(courseIDDouble);
        when(iCourseIDMapperDouble.toDataModel(courseIDDouble)).thenReturn(courseIDDataModelDouble);
        when(iCourseRepositoryDouble.existsById(courseIDDataModelDouble)).thenReturn(true);

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        Course result = courseRepositorySpringData.save(courseDouble);

        // Assert
        assertNull(result);
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
    void shouldReturnEmptyOptionalIfIDNotFound() {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseID courseIDDouble = mock(CourseID.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);

        when(iCourseIDMapperDouble.toDataModel(courseIDDouble)).thenReturn(courseIDDataModel);
        when(iCourseRepositoryDouble.findById(courseIDDataModel)).thenReturn(Optional.empty());

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        Optional<Course> result = courseRepositorySpringData.ofIdentity(courseIDDouble);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalOfIdentityIfException() throws Exception {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseDataModel courseDataModel = mock(CourseDataModel.class);
        CourseID courseIDDouble = mock(CourseID.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);

        when(iCourseIDMapperDouble.toDataModel(courseIDDouble)).thenReturn(courseIDDataModel);
        when(iCourseRepositoryDouble.findById(courseIDDataModel)).thenReturn(Optional.of(courseDataModel));
        when(iCourseMapperDouble.toDomain(courseDataModel)).thenThrow(new Exception());

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        Optional<Course> result = courseRepositorySpringData.ofIdentity(courseIDDouble);

        // Assert
        assertTrue(result.isEmpty());
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

    @Test
    void shouldReturnFalseIfRepositoryNOTContainsOfIdentity() {
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

    @Test
    void shouldReturnFalseIfIDIsNullContainsOfIdentity() {
        // Arrange
        ICourseRepositorySpringData iCourseRepositoryDouble = mock(ICourseRepositorySpringData.class);
        ICourseMapper iCourseMapperDouble = mock(ICourseMapper.class);
        ICourseIDMapper iCourseIDMapperDouble = mock(ICourseIDMapper.class);

        CourseID courseIDDouble = null;

        CourseRepositorySpringDataImpl courseRepositorySpringData = new CourseRepositorySpringDataImpl(iCourseRepositoryDouble, iCourseMapperDouble, iCourseIDMapperDouble);

        // Act
        boolean result = courseRepositorySpringData.containsOfIdentity(courseIDDouble);

        // Assert
        assertFalse(result);
    }
}