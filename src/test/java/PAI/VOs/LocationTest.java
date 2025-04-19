package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LocationTest {

    @Test

    void shouldCreateValidLocation() {
        //Arrange
        Location l1 = new Location("Vila Nova de Gaia");

        //Act + Assert
        assertNotNull(l1);
    }

    public static Stream<Arguments> shouldCreateInvalidLocationsWhenInvalidCharsAndSpaces() {
        return Stream.of(
                arguments("Tomar!"),
                arguments("T0mar"),
                arguments(" Tomar"),
                arguments("Tomar "),
                arguments("Tomar1")
        );
    }

    @ParameterizedTest
    @MethodSource("shouldCreateInvalidLocationsWhenInvalidCharsAndSpaces")
    void shouldNotCreateLocation(String location) {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new Location(location));
    }

    @Test
    void shouldReturnExceptionWhenLocationIsNull() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new Location(null));
    }

    @Test
    void shouldReturnExceptionWhenLocationIsEmpty() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new Location(""));
    }

    @Test
    void shouldGetLocation() {
        //Arrange
        Location l1 = new Location("Vila Nova de Gaia");
        //Act
        String result = l1.getLocation();
        //Assert
        assertEquals("Vila Nova de Gaia", result);
    }

    @Test
    void getLocationShouldReturnLocationString () {
        // Arrange
        Location location = new Location("Coimbra");

        // Act
        String result = location.getLocation();

        // Assert
        assertEquals("Coimbra", result);
    }
}