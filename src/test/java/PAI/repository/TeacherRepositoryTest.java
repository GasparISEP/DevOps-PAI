package PAI.repository;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherRepositoryTest {

    // Arrange
    private ITeacherListFactory _teacherListFactory;

    private void createFactoriesDoubles(){
        _teacherListFactory = mock(ITeacherListFactory.class);
    }

    // Tests Beginning

    @Test
    void shouldCreateTeacherRepository() {
        // Arrange
        createFactoriesDoubles();

        // Act
        new TeacherRepositoryImpl(_teacherListFactory);

        // Assert
        assertNotNull(_teacherListFactory);
    }

    @Test
    void shouldReturnTeacherWhenSaveIsCalled(){
        // Arrange
        createFactoriesDoubles();
        Teacher teacher = mock(Teacher.class);

        TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl(_teacherListFactory);

        // Act
        Teacher teacher1 = teacherRepository.save(teacher);

        // Assert
        assertEquals(teacher, teacher1);
    }

    @Test
    void shouldReturnAllTeachersWhenFindAllIsCalled() {
        // Arrange
        createFactoriesDoubles();
        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);
        List<Teacher> listTeachersDouble = List.of(teacher1, teacher2);
        _teacherListFactory = () -> listTeachersDouble;
        TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl(_teacherListFactory);

        // Act
        Iterable<Teacher> result = teacherRepository.findAll();

        // Assert
        assertIterableEquals(listTeachersDouble, result);
    }

    @Test
    void shouldReturnATeacherWhenOfIdentityIsCalledWithExistingID(){
        // Arrange
        createFactoriesDoubles();
        TeacherID idDouble = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.identity()).thenReturn(idDouble);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepositoryImpl repository = new TeacherRepositoryImpl(_teacherListFactory);

        // Act
        Optional<Teacher> result = repository.ofIdentity(idDouble);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotReturnATeacherWhenOfIdentityIsCalledWithNoExistingID(){
        // Arrange
        createFactoriesDoubles();
        TeacherID idDouble = mock(TeacherID.class);
        TeacherID id2Double = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.identity()).thenReturn(idDouble);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepositoryImpl repository = new TeacherRepositoryImpl(_teacherListFactory);

        // Act
        Optional<Teacher> result = repository.ofIdentity(id2Double);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenTeacherIsPresentAndContainsOfIdentityIsCalled(){
        // Arrange
        createFactoriesDoubles();
        TeacherID idDouble = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.identity()).thenReturn(idDouble);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepositoryImpl repository = new TeacherRepositoryImpl(_teacherListFactory);

        // Act
        boolean result = repository.containsOfIdentity(idDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherIsNotPresentAndContainsOfIdentityIsCalled(){
        // Arrange
        createFactoriesDoubles();
        TeacherID idDouble = mock(TeacherID.class);
        TeacherID id2Double = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        when(teacher.identity()).thenReturn(idDouble);

        List<Teacher> teachers = List.of(teacher);
        _teacherListFactory = () -> teachers;

        TeacherRepositoryImpl repository = new TeacherRepositoryImpl(_teacherListFactory);

        // Act
        boolean result = repository.containsOfIdentity(id2Double);

        // Assert
        assertFalse(result);
    }

    // [Temporary] method still not properly implemented, but needs to exist in order for the class to implement interface
    @Test
    void testExistsByIDorNIF () {

        createFactoriesDoubles();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(_teacherListFactory);
        TeacherID teacherIDDouble = mock(TeacherID.class);
        NIF nifDouble = mock(NIF.class);

        boolean result = teacherRepository.existsByIDorNIF(teacherIDDouble, nifDouble);

        assertFalse(result);
    }
}