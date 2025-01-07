package PAI.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentTest {

    @Test
    void validAttributesCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
    }

    @Test
    void zeroAsUniqueNumberDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(0, "Rita", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void negativeUniqueNumberDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(-1, "Rita", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void emptyNameDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(1, "", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void blankNameDoesNotCreateAnObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(1, " ", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void nullNameDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(1, null, "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void emptyNIFDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", "", "933741758", "joaquim@gmail.com", address1));
    }

    @Test
    void blankNIFDoesNotCreateAnObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", " ", "933741758", "joaquim@gmail.com", address1));
    }

    @Test
    void nullNIFDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", null, "933741758", "joaquim@gmail.com", address1));
    }

    @Test
    void emptyPhoneDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(3, "Rute", "569874126", "", "rute@gmail.com", address1));
    }

    @Test
    void blankPhoneDoesNotCreateAnObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", "569874126", " ", "joaquim@gmail.com", address1));
    }

    @Test
    void nullPhoneDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(3, "Rute", "32165498", null, "rute@gmail.com", address1));
    }

    @Test
    void emptyEmailDoesNotCreateObject () throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(4, "Pedro", "159753824", "963996987", "", address1));
    }

    @Test
    void blankEmailDoesNotCreateAnObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", "569874126", "963996987", " ", address1));
    }

    @Test
    void nullEmailDoesNotCreateObject () throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act + assert
        assertThrows(Exception.class, () -> new Student(4, "Pedro", "159753824", "963996987", null, address1));
    }

}