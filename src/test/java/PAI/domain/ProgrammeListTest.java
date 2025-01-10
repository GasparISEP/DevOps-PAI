package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeListTest {

    @Test
    void shouldRegisterValidProgramme() throws Exception {
        // Arrange
        ProgrammeList list = new ProgrammeList();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);

        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);


        // Act
        boolean result = list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        // Asssert
        assertTrue(result);
    }


    @Test
    void duplicatedShouldNotRegisterValidProgramme() throws Exception {
        // Arrange
        ProgrammeList list = new ProgrammeList();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Address addressIsep = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", addressIsep, assistantProfessor, CSE);


        // Act
        list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        boolean result = list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

            // Asssert
        assertFalse(result);
    }
}