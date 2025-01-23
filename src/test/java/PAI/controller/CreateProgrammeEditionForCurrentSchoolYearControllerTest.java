package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    @Test
    void shouldCreateController() throws Exception {
        //arrange
        SchoolYearRepository syr1 = new SchoolYearRepository();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        //act
        CreateProgrammeEditionForCurrentSchoolYearController controller = new CreateProgrammeEditionForCurrentSchoolYearController(per1,syr1);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldNotCreateControllerIfSchoolYearRepositoryIsNull() throws Exception {
        //arrange
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        //assert
        assertThrows(Exception.class, () -> new CreateProgrammeEditionForCurrentSchoolYearController(per1, null));
    }
    @Test
    void shouldNotCreateControllerIfProgrammeEditionRepositoryIsNull() throws Exception {
        //arrange
        SchoolYearRepository syr1 = new SchoolYearRepository();
        //assert
        assertThrows(Exception.class, () -> new CreateProgrammeEditionForCurrentSchoolYearController(null, syr1));
    }


    @Test
    void shouldCreateProgrammeEdition() throws Exception{
        //arrange
        SchoolYearRepository syr1 = new SchoolYearRepository();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "14-05-2007", assistantProfessor, 70, CSE );
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        syr1.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");

        CreateProgrammeEditionForCurrentSchoolYearController ctrl = new CreateProgrammeEditionForCurrentSchoolYearController(per1, syr1);

        //act
        boolean isCreated = ctrl.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertTrue(isCreated);
    }

    @Test
    void shouldNotCreateProgrammeEditionIfCurrentSchoolYearIsNull() throws Exception{
        //arrange
        SchoolYearRepository syr1 = new SchoolYearRepository();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "14-05-2007", assistantProfessor, 70, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        CreateProgrammeEditionForCurrentSchoolYearController controller = new CreateProgrammeEditionForCurrentSchoolYearController(per1,syr1);
        boolean isCreated = controller.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertFalse(isCreated);
    }

    @Test
    void shouldReturnFalseIfNotCreateProgrammeEdition() throws Exception{
        //arrange
        SchoolYearRepository syr1 = new SchoolYearRepository();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository();

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "14-05-2007", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        syr1.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");

        CreateProgrammeEditionForCurrentSchoolYearController ctrl = new CreateProgrammeEditionForCurrentSchoolYearController(per1, syr1);

        //act
        ctrl.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        boolean isCreated = ctrl.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertFalse(isCreated);
    }


}