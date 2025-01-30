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
        ProgrammeList programmeList = new ProgrammeList();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        Programme lei = programmeList.getAllProgrammes().get(0);
        //act
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(lei);
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
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(lei);
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(course1);
        });
    }

    @Test
    void shouldAddCourseToProgrammeIfAllArgumentsAreValid() throws Exception {
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
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(lei);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(course1);
        //assert
        assertTrue(addCourseToProgramme);
    }
}
