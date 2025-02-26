package PAI.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentRepositoryTest {

    private static StudentFactory _factoryStudentDouble;
    private static Address _addressDouble;
    private static Student _studentDouble1;
    private static Student _studentDouble2;
    private static Student _studentDouble3;

    @BeforeAll
        //arrange
    static void setup() throws Exception {
        _addressDouble = mock(Address.class);
        _factoryStudentDouble = mock(StudentFactory.class);
        _studentDouble1 = mock(Student.class);
        _studentDouble2 = mock(Student.class);
        _studentDouble3 = mock(Student.class);
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

    static Stream<Arguments> testValidStudents(){
        return Stream.of(
                Arguments.of(12345, "Daniela", "123456789", "911855911", "danijose@gmail.com", _addressDouble, _studentDouble1),
                Arguments.of(67890, "Miguel", "132489912", "912345678", "miguel@gmail.com", _addressDouble, _studentDouble2),
                Arguments.of(11223, "Paula", "456789123", "910000000", "paula@gmail.com", _addressDouble, _studentDouble3)
        );
    }
    @ParameterizedTest
    @MethodSource()
    void testValidStudents(int uniqueNumber, String name, String NIF, String phone, String email, Address address, Student studentDouble) throws Exception {
        // Arrange
        StudentRepository studentRepository = new StudentRepository(_factoryStudentDouble);
        when(_factoryStudentDouble.newStudent(uniqueNumber, name, NIF, phone, email, address)).thenReturn(studentDouble);

        // Act
        boolean result = studentRepository.registerStudent(uniqueNumber, name, NIF, phone, email, address);

        // Assert
        assertTrue(result);
    }

/*

    //US17
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

    //US17
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






