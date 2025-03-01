package PAI.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanTest {

    @Test
    void testRegisterProgrammeInTheSystemFailure() {
        //arrange
        ProgrammeList programmeList = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);
        });

        assertEquals("Programme List cannot be null.", exception.getMessage());
    }

    @Test
    void testAddCourseInStudyPlanSuccess() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseRepository courseRepository = new CourseRepository(courseFactory);
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

        // Criar objetos necessários para o teste
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        //CourseRepository courseRepository = new CourseRepository();
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
    void testRegisterProgrammeInTheSystemCorrectly() throws Exception{
        // Criar as instâncias reais das classes necessárias
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

        // Criar dados de entrada
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);

        boolean result = controller.registerProgrammeInTheSystemIncludingStudyPlan(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher);

        assertTrue(result);
    }

    @Test
    void testAddCourseInStudyPlanWithNullProgramme() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseRepository courseRepository = new CourseRepository(courseFactory);
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

        // Criar objetos necessários para o teste
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = new DegreeType("Master", 240);
        Department department = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        //CourseRepository courseRepository = new CourseRepository();
        Programme programme = new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher);
        Course course1 = new Course("Programming", "PROG", 5, 1);
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        programme.addCourseToAProgramme(course1);

        // Testar erro ao tentar adicionar um curso inválido
        Exception exception = assertThrows(Exception.class, () -> {
            controller.addCourseToStudyPlan(1, 1, course1, null);
        });

        assertEquals("Programme cannot be null.", exception.getMessage());
    }
}