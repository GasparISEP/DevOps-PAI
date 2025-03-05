package PAI.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodListFactoryTest {

    @Test
    void shouldCreateArrayList() {
        //arrange
        AccessMethodListFactory accessMethodListFactory = new AccessMethodListFactory();
        //act + assert
        assertNotNull(accessMethodListFactory);
    }
}