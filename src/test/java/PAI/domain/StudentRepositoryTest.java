package PAI.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentRepositoryTest {

    @Nested
    class TestsWithIsolation {
        private StudentFactory _studentFactoryDouble;
        private StudentListFactory _studentListFactoryDouble;
        private Address _addressDouble;
        private Student _studentDouble1;
        private Student _studentDouble2;

        @BeforeEach
            //arrange
        void setup() {
            _studentFactoryDouble = mock(StudentFactory.class);
            _studentListFactoryDouble = mock(StudentListFactory.class);

            _addressDouble = mock(Address.class);
            _studentDouble1 = mock(Student.class);
            _studentDouble2 = mock(Student.class);

            ArrayList<Student> studentListDouble = new ArrayList<>();
            when(_studentListFactoryDouble.newArrayList()).thenReturn(studentListDouble);
        }

        @Test
        void shouldCreateStudentRepository() throws Exception {

            //act
            new StudentRepository(_studentFactoryDouble, _studentListFactoryDouble);
        }

        @Test
        void shouldCreateStudentRepositoryDefault() throws Exception {

            //act
            new StudentRepository();
        }

        @Test
        void testRegisterDuplicateNIFThrowsException() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryDouble, _studentListFactoryDouble);

            when(_studentFactoryDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryDouble.newStudent(67890, "Miguel", "123456789", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_studentDouble1.hasSameNIF(_studentDouble2)).thenReturn(true);

            studentRepository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            // Act & Assert
            assertThrows(Exception.class, () -> {
                studentRepository.registerStudent(67890, "Miguel", "123456789", "912345678", "miguel@gmail.com", _addressDouble);
            });
        }

        @Test
        void testRegisterDuplicateUniqueNumberThrowsException() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryDouble, _studentListFactoryDouble);
            when(_studentFactoryDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryDouble.newStudent(12345, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_studentDouble1.hasSameUniqueNumber(_studentDouble2)).thenReturn(true);

            studentRepository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            // Act & Assert
            assertThrows(Exception.class, () -> {
                studentRepository.registerStudent(12345, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);
            });
        }

        @Test
        void shouldReturnTrueWhenValidStudentsAreRegistered() throws Exception {
            // Arrange
            StudentRepository studentRepository = new StudentRepository(_studentFactoryDouble, _studentListFactoryDouble);
            when(_studentFactoryDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryDouble.newStudent(67890, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
            when(_studentDouble2.hasSameUniqueNumber(_studentDouble1) && _studentDouble2.hasSameNIF(_studentDouble1)).thenReturn(false);

            studentRepository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);

            // Act
            boolean result = studentRepository.registerStudent(67890, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

            // Assert
            assertTrue(result);
        }

        @Test
        void shouldReturnOptionalWithStudentIfStudentWithSpecificNIFIsFound() throws Exception {
            // Arrange
            int uniqueNumberToBeFound = 67890;
            StudentRepository studentRepository = new StudentRepository(_studentFactoryDouble, _studentListFactoryDouble);
            when(_studentFactoryDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryDouble.newStudent(uniqueNumberToBeFound, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);

            studentRepository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);
            studentRepository.registerStudent(uniqueNumberToBeFound, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

            when(_studentDouble1.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(false);
            when(_studentDouble2.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(true);

            // Act
            Optional<Student> studentFound = studentRepository.getStudentByUniqueNumber(uniqueNumberToBeFound);

            // Assert
            assertEquals(studentFound.get(), _studentDouble2);
        }

        @Test
        void shouldReturnOptionalWithoutStudentIfStudentWithSpecificNIFIsNotFound() throws Exception {
            // Arrange
            int uniqueNumberToBeFound = 12345;
            StudentRepository studentRepository = new StudentRepository(_studentFactoryDouble, _studentListFactoryDouble);
            when(_studentFactoryDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
            when(_studentFactoryDouble.newStudent(67890, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);

            studentRepository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);
            studentRepository.registerStudent(67890, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

            when(_studentDouble1.hasThisUniqueNumber(uniqueNumberToBeFound) || _studentDouble2.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(false);

            // Act
            Optional<Student> studentNotFound = studentRepository.getStudentByUniqueNumber(uniqueNumberToBeFound);

            // Assert
            assertTrue(studentNotFound.isEmpty());
        }
    }
    //
    //

    @Nested
    class TestsWithoutIsolation {

        private StudentFactory _studentFactory;
        private StudentListFactory _studentListFactory;
        private Address _address1;
        private Address _address2;

        @BeforeEach
        //arrange
        void setup() throws Exception{
            _studentFactory = new StudentFactory();
            _studentListFactory = new StudentListFactory();
            _address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
            _address2 = new Address("Rua das Flores, nº7", "3000-200", "Coimbra", "Portugal");
        }

        @Test
        void shouldCreateRepositoryWithValidInputs() {
            //arrange

            //act
            new StudentRepository(_studentFactory, _studentListFactory);
        }

        @Test
        void shouldThrowExceptionWhenStudentWithDuplicateNIFIsRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactory, _studentListFactory);

            // Act
            repository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);

            // Assert
            assertThrows(Exception.class, () -> {
                repository.registerStudent(67890, "Miguel", "123456789", "912345678", "miguel@gmail.com", _address2);
            });
        }

        @Test
        void shouldThrowExceptionWhenStudentWithDuplicateUniqueNumberIsRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactory, _studentListFactory);

            // Act
            repository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);

            // Assert
            assertThrows(Exception.class, () -> {
                repository.registerStudent(12345, "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);
            });
        }

        @Test
        void shouldReturnTrueWhenStudentsWithValidAttributesAreRegistered() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactory, _studentListFactory);

            // Act
            boolean result1 = repository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            boolean result2 = repository.registerStudent(67890, "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);
            boolean result3 = repository.registerStudent(11223, "Paula", "456789123", "910000000", "paula@gmail.com", _address1);

            // Assert
            assertTrue(result1 && result2 && result3);
        }

        @Test
        void shouldReturnEmptyOptionalIfUniqueNumberDoesntExistInTheRepository() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactory, _studentListFactory);
            repository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            repository.registerStudent(67890, "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);

            // Act
            Optional<Student> studentFromList = repository.getStudentByUniqueNumber(67890);

            // Assert
            assertTrue(studentFromList.isPresent());
        }

        @Test
        void shouldReturnStudentOptionalIfUniqueNumberExistsInTheRepository() throws Exception {
            // Arrange
            StudentRepository repository = new StudentRepository(_studentFactory, _studentListFactory);
            repository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _address1);
            repository.registerStudent(67890, "Miguel", "987654321", "912345678", "miguel@gmail.com", _address2);

            // Act
            Optional<Student> studentFromList = repository.getStudentByUniqueNumber(55555);

            // Assert
            assertTrue(studentFromList.isEmpty());
        }
    }
}