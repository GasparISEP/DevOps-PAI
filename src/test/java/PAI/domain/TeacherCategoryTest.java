package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryTest {

    @Test
    void shouldCreateTeacherCategory() throws Exception {
        //arrange
        //act
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        //assert
        assertNotNull(tc1);
    }

    @Test 
    void nullNameGeneratesExeception(){
        assertThrows(Exception.class, () -> new TeacherCategory(null));
    }

    @Test 
    void emptyNameGeneratesException(){
        assertThrows(Exception.class, () -> new TeacherCategory(""));
    }


    @Test
    void shouldReturnTrueIfTeacherCategoryEqualsObject()throws Exception {
        //arrange
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Object tc2 = tc1;
        //act
        boolean result = tc1.equals(tc2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectIsNotTeacherCategory() throws Exception{
        //arrange
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        AccessMethod am1 = new AccessMethod("M23");
        //act
        boolean result = tc1.equals(am1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfNameOfBothObjectsAreEqual() throws Exception{
        //arrange
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory tc2 = new TeacherCategory("Professor Adjunto");

        //act
        boolean result = tc1.equals(tc2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameOfBothObjectsAreNotEqual() throws Exception{
        //arrange
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory tc2 = new TeacherCategory("Professor Coordenador");

        //act
        boolean result = tc1.equals(tc2);
        //assert
        assertFalse(result);
    }
}