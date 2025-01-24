package PAI.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import PAI.domain.Course;
import PAI.domain.DegreeType;
import PAI.domain.Department;
import PAI.domain.Programme;
import PAI.domain.Teacher;
import PAI.domain.TeacherCategory;

public class AddCourseToProgrammeControllerTest {
    
    
    
    @Test
    void shouldCreateAddCourseToProgrammeController() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        //act
        AddCourseToProgrammeController addCourseToProgrammeController = new AddCourseToProgrammeController(lei);
        //assert
        assertNotNull(addCourseToProgrammeController);
    }

    @Test
    void shouldReturnExceptionIfProgrammeIsNullWhenCreatingAddCourseToProgrammeController() throws Exception {
        // arrange
        //act
        //assert
        assertThrows(IllegalArgumentException.class, () -> { 
            new AddCourseToProgrammeController(null);
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
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        AddCourseToProgrammeController addCourseToProgrammeController = new AddCourseToProgrammeController(lei);
        //act
        boolean addCourseToProgramme = addCourseToProgrammeController.addCourseToProgramme(1, course1);
        //assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldNotAddCourseToProgrammeIfSemesterGivenBelowFirstSemester() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        AddCourseToProgrammeController addCourseToProgrammeController = new AddCourseToProgrammeController(lei);
        //act
        boolean addCourseToProgramme = addCourseToProgrammeController.addCourseToProgramme(0, course1);
        //assert
        assertFalse(addCourseToProgramme);
    }

    @Test
    void shouldNotAddCourseToProgrammeIfSemesterGivenAboveLastSemester() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Course course1 = new Course("matemática", "MTA", 5, 1);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        AddCourseToProgrammeController addCourseToProgrammeController = new AddCourseToProgrammeController(lei);
        //act
        boolean addCourseToProgramme = addCourseToProgrammeController.addCourseToProgramme(3, course1);
        //assert
        assertFalse(addCourseToProgramme);
    }

    @Test
    void shouldNotAddCourseToProgrammeIfCourseIsNull() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710","A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal","20-12-2010", teacherCategory1, 100,department1);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Programme lei = new Programme("Engenharia Informática", "LEI", 30, 2, degree1, department1, teacher1);
        AddCourseToProgrammeController addCourseToProgrammeController = new AddCourseToProgrammeController(lei);
        //act
        boolean addCourseToProgramme = addCourseToProgrammeController.addCourseToProgramme(3, null);
        //assert
        assertFalse(addCourseToProgramme);
    }
}
