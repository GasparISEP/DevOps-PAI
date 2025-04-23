package PAI.service.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.domain.course.Course;

import PAI.domain.course.ICourseFactory;
import PAI.repository.courseRepository.ICourseRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CourseServiceImplTest {

    @Test
    void should_return_a_valid_constructor() {
        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);

        //Act + Assert
        assertDoesNotThrow(() -> new CourseServiceImpl(factory, repository));
    }

    @Test
    void should_throw_exception_when_factory_is_null() {
        //Arrange
        ICourseFactory factory = null;
        ICourseRepository repository = mock(ICourseRepository.class);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new CourseServiceImpl(null, repository));
    }

    @Test
    void should_throw_exception_when_repository_is_null() {
        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = null;

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new CourseServiceImpl(factory, null));
    }

    @Test
    void should_create_and_save_new_course() throws Exception {

        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository);

        Name name = new Name("Programação");
        Acronym acronym = new Acronym("LEI");
        Course course = mock(Course.class);

        when(factory.createCourse(name, acronym)).thenReturn(course);
        when(repository.save(course)).thenReturn(course);

        //Act
        Course result = service.newCourse(name, acronym);

        //Assert
        assertNotNull(result);
        assertEquals(course, result);

        verify(factory).createCourse(name, acronym);
        verify(repository).save(course);
    }

    @Test
    void should_return_all_courses_from_repositories() {

        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository);

        List<Course> courseList = List.of(mock(Course.class), mock(Course.class));
        when(repository.findAll()).thenReturn(courseList);

        //Act
        Iterable<Course> result = service.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(result, courseList);

        verify(repository).findAll();

    }
    // Testing Optional method
    @Test
    void should_return_Optional_Empty_if_CourseId_is_Null() {
        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository);
        // Act
        Optional<Course> result = service.ofIdentity(null);
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_Course_if_CourseId_exists() {
        // Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository);

        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        Course course = mock(Course.class);

        when(repository.ofIdentity(courseID)).thenReturn(Optional.of(course));

        // Act
        Optional<Course> result = service.ofIdentity(courseID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(course, result.get());
        verify(repository).ofIdentity(courseID);
    }

    @Test
    void should_return_true_if_course_exists() {

        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        Course course = mock(Course.class);
        when(repository.ofIdentity(courseID)).thenReturn(Optional.of(course));

        //Act + Assert
        assertTrue(service.containsOfIdentity(courseID));
    }

    @Test
    void should_return_false_if_course_does_not_exist() {

        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository);

        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("ABCDEF"));
        when(repository.ofIdentity(courseID)).thenReturn(Optional.empty());

        //Act + Assert
        assertFalse(service.containsOfIdentity(courseID));
    }

}