package PAI.service.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.assembler.course.ICourseAssembler;
import PAI.domain.course.Course;

import PAI.domain.course.ICourseFactory;
import PAI.dto.course.CourseIDDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.domain.repositoryInterfaces.course.ICourseRepository;
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
        ICourseAssembler assembler = mock(ICourseAssembler.class);

        //Act + Assert
        assertDoesNotThrow(() -> new CourseServiceImpl(factory, repository, assembler));
    }

    @Test
    void should_throw_exception_when_factory_is_null() {
        //Arrange
        ICourseFactory factory = null;
        ICourseRepository repository = mock(ICourseRepository.class);
        ICourseAssembler assembler = mock(ICourseAssembler.class);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new CourseServiceImpl(null, repository, assembler));
    }

    @Test
    void should_throw_exception_when_repository_is_null() {
        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseAssembler assembler = mock(ICourseAssembler.class);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new CourseServiceImpl(factory, null, assembler));
    }

    @Test
    void should_throw_exception_when_assembler_is_null() {
        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new CourseServiceImpl(factory, repository, null));
    }

    @Test
    void should_return_list_of_CourseIDDTOs() {
        // Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);

        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        CourseID id1 = new CourseID(new Acronym("ABC"), new Name("Programacao"));
        CourseID id2 = new CourseID(new Acronym("DEF"), new Name("Direito"));
        CourseIDDTO dto1 = new CourseIDDTO("ABC", "Programacao");
        CourseIDDTO dto2 = new CourseIDDTO("DEF", "Direito");

        when(repository.findAll()).thenReturn(List.of(course1, course2));
        when(course1.identity()).thenReturn(id1);
        when(course2.identity()).thenReturn(id2);
        when(assembler.toIDDTO(id1)).thenReturn(dto1);
        when(assembler.toIDDTO(id2)).thenReturn(dto2);

        // Act
        List<CourseIDDTO> result = service.getAllCourseIDDTOs();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
        verify(repository).findAll();
        verify(assembler).toIDDTO(id1);
        verify(assembler).toIDDTO(id2);
    }

    @Test
    void should_return_empty_list_when_no_courses() {
        // Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);

        when(repository.findAll()).thenReturn(List.of());

        // Act
        List<CourseIDDTO> result = service.getAllCourseIDDTOs();

        // Assert
        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verifyNoInteractions(assembler);
    }

    @Test
    void should_create_and_save_new_course() throws Exception {

        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository,assembler);

        Name name = new Name("Programação");
        Acronym acronym = new Acronym("LEI");
        Course course = mock(Course.class);

        when(factory.createCourse(name, acronym)).thenReturn(course);
        when(repository.save(course)).thenReturn(course);

        //Act
        Course result = service.createAndSaveCourse(name, acronym);

        //Assert
        assertNotNull(result);
        assertEquals(course, result);

        verify(factory).createCourse(name, acronym);
        verify(repository).save(course);
    }

    @Test
    void should_return_all_courses_from_repositories() throws Exception {

        //Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);

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
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);
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
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);

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
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);
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
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);

        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("ABCDEF"));
        when(repository.ofIdentity(courseID)).thenReturn(Optional.empty());

        //Act + Assert
        assertFalse(service.containsOfIdentity(courseID));
    }

    @Test
    void shouldThrowBusinessRuleViolationException_whenCourseNameAlreadyExists() throws Exception {
        // Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);

        Name name = new Name("Programação");
        Acronym acronym = new Acronym("LEI");
        Course course = mock(Course.class);

        when(factory.createCourse(name, acronym)).thenReturn(course);
        when(repository.existsCourseIgnoringCaseByName(name)).thenReturn(true);

        // Act + Assert
        assertThrows(BusinessRuleViolationException.class, () -> service.createAndSaveCourse(name, acronym));
    }

    @Test
    void shouldThrowBusinessRuleViolationException_whenCourseAcronymAlreadyExists() throws Exception {
        // Arrange
        ICourseFactory factory = mock(ICourseFactory.class);
        ICourseRepository repository = mock(ICourseRepository.class);
        ICourseAssembler assembler = mock(ICourseAssembler.class);
        CourseServiceImpl service = new CourseServiceImpl(factory, repository, assembler);

        Name name = new Name("Programação");
        Acronym acronym = new Acronym("LEI");
        Course course = mock(Course.class);

        when(factory.createCourse(name, acronym)).thenReturn(course);
        when(repository.existsCourseByAcronym(acronym)).thenReturn(true);

        // Act + Assert
        assertThrows(BusinessRuleViolationException.class, () -> service.createAndSaveCourse(name, acronym));
    }

}