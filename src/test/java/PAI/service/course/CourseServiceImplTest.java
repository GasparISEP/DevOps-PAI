package PAI.service.course;

import PAI.VOs.Acronym;
import PAI.VOs.Name;
import PAI.domain.course.Course;

import PAI.domain.course.ICourseFactory;
import PAI.repository.courseRepository.ICourseRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CourseServiceImplTest {

    @Test
    void should_return_a_valid_constructor(){
        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);

        //Act + Assert
        assertDoesNotThrow(() -> new CourseServiceImpl(factory,repository));
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
    void should_create_and_save_new_course(){

        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        CourseServiceImpl service = new CourseServiceImpl(factory,repository);

        Name name = new Name ("Programação");
        Acronym acronym = new Acronym("LEI");
        Course course = mock(Course.class);

        when(factory.createCourse(name,acronym)).thenReturn(course);
        when(repository.save(course)).thenReturn(course);

        //Act
        Course result = service.newCourse(name, acronym);

        //Assert
        assertNotNull(result);
        assertEquals(course, result);

        verify(factory).createCourse(name,acronym);
        verify(repository).save(course);
    }

    @Test
    void should_return_all_courses_from_repositories() {

        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        CourseServiceImpl service = new CourseServiceImpl(factory,repository);

        List <Course> courseList = List.of(mock(Course.class),mock(Course.class));
        when(repository.findAll()).thenReturn(courseList);

        //Act
        Iterable<Course> result = service.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(result,courseList);

        verify(repository).findAll();

    }

}