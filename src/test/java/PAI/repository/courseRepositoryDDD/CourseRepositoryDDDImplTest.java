package PAI.repository.courseRepositoryDDD;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactoryDDD;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(ICourseRepositoryListFactoryDDD.class);
        // act
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);
        // assert
        assertNotNull(courseRepositoryDDDImpl);
    }

    @Test
    void shouldNotCreateRepositoryDDDImplInstanceIfCourseFactoryIsNull(){
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(ICourseRepositoryListFactoryDDD.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new CourseRepositoryDDDImpl(null, iCourseRepositoryListFactoryDDD));
    }

    @Test
    void shouldNotCreateRepositoryDDDImplInstanceIfCourseRepositoryListFactoryIsNull(){
        // arrange
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new CourseRepositoryDDDImpl(iCourseFactoryDDD,null));
    }

    @Test
    void shouldGetAllCoursesInRepository(){
        // arrange
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(ICourseRepositoryListFactoryDDD.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);
        // act
        Iterable<Course> list = courseRepositoryDDDImpl.findAll();
        // assert
        assertNotNull(list);
    }

    @Test
    void shouldReturnCourseDDDIfCourseDDDSavedInRepository(){
        // arrange
        Course course = mock(Course.class);
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(ICourseRepositoryListFactoryDDD.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);
        // act
        Course result = courseRepositoryDDDImpl.save(course);
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

        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(ICourseRepositoryListFactoryDDD.class);
        when(iCourseRepositoryListFactoryDDD.createCourseRepositoryList()).thenReturn(list);

        CourseRepositoryDDDImpl repository = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        // act
        Course result = repository.save(course);

        // assert
        assertNull(result);
        verify(list, never()).add(any());
    }

    @Test
    void shouldReturnTrueIfCourseIsRegistered() throws Exception {
        //arrange
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(ICourseRepositoryListFactoryDDD.class);
        CourseRepositoryDDDImpl repository = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts quantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        Course course = mock(Course.class);
        when(iCourseFactoryDDD.createCourse(courseID, name, acronym, quantityCreditsEcts, durationCourseInCurricularYear)).thenReturn(course);

        //act
        boolean result = repository.registerCourse(courseID, name, acronym, quantityCreditsEcts, durationCourseInCurricularYear);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseIsNotRegisteredBecauseItsAlreadyRegistered() throws Exception {
        // arrange
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(ICourseRepositoryListFactoryDDD.class);
        CourseRepositoryDDDImpl repository = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        CourseQuantityCreditsEcts quantityCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        DurationCourseInCurricularYear durationCourseInCurricularYear = mock(DurationCourseInCurricularYear.class);

        Course course = mock(Course.class);
        when(course.identity()).thenReturn(courseID);
        when(iCourseFactoryDDD.createCourse(courseID, name, acronym, quantityCreditsEcts, durationCourseInCurricularYear))
                .thenReturn(course);

        repository.registerCourse(courseID, name, acronym, quantityCreditsEcts, durationCourseInCurricularYear);

        //act
        boolean result = repository.registerCourse(courseID, name, acronym, quantityCreditsEcts, durationCourseInCurricularYear);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionIfCourseDDDIfInputIsNull(){
        // arrange
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> courseRepositoryDDDImpl.save(null));

    }

    @Test
    void shouldReturnCourseDDDOptionalIfCourseIdInRepository() {
        // arrange
        CourseID courseID = mock(CourseID.class);
        Course course = mock(Course.class);
        List courseList = mock(List.class);
        Stream stream = mock(Stream.class);
        Stream filteredStream = mock(Stream.class);

        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        when(iCourseRepositoryListFactoryDDD.createCourseRepositoryList()).thenReturn(courseList);
        when(course.identity()).thenReturn(courseID);
        when(courseList.stream()).thenReturn(stream);
        when(stream.filter(any())).thenReturn(filteredStream);
        when(filteredStream.findFirst()).thenReturn(Optional.of(course));

        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        // act
        Optional<Course> result = courseRepositoryDDDImpl.ofIdentity(courseID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(course, result.get());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseIdNull() {
        // arrange
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);
        // act
        Optional<Course> result = courseRepositoryDDDImpl.ofIdentity(null);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfCourseIdNotInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);
        // act
        Optional<Course> result = courseRepositoryDDDImpl.ofIdentity(courseID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueIfCourseIDExistInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        Course course = mock(Course.class);
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = spy(new CourseRepositoryDDDImpl(iCourseFactoryDDD,iCourseRepositoryListFactoryDDD));
        when(courseRepositoryDDDImpl.ofIdentity(courseID)).thenReturn(Optional.of(course));
        // act
        boolean result = courseRepositoryDDDImpl.containsOfIdentity(courseID);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfIfCourseIDNotExistInRepository(){
        // arrange
        CourseID courseID = mock(CourseID.class);
        ICourseFactoryDDD iCourseFactoryDDD = mock(ICourseFactoryDDD.class);
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = mock(CourseRepositoryListFactoryImpl.class);
        CourseRepositoryDDDImpl courseRepositoryDDDImpl = spy(new CourseRepositoryDDDImpl(iCourseFactoryDDD,iCourseRepositoryListFactoryDDD));
        when(courseRepositoryDDDImpl.ofIdentity(courseID)).thenReturn(Optional.empty());
        // act
        boolean result = courseRepositoryDDDImpl.containsOfIdentity(courseID);
        // assert
        assertFalse(result);
    }
}