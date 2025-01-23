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
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);

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
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);


        // Act
        list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        boolean result = list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

            // Asssert
        assertFalse(result);
    }

    @Test
    void changeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeList list = new ProgrammeList();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100,CSE);
        Teacher teacher1 = new Teacher("ABC", "John Travis", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        // Act
        boolean result = list.changeProgrammeDirector(CE,teacher1);

        // Asssert
        assertTrue(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeList list = new ProgrammeList();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100,CSE);
        Teacher teacher1 = new Teacher("ABC", "John Travis", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        // Act
        boolean result = list.changeProgrammeDirector(CE,null);

        // Asssert
        assertFalse(result);
    }
}