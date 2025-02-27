package PAI.domain;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryRepositoryTest {

    @Test
    void shouldReturnTrueIfIsAlreadyRegisteredInTheTeacherCategoryRepository() throws Exception {
        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        //TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);
        //creating a real instance of TeacherCategory, to ensure that the equals method is used as intended. This is crucial for the contains method in your isTeacherCategoryRegistered method to work correctly.
        TeacherCategory doubleTeacherCategory = new TeacherCategory("Professor Adjunto");
        when(doubleTeacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(doubleTeacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act
        boolean result = teacherCategoryRepository.isTeacherCategoryRegistered(doubleTeacherCategory);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCategoryIsNotRegisteredInTeacherCategoryRepository() throws Exception {
        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        TeacherCategory doubleTeacherCategory1 = mock(TeacherCategory.class);
        TeacherCategory doubleTeacherCategory2 = mock(TeacherCategory.class);
        when(doubleTeacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(doubleTeacherCategory1);

        // Act
        boolean result = teacherCategoryRepository.isTeacherCategoryRegistered(doubleTeacherCategory2);
        // Assert
        assertFalse(result);

    }

    @Test
    void testTeacherCategoryRepositoryCreationValid() {

        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);

        // Act
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(doubleTeacherCategoryFactory);

        // Assert
        assertNotNull(teacherCategoryRepository);

    }

    @Test
    void shouldReturnTrueIIfTeacherCategoryIsRegistered() throws Exception {

        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);

        when(doubleTeacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(doubleTeacherCategory);

        // Act
        boolean result = teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Assert
        assertTrue(result);


    }

    @Test
    void shouldReturnFalseIfTeacherCategoryCannotBeRegistered() throws Exception {
        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);

        when(doubleTeacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(doubleTeacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act
        boolean result = teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Assert
        assertFalse(result);
    }

    //Testing that the list is not retrieved if empty
    @Test
    void shouldReturnExceptionIfCategoryListIsEmpty() throws IllegalStateException {
        // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(doubleTeacherCategoryFactory);

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> teacherCategoryRepository.getTeacherCategoryList());



    }

    //Testing that the retrieved list has registered objects
    @Test
    void shouldReturnCategoryListWithRegisteredCategories() throws Exception {
     // Arrange
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);
        when(doubleTeacherCategoryFactory.createTeacherCategory("Professor Adjunto")).thenReturn(doubleTeacherCategory);
        teacherCategoryRepository.registerTeacherCategory("Professor Adjunto");

        // Act

        List<TeacherCategory> teacherCategoryList = teacherCategoryRepository.getTeacherCategoryList();

        // Assert
        assertFalse(teacherCategoryList.isEmpty());


    }
}