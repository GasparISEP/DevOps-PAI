package PAI.domain;

import PAI.domain.AccessMethodRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodListRepositoryTest {

    @Test
    void testAccessMethodListCreationValid() {
        //arrange
        //act
        AccessMethodRepository acml1 = new AccessMethodRepository ();
        //assert
        assertNotNull(acml1);
    }

    @Test
    void shouldReturnTrueIfAccessMethodIsRegistered() throws Exception {
        //arrange
        AccessMethodRepository acml1 = new AccessMethodRepository ();
        //act
        boolean result = acml1.registerAccessMethod("Maiores 23");
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodIsNotRegistered() throws Exception {
        //arrange
        AccessMethodRepository acml1 = new AccessMethodRepository ();
        acml1.registerAccessMethod("Maiores 23");
        //act
        boolean result = acml1.registerAccessMethod("Maiores 23");
        //assert
        assertFalse(result);
    }
}