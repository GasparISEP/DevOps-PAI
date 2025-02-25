package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class US03AddCourseToProgrammeControllerTest {
    
    
    
    @Test
    void shouldCreateAddCourseToProgrammeController() throws Exception {
        // arrange
        CourseRepository courseRepository = mock(CourseRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        //act
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //assert
        assertNotNull(US03AddCourseToProgrammeController);
    }


    @Test
    void shouldNotAddCourseToProgrammeIfCourseAlreadyInList() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble = mock(Course.class);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        when(programmeDouble.addCourseToAProgramme(courseDouble)).thenThrow(new Exception("Course is already added to the programme."));
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, courseDouble);
        });
    }

    @Test
    void shouldAddCourseToProgramme() throws Exception {
        // arrange
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble = mock(Course.class);
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, courseDouble);
        //assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldThrowExceptionIfProgrammeListIsNull() {
        // arrange
        ProgrammeList nullProgrammeList = null;
        CourseRepository courseRepository = mock(CourseRepository.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(nullProgrammeList, courseRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseIsNull() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Programme programmeDouble = mock(Programme.class);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, null);
        });
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneCourseInList() throws Exception {
        // arrange
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Course courseDouble = mock(Course.class);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDouble);

        when(courseRepositoryDouble.getAllCourses()).thenReturn(courseList);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(mock(ProgrammeList.class), courseRepositoryDouble);

        // act
        List<Course> allCourses = US03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(1, allCourses.size());
    }

    @Test
    void shouldReturnCourseInList() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);

        // act
        List<Course> allCourses = courseRepository.getAllCourses();

        // assert
        Course expectedCourse = new Course("matemática", "MTA", 5, 1);
        assertEquals(expectedCourse, allCourses.get(0));
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneProgrammeInList() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        ProgrammeList programmeList = new ProgrammeList();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        // act
        List<Programme> allProgrammes = programmeList.getAllProgrammes();
        // assert
        assertEquals(1, allProgrammes.size());
    }

    @Test
    void shouldReturnProgrammeInList() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        ProgrammeList programmeList = new ProgrammeList();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);

        // act
        List<Programme> allProgrammes = programmeList.getAllProgrammes();

        // assert
        Programme expectedProgramme = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        assertEquals(expectedProgramme, allProgrammes.get(0));
    }

    @Test
    void shouldReturnAllProgrammes() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        ProgrammeList programmeList = new ProgrammeList();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);

        // act
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(programmeList, new CourseRepository());
        List<Programme> allProgrammes = controller.getAllProgrammes();

        // assert
        Programme expectedProgramme = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        assertEquals(expectedProgramme, allProgrammes.get(0));
    }

    @Test
    void shouldReturnAllCourses() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);

        // act
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(new ProgrammeList(), courseRepository);
        List<Course> allCourses = controller.getAllCourses();

        // assert
        Course expectedCourse = new Course("matemática", "MTA", 5, 1);
        assertEquals(expectedCourse, allCourses.get(0));
    }

    @Test
    void shouldThrowExceptionIfCourseRepositoryIsNull() {
        // arrange
        ProgrammeList programmeList = new ProgrammeList();

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeList, null);
        });
    }
}
