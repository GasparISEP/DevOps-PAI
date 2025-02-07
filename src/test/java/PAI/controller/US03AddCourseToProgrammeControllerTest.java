package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class US03AddCourseToProgrammeControllerTest {
    
    
    
    @Test
    void shouldCreateAddCourseToProgrammeController() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseRepository courseRepository = new CourseRepository();
        ProgrammeList programmeList = new ProgrammeList();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        //act
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //assert
        assertNotNull(US03AddCourseToProgrammeController);
    }


    @Test
    void shouldNotAddCourseToProgrammeIfCourseAlreadyInList() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        ProgrammeList programmeList = new ProgrammeList();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        Programme lei = programmeList.getAllProgrammes().get(0);
        lei.addCourseToAProgramme(courseRepository.getAllCourses().get(0));
        Course course1 = lei.getCourseList().get(0);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(lei, course1);
        });
    }

    @Test
    void shouldAddCourseToProgramme() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        ProgrammeList programmeList = new ProgrammeList();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        Course course1 = courseRepository.getAllCourses().get(0);
        Programme lei = programmeList.getAllProgrammes().get(0);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(lei, course1);
        //assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldThrowExceptionIfProgrammeListIsNull() throws Exception {
        // arrange
        ProgrammeList nullProgrammeList = null;
        CourseRepository courseRepository = new CourseRepository();
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(nullProgrammeList, courseRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseListIsNull() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        ProgrammeList programmeList = new ProgrammeList();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        Programme lei = programmeList.getAllProgrammes().get(0);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(lei, null);
        });
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneCourseInList() throws Exception {
        // arrange
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        // act
        List<Course> allCourses = courseRepository.getAllCourses();
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
