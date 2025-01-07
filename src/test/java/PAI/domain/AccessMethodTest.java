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
}