package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void shouldCreatPhoneNumber(){
        //act
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");

        //assert
        assertNotNull(phoneNumber);
    }
}