package PAI.controller;

import static org.junit.jupiter.api.Assertions.*;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanTest {

    @Test
    void testRegisterProgrammeInTheSystemFailure() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan();
        controller.RegisterProgrammeInSystem(programmeList);

        // Criar dados de entrada
        String acronym = "EI";
        int quantityOfEcts = 180;
        int quantityOfSemesters = 6;
        DegreeType degreeType = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                new Department("CSE", "Computer Science Engineer"));
        CourseRepository courseRepository = new CourseRepository();


        // Chamar o metodo a testar com valores inválidos e verificar se lança exceção
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerProgrammeInTheSystem(null, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher, courseRepository);
        });

        assertEquals("Name must not be empty", exception.getMessage());
    }

    @Test
    void testRegisterCourseInStudyPlan_Success() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan();
        controller.RegisterProgrammeInSystem(programmeList);

        // Criar objetos necessários para o teste
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                new Department("CSE", "Computer Science Engineer"));
        CourseRepository courseRepository = new CourseRepository();
        Programme programme = new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher);
        Course course1 = new Course("Programming", "PROG", 5, 1);
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        programme.addCourseToAProgramme(course1);


        // Chamar o metodo a testar
        boolean result = controller.registerCourseInStudyPlan(1, 1, course1, programme);

        // Verificar resultado
        assertTrue(result);
    }

    @Test
    void testRegisterCourseInStudyPlan_Failure() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan();
        controller.RegisterProgrammeInSystem(programmeList);

        // Criar objetos necessários para o teste
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                new Department("CSE", "Computer Science Engineer"));
        CourseRepository courseRepository = new CourseRepository();
        Programme programme = new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher);
        Course course1 = new Course("Programming", "PROG", 5, 1);
        Course course2 = new Course("Programminga", "PRO", 5, 1);
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        programme.addCourseToAProgramme(course1);

        // Testar erro ao tentar adicionar um curso inválido
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerCourseInStudyPlan(1, 1, course2, programme);
        });

        assertEquals("The course provided is not part of the programme.", exception.getMessage());
    }
}