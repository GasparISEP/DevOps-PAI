package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionRepositoryTest {

    @Test
    void shouldReturnTrueIfProgrammeEditionIsCreatedSuccessfully() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        // act
        boolean result = per1.createProgrammeEdition(p1, sy1);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTheAddedProgrammeEditionHasTheSameProgrammeAsAnExistingOneButADifferentYear() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sy2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        per1.createProgrammeEdition(p1, sy1);

        // act
        boolean result = per1.createProgrammeEdition(p1, sy2);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTheAddedProgrammeEditionHasTheSameYearAsAnExistingOneButADifferentProgramme() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Programme p2 = new Programme("LEI", "LEI", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        per1.createProgrammeEdition(p1, sy1);

        // act
        boolean result = per1.createProgrammeEdition(p2, sy1);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionAlreadyRegistered() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        per1.createProgrammeEdition(p1, sy1);

        // act
        boolean result = per1.createProgrammeEdition(p1, sy1);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeIsNull() throws Exception {
        // arrange
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        // act
        boolean result = per1.createProgrammeEdition(null, sy1);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfGivenSchoolYearIsNull() throws Exception {
        // arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        // act
        boolean result = per1.createProgrammeEdition(p1, null);

        // assert
        assertFalse(result);
    }

    //US17
    @Test
    void shouldFindProgrammeEditionBySchoolYearAndProgramme() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);

        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        ProgrammeEditionRepository repository = new ProgrammeEditionRepository();

        repository.createProgrammeEdition(programme, schoolYear);

        // Act
        Optional<ProgrammeEdition> result = repository.findProgrammeEditionBySchoolYearAndProgramme(programme, schoolYear);

        // Assert
        assertTrue(result.isPresent());

    }

    //US17
    @Test
    void shouldReturnEmptyIfProgrammeEditionNotFound() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);

        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        Programme programme2 = new Programme("Electrical Engineering", "EE", 20, 6, master, CSE, teacher);
        SchoolYear schoolYear2 = new SchoolYear("Ano letivo de", "01-01-2025", "31-12-2025");

        ProgrammeEditionRepository repository = new ProgrammeEditionRepository();

        repository.createProgrammeEdition(programme1, schoolYear1);

        // Act
        Optional<ProgrammeEdition> result = repository.findProgrammeEditionBySchoolYearAndProgramme(programme2, schoolYear2);

        // Assert
        assertFalse(result.isPresent());
    }

}