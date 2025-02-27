package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    @Test
    void shouldCreateController() throws Exception {
        //arrange
        SchoolYearRepository syr1 = mock(SchoolYearRepository.class);
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
        //act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(per1,syr1);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldCreateProgrammeEdition() throws Exception{
        //arrange
        Programme doubleProgramme = mock(Programme.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        SchoolYearFactory doubleSchoolYearFactory = mock(SchoolYearFactory.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);

        when(doubleSchoolYearFactory.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024")).thenReturn(doubleSchoolYear);

        doubleSchoolYearRepository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        when(doubleSchoolYearRepository.getCurrentSchoolYear()).thenReturn(doubleSchoolYear);

        when(doubleProgrammeEditionRepository.createProgrammeEdition(doubleProgramme,doubleSchoolYear)).thenReturn(true);

        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(doubleProgrammeEditionRepository, doubleSchoolYearRepository);

        //act
        boolean isCreated = ctrl.createAProgrammeEditionInTheCurrentSchoolYear(doubleProgramme);
        //assert
        assertTrue(isCreated);
    }

    @Test
    void shouldNotCreateProgrammeEditionIfCurrentSchoolYearIsNull() throws Exception{
        //arrange
        SchoolYearRepository syr1 = mock(SchoolYearRepository.class);
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "14-05-2007", assistantProfessor, 70, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(per1,syr1);
        boolean isCreated = controller.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertFalse(isCreated);
    }

    @Test
    void shouldReturnFalseIfNotCreateProgrammeEdition() throws Exception{
        //arrange
        SchoolYearRepository syr1 = mock(SchoolYearRepository.class);
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "14-05-2007", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        syr1.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");

        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(per1, syr1);

        //act
        ctrl.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        boolean isCreated = ctrl.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertFalse(isCreated);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionRepositoryIsNull() throws Exception{
        //arrange
        SchoolYearRepository syr1 = mock(SchoolYearRepository.class);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010", assistantProfessor,100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        syr1.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(null, syr1);

        //act
        boolean isCreated = ctrl.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertFalse(isCreated);
    }

    @Test
    void shouldReturnFalseIfSchoolYearRepositoryIsNull() throws Exception{
        //arrange
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010", assistantProfessor,100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(per1, null);

        //act
        boolean isCreated = ctrl.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertFalse(isCreated);
    }


}