package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProgrammeTest {
    // Test to check the constructor
    @Test
    void createAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,degree1);
    }


    // Test to empty name in Programme
    @Test
    void emptyNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert

        assertThrows(Exception.class, () -> new Programme("", "CE", 20, 6, degree1));
    }


    // Test to a null name in Programme
    @Test
    void nullNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert

        assertThrows(Exception.class, () -> new Programme(null, "CE", 20, 6, degree1));
    }


    // Test to empty Acronym in Programme
    @Test
    void emptyAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "", 20,6, degree1));
    }

    // Test to a null Acronym in Programme
    @Test
    void nullAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", null, 20,6, degree1));
    }

    // Test to check if negative number of ECTS dont create a programme
    @Test
    void lessThan0ECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", -1,6, degree1));
    }

    // Teste to check if number 0 of ECTS dont create a programme
    @Test
    void zeroECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 0,6, degree1));
    }


    @Test
    void moreThan30ECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,6, degree1));

    }

    @Test
    void lessThanZeroSemestersDontCreateAProgramme () throws Exception {
        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,-1, degree1));

    }

    @Test
    void ZeroSemestersDontCreateAProgramme () throws Exception {
        //arrange
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,0, degree1));

    }

    //Add Course To Programme
    @Test
    void shouldReturnTrueIfCourseIsAddToAProgramme() throws Exception {
        //arrange

        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",20);
        Course course1 = new Course("matemática", "MTA", 30, teacher1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 20, 6, degree1);
        // act
        boolean result = lei.addCourse(course1);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseIsAlreadyInAProgramme() throws Exception {
        //arrange

        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Address address1 = new Address("Rua São Tomé Nº100", "4435-696","Gondomar","Portugal");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123",address1, teacherCategory1,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",20);
        Course course1 = new Course("matemática", "MTA", 30, teacher1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 20, 6, degree1);
        // act
        lei.addCourse(course1);
        boolean result = lei.addCourse(course1);
        // assert
        assertFalse(result);
    }
}