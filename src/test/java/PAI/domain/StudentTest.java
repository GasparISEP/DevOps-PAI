package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldReturnTrueIfStudentIsRepeatedWithDuplicateUniqueNumber() throws Exception {
        // Arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");

        Student student1 = new Student(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);
        Student duplicateByNumber = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        List<Student> existingStudents = List.of(student1);

        // Act
        boolean result = duplicateByNumber.isStudentRepeated(existingStudents);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfStudentIsRepeatedWithDuplicateNIF() throws Exception {
        // Arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Address address2 = new Address("Rua das Flores, nº7", "3000-200", "Coimbra", "Portugal");

        Student student1 = new Student(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);
        Student duplicateByNIF = new Student(67890, "Miguel", "123456789", "912345678", "miguel@gmail.com", address2);

        List<Student> existingStudents = List.of(student1);

        // Act
        boolean result = duplicateByNIF.isStudentRepeated(existingStudents);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotRepeatedWithDuplicateUniqueNumber() throws Exception {
        // Arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Address address2 = new Address("Rua das Flores, nº7", "3000-200", "Coimbra", "Portugal");

        Student student1 = new Student(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);
        Student uniqueStudent = new Student(54321, "João", "112233445", "919999999", "joao@gmail.com", address2);

        List<Student> existingStudents = List.of(student1);

        // Act
        boolean result = uniqueStudent.isStudentRepeated(existingStudents);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameUniqueNumber() throws Exception {
        // Arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);


        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(1, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        // Act
        boolean result = student1.hasSameUniqueNumber(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameUniqueNumber() throws Exception {
        // Arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);


        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        // Act
        boolean result = student1.hasSameUniqueNumber(student2);

        // Assert
        assertFalse(result);
    }

}