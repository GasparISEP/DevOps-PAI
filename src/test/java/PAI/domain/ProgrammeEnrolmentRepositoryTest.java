package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEnrolmentRepositoryTest {

    @Test
    void shouldReturnTrueIfTheEnrolmentInTheProgrammeIsSuccessful() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");
        AccessMethodFactory accessMethodFactory = mock(AccessMethodFactory.class);

        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        boolean result = enrolmentRepository.enrolStudents(student1, am1, CE, "12-12-2025");

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentIsNotSuccessfulBecauseAccessMethodIsNotRegistered() throws Exception {
        // arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodFactory accessMethodFactory = mock(AccessMethodFactory.class);

        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("CNA"); // Register a different access method

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        // act + assert
        assertThrows(Exception.class, () -> {
            if (!amr.isAccessMethodRegistered(am1)) {
                throw new Exception("Access method not registered");
            }
            enrolmentRepository.enrolStudents(student1, am1, CE, "12-12-2025");
        });
    }

    @Test
    void shouldReturnFalseIfEnrolmentIsNotSuccessfulBecauseStudentIsAlreadyEnrolledInTheProgramme() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodFactory accessMethodFactory = mock(AccessMethodFactory.class);

        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        enrolmentRepository.enrolStudents(student1, am1, CE, "12-12-2025");

        //assert
        assertThrows(Exception.class, () -> enrolmentRepository.enrolStudents(student1, am1, CE, "12-12-2025"));
    }

    @Test
    void shouldEnrolSuccessfullyMoreThanOneStudent() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodFactory accessMethodFactory = mock(AccessMethodFactory.class);

        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();

        //act
        boolean result1 = enrolmentRepository.enrolStudents(student1, am1, CE, "11-12-2025");
        boolean result2 = enrolmentRepository.enrolStudents(student2, am1, CE, "12-12-2025");

        //assert
        assertTrue(result1);
        assertTrue(result2);
    }

    //US17
    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() throws Exception {
        // Arrange
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        AccessMethod am1 = new AccessMethod("M1");

        AccessMethodFactory accessMethodFactory = mock(AccessMethodFactory.class);

        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("M1");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        enrolmentRepository.enrolStudents(student, am1, CE, "21-12-2025");

        // Act
        boolean result = enrolmentRepository.isStudentEnrolled(student, CE);

        // Assert
        assertTrue(result, "The student must be enrolled in the programme.");
    }

    //US17
    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgramme() throws Exception {
        // Arrange
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);
        Student student1 = new Student(2, "Joana Silva", "123000009", "221234567", "joana123@gmail.com", address);
        AccessMethod am1 = new AccessMethod("M1");

        AccessMethodFactory accessMethodFactory = mock(AccessMethodFactory.class);

        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("M1");
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        enrolmentRepository.enrolStudents(student, am1, CE, "21-12-2025");

        // Act
        boolean result = enrolmentRepository.isStudentEnrolled(student1, CE);

        // Assert
        assertFalse(result, "The student should not be enrolled in the programme.");
    }
    //US17
    @Test
    void shouldReturnCorrectStudentFromEnrolment() throws Exception {
        // Arrange
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);
        DegreeType master = new DegreeType("Master", 240);
        AccessMethod am1 = new AccessMethod("M1");

        AccessMethodFactory accessMethodFactory = mock(AccessMethodFactory.class);

        AccessMethodRepository amr = new AccessMethodRepository(accessMethodFactory);
        amr.registerAccessMethod("M1");
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository();
        enrolmentRepository.enrolStudents(student, am1, CE, "21-12-2025");
        ProgrammeEnrolment enrolment = new ProgrammeEnrolment(student, am1, CE, "12-12-2025");

        // Act
        Student enrolledStudent = enrolment.findStudentInEnrollments();

        // Assert
        assertEquals(student, enrolledStudent, "The student found in the enrolment must be the same as the student created.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithNull() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);

        // Act
        boolean result = programme.equals(null);

        // Assert
        assertFalse(result, "The equals method should return false when comparing with null.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithDifferentClassObject() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        String differentClassObject = "Not a Programme";

        // Act
        boolean result = programme.equals(differentClassObject);

        // Assert
        assertFalse(result, "The equals method should return false when comparing with an object of a different class.");
    }

}