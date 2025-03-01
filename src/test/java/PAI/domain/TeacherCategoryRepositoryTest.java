package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryRepositoryTest {

    @Test
    void shouldReturnTrueIfCategoryIsAlreadyRegisteredInTheRepository() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act
        // Now we will directly check if the category is present in the repository using getTeacherCategoryByName
        boolean result = teacherCategoryRepository.getTeacherCategoryByName("Professor Adjunto").isPresent();

        // Assert
        assertTrue(result);  // Assert that the category is registered and can be found
    }

    @Test
    void shouldReturnFalseIfCategoryIsNotRegisteredInTheRepository() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory teacherCategory2 = new TeacherCategory("Professor Titular");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory1);

        // Act
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");
        boolean result = teacherCategoryRepository.getTeacherCategoryByName("Professor Titular").isPresent();

        // Assert
        assertFalse(result);  // Assert that teacherCategory2 is not registered
    }

    @Test
    void testTeacherCategoryRepositoryCreationValid() {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);

        // Act
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(teacherCategoryFactory);

        // Assert
        assertNotNull(teacherCategoryRepository);  // Assert that the repository is created successfully
    }

    @Test
    void shouldReturnTrueIfTeacherCategoryIsRegistered() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory);

        // Act
        boolean result = teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Assert
        assertTrue(result);  // Assert that the category was registered successfully
    }

    @Test
    void shouldReturnFalseIfTeacherCategoryCannotBeRegistered() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act
        boolean result = teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Assert
        assertFalse(result);  // Assert that the category cannot be registered again
    }

    // Test for empty list retrieval
    @Test
    void shouldReturnExceptionIfCategoryListIsEmpty() {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(teacherCategoryFactory);

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> teacherCategoryRepository.getTeacherCategoryList());  // Assert that exception is thrown
    }

    // Test for retrieving list with registered categories
    @Test
    void shouldReturnCategoryListWithRegisteredCategories() throws Exception {
        // Arrange
        TeacherCategoryFactory teacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(teacherCategoryFactory);
        TeacherCategory teacherCategory = new TeacherCategory("Professor Adjunto");

        when(teacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(teacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act
        List<TeacherCategory> teacherCategoryList = teacherCategoryRepository.getTeacherCategoryList();

        // Assert
        assertFalse(teacherCategoryList.isEmpty());  // Assert that the list is not empty and contains the registered category
    }
}