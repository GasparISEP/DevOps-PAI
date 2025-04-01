package PAI.repository.courseRepositoryDDD;

import PAI.VOs.CourseID;
import PAI.domain.CourseDDD;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseRepositoryDDDImplTest {

    @Test
    void shouldCreateRepositoryDDDImplInstance(){
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        // act
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseRepositoryListFactoryDDD);
        // assert
        assertNotNull(courseRepositoryDDDImpl);
    }

    @Test
    void shouldNotCreateRepositoryDDDImplInstanceIfCourseRepositoryListFactoryIsNull(){
        // arrange
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new CourseRepositoryDDDImpl(null));
    }

    @Test
    void shouldGetAllCoursesInRepository(){
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseRepositoryListFactoryDDD);
        // act
        Iterable<CourseDDD> list = courseRepositoryDDDImpl.findAll();
        // assert
        assertNotNull(list);
    }

    @Test
    void shouldReturnCourseDDDIfCourseDDDSavedInRepository(){
        // arrange
        CourseDDD courseDDD = mock(CourseDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseRepositoryListFactoryDDD);
        // act
        CourseDDD result = courseRepositoryDDDImpl.save(courseDDD);
        // assert
        assertEquals(courseDDD, result);
    }

    @Test
    void shouldThrowExceptionIfCourseDDDIfInputIsNull(){
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseRepositoryListFactoryDDD);
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> courseRepositoryDDDImpl.save(null));

    }

    @Test
    void shouldReturnCourseDDDOptionalIfCourseIdInRepository() {
        // arrange
        CourseID courseID = mock(CourseID.class);
        CourseDDD courseDDD = mock(CourseDDD.class);
        List<CourseDDD> courseList = mock(List.class);
        Stream<CourseDDD> stream = mock(Stream.class);
        Stream<CourseDDD> filteredStream = mock(Stream.class);

        ICourseRepositoryListFactoryDDD factory = mock(CourseRepositoryListFactoryImpl.class);
        when(factory.createCourseRepositoryList()).thenReturn(courseList);
        when(courseDDD.identity()).thenReturn(courseID);
        when(courseList.stream()).thenReturn(stream);
        when(stream.filter(any())).thenReturn(filteredStream);
        when(filteredStream.findFirst()).thenReturn(Optional.of(courseDDD));

        CourseRepositoryDDDImpl repository = new CourseRepositoryDDDImpl(factory);

        // act
        Optional<CourseDDD> result = repository.ofIdentity(courseID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(courseDDD, result.get());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseIdNull() {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseRepositoryListFactoryDDD);
        // act
        Optional<CourseDDD> result = courseRepositoryDDDImpl.ofIdentity(null);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseIdNotInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseRepositoryListFactoryDDD);
        // act
        Optional<CourseDDD> result = courseRepositoryDDDImpl.ofIdentity(courseID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueIfCourseIDExistInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        CourseDDD courseDDD = mock(CourseDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = spy(new CourseRepositoryDDDImpl(iCourseRepositoryListFactoryDDD));
        when(courseRepositoryDDDImpl.ofIdentity(courseID)).thenReturn(Optional.of(courseDDD));
        // act
        boolean result = courseRepositoryDDDImpl.containsOfIdentity(courseID);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfIfCourseIDNotExistInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = spy(new CourseRepositoryDDDImpl(iCourseRepositoryListFactoryDDD));
        when(courseRepositoryDDDImpl.ofIdentity(courseID)).thenReturn(Optional.empty());
        // act
        boolean result = courseRepositoryDDDImpl.containsOfIdentity(courseID);
        // assert
        assertFalse(result);
    }
}