package PAI.persistence.mem.courseRepository;

import PAI.VOs.*;
import PAI.domain.course.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseRepositoryImplTest {

    @Test
    void shouldCreateRepositoryImplInstance(){
        // arrange
        ICourseRepositoryListFactory iCourseRepositoryListFactoryDouble = mock(ICourseRepositoryListFactory.class);
        // act
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactoryDouble);
        // assert
        assertNotNull(courseRepositoryImpl);
    }

    @Test
    void shouldNotCreateRepositoryDDDImplInstanceIfCourseRepositoryListFactoryIsNull(){
        // arrange
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new CourseRepositoryImpl(null));
    }

    @Test
    void shouldGetAllCoursesInRepository(){
        // arrange
        ICourseRepositoryListFactory iCourseRepositoryListFactoryDouble = mock(ICourseRepositoryListFactory.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactoryDouble);
        // act
        Iterable<Course> list = courseRepositoryImpl.findAll();
        // assert
        assertNotNull(list);
    }

    @Test
    void shouldReturnCourseDDDIfCourseDDDSavedInRepository(){
        // arrange
        Course course = mock(Course.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactoryDouble = mock(ICourseRepositoryListFactory.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactoryDouble);
        // act
        Course result = courseRepositoryImpl.save(course);
        // assert
        assertEquals(course, result);
    }

    @Test
    void shouldReturnNullIfCourseIDAlreadyExistsInRepository() {
        // arrange
        CourseID courseID = mock(CourseID.class);
        Course course = mock(Course.class);
        when(course.identity()).thenReturn(courseID);

        List<Course> list = mock(ArrayList.class);
        when(list.stream()).thenReturn(Stream.of(course));

        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        when(iCourseRepositoryListFactory.createCourseRepositoryList()).thenReturn(list);

        CourseRepositoryImpl repository = new CourseRepositoryImpl(iCourseRepositoryListFactory);

        // act
        Course result = repository.save(course);

        // assert
        assertNull(result);
        verify(list, never()).add(any());
    }

    @Test
    void shouldThrowExceptionIfInputIsNull(){
        // arrange
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactory);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> courseRepositoryImpl.save(null));

    }

    @Test
    void shouldReturnCourseOptionalIfCourseIdInRepository() {
        // arrange
        CourseID courseID = mock(CourseID.class);
        Course course = mock(Course.class);
        List courseList = mock(List.class);
        Stream stream = mock(Stream.class);
        Stream filteredStream = mock(Stream.class);

        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        when(iCourseRepositoryListFactory.createCourseRepositoryList()).thenReturn(courseList);
        when(course.identity()).thenReturn(courseID);
        when(courseList.stream()).thenReturn(stream);
        when(stream.filter(any())).thenReturn(filteredStream);
        when(filteredStream.findFirst()).thenReturn(Optional.of(course));

        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactory);

        // act
        Optional<Course> result = courseRepositoryImpl.ofIdentity(courseID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(course, result.get());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseIdNull() {
        // arrange
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactory);
        // act
        Optional<Course> result = courseRepositoryImpl.ofIdentity(null);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseIdNotInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactory);
        // act
        Optional<Course> result = courseRepositoryImpl.ofIdentity(courseID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueIfCourseIDExistInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        Course course = mock(Course.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);

        CourseRepositoryImpl courseRepositoryImpl = spy(new CourseRepositoryImpl(iCourseRepositoryListFactory));

        when(courseRepositoryImpl.ofIdentity(courseID)).thenReturn(Optional.of(course));

        // act
        boolean result = courseRepositoryImpl.containsOfIdentity(courseID);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfIfCourseIDNotExistInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);

        CourseRepositoryImpl courseRepositoryImpl = spy(new CourseRepositoryImpl(iCourseRepositoryListFactory));
        when(courseRepositoryImpl.ofIdentity(courseID)).thenReturn(Optional.empty());

        // act
        boolean result = courseRepositoryImpl.containsOfIdentity(courseID);
        // assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTrueIfCourseWithCourseAcronymExistsInRepository() {
        // arrange
        Acronym courseAcronym = new Acronym("APROG");

        Course courseDouble = mock(Course.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseDouble.identity()).thenReturn(courseIDDouble);
        when(courseIDDouble.getAcronym()).thenReturn(courseAcronym);

        List<Course> list = new ArrayList<>();

        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        when(iCourseRepositoryListFactory.createCourseRepositoryList()).thenReturn(list);

        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactory);

        list.add(courseDouble);

        // act
        boolean result = courseRepositoryImpl.existsCourseByAcronym(courseAcronym);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseAcronymDoesNotExistInRepository() {
        // arrange
        Acronym courseAcronym = new Acronym("APROG");
        Acronym courseAcronym2 = new Acronym("APROGII");

        Course courseDouble = mock(Course.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseDouble.identity()).thenReturn(courseIDDouble);
        when(courseIDDouble.getAcronym()).thenReturn(courseAcronym2);

        List<Course> list = new ArrayList<>();

        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        when(iCourseRepositoryListFactory.createCourseRepositoryList()).thenReturn(list);

        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactory);

        list.add(courseDouble);

        // act
        boolean result = courseRepositoryImpl.existsCourseByAcronym(courseAcronym);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseAcronymIsNull() {
        // arrange
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(mock(ICourseRepositoryListFactory.class));

        // act
        boolean result = courseRepositoryImpl.existsCourseByAcronym(null);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseNameExistsInRepository() {
        // arrange
        Name courseName = new Name("Software Engineering");
        Course courseDouble = mock(Course.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseDouble.identity()).thenReturn(courseIDDouble);
        when(courseIDDouble.getName()).thenReturn(courseName);

        List<Course> list = new ArrayList<>();

        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        when(iCourseRepositoryListFactory.createCourseRepositoryList()).thenReturn(list);

        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactory);

        list.add(courseDouble);

        // act
        boolean result = courseRepositoryImpl.existsCourseByName(courseName);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseNameDoesNotExistInRepository() {
        // arrange
        Name courseName = mock(Name.class);
        Name courseName2 = new Name("Software Engineering");

        Course courseDouble = mock(Course.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseDouble.identity()).thenReturn(courseIDDouble);
        when(courseIDDouble.getName()).thenReturn(courseName);

        List<Course> list = new ArrayList<>();

        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        when(iCourseRepositoryListFactory.createCourseRepositoryList()).thenReturn(list);

        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseRepositoryListFactory);

        list.add(courseDouble);

        // act
        boolean result = courseRepositoryImpl.existsCourseByName(courseName2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseNameIsNull() {
        // arrange
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(mock(ICourseRepositoryListFactory.class));

        // act
        boolean result = courseRepositoryImpl.existsCourseByName(null);

        // assert
        assertFalse(result);
    }
}