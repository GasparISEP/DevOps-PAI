package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberDataModelTest {

    @Test
    void emptyConstructorShouldCreatePhoneNumberDataModel() {
        //arrange

        //act
        PhoneNumberDataModel phoneNumberDataModel = new PhoneNumberDataModel();

        //assert
        assertNotNull(phoneNumberDataModel);
    }

    @Test
    void constructorWithParametersShouldCreatePhoneNumberDataModel() {
        //arrange

        //act
        PhoneNumberDataModel phoneNumberDataModel = new PhoneNumberDataModel("+351", "912345678");

        //assert
        assertNotNull(phoneNumberDataModel);
    }

    @Test
    void getCountryCodeShouldReturnCountryCode() {
        //arrange
        PhoneNumberDataModel phoneNumberDataModel = new PhoneNumberDataModel("+351", "912345678");

        //act
        String countryCode = phoneNumberDataModel.getCountryCode();

        //assert
        assertEquals(countryCode, "+351");
    }

    @Test
    void getPhoneNumberShouldReturnNumber() {
        //arrange
        PhoneNumberDataModel phoneNumberDataModel = new PhoneNumberDataModel("+351", "912345678");

        //act
        String countryCode = phoneNumberDataModel.getNumber();

        //assert
        assertEquals(countryCode, "912345678");
    }

}