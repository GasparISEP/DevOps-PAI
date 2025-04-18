package PAI.mapper;

import PAI.VOs.PhoneNumber;
import PAI.persistence.datamodel.PhoneNumberDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PhoneNumberMapperTest {

    @Test
    void shouldCreatePhoneNumberDataModelFromDomain() {
        //arrange
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        PhoneNumberMapper mapper = new PhoneNumberMapper();

        when(phoneNumber.getCountryCode()).thenReturn("+351");
        when(phoneNumber.getNumber()).thenReturn("912345678");

        //act
        PhoneNumberDataModel phoneNumberDataModel = mapper.domainToDataModel(phoneNumber);

        //assert
        assertEquals("+351", phoneNumberDataModel.getCountryCode());
        assertEquals("912345678", phoneNumberDataModel.getNumber());
    }

    @Test
    void shouldCreatePhoneNumberVOFromDataModel() throws Exception {
        //arrange
        PhoneNumberDataModel phoneNumberDataModel = mock(PhoneNumberDataModel.class);
        PhoneNumberMapper mapper = new PhoneNumberMapper();

        when(phoneNumberDataModel.getCountryCode()).thenReturn("+351");
        when(phoneNumberDataModel.getNumber()).thenReturn("912345678");

        //act
        PhoneNumber phoneNumber = mapper.dataModelToDomain(phoneNumberDataModel);

        //assert
        assertEquals("+351", phoneNumber.getCountryCode());
        assertEquals("912345678", phoneNumber.getNumber());
    }

}