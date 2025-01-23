package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodRepositoryTest {


    @Test
    void shouldReturnTrueIfIsAlreadyRegisteredInTheAccessMethodRepository() throws Exception {
        //arrange
        AccessMethodRepository amr = new AccessMethodRepository ();
        AccessMethod am1 = new AccessMethod("Maiores 23");
        amr.registerAccessMethod("Maiores 23");
        //act
        boolean result = amr.isAccessMethodRegistered(am1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodIsNotRegisteredInAccessMethodRepository() throws Exception {
        //arrange
        AccessMethodRepository amr = new AccessMethodRepository ();
        AccessMethod am1 = new AccessMethod("Maiores 23");
        amr.registerAccessMethod("CNA");
        //act
        boolean result = amr.isAccessMethodRegistered(am1);
        //assert
        assertFalse(result);
    }

    @Test
    void testAccessMethodListCreationValid() {
        //arrange
        //act
        AccessMethodRepository acml1 = new AccessMethodRepository();
        //assert
        assertNotNull(acml1);
    }

    @Test
    void shouldReturnTrueIfAccessMethodIsRegistered() throws Exception {
        //arrange
        AccessMethodRepository acml1 = new AccessMethodRepository();
        //act
        boolean result = acml1.registerAccessMethod("Maiores 23");
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodIsNotRegistered() throws Exception {
        //arrange
        AccessMethodRepository acml1 = new AccessMethodRepository();
        acml1.registerAccessMethod("Maiores 23");
        //act
        boolean result = acml1.registerAccessMethod("Maiores 23");
        //assert
        assertFalse(result);
    }


    @Test
    void shouldReturnsOptionalWithAccessMethodIfCanBeCreated(){
        // arrange
        AccessMethodRepository amr1 = new AccessMethodRepository();

        // act
        Optional<AccessMethod> result = amr1.createAccessMethod("Maiores 23");

        // assert
        assertTrue(result.isPresent(), "Expected Optional to be present");
    }

    @Test
    void shouldReturnEmptyOptionalIfAccessMethodAlreadyRegistered(){
        // arrange
        AccessMethodRepository amr1 = new AccessMethodRepository();
        amr1.createAccessMethod("Maiores 23");

        // act
        Optional<AccessMethod> result = amr1.createAccessMethod("Maiores 23");

        // assert
        assertFalse(result.isPresent(), "Expected Optional to be empty");
    }

    @Test
    void shouldReturnEmptyOptionalIfAccessMethodCannotBeCreated(){
        // arrange
        AccessMethodRepository amr1 = new AccessMethodRepository();

        // act
        Optional<AccessMethod> result = amr1.createAccessMethod("");

        // assert
        assertFalse(result.isPresent(), "Expected Optional to be empty");
    }
}
