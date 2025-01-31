package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

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

    //US17
    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("M1");
        DegreeType degreeType = new DegreeType("Master", 30);
        Department department = new Department("DCE", "Department of Computer Engineering");
        Teacher teacher = new Teacher("JOD", "Doe", "jod@university.com", "123456789", "R101", "PhD", "Street", "1234-665", "City", "Country", "12-01-2023",
                new TeacherCategory("Professor"), 100, department);
        CourseRepository courseRepository = new CourseRepository();
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, degreeType, department, teacher);
        programme.enrolStudentInProgramme(student, am1, amr);
        StudentRepository repository = new StudentRepository();

        // Act
        boolean result = repository.isStudentEnrolledInProgramme(student, programme);

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
        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("M1");
        DegreeType degreeType = new DegreeType("Master", 30);
        Department department = new Department("DCE", "Department of Computer Engineering");
        Teacher teacher = new Teacher("JOD", "Doe", "jod@university.com", "123456789", "R101", "PhD", "Street", "1234-665", "City", "Country", "12-01-2023",
                new TeacherCategory("Professor"), 100, department);
        CourseRepository courseRepository = new CourseRepository();
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, degreeType, department, teacher);
        StudentRepository repository = new StudentRepository();

        // Act
        boolean result = repository.isStudentEnrolledInProgramme(student, programme);

        // Assert
        assertFalse(result, "The student should not be enrolled in the programme.");
    }


    @Test
    void testFindStudentByUniqueNumber_WhenStudentExists() throws Exception {
        // Arrange
        StudentRepository studentRepository = new StudentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1001, "João Silva", "123456789", "912345678", "joao@email.com", add1);
        studentRepository.registerStudent(1001, "João Silva", "123456789", "912345678", "joao@email.com", add1);

        // Act
        Optional<Student> foundStudent = studentRepository.findStudentByUniqueNumber(1001);

        // Assert
        assertTrue(foundStudent.isPresent(), "Student is present");
        assertEquals(student.getUniqueNumber(), foundStudent.get().getUniqueNumber(), "Student unique number is the same");
    }

    @Test
    void testFindStudentByUniqueNumber_WhenStudentDoesNotExist() {
        //arrange
        StudentRepository studentRepository = new StudentRepository();
        // Act
        Optional<Student> foundStudent = studentRepository.findStudentByUniqueNumber(9999);

        // Assert
        assertFalse(foundStudent.isPresent(), "Student not found.");
    }


}



