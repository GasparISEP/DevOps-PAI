package PAI.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PAI.domain.*;
import PAI.factory.ProgrammeCourseListFactory;
import PAI.factory.ProgrammeFactory;
import PAI.factory.ProgrammeListArrayListFactory;
import PAI.repository.CourseRepository;
import PAI.repository.ProgrammeList;
import PAI.repository.StudyPlan;
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
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory,programmeListArrayListFactory);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

        // Criar objetos necessários para o teste
        Programme programme = mock(Programme.class);
        Course course = mock(Course.class);
        StudyPlan studyPlan = mock(StudyPlan.class);

        when(programme.addCourseToAProgramme(course)).thenReturn(true);
        when(programme.getStudyPlan()).thenReturn(studyPlan);
        when(studyPlan.addCourseToStudyPlan(1,1,course,programme)).thenReturn(true);

        // Chamar o metodo a testar
        boolean result = controller.addCourseToStudyPlan(1, 1, course, programme);

        // Verificar resultado
        assertTrue(result);
    }

    @Test
    void testRegisterProgrammeInTheSystemCorrectly() throws Exception{
        // Criar as instâncias reais das classes necessárias
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory,programmeListArrayListFactory);
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

        // Criar dados de entrada
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        boolean result = controller.registerProgrammeInTheSystemIncludingStudyPlan(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher, programmeCourseListFactory);

        assertTrue(result);
    }

    @Test
    void testAddCourseInStudyPlanWithNullProgramme() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory,programmeListArrayListFactory);


        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

        CourseRepository courseRepository = mock(CourseRepository.class);

        Programme programme = mock(Programme.class);
        Course course = mock(Course.class);
        StudyPlan studyPlan = mock(StudyPlan.class);

        when(courseRepository.registerCourse("Programming", "PROG", 5, 1)).thenReturn(true);
        when(programme.addCourseToAProgramme(course)).thenReturn(true);
        when(programme.getStudyPlan()).thenReturn(studyPlan);
        when(studyPlan.addCourseToStudyPlan(1,1,course,programme)).thenReturn(true);

        // Testar erro ao tentar adicionar um curso inválido
        Exception exception = assertThrows(Exception.class, () -> {
            controller.addCourseToStudyPlan(1, 1, course, null);
        });

        assertEquals("Programme cannot be null.", exception.getMessage());
    }

    @Test
    void testShouldntAddCourseInStudyPlan() throws Exception {
        // Criar as instâncias reais das classes necessárias
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory,programmeListArrayListFactory);

        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan controller = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(programmeList);

        // Criar objetos necessários para o teste
        Programme programme = mock(Programme.class);
        Course course = mock(Course.class);
        StudyPlan studyPlan = mock(StudyPlan.class);

        when(programme.addCourseToAProgramme(course)).thenReturn(true);
        when(programme.getStudyPlan()).thenReturn(studyPlan);
        when(studyPlan.addCourseToStudyPlan(1,1,course,programme)).thenReturn(false);

        // Chamar o metodo a testar
        boolean result = controller.addCourseToStudyPlan(1, 1, course, programme);

        // Verificar resultado
        assertFalse(result);
    }
}