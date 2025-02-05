package PAI.controller;

import static org.junit.jupiter.api.Assertions.*;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanTest {

    @Test
    void testRegisterProgrammeInTheSystemFailure() throws Exception{
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

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

        // Chamar o metodo a testar com valores inválidos e verificar se lança exceção
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerProgrammeInTheSystemIncludingStudyPlan(null, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher);
        });
        assertEquals("Name must not be empty", exception.getMessage());
    }

    @Test
    void testAddCourseInStudyPlanSuccess() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

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
        boolean result = controller.addCourseToStudyPlan(1, 1, course1, programme);

        // Verificar resultado
        assertTrue(result);
    }

    @Test
    void testAddCourseInStudyPlanFailure() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

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
            controller.addCourseToStudyPlan(1, 1, course2, programme);
        });

        assertEquals("Invalid course or programme.", exception.getMessage());
    }

    @Test
    void testRegisterProgrammeInTheSystemCorrectly() throws Exception{
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

        // Criar dados de entrada
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

        boolean result = controller.registerProgrammeInTheSystemIncludingStudyPlan(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher);

        assertTrue(result);
    }

    @Test
    void testAddCourseInStudyPlanFailureBecauseAlreadyExistsInStudyPlan() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

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
        controller.addCourseToStudyPlan(1, 1, course1, programme);

        // Testar erro ao tentar adicionar um curso inválido
        Exception exception = assertThrows(Exception.class, () -> {
            controller.addCourseToStudyPlan(1, 1, course1, programme);
        });

        assertEquals("Cannot register course: Course already registered in Study Plan.", exception.getMessage());
    }

    @Test
    void testAddCourseInStudyPlanWithNullProgramme() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

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
            controller.addCourseToStudyPlan(1, 1, course2, null);
        });

        assertEquals("Programme cannot be null.", exception.getMessage());
    }

    @Test
    void testAddCourseInStudyPlanFailureBecauseCourseIsInvalid() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeList programmeList = new ProgrammeList();
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

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

        Programme programme = new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher);
        Course invalidCourse = new Course("Artificial Intelligence", "AI", 6, 1);

        Exception exception = assertThrows(Exception.class, () -> {
            controller.addCourseToStudyPlan(1, 1, invalidCourse, programme);
        });

        // Verificar que o retorno é false
        assertEquals("Invalid course or programme.", exception.getMessage());
    }
}