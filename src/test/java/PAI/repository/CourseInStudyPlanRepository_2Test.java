package PAI.repository;

import PAI.VOs.*;
import PAI.domain.CourseInStudyPlan_2;
import PAI.factory.ICourseInStudyPlanFactory_2;
import PAI.factory.ICourseInStudyPlanListFactory_2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanRepository_2Test {

    @Test
    public void testCreateCourseInStudyPlan_AddsNewCourse() throws Exception {
        // Arrange: criar mocks para as dependências
        ICourseInStudyPlanFactory_2 factory = mock(ICourseInStudyPlanFactory_2.class);
        ICourseInStudyPlanListFactory_2 listFactory = mock(ICourseInStudyPlanListFactory_2.class);
        List<CourseInStudyPlan_2> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        // Criar mocks para os parâmetros
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Simular o comportamento da fábrica para criar uma nova instância
        // A igualdade de CourseInStudyPlan_2 depende do CourseID, portanto,
        // chamadas com os mesmos parâmetros produzirão objetos iguais.
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID));

        CourseInStudyPlanRepository_2 repository = new CourseInStudyPlanRepository_2(factory, listFactory);

        // Act: criar o curso pela primeira vez
        boolean createdFirst = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        // Tentar criar novamente o mesmo curso (mesmo CourseID)
        boolean createdSecond = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertTrue(createdFirst, "Deve criar o curso na primeira chamada");
        assertFalse(createdSecond, "Não deve criar o curso novamente se já existir");
        assertEquals(1, repository.getAllCourseInStudyPlanList_2().size(), "A lista deve conter apenas um curso");
    }

    @Test
    public void testGetAllCourseInStudyPlanList_ReturnsAllCourses() throws Exception {
        // Arrange: criar mocks para as dependências
        ICourseInStudyPlanFactory_2 factory = mock(ICourseInStudyPlanFactory_2.class);
        ICourseInStudyPlanListFactory_2 listFactory = mock(ICourseInStudyPlanListFactory_2.class);
        List<CourseInStudyPlan_2> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        // Criar mocks para os parâmetros
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Configurar a fábrica para retornar novas instâncias
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID));
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID));

        CourseInStudyPlanRepository_2 repository = new CourseInStudyPlanRepository_2(factory, listFactory);

        // Act: criar dois cursos com CourseID diferentes
        boolean created1 = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID);
        boolean created2 = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID);
        List<CourseInStudyPlan_2> allCourses = repository.getAllCourseInStudyPlanList_2();

        // Assert
        assertTrue(created1, "Deve criar o primeiro curso");
        assertTrue(created2, "Deve criar o segundo curso");
        assertEquals(2, allCourses.size(), "A lista deve conter dois cursos");
    }

    @Test
    public void testFindByCourseInStudyPlanID_Found() throws Exception {
        // Arrange: criar mocks para a factory e a lista
        ICourseInStudyPlanFactory_2 factory = mock(ICourseInStudyPlanFactory_2.class);
        ICourseInStudyPlanListFactory_2 listFactory = mock(ICourseInStudyPlanListFactory_2.class);
        List<CourseInStudyPlan_2> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        // Criar mocks para os parâmetros
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Simular a criação de um novo CourseInStudyPlan_2
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID));

        CourseInStudyPlanRepository_2 repository = new CourseInStudyPlanRepository_2(factory, listFactory);

        // Act: criar um curso no repositório
        repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        // Recuperar o curso criado e o seu identificador
        CourseInStudyPlan_2 createdCourse = repository.getAllCourseInStudyPlanList_2().get(0);
        CourseInStudyPlanID courseInStudyPlanID = createdCourse.getCourseInStudyPlanID();

        // Act: procurar o curso pelo identificador
        Optional<CourseInStudyPlan_2> foundCourseOpt = repository.findByCourseInStudyPlanID(courseInStudyPlanID);

        // Assert: deve encontrar o curso e ser o mesmo que foi criado
        assertTrue(foundCourseOpt.isPresent(), "Deve encontrar o curso com o ID fornecido");
        assertEquals(createdCourse, foundCourseOpt.get(), "O curso encontrado deve ser igual ao criado");
    }

    @Test
    public void testFindByCourseInStudyPlanID_NotFound() throws Exception {
        // Arrange: criar mocks para a factory e a lista
        ICourseInStudyPlanFactory_2 factory = mock(ICourseInStudyPlanFactory_2.class);
        ICourseInStudyPlanListFactory_2 listFactory = mock(ICourseInStudyPlanListFactory_2.class);
        List<CourseInStudyPlan_2> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanRepository_2 repository = new CourseInStudyPlanRepository_2(factory, listFactory);

        // Act: criar um CourseInStudyPlanID que não exista na lista
        CourseInStudyPlanID nonExistentID = new CourseInStudyPlanID();
        Optional<CourseInStudyPlan_2> foundCourseOpt = repository.findByCourseInStudyPlanID(nonExistentID);

        // Assert: não deve encontrar nenhum curso
        assertFalse(foundCourseOpt.isPresent(), "Não deve encontrar um curso com um ID inexistente");
    }

}