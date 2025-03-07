package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodTest {

    @Test
    void ValidNameAccessMethodCreatesAccessMethod() throws Exception {
        //arrange

        //act
        AccessMethod am1 = new AccessMethod("Maiores 23");

        //assert
        assertNotNull(am1);
    }

    @Test
    void NullNameAccessMethodDoesNotCreateAccessMethod() throws Exception {
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new AccessMethod(null));
    }

    @Test
    void EmptyNameAccessMethodDoesNotCreateAccessMethod() throws Exception {
        // arrange
        // act
        // assert
        assertThrows(Exception.class, () -> new AccessMethod(""));
    }

    @Test
    void BlankNameAccessMethodDoesNotCreateAccessMethod() throws Exception {
        // arrange
        // act
        // assert
        assertThrows(Exception.class, () -> new AccessMethod("   "));
    }

    @Test
    void shouldReturnTrueIfAccessMethodEqualsObject()throws Exception {
        //arrange
        AccessMethod am1 = new AccessMethod("M23");
        Object am2 = am1;
        //act
        boolean result = am1.equals(am2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectIsNotAccessMethod() throws Exception{
        //arrange
        AccessMethod am1 = new AccessMethod("M23");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        //act
        boolean result = am1.equals(tc1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfNameOfBothObjectsAreEqual() throws Exception{
        //arrange
        AccessMethod am1 = new AccessMethod("M23");
        AccessMethod am2 = new AccessMethod("M23");

        //act
        boolean result = am1.equals(am2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameOfBothObjectsAreNotEqual() throws Exception{
        //arrange
        AccessMethod am1 = new AccessMethod("M23");
        AccessMethod am2 = new AccessMethod("CNA");

        //act
        boolean result = am1.equals(am2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfNameIsAnAccessMethod() throws Exception {
        // Arrange
        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        // Act
        boolean result = am1.hasThisAccessMethodName("Concurso Nacional");

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameIsAnAccessMethod() throws Exception {
        // Arrange
        AccessMethod am1 = new AccessMethod("Concurso Nacional");

        // Act
        boolean result = am1.hasThisAccessMethodName("Maiores 23");

        // Assert
        assertFalse(result);
    }
}