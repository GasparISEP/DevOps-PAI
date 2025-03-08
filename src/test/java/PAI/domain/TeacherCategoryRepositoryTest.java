package PAI.domain;

import PAI.repository.TeacherCategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryRepositoryTest {

    // Helper method to create repository with explicit dependencies.
    private TeacherCategoryRepository createRepository(TeacherCategoryFactory teacherCategoryFactory) {
        TeacherCategoryRepositoryListFactory listFactory = new TeacherCategoryRepositoryListFactory();
        return new TeacherCategoryRepository(teacherCategoryFactory, listFactory);
    }

    @Test
    void shouldReturnTrueIfCategoryIsAlreadyRegisteredInTheRepository() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = createRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act
        boolean result = teacherCategoryRepository.getTeacherCategoryByName("Professor Adjunto").isPresent();

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCategoryIsNotRegisteredInTheRepository() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = createRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory1 = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory1);

        // Act
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");
        boolean result = teacherCategoryRepository.getTeacherCategoryByName("Professor Titular").isPresent();

        // Assert
        assertFalse(result);
    }

    @Test
    void testTeacherCategoryRepositoryCreationValid() {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);

        // Act
        TeacherCategoryRepository teacherCategoryRepository = createRepository(teacherCategoryFactory);

        // Assert
        assertNotNull(teacherCategoryRepository);
    }

    @Test
    void shouldReturnTrueIfTeacherCategoryIsRegistered() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = createRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory);

        // Act
        boolean result = teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCategoryCannotBeRegistered() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = createRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act
        boolean result = teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnExceptionIfCategoryListIsEmpty() {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = createRepository(teacherCategoryFactory);

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> teacherCategoryRepository.getTeacherCategoryList());
    }

    @Test
    void shouldReturnCategoryListWithRegisteredCategories() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = createRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act
        List<TeacherCategory> teacherCategoryList = teacherCategoryRepository.getTeacherCategoryList();

        // Assert
        assertFalse(teacherCategoryList.isEmpty());
    }
}