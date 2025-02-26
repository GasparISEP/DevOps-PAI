package PAI.domain;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear(){

        // arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition3Double = mock(ProgrammeEdition.class);

        //only edition1 and edition2 are associated with the department and school year
        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double,schoolYear1Double)).thenReturn(true);
        when(edition2Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double,schoolYear1Double)).thenReturn(true);
        when(edition3Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double,schoolYear1Double)).thenReturn(false);

        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);
        Student student3Double = mock(Student.class);

        //to ensure that each student double has a different unique number
        when(student1Double.getUniqueNumber()).thenReturn(1);
        when(student2Double.getUniqueNumber()).thenReturn(2);
        when(student3Double.getUniqueNumber()).thenReturn(3);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo PEERepo = new ProgrammeEditionEnrollmentRepo();

        //only student 1 and 2 will be counted,  since edition3 is not associated with the department/school year
        PEERepo.enrollStudentInProgrammeEdition(student1Double, edition1Double, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student2Double, edition2Double, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student3Double, edition3Double, currentDate);

        // act
        int result =PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(2, result);
    }

    @Test
    void shouldReturnZeroWhenNoStudentsAreEnrolledInDepartmentAndSchoolYear(){
        //arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition editionDouble = mock (ProgrammeEdition.class);

        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(false);

        //to ensure that each student double has a different unique number
        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);

        when(student1Double.getUniqueNumber()).thenReturn(1);
        when(student2Double.getUniqueNumber()).thenReturn(2);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo PEERepo = new ProgrammeEditionEnrollmentRepo();

        //no students will be counted,since editionDouble is not associated with the department and school year
        PEERepo.enrollStudentInProgrammeEdition(student1Double, editionDouble, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student2Double, editionDouble, currentDate);

        // act
        int result =PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(0, result);
    }

    //Test counting when there is a student enrolled in multiple programme editions (the student should be counted only once)
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInMultipleEditions() {
        // arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition edition1Double = mock (ProgrammeEdition.class);
        ProgrammeEdition edition2Double= mock(ProgrammeEdition.class);

        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(edition2Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);

        Student student1Double = mock(Student.class);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentRepo PEERepo = new ProgrammeEditionEnrollmentRepo();

        //the same student is enrolled in two different editions but should be counted only once
        PEERepo.enrollStudentInProgrammeEdition(student1Double, edition1Double, currentDate);
        PEERepo.enrollStudentInProgrammeEdition(student1Double, edition2Double, currentDate);

        // act
        int result = PEERepo.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(1, result);
    }

    //US21
    //Test counting 2 students enrolled in a programme edition
    @Test
    void shouldReturnNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo repo = new ProgrammeEditionEnrollmentRepo();

        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address add2 = new Address("Rua da Alegria", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        Student st2 = new Student(2, "Pedro Silva", "123456788", "221234567", "pedro123@gmail.com", add2);
        SchoolYear sy1 = new SchoolYear("adeus", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        LocalDate currentDate = LocalDate.now();

        int NumberOfStudentsEnrolledInAProgrammeEdition = 2;

        // Act
        repo.enrollStudentInProgrammeEdition(st1, pe1, currentDate);
        repo.enrollStudentInProgrammeEdition(st2, pe1, currentDate);
        int result = repo.getTheNumberOfStudentsEnrolledInAProgrammeEdition(pe1);

        // Assert
        assertEquals(NumberOfStudentsEnrolledInAProgrammeEdition, result);
    }

    //Test counting when there are no students enrolled in a programme edition
    @Test
    void shouldReturnZeroIfProgrammeEditionHasZeroStudentsEnrolled() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo repo = new ProgrammeEditionEnrollmentRepo();

        SchoolYear sy1 = new SchoolYear("adeus", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);

        int NumberOfStudentsEnrolledInAProgrammeEdition = 0;

        // Act
        int result = repo.getTheNumberOfStudentsEnrolledInAProgrammeEdition(pe1);

        // Assert
        assertEquals(NumberOfStudentsEnrolledInAProgrammeEdition, result);
    }

    //Test counting in a programme edition different from the one where the students are enrolled
    @Test
    void shouldReturnZeroIfCheckingNumberOfStudentsInDifferentProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentRepo repo = new ProgrammeEditionEnrollmentRepo();

        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Address add2 = new Address("Rua da Alegria", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        Student st2 = new Student(2, "Pedro Silva", "123456788", "221234567", "pedro123@gmail.com", add2);
        SchoolYear sy1 = new SchoolYear("adeus", "20-01-2023", "23-02-2023");
        SchoolYear sy2 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        ProgrammeEdition pe2 = new ProgrammeEdition(p1, sy2);
        LocalDate currentDate = LocalDate.now();

        int NumberOfStudentsEnrolledInAProgrammeEdition = 0;

        // Act
        repo.enrollStudentInProgrammeEdition(st1, pe2, currentDate);
        repo.enrollStudentInProgrammeEdition(st2, pe2, currentDate);

        int result = repo.getTheNumberOfStudentsEnrolledInAProgrammeEdition(pe1);

        // Assert
        assertEquals(NumberOfStudentsEnrolledInAProgrammeEdition, result);
    }
}