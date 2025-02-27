package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    @Test
    void shouldCreateController() throws Exception {
        //arrange
        SchoolYearRepository syr1 = mock(SchoolYearRepository.class);
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        //act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(per1,syr1, programmeList);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldCreateProgrammeEdition() throws Exception{
        //arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        Programme doubleProgramme = mock(Programme.class);
        SchoolYearRepository doubleSchoolYearRepository = mock(SchoolYearRepository.class);
        SchoolYearFactory doubleSchoolYearFactory = mock(SchoolYearFactory.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        ProgrammeEditionRepository doubleProgrammeEditionRepository = mock(ProgrammeEditionRepository.class);

        when(doubleSchoolYearFactory.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024")).thenReturn(doubleSchoolYear);

        doubleSchoolYearRepository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        when(doubleSchoolYearRepository.getCurrentSchoolYear()).thenReturn(doubleSchoolYear);

        when(doubleProgrammeEditionRepository.createProgrammeEdition(doubleProgramme,doubleSchoolYear)).thenReturn(true);

        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(doubleProgrammeEditionRepository, doubleSchoolYearRepository, programmeList);

        //act
        boolean isCreated = ctrl.createAProgrammeEditionInTheCurrentSchoolYear(doubleProgramme);
        //assert
        assertTrue(isCreated);
    }

    @Test
    void shouldNotCreateProgrammeEditionIfCurrentSchoolYearIsNull() throws Exception{
        //arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        SchoolYearRepository syr1 = mock(SchoolYearRepository.class);
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "14-05-2007", assistantProfessor, 70, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        //act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(per1,syr1, programmeList);
        boolean isCreated = controller.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertFalse(isCreated);
    }

    @Test
    void shouldReturnFalseIfNotCreateProgrammeEdition() throws Exception{
        //arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        SchoolYearRepository syr1 = mock(SchoolYearRepository.class);
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "14-05-2007", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        syr1.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");

        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(per1, syr1, programmeList);

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
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010", assistantProfessor,100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        syr1.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(null, syr1, programmeList);

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
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010", assistantProfessor,100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);

        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(per1, null, programmeList);

        //act
        boolean isCreated = ctrl.createAProgrammeEditionInTheCurrentSchoolYear(p1);
        //assert
        assertFalse(isCreated);
    }

//                                    ISOLATED UNIT TESTS

    @Test
    void shouldReturnListOfProgrammesInTheRepository() throws Exception {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - getProgrammeList
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        Teacher teacher = mock(Teacher.class);
        DegreeType master = mock(DegreeType.class);
        Department cse = mock(Department.class);
        US18_CreateProgrammeEditionForCurrentSchoolYearController ctrl = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);
        List<Programme> expectedProgrammes = List.of(new Programme("Computer Engineering", "CE", 20, 6, master, cse, teacher), new Programme("Matemática", "MAT", 20, 6, master, cse, teacher));
        when(ctrl.getProgrammeList()).thenReturn(expectedProgrammes);

        // Act
        List<Programme> result = ctrl.getProgrammeList();

        // Assert
        assertEquals(expectedProgrammes, result);
        assertEquals(2, result.size());
        verify(programmeList).getAllProgrammes();
    }
    @Test
    void shouldNotCreateProgrammeEditionIfSchoolYearIsNull() throws Exception{

        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - CreateProgrammeEditionInTheCurrentSchoolYear

        // Arrange
        ProgrammeList pl = mock(ProgrammeList.class);
        SchoolYearRepository sy = mock(SchoolYearRepository.class);
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);

        Programme p1 = mock(Programme.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, sy, pl);

        when (sy.getCurrentSchoolYear()).thenReturn(null);

        // Act
        boolean result = controller.createAProgrammeEditionInTheCurrentSchoolYear(p1);

        // Assert
        assertFalse(result);
    }
}