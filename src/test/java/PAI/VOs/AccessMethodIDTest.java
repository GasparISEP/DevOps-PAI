package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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

    @Test
    void shouldReturnUUIDtoString(){
        //arrange
        AccessMethodID accessMethodID = new AccessMethodID();
        //act
        String id = accessMethodID.toString();
        //assert
        assertEquals(id, accessMethodID.toString());
    }

    @Test
    void shouldCreateAccessMethodIDWithUUIDinput(){
        // arrange
        UUID uuid = mock(UUID.class);
        // act
        AccessMethodID accessMethodID = new AccessMethodID(uuid);
        // assert
        assertNotNull(accessMethodID);
    }

    @Test
    void shouldGetUUID(){
        // arrange
        UUID uuid = mock(UUID.class);
        AccessMethodID accessMethodID = new AccessMethodID(uuid);
        // act
        UUID result = accessMethodID.getAccessMethodID();
        // assert
        assertEquals(uuid, result);
    }

}