package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryRepositoryTest {

    @Test
    void shouldReturnTrueIfIsAlreadyRegisteredInTheTeacherCategoryRepository() throws Exception {
        //arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        tcr.registerTeacherCategory("Professor Adjunto");
        //act
        boolean result = tcr.isTeacherCategoryRegistered(tc1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCategorydIsNotRegisteredInTeacherCategoryRepository() throws Exception {
        //arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        tcr.registerTeacherCategory("Professor Coordenador");
        //act
        boolean result = tcr.isTeacherCategoryRegistered(tc1);
        //assert
        assertFalse(result);

    }

    @Test
    void testTeacherCategoryRepositoryCreationValid() {
        //arrange
        //act
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        //assert
        assertNotNull(tcr);

    }

    @Test
    void shouldReturnTrueIIfTeacherCategoryIsRegistered() throws Exception {
        //arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        //act
        boolean result = tcr.registerTeacherCategory("Professor Adjunto");
        //assert
        assertTrue(result);

    }

    @Test
    void shouldReturnFalseIfTeacherCategoryIsNotRegistered() throws Exception {
        //arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        tcr.registerTeacherCategory("Professor Adjunto");
        //act
        boolean result = tcr.registerTeacherCategory("Professor Adjunto");
        //assert
        assertFalse(result);
    }

    //Testing that the list is not retrieved if empty
    @Test
    void shouldReturnExceptionIfCategoryListIsEmpty() throws IllegalStateException {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        // Act + Assert
        assertThrows(IllegalStateException.class, () -> tcr.getTeacherCategoriesList());
    }

    //Testing that the retrieved list has registered objects
    @Test
    void shouldReturnCategoryListWithRegisteredCategories() throws Exception {
        // Arrange
        TeacherCategoryRepository tcr = new TeacherCategoryRepository();
        tcr.registerTeacherCategory("Assistant Professor");
        tcr.registerTeacherCategory("Coordinating Professor");
        // Act
        List<TeacherCategory> result = tcr.getTeacherCategoriesList();
        // Assert
        assertEquals(2, result.size());
    }
}