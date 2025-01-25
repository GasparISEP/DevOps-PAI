package PAI.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void validAttributesCreateAnObject() throws IllegalArgumentException {
        //arrange

        //act
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
    }

    @Test
    void emptyStreetDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("", "3745-144", "Tomar", "Portugal"));
    }

    @Test
    void blankStreetDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address(" ", "4581-236", "Coimbra", "Portugal"));
    }

    @Test
    void nullStreetDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address(null, "4259-365", "Loulé", "Portugal"));
    }

    @Test
    void emptyPostcodeDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Praceta do Sol, nº19", "", "Tomar", "Portugal"));
    }

    @Test
    void blankPostcodeDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Avenida São Gonçalo, nº45", " ", "Coimbra", "Portugal"));
    }

    @Test
    void nullPostcodeDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Praça do freixal, nº12, 4º, Drt", null, "Loulé", "Portugal"));
    }

    @Test
    void emptyLocationDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Praceta do Sol, nº19", "3745-144", "", "Portugal"));
    }

    @Test
    void blankLocationDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Avenida São Gonçalo, nº45", "4581-236", " ", "Portugal"));
    }

    @Test
    void nullLocationDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Praça do freixal, nº12, 4º, Drt", "4259-365", null, "Portugal"));
    }

    @Test
    void emptyCountryDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Praceta do Sol, nº19", "3745-144", "Tomar", ""));
    }

    @Test
    void blankCountryDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Avenida São Gonçalo, nº45", "4581-236", "Coimbra", " "));
    }

    @Test
    void nullCountryDoesNotCreateAnObject() {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Address("Praça do freixal, nº12, 4º, Drt", "4259-365", "Loulé", null));
    }
}