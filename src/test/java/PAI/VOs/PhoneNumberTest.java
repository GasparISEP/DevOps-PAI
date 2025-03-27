package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void shouldCreatPhoneNumber() throws Exception{
        //act
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");

        //assert
        assertNotNull(phoneNumber);
    }

    @Test
    void nullCountryCodeThrowsException(){
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber(null, "999999999"));
    }

    @Test
    void emptyCountryCodeThrowsException(){
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("", "999999999"));
    }
}