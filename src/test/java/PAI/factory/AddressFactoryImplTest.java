package PAI.factory;

import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

public class AddressFactoryImplTest {

    @Test
    void shouldCreateAddress()  {
        //arrange
        AddressFactory addressFactory = new AddressFactoryImpl();

        String street = "Praceta do Sol, nº19";
        String postalCode = "3745-144";
        String location = "Tomar";
        String country = "Portugal";

        try (MockedConstruction<Address> addressDouble = mockConstruction(
                Address.class, (mock, context) -> {

                })) {

            //act
            Address address = addressFactory.createAddress(street,postalCode,location,country);

            //assert
            List<Address> constructed = addressDouble.constructed();
            Address created = (constructed).get(0);
            assertEquals(created, address);

        }
    }

    public static Stream<Arguments> provideInvalidStreet() {
        return Stream.of(
                arguments((String) null),
                arguments(""),
                arguments(" ")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidStreet")
    void invalidStreetDoesNotCreateObject(String street) {
        //arrange
        AddressFactory addressFactory = new AddressFactoryImpl();

        String postalCode = "3745-144";
        String location = "Tomar";
        String country = "Portugal";

        try (
                MockedConstruction<Address> addressDouble = mockConstruction(Address.class, (mock, context) -> {
                    throw new IllegalArgumentException("Street cannot be empty!");
                })) {
            //Act
            try {
                addressFactory.createAddress(street,postalCode,location,country);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Street cannot be empty!"));
            }
        }
    }

    public static Stream<Arguments> provideInvalidPostalCode() {
        return Stream.of(
                arguments((String) null),
                arguments(""),
                arguments(" ")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPostalCode")
    void invalidPostalCodeDoesNotCreateObject(String postalCode) {
        //arrange
        AddressFactory addressFactory = new AddressFactoryImpl();

        String street = "Praceta do Sol, nº19";
        String location = "Tomar";
        String country = "Portugal";

        try (
                MockedConstruction<Address> addressDouble = mockConstruction(Address.class, (mock, context) -> {
                    throw new IllegalArgumentException("Postal code cannot be empty!");
                })) {
            //Act
            try {
                addressFactory.createAddress(street,postalCode,location,country);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Postal code cannot be empty!"));
            }
        }
    }

    public static Stream<Arguments> provideInvalidLocation() {
        return Stream.of(
                arguments((String) null),
                arguments(""),
                arguments(" ")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLocation")
    void invalidLocationDoesNotCreateObject(String location) {
        //arrange
        AddressFactory addressFactory = new AddressFactoryImpl();

        String street = "Praceta do Sol, nº19";
        String postalCode = "3745-144";
        String country = "Portugal";

        try (
                MockedConstruction<Address> addressDouble = mockConstruction(Address.class, (mock, context) -> {
                    throw new IllegalArgumentException("Location cannot be empty!");
                })) {
            //Act
            try {
                addressFactory.createAddress(street,postalCode,location,country);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Location cannot be empty!"));
            }
        }
    }

    public static Stream<Arguments> unmatchedLocation() {
        return Stream.of(
                arguments("Tomar!"),
                arguments("T0mar"),
                arguments(" Tomar"),
                arguments("Tomar "),
                arguments("Tomar1")
        );
    }

    @ParameterizedTest
    @MethodSource("unmatchedLocation")
    void locationDoesNotMatchFormat(String location) {
        //arrange
        AddressFactory addressFactory = new AddressFactoryImpl();

        String street = "Praceta do Sol, nº19";
        String postalCode = "3745-144";
        String country = "Portugal";

        try (
                MockedConstruction<Address> addressDouble = mockConstruction(Address.class, (mock, context) -> {
                    throw new IllegalArgumentException("Write a valid location!");
                })) {
            //Act
            try {
                addressFactory.createAddress(street,postalCode,location,country);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Write a valid location!"));
            }
        }
    }

    public static Stream<Arguments> provideInvalidCountry() {
        return Stream.of(
                arguments((String) null),
                arguments(""),
                arguments(" ")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCountry")
    void invalidCountryDoesNotCreateObject(String country) {
        //arrange
        AddressFactory addressFactory = new AddressFactoryImpl();

        String street = "Praceta do Sol, nº19";
        String postalCode = "3745-144";
        String location = "Tomar";

        try (
                MockedConstruction<Address> addressDouble = mockConstruction(Address.class, (mock, context) -> {
                    throw new IllegalArgumentException("Country cannot be empty!");
                })) {
            //Act
            try {
                addressFactory.createAddress(street,postalCode,location,country);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Country cannot be empty!"));
            }
        }
    }
    public static Stream<Arguments> unmatchedCountry() {
        return Stream.of(
                arguments("Portugal!"),
                arguments("P0rtugal"),
                arguments(" Portugal"),
                arguments("Portugal "),
                arguments("Portugal4")
        );
    }

    @ParameterizedTest
    @MethodSource("unmatchedCountry")
    void countryDoesNotMatchFormat(String country) {
        //arrange
        AddressFactory addressFactory = new AddressFactoryImpl();

        String street = "Praceta do Sol, nº19";
        String postalCode = "3745-144";
        String location = "Tomar";

        try (
                MockedConstruction<Address> addressDouble = mockConstruction(Address.class, (mock, context) -> {
                    throw new IllegalArgumentException("Write a valid country!");
                })) {
            //Act
            try {
                addressFactory.createAddress(street,postalCode,location,country);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Write a valid country!"));
            }
        }
    }

}
