package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionTest {

    //CONSTRUCTOR TESTS
    @Test
    void validProgrammeAndSchoolYearCreatesAProgrammeEdition() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        // Act
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);

        // Assert
        assertNotNull(pe1);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIsNull() throws Exception {
        // Arrange
        Programme p1 = null;
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEdition(p1, sy1));
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIsNull() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEdition(p1, sy1));
    }

    //EQUALS TESTS
    @Test
    void shouldReturnFalseIfObjectComparedIsNotProgrammeEdition() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        AccessMethod am1 = new AccessMethod("M23");

        // Act
        boolean result = pe1.equals(am1);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTwoProgrammeEditionsHaveTheSameProgrammesAndSchoolYears() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        ProgrammeEdition pe2 = new ProgrammeEdition(p1, sy1);

        // Act
        boolean result = pe1.equals(pe2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionsHaveDifferentProgrammesAndSameSchoolYears() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Programme p2 = new Programme("Computer Science", "CC", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        ProgrammeEdition pe2 = new ProgrammeEdition(p2, sy1);

        // Act
        boolean result = pe1.equals(pe2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionsHaveDifferentSchoolYearsAndSameProgramme() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sy2 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2026");
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        ProgrammeEdition pe2 = new ProgrammeEdition(p1, sy2);

        // Act
        boolean result = pe1.equals(pe2);

        // Assert
        assertFalse(result);
    }

    //US17
    @Test
    void testEqualsWithDifferentObjectType() throws Exception {
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        DegreeType master = new DegreeType("Master", 240);
        Teacher teacher1 = new Teacher("XYZ", "Jane Doe", "xyz@isep.ipp.pt", "987654321", "B107", "Doutoramento em Engenharia de Software, 2010, ISEP", "Rua São João Porto",
                "4249-016", "Porto", "Portugal", "15-07-2015", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition edition1 = new ProgrammeEdition(p1, sy1);

        assertFalse(edition1.equals("Not a ProgrammeEdition"));
    }

    //US17
    @Test
    void testFindProgrammeInProgrammeEdition() throws Exception {
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        DegreeType master = new DegreeType("Master", 240);
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition programmeEdition = new ProgrammeEdition(p1, sy1);

        Programme result = programmeEdition.findProgrammeInProgrammeEdition();

        assertNotNull(result);
        assertEquals(p1, result);
    }

    //US17
    @Test
    void testFindSchoolYearInProgrammeEdition() throws Exception {
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(p1, sy1);

        SchoolYear result = programmeEdition.findSchoolYearInProgrammeEdition();

        assertNotNull(result);
        assertEquals(sy1, result);
    }

}