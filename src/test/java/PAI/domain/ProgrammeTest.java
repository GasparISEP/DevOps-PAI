package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProgrammeTest {
    // Test to check the constructor
    @Test
    void createAProgramme () throws Exception {

        //arrange
        DegreeType degree1 = new DegreeType("Master",240);
        Semester semester1 = new Semester(4);

        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20,semester1,degree1);
    }


    // Test to empty name in Programme
    @Test
    void emptyNameDontCreateAProgramme () throws Exception {

        //arrange
        Semester semester1 = new Semester(4);
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert

        assertThrows(Exception.class, () -> new Programme("", "CE", 20, semester1, degree1));
    }


    // Test to a null name in Programme
    @Test
    void nullNameDontCreateAProgramme () throws Exception {

        //arrange
        Semester semester1 = new Semester(4);
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert

        assertThrows(Exception.class, () -> new Programme(null, "CE", 20, semester1, degree1));
    }


    // Test to empty Acronym in Programme
    @Test
    void emptyAcronymDontCreateAProgramme () throws Exception {

        //arrange
        Semester semester1 = new Semester(4);
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "", 20, semester1, degree1));
    }

    // Test to a null Acronym in Programme
    @Test
    void nullAcronymDontCreateAProgramme () throws Exception {

        //arrange
        Semester semester1 = new Semester(4);
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", null, 20,semester1, degree1));
    }

    // Test to check if negative number of ECTS dont create a programme
    @Test
    void lessThan0ECTSDontCreateAProgramme () throws Exception {

        //arrange
        Semester semester1 = new Semester(4);
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", -1,semester1, degree1));
    }

    // Teste to check if number 0 of ECTS dont create a programme
    @Test
    void zeroECTSDontCreateAProgramme () throws Exception {

        //arrange
        Semester semester1 = new Semester(4);
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 0,semester1, degree1));
    }


    @Test
    void moreThan30ECTSDontCreateAProgramme () throws Exception {

        //arrange
        Semester semester1 = new Semester(4);
        DegreeType degree1 = new DegreeType("Master",240);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,semester1, degree1));

    }
  
}