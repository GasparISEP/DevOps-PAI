package PAI.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentTest {

    @Test
    void validAttributesCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
    }

    @Test
    void zeroAsUniqueNumberDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(0, "Rita", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void negativeUniqueNumberDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(-1, "Rita", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void emptyNameDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(1, "", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void blankNameDoesNotCreateAnObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(1, " ", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void nullNameDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(1, null, "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void emptyNIFDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", "", "933741758", "joaquim@gmail.com", address1));
    }

    @Test
    void blankNIFDoesNotCreateAnObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", " ", "933741758", "joaquim@gmail.com", address1));
    }

    @Test
    void nullNIFDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", null, "933741758", "joaquim@gmail.com", address1));
    }

    @Test
    void emptyPhoneDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(3, "Rute", "569874126", "", "rute@gmail.com", address1));
    }

    @Test
    void blankPhoneDoesNotCreateAnObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", "569874126", " ", "joaquim@gmail.com", address1));
    }

    @Test
    void nullPhoneDoesNotCreateObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(3, "Rute", "32165498", null, "rute@gmail.com", address1));
    }

    @Test
    void emptyEmailDoesNotCreateObject () throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(4, "Pedro", "159753824", "963996987", "", address1));
    }

    @Test
    void blankEmailDoesNotCreateAnObject() throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(2, "Joaquim", "569874126", "963996987", " ", address1));
    }

    @Test
    void nullEmailDoesNotCreateObject () throws Exception {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(4, "Pedro", "159753824", "963996987", null, address1));
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameUniqueNumber() throws Exception {
        // Arrange
        Address address1 = mock(Address.class);
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);


        Address address2 = mock(Address.class);
        Student student2 = new Student(1, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        // Act
        boolean result = student1.hasSameUniqueNumber(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameUniqueNumber() throws Exception {
        // Arrange
        Address address1 = mock(Address.class);
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);


        Address address2 = mock(Address.class);
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        // Act
        boolean result = student1.hasSameUniqueNumber(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameNIF() throws Exception {
        // Arrange
        Address address1 = mock(Address.class);
        Address address2 = mock(Address.class);

        Student student1 = new Student(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);
        Student student2 = new Student(12346, "Daniela", "123456789", "911855911", "danijose@gmail.com", address2);

        // Act
        boolean result = student1.hasSameNIF(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameNIF() throws Exception {
        // Arrange
        Address address1 = mock(Address.class);
        Address address2 = mock(Address.class);

        Student student1 = new Student(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);
        Student student2 = new Student(54321, "João", "112233445", "919999999", "joao@gmail.com", address2);

        // Act
        boolean result = student1.hasSameNIF(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTheUniqueNumber() throws Exception {
        //assert
        Address address1 = mock(Address.class);
        Student student1 = new Student(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);

        //act
        int result = student1.getUniqueNumber();

        //assert
        assertEquals(result, 12345);
    }


    @Test
    void shouldReturnTrueIfTheUniqueNumberIsFoundInAStudent() throws Exception {
        // Arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        // Act
        boolean result = student1.hasThisUniqueNumber(1);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTheUniqueNumberIsFoundInAStudent() throws Exception {
        // Arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        // Act
        boolean result = student1.hasThisUniqueNumber(2);

        // Assert
        assertFalse(result);
    }
}