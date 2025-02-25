package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodFactoryTest {

    @Test
    void shouldCreateAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        //act
        AccessMethod accessMethod = accessMethodFactory.createAccessMethod("Maiores 23");
        //assert
        assertNotNull(accessMethod);
    }

    @Test
    void shouldNotCreateAccessMethod() throws InstantiationException {
        //arrange
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> accessMethodFactory.createAccessMethod(""));
    }

}