package PAI.factory;

import PAI.domain.Address;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressFactoryTest {

    @Test
    void shouldCreateAddress()  {
        //arrange
        String street = "Praceta do Sol, nº19";
        String postalCode = "3745-144";
        String location = "Tomar";
        String country = "Portugal";

        try (MockedConstruction<Address> addressDouble = mockConstruction(
                Address.class, (mock, context) -> {
                    String actualStreet = (String) context.arguments().get(0);
                    String actualPostalCode = (String) context.arguments().get(1);
                    String actualLocation = (String) context.arguments().get(2);
                    String actualCountry = (String) context.arguments().get(3);
                    when(mock.getStreet()).thenReturn(actualStreet);
                    when(mock.getPostalCode()).thenReturn(actualPostalCode);
                    when(mock.getLocation()).thenReturn(actualLocation);
                    when(mock.getCountry()).thenReturn(actualCountry);
                })) {

            AddressFactory addressFactory = new AddressFactory();

            //act
            Address address = addressFactory.createAddress(street,postalCode,location,country);

            //assert
            List<Address> addresses = addressDouble.constructed();
            assertEquals(1, addresses.size());

            assertEquals(street, address.getStreet());
            assertEquals(postalCode, address.getPostalCode());
            assertEquals(location, address.getLocation());
            assertEquals(country, address.getCountry());
        }
    }






}
