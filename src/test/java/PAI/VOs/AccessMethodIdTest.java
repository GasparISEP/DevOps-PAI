package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodIdTest {

    @Test
    void shouldCreateAccessMethodId(){
        //arrange
        //act
        AccessMethodId accessMethodId = new AccessMethodId();
        //assert
        assertNotNull(accessMethodId);
    }


}