package PAI.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

public class US03AddCourseToProgrammeControllerTest {
    
    
    
    @Test
    void shouldCreateAddCourseToProgrammeController() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseRepository courseRepository = new CourseRepository();
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1, courseRepository);
        //act
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(lei);
        //assert
        assertNotNull(US03AddCourseToProgrammeController);
    }

    @Test
    void shouldReturnExceptionIfProgrammeIsNullWhenCreatingAddCourseToProgrammeController() throws Exception {
        // arrange
        //act
        //assert
        assertThrows(IllegalArgumentException.class, () -> { 
            new US03_AddCourseToProgrammeController(null);
        });    
    }


    @Test
    void shouldAddCourseToProgrammeIfAllArgumentsAreValid() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1, courseRepository);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(lei);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(course1);
        //assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shoudNotAddCourseToProgrammeIfCourseNotInCourseRepository() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1, courseRepository);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(lei);
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(course1);
        });
    }

    @Test
    void shouldNotAddCourseToProgrammeIfCourseAlreadyInList() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1, courseRepository);
        lei.addCourseToAProgramme(course1);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(lei);
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(course1);
        });
    }

    @Test
    void shouldNotAddCourseToProgrammeIfCourseIsNull() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseRepository courseRepository = new CourseRepository();
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1,courseRepository);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(lei);
        // act & assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(null);
        });
    }
}
