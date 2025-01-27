package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
class ProgrammeTest {
    // Test to check the constructor
    @Test
    void createAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
    }


    // Test to empty name in Programme
    @Test
    void emptyNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert

        assertThrows(Exception.class, () -> new Programme("", "CE", 20,6,master,CSE,teacher1));
    }


    // Test to a null name in Programme
    @Test
    void nullNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert

        assertThrows(Exception.class, () -> new Programme(null, "CE", 20,6,master,CSE,teacher1));
    }


    // Test to empty Acronym in Programme
    @Test
    void emptyAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "", 20,6,master,CSE,teacher1));
    }

    // Test to a null Acronym in Programme
    @Test
    void nullAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", null, 20,6,master,CSE,teacher1));
    }

    // Test to check if negative number of ECTS dont create a programme
    @Test
    void lessThan0ECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", -1,6,master,CSE,teacher1));
    }

    // Teste to check if number 0 of ECTS dont create a programme
    @Test
    void zeroECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 0,6,master,CSE,teacher1));
    }


    @Test
    void moreThan30ECTSDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);



        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,6,master,CSE,teacher1));

    }

    @Test
    void lessThanZeroSemestersDontCreateAProgramme () throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,-1,master,CSE,teacher1));

    }

    @Test
    void ZeroSemestersDontCreateAProgramme () throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,0,master,CSE,teacher1));

    }

    @Test
    void specialCharactersInNameDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert

        assertThrows(Exception.class, () -> new Programme("@Computer Science", "CE", 20,6,master,CSE,teacher1));
    }


    @Test
    void numbersInAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "123", 20,6,master,CSE,teacher1));
    }

    @Test
    void specialCharactersInAcronymDontCreateAProgramme () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);


        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "@CE", 20,6,master,CSE,teacher1));
    }

    @Test
    void equalsProgrammeReturnTrue () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010",assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertTrue(result);
    }

    @Test
    void notEqualsProgrammeReturnFalse () throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Programme CEE = new Programme("Computer Engineering", "CEE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }

    @Test
    void equalsCompareObjectReturnTrue() throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.equals(CE);

        //assert
        assertTrue(result);
    }

    @Test
    void equalsDontCompareDifferentObjectAndReturnFalse() throws Exception {

        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.equals(teacher);

        //assert
        assertFalse(result);
    }



    @Test
    void creatNewProgrammeDirector() throws Exception {
        //arrange

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Teacher teacher1 = new Teacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act + assert
        CE.newProgrammeDirector(teacher1);
    }

    @Test
    void shouldReturnTrueIfTheEnrolmentInTheProgrammeIsSuccessful() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result = CE.enrolStudentInProgramme(student1, am1, amr);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentIsNotSuccessfulBecauseAccessMethodIsNotRegistered() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("CNA");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010",assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act + assert
        assertThrows(Exception.class, () -> CE.enrolStudentInProgramme(student1, am1, amr));
    }

    @Test
    void shouldReturnFalseIfEnrolmentIsNotSuccessfulBecauseStudentIsAlreadyEnrolledInTheProgramme() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        CE.enrolStudentInProgramme(student1, am1, amr);

        //assert
        assertThrows(Exception.class, () -> CE.enrolStudentInProgramme(student1, am1, amr));
    }

    @Test
    void shouldEnrolSuccessfullyMoreThanOneStudent() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        AccessMethod am1 = new AccessMethod("Maiores 23");

        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("Maiores 23");

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        boolean result1 = CE.enrolStudentInProgramme(student1, am1, amr);
        boolean result2 = CE.enrolStudentInProgramme(student2, am1, amr);

        //assert
        assertTrue(result1);
        assertTrue(result2);
    }

    // AddCourseToASemesterOfProgramme tests

    @Test
    void shouldReturnTrueIfCourseIsAddedToASemesterOfAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Nº100", "4435-696","Gondomar","Portugal", "20-12-2010", teacherCategory1,100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);        //act
        boolean result = lei.addCourseToASemesterOfAProgramme(1, course1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnExceptionIfSemesterDoesNotExistInAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(3, course1));
    }

    @Test
    void shouldReturnExceptionIfSemesterIsNotPositive() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(0, course1));
    }

    @Test
    void shouldReturnExceptionIfCourseInNull() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1,100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = null;
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);

        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(2, course1));
    }

    @Test
    void shouldReturnExceptionIfAnualCourseAlreadyExistsInTwoDifferentSemestersInAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1,100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 2);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 4, degree1, department1, teacher1);

        lei.addCourseToASemesterOfAProgramme(1, course1);
        lei.addCourseToASemesterOfAProgramme(2, course1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(3, course1));
    }

    @Test
    void shouldReturnExceptionIfCourseAlreadyExistsInAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1,100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 4, degree1, department1, teacher1);
        lei.addCourseToASemesterOfAProgramme(1, course1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(2, course1));
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,4,5,6})
    void shouldReturnExceptionIfAnualCourseIsBeingAddedToADifferentYearInAProgramme_test1(int semester) throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 6, 2);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 4, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 6, 2);
        lei.addCourseToASemesterOfAProgramme(2, course1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(semester, course1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,5,6})
    void shouldReturnExceptionIfAnualCourseIsBeingAddedToADifferentYearInAProgramme_test2(int semester) throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1,100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 6, 2);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 4, degree1, department1, teacher1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 6, 2);
        lei.addCourseToASemesterOfAProgramme(3, course1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(semester, course1));
    }

    @Test
    void shouldReturnTrueIfAnualCourseIsBeingAddedToTheSameYearInAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1,100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course estagio = new Course("matemática", "MTA", 6, 2);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        lei.addCourseToASemesterOfAProgramme(2, estagio);
        //act
        boolean result = lei.addCourseToASemesterOfAProgramme(1, estagio);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnExceptionIfCourseAddedToASemesterSurpassTheLimtitCreditECTSForThatSemester() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1,100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 20, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(1, course1));
    }

    @Test
    void shouldReturnFalseIfCourseToBeAddedAlreadyExistsInSemesterOfAProgramme() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010",teacherCategory1,100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        lei.addCourseToASemesterOfAProgramme(1, course1);
        //act & assert
        assertThrows(Exception.class, () -> lei.addCourseToASemesterOfAProgramme(1, course1));
    }

    @Test
    void shouldReturnTrueIfCourseIsAddedToASemesterOfAProgrammeThatContainsOtherDifferentCourses() throws Exception {
        //arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1,100, department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Course course2 = new Course("português", "POR", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        lei.addCourseToASemesterOfAProgramme(1, course1);
        //act
        boolean result = lei.addCourseToASemesterOfAProgramme(1, course2);
        //assert
        assertTrue(result);
    }

    //US17
    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() throws Exception {
        // Arrange: Criando um aluno
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);

        // Arrange: Criando um programa
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("M1");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        programme.enrolStudentInProgramme(student,am1,amr);

        // Act
        boolean result = programme.isStudentEnrolled(student);

        // Assert
        assertTrue(result, "The student must be enrolled in the programme.");
    }

    //US17
    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgramme() throws Exception {
        // Arrange: Criando um aluno
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);
        Student student1 = new Student(2, "Joana Silva", "123000009", "221234567", "joana123@gmail.com", address);
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("M1");
        DegreeType master = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        programme.enrolStudentInProgramme(student,am1,amr);

        // Act
        boolean result = programme.isStudentEnrolled(student1);

        // Assert
        assertFalse(result, "The student should not be enrolled in the programme.");
    }
    //US17
    @Test
    void shouldReturnCorrectStudentFromEnrolment() throws Exception {
        // Arrange: Criando um aluno
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", address);
        DegreeType master = new DegreeType("Master", 240);
        AccessMethod am1 = new AccessMethod("M1");
        AccessMethodRepository amr = new AccessMethodRepository();
        amr.registerAccessMethod("M1");
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", assistantProfessor, 100, department);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher);
        programme.enrolStudentInProgramme(student,am1,amr);
        Enrolment enrolment = new Enrolment(student, am1);

        // Act
        Student enrolledStudent = enrolment.findStudentInEnrollments();

        // Assert
        assertEquals(student, enrolledStudent, "The student found in the enrolment must be the same as the student created.");
    }
}