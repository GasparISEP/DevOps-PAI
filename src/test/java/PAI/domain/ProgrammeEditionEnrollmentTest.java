package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionEnrollmentTest {

    @Test
    void should_return_a_valid_programme_edition_enrollment() throws Exception {
        //arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student (1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear ("ola","20-01-2024","23-02-2024");
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);

        LocalDate currentDate = LocalDate.now();

        //act + assert
        ProgrammeEditionEnrollment pee1 = new ProgrammeEditionEnrollment(st1,pe1,currentDate);
    }

    @Test
    void everythingNullGenerateException () throws Exception {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrollment (null,null,null));
    }
    @Test
    void programmeNullGenerateException () throws Exception {
        //arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student (1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        //act + assert
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrollment (st1,null,LocalDate.now()));
    }


    //testing invalid enrollment date
    public static Stream<Arguments> provideInvalidEnrollmentDate() {
        return Stream.of(
                arguments(null, "Enrollment date cannot be null!"),
                arguments(LocalDate.of(2024,12,29), "Enrollment date must be the current day!"),
                arguments(LocalDate.of (2026,1,23), "Enrollment date must be the current day!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidEnrollmentDate")
    void testInvalidEnrollmentDate (LocalDate enrollmentDate, String expectedMessage) throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student (1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear ("ola","20-01-2024","23-02-2024");
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, department1);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new ProgrammeEditionEnrollment(st1, pe1, enrollmentDate);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsNull_EqualsMethod() throws Exception {
        //arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        LocalDate currentDate = LocalDate.now();

        //act
        ProgrammeEditionEnrollment enrollment1 = new ProgrammeEditionEnrollment(st1, pe1, currentDate);

        //assert
        assertFalse(enrollment1.equals(null));
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionIsEqualThis_EqualsMethod() throws Exception {
        //arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        LocalDate currentDate = LocalDate.now();

        //act
        ProgrammeEditionEnrollment enrollment1 = new ProgrammeEditionEnrollment(st1, pe1, currentDate);

        //assert
        assertTrue(enrollment1.equals(enrollment1));
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() throws Exception {
        // Arrange com objetos idênticos
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        LocalDate currentDate = LocalDate.now();

        // Act
        ProgrammeEditionEnrollment enrollment1 = new ProgrammeEditionEnrollment(st1, pe1, currentDate);
        ProgrammeEditionEnrollment enrollment2 = new ProgrammeEditionEnrollment(st1, pe1, currentDate);

        // Assert
        assertTrue(enrollment1.equals(enrollment2));
    }

    @Test
    void shouldReturnProgrammeEditionFromEnrollment() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(st1, pe1, currentDate);

        // Act
        ProgrammeEdition foundProgrammeEdition = enrollment.findProgrammeEditionInEnrollment();

        // Assert
        assertNotNull(foundProgrammeEdition);
        assertEquals(pe1, foundProgrammeEdition);
    }

    @Test
    void shouldReturnStudentFromProgrammeEdition() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(st1, pe1, currentDate);

        // Act
        Student foundStudent = enrollment.findStudentInProgrammeEdition();

        // Assert
        assertNotNull(foundStudent);
        assertEquals(st1, foundStudent);
    }

    //US26
    // Test returns true when the department and school year are correctly associated with the enrollment
    @Test
    void shouldReturnTrueWhenDepartmentAndSchoolYearAreAssociated() {
        // arrange
        Student student1Double = mock(Student.class);
        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        ProgrammeEdition editionDouble = mock(ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(departmentDouble,schoolYearDouble)).thenReturn(true);

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(student1Double,editionDouble,enrollmentDate);

        // act
        boolean result = enrollment.isEnrollmentAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble);

        // assert
        assertTrue(result);
    }

    // Test returns false when department and school year are not associated with the enrollment
    @Test
    void shouldReturnFalseWhenDepartmentAndSchoolYearAreNotAssociatedWithTheEnrollment() {
        // arrange
        Student student1Double = mock(Student.class);
        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        ProgrammeEdition editionDouble = mock(ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        when(editionDouble.isEditionAssociatedToDepartmentAndSchoolYear(departmentDouble,schoolYearDouble)).thenReturn(false);

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(student1Double,editionDouble,enrollmentDate);

        // act
        boolean result = enrollment.isEnrollmentAssociatedToDepartmentAndSchoolYear(departmentDouble, schoolYearDouble);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnStudentUniqueNumber() {
        // Arrange
        Student studentMock = mock(Student.class);
        when(studentMock.getUniqueNumber()).thenReturn(12345);

        ProgrammeEdition editionMock = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(studentMock, editionMock, LocalDate.now());

        // Act
        int uniqueNumber = enrollment.getStudentUniqueNumber();

        // Assert
        assertEquals(12345, uniqueNumber);
    }

    @Test
    void shouldReturnFalseIfObjectIsDifferent_EqualsMethod() {
        // Arrange
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollment = new ProgrammeEditionEnrollment(st1, pe1, currentDate);

        // Act & Assert
        assertFalse(enrollment.equals(new Object()));
    }

}