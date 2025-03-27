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

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber(null, "999999999"));
    }

    @Test
    void emptyCountryCodeThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("", "999999999"));
    }

    @Test
    void nullNumberThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("+351", null));
    }

    @Test
    void emptyNumberThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("+351", ""));
    }

    @Test
    void invalidCountryCodeThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("000", ""));
    }
}