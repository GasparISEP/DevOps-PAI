package PAI.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @Test
    void validAttributesCreateObject() throws Exception {

        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");

        //act
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
    }

    @Test
    void testRegisterDuplicateNIFThrowsException() throws Exception {
        // Arrange
        StudentRepository repository = new StudentRepository();
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Address address2 = new Address("Rua das Flores, nº7", "3000-200", "Coimbra", "Portugal");

        // Act
        repository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);

        // Assert
        assertThrows(Exception.class, () -> {
            repository.registerStudent(67890, "Miguel", "123456789", "912345678", "miguel@gmail.com", address2);
        });
    }

    @Test
    void testRegisterDuplicateUniqueNumberThrowsException() throws Exception {
        // Arrange
        StudentRepository repository = new StudentRepository();
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Address address2 = new Address("Rua das Flores, nº7", "3000-200", "Coimbra", "Portugal");

        // Act
        repository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);

        // Assert
        assertThrows(Exception.class, () -> {
            repository.registerStudent(12345, "Miguel", "987654321", "912345678", "miguel@gmail.com", address2);
        });
    }

    @Test
    void testAddThreeValidStudents() throws Exception {
        // Arrange
        StudentRepository repository = new StudentRepository();
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Address address2 = new Address("Rua das Flores, nº7", "3000-200", "Coimbra", "Portugal");
        Address address3 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");

        // Act
        boolean result1 = repository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);
        boolean result2 = repository.registerStudent(67890, "Miguel", "987654321", "912345678", "miguel@gmail.com", address2);
        boolean result3 = repository.registerStudent(11223, "Paula", "456789123", "910000000", "paula@gmail.com", address3);

        // Assert
        assertTrue(result1 && result2 && result3);
    }

}