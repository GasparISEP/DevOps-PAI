package PAI.domain;

import org.junit.jupiter.api.Test;

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
}