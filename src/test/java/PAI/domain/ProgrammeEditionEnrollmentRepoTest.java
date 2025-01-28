package PAI.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrollmentRepoTest {

    @Test
    void shouldThrowExceptionWhenStudentIsNull() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo();
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,t1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        LocalDate currentDate = LocalDate.now();

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInProgrammeEdition(null, pe1, currentDate);
        });

        // Assert
        assertEquals("ProgrammeEdition and Student cannot be null.", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIsNull() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo();
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com", new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        LocalDate currentDate = LocalDate.now();

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInProgrammeEdition(st1, null, currentDate);
        });

        // Assert
        assertEquals("ProgrammeEdition and Student cannot be null.", thrown.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenProgrammeEditionEnrollmentAlreadyExists() throws Exception {
        //arrange
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,t1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        LocalDate currentDate = LocalDate.now();

        //act
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInProgrammeEdition(st1, pe1, currentDate);
            repository.enrollStudentInProgrammeEdition(st1, pe1, currentDate);
        });

        //assert
        assertEquals("This programme edition enrollment is already in the list.", thrown.getMessage());
    }

    @Test
    void shouldReturnAValidProgrammeEditionEnrollment () throws Exception {
        //arrange
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,t1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        LocalDate currentDate = LocalDate.now();

        //act
        Optional<ProgrammeEditionEnrollment> result = repository.enrollStudentInProgrammeEdition(st1,pe1,currentDate);

        //assert
        assertTrue(result.isPresent(), "The student was enrolled in a programme edition successfully.");
    }

    @Test
    void shouldReturnATwoValidProgrammeEditionEnrollments () throws Exception {
        //arrange
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");

        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,t1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        LocalDate currentDate = LocalDate.now();

        Address add2 = new Address("Rua das Flores", "4567-565", "Porto", "Portugal");
        Student st2 = new Student(124,"Maria","223444679","256333444","124@gmail.com",add2);
        Department d2 = new Department("DCS","Department of Computer Science");
        SchoolYear sy2 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt2 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor1 = new TeacherCategory("Assistant Professor");
        Teacher t2 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d2);
        Programme p2 = new Programme("SWITCH DEV","SDV",30,1,dt2,d2,t2);
        ProgrammeEdition pe2 = new ProgrammeEdition(p2,sy2);
        LocalDate currentDate1 = LocalDate.now();
        //act
        Optional<ProgrammeEditionEnrollment> result1 = repository.enrollStudentInProgrammeEdition(st1,pe1,currentDate);
        Optional<ProgrammeEditionEnrollment> result2 = repository.enrollStudentInProgrammeEdition(st2,pe2,currentDate1);

        //assert
        assertTrue(result1.isPresent(), "The student was enrolled in a programme edition successfully.");
        assertTrue(result2.isPresent(), "The student was enrolled in a programme edition successfully.");

    }

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgrammeEdition() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo repo = new ProgrammeEditionEnrollmentRepo();

        // Act
        repo.enrollStudentInProgrammeEdition(st1, pe1, currentDate);

        // Assert
        assertTrue(repo.isStudentEnrolledInThisProgrammeEdition(st1, pe1));
    }

    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgrammeEdition() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        ProgrammeEdition pe2 = new ProgrammeEdition(p1, new SchoolYear("ola", "01-01-2025", "01-02-2025"));
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo repo = new ProgrammeEditionEnrollmentRepo();

        // Act
        repo.enrollStudentInProgrammeEdition(st1, pe1, currentDate);

        // Assert
        assertFalse(repo.isStudentEnrolledInThisProgrammeEdition(st1, pe2));
    }

    //US26
    // Test counting with multiple schoolYears
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear()throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        SchoolYear schoolYear2 = new SchoolYear("Ano letivo de", "01-09-2022", "31-07-2023");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);

        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, teacher1);
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, teacher1);

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");
        Student student1 = new Student (1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address1);
        Student student2 = new Student (2, "Rita Mendes", "123455649", "221234567", "rita123@gmail.com", address2);
        Student student3 = new Student (3, "Ana Luisa", "123456439", "221234569", "ana123@gmail.com", address3);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo PEERepo = new ProgrammeEditionEnrollmentRepo();

        PEERepo.enrollStudentInProgrammeEdition(student1, edition1, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student2, edition2, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student3, edition3, currentDate);

        // act
        int result =PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department1, schoolYear1);

        // assert
        assertEquals(2, result);
    }

    //Test counting with multiple departments
    @Test
    void shouldReturnCorrectCountWhenStudentsFromDifferentDepartmentsAreEnrolled() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Department department2 = new Department("DEQ", "Departamento Engenharia Química");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        Teacher teacher2 = new Teacher("DEF", "Jane Smith", "def@isep.ipp.pt", "987654321", "B107", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department2);

        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1,t1 );
        Programme programme2 = new Programme("Licenciatura Engenharia Química", "LEQ", 30, 6, master, department2, teacher2);

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");

        Student student1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address1);
        Student student2 = new Student(2, "Rita Mendes", "123455649", "221234567", "rita123@gmail.com", address2);
        Student student3 = new Student(3, "Ana Luisa", "123456439", "221234569", "ana123@gmail.com", address3);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo PEERepo = new ProgrammeEditionEnrollmentRepo();

        PEERepo.enrollStudentInProgrammeEdition(student1, edition1, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student2, edition2, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student3, edition2, currentDate);

        // act
        int result = PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department1, schoolYear1);

        // assert
        assertEquals(1, result);
    }

    // Test returns 0 when no students are enrolled in the department and school year
    @Test
    void shouldReturnZeroWhenNoStudentsAreEnrolledInDepartmentAndSchoolYear() throws Exception{
        //arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");
        SchoolYear schoolYear2 = new SchoolYear("Ano letivo de", "01-09-2022", "31-07-2023");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);

        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, t1);
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, t1);

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);
        ProgrammeEdition edition3 = new ProgrammeEdition(programme2, schoolYear2);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address address2 = new Address("Rua do Lumiar", "4554-566", "Porto", "Portugal");
        Address address3 = new Address("Rua da Pedra", "4556-575", "Porto", "Portugal");
        Student student1 = new Student (1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address1);
        Student student2 = new Student (2, "Rita Mendes", "123455649", "221234567", "rita123@gmail.com", address2);
        Student student3 = new Student (3, "Ana Luisa", "123456439", "221234569", "ana123@gmail.com", address3);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo PEERepo = new ProgrammeEditionEnrollmentRepo();

        PEERepo.enrollStudentInProgrammeEdition(student1, edition3, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student2, edition3, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student3, edition3, currentDate);
        // act
        int result =PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department1, schoolYear1);
        // assert
        assertEquals(0, result);
    }

    //Test counting when there is a student enrolled in multiple programme editions (the student should be counted only once)
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInMultipleEditions() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");

        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher t1 = new Teacher("CED", "Jane Doe", "ced@isep.ipp.pt", "100056789", "B107",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);

        Programme programme1 = new Programme("Licenciatura Engenharia Informática", "LEI", 25, 6, master, department1, t1);
        Programme programme2 = new Programme("Licenciatura Engenharia de Computação", "LEC", 30, 6, master, department1, t1);

        ProgrammeEdition edition1 = new ProgrammeEdition(programme1, schoolYear1);
        ProgrammeEdition edition2 = new ProgrammeEdition(programme2, schoolYear1);

        Address address1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address1);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo PEERepo = new ProgrammeEditionEnrollmentRepo();

        PEERepo.enrollStudentInProgrammeEdition(student1, edition1, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student1, edition2, currentDate);

        // act
        int result = PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department1, schoolYear1);

        // assert
        assertEquals(1, result);
    }
}
