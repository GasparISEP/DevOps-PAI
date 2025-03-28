package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessMethodIDTest {

    @Test
    void shouldCreateAccessMethodId(){
        //arrange
        //act
        AccessMethodID accessMethodId = new AccessMethodID();
        //assert
        assertNotNull(accessMethodId);
    }

    @Test
    void shouldReturnTrueIfSameObject(){
        //arrange
        AccessMethodID accessMethodId = new AccessMethodID();
        //act
        boolean result = accessMethodId.equals(accessMethodId);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfDifferentId(){
        //arrange
        AccessMethodID accessMethodId1 = new AccessMethodID();
        AccessMethodID accessMethodId2 = new AccessMethodID();
        //act
        boolean result = accessMethodId1.equals(accessMethodId2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDifferentObject(){
        //arrange
        AccessMethodID accessMethodId = new AccessMethodID();
        String id = "12345";
        //act
        boolean result = accessMethodId.equals(id);
        //assert
        assertFalse(result);
    }


}