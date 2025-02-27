package PAI.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentRepositoryTest {

    private StudentFactory _factoryStudentDouble;
    private Address _addressDouble;
    private Student _studentDouble1;
    private Student _studentDouble2;

    @BeforeEach
        //arrange
    void setup() throws Exception {
        _addressDouble = mock(Address.class);
        _factoryStudentDouble = mock(StudentFactory.class);
        _studentDouble1 = mock(Student.class);
        _studentDouble2 = mock(Student.class);
    }

    @Test
    void shouldCreateStudentRepository() throws Exception {

        //act
        new StudentRepository(_factoryStudentDouble);
    }

    @Test
    void shouldCreateStudentRepositoryDefault() throws Exception {

        //act
        new StudentRepository();
    }

    @Test
    void testRegisterDuplicateNIFThrowsException() throws Exception {
        // Arrange
        StudentRepository studentRepository = new StudentRepository(_factoryStudentDouble);

        when(_factoryStudentDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
        when(_factoryStudentDouble.newStudent(67890, "Miguel", "123456789", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
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
        StudentRepository studentRepository = new StudentRepository(_factoryStudentDouble);
        when(_factoryStudentDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
        when(_factoryStudentDouble.newStudent(12345, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
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
        StudentRepository studentRepository = new StudentRepository(_factoryStudentDouble);
        when(_factoryStudentDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
        when(_factoryStudentDouble.newStudent(67890, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);
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
        StudentRepository studentRepository = new StudentRepository(_factoryStudentDouble);
        when(_factoryStudentDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
        when(_factoryStudentDouble.newStudent(uniqueNumberToBeFound, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);

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
        StudentRepository studentRepository = new StudentRepository(_factoryStudentDouble);
        when(_factoryStudentDouble.newStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble)).thenReturn(_studentDouble1);
        when(_factoryStudentDouble.newStudent(67890, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble)).thenReturn(_studentDouble2);

        studentRepository.registerStudent(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble);
        studentRepository.registerStudent(67890, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble);

        when(_studentDouble1.hasThisUniqueNumber(uniqueNumberToBeFound) || _studentDouble2.hasThisUniqueNumber(uniqueNumberToBeFound)).thenReturn(false);

        // Act
        Optional<Student> studentNotFound = studentRepository.getStudentByUniqueNumber(uniqueNumberToBeFound);

        // Assert
        assertTrue(studentNotFound.isEmpty());
    }

/*


    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("M1");
        DegreeType degreeType = new DegreeType("Master", 30);
        Department department = new Department("DCE", "Department of Computer Engineering");
        Teacher teacher = new Teacher("JOD", "Doe", "jod@university.com", "123456789", "R101", "PhD", "Street", "1234-665", "City", "Country", "12-01-2023",
                new TeacherCategory("Professor"), 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, degreeType, department, teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        enrolmentRepository.enrolStudents(student, am1, programme, "21-12-2025");

        // Act
        boolean result = enrolmentRepository.isStudentEnrolled(student, programme);

        // Assert
        assertTrue(result, "The student must be enrolled in the programme.");
    }


    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgramme() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodFactory accessMethodFactory = new AccessMethodFactory();
        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("M1");
        DegreeType degreeType = new DegreeType("Master", 30);
        Department department = new Department("DCE", "Department of Computer Engineering");
        Teacher teacher = new Teacher("JOD", "Doe", "jod@university.com", "123456789", "R101", "PhD", "Street", "1234-665", "City", "Country", "12-01-2023",
                new TeacherCategory("Professor"), 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, degreeType, department, teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        enrolmentRepository.isStudentEnrolled(student, programme);

        // Act
        boolean result = enrolmentRepository.isStudentEnrolled(student, programme);

        // Assert
        assertFalse(result, "The student should not be enrolled in the programme.");
    }
*/

}






