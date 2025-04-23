package PAI.repository.courseRepository;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactory;

import PAI.persistence.mem.courseRepository.CourseRepositoryImpl;
import PAI.persistence.mem.courseRepository.CourseRepositoryListFactoryImpl;
import PAI.persistence.mem.courseRepository.ICourseRepositoryListFactory;
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
    void shouldCreateRepositoryDDDImplInstance(){
        // arrange
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        // act
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
        // assert
        assertNotNull(courseRepositoryImpl);
    }

    @Test
    void shouldNotCreateRepositoryDDDImplInstanceIfCourseFactoryIsNull(){
        // arrange
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new CourseRepositoryImpl(null, iCourseRepositoryListFactory));
    }

    @Test
    void shouldNotCreateRepositoryDDDImplInstanceIfCourseRepositoryListFactoryIsNull(){
        // arrange
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new CourseRepositoryImpl(iCourseFactory,null));
    }

    @Test
    void shouldGetAllCoursesInRepository(){
        // arrange
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
        // act
        Iterable<Course> list = courseRepositoryImpl.findAll();
        // assert
        assertNotNull(list);
    }

    @Test
    void shouldReturnCourseDDDIfCourseDDDSavedInRepository(){
        // arrange
        Course course = mock(Course.class);
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
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

        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
        when(iCourseRepositoryListFactory.createCourseRepositoryList()).thenReturn(list);

        CourseRepositoryImpl repository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);

        // act
        Course result = repository.save(course);

        // assert
        assertNull(result);
        verify(list, never()).add(any());
    }

//    @Test
//    void shouldReturnTrueIfCourseIsRegistered() throws Exception {
//        //arrange
//        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
//        CourseRepositoryImpl repository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        CourseID courseID = mock(CourseID.class);
//        Name name = mock(Name.class);
//        Acronym acronym = mock(Acronym.class);
//
//        Course course = mock(Course.class);
//        when(iCourseFactory.createCourse(name, acronym)).thenReturn(course);
//
//        //act
//        boolean result = repository.registerCourse(name, acronym);
//        //assert
//        assertTrue(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfCourseIsNotRegisteredBecauseItsAlreadyRegistered() throws Exception {
//        // arrange
//        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(ICourseRepositoryListFactory.class);
//        CourseRepositoryImpl repository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        CourseID courseID = mock(CourseID.class);
//        Name name = mock(Name.class);
//        Acronym acronym = mock(Acronym.class);
//
//
//        Course course = mock(Course.class);
//        when(course.identity()).thenReturn(courseID);
//        when(iCourseFactory.createCourse(name, acronym))
//                .thenReturn(course);
//
//        repository.registerCourse(name, acronym);
//
//        //act
//        boolean result = repository.registerCourse(name, acronym);
//        //assert
//        assertFalse(result);
//    }

    @Test
    void shouldThrowExceptionIfCourseDDDIfInputIsNull(){
        // arrange
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> courseRepositoryImpl.save(null));

    }

    @Test
    void shouldReturnCourseDDDOptionalIfCourseIdInRepository() {
        // arrange
        CourseID courseID = mock(CourseID.class);
        Course course = mock(Course.class);
        List courseList = mock(List.class);
        Stream stream = mock(Stream.class);
        Stream filteredStream = mock(Stream.class);

        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        when(iCourseRepositoryListFactory.createCourseRepositoryList()).thenReturn(courseList);
        when(course.identity()).thenReturn(courseID);
        when(courseList.stream()).thenReturn(stream);
        when(stream.filter(any())).thenReturn(filteredStream);
        when(filteredStream.findFirst()).thenReturn(Optional.of(course));

        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);

        // act
        Optional<Course> result = courseRepositoryImpl.ofIdentity(courseID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(course, result.get());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseIdNull() {
        // arrange
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
        // act
        Optional<Course> result = courseRepositoryImpl.ofIdentity(null);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseIdNotInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryImpl courseRepositoryImpl = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
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
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryImpl courseRepositoryImpl = spy(new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory));
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
        ICourseFactory iCourseFactory = mock(ICourseFactory.class);
        ICourseRepositoryListFactory iCourseRepositoryListFactory = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryImpl courseRepositoryImpl = spy(new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory));
        when(courseRepositoryImpl.ofIdentity(courseID)).thenReturn(Optional.empty());
        // act
        boolean result = courseRepositoryImpl.containsOfIdentity(courseID);
        // assert
        assertFalse(result);
    }
}