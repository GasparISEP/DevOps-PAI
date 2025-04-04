package PAI.repository.courseInStudyPlanRepo;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanDDDFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanDDDRepositoryImpTest {

    @Test
    void testCreateCourseInStudyPlanAddsNewCourse() throws Exception {
        // Arrange
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Simular o comportamento da fábrica para criar uma nova instância
        // A igualdade de CourseInStudyPlan_2 depende do CourseID, portanto, chamadas com os mesmos parâmetros produzirão objetos iguais.
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID));

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        // Act
        boolean createdFirst = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        boolean createdSecond = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertTrue(createdFirst);
        assertFalse(createdSecond);
        assertEquals(1, repository.getAllCourseInStudyPlanList_2().size());
    }

    @Test
    public void testGetAllCourseInStudyPlanListReturnsAllCourses() throws Exception {
        // Arrange: criar mocks para as dependências
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlanDDD(semester, curricularYear, courseID1, studyPlanID));
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlanDDD(semester, curricularYear, courseID2, studyPlanID));

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        // Act
        boolean created1 = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID);
        boolean created2 = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID);
        List<CourseInStudyPlanDDD> allCourses = repository.getAllCourseInStudyPlanList_2();

        // Assert
        assertTrue(created1);
        assertTrue(created2);
        assertEquals(2, allCourses.size());
    }

    @Test
    void testFindCourseInStudyPlanByIDFound() throws Exception {
        // Arrange
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID));

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        // Act
        repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        CourseInStudyPlanDDD createdCourse = repository.getAllCourseInStudyPlanList_2().get(0);
        CourseInStudyPlanID courseInStudyPlanID = createdCourse.identity();

        // Act
        Optional<CourseInStudyPlanDDD> foundCourseOpt = repository.ofIdentity(courseInStudyPlanID);

        // Assert
        assertTrue(foundCourseOpt.isPresent());
        assertEquals(createdCourse, foundCourseOpt.get());
    }

    @Test
    void testFindCourseInStudyPlanByIDNotFound() throws Exception {
        // Arrange
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Act
        CourseInStudyPlanID nonExistentID = new CourseInStudyPlanID(courseID, studyPlanID);
        Optional<CourseInStudyPlanDDD> foundCourseOpt = repository.ofIdentity(nonExistentID);

        // Assert
        assertFalse(foundCourseOpt.isPresent());
    }

    @Test
    void testSaveAddsCourseInStudyPlan() {
        //arrange
        CourseInStudyPlanDDD courseInStudyPlanDDD = mock(CourseInStudyPlanDDD.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        when(courseInStudyPlanDDD.identity()).thenReturn(courseInStudyPlanID);

        CourseInStudyPlanDDD saved = repository.save(courseInStudyPlanDDD);

        //act + assert
        assertEquals(courseInStudyPlanDDD, saved);
        assertTrue(repository.containsOfIdentity(courseInStudyPlanID));
    }

    @Test
    void testFindAllReturnsAllCoursesInStudyPlan() {
        //arrange
        CourseInStudyPlanDDD courseInStudyPlanDDD1 = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD courseInStudyPlanDDD2 = mock(CourseInStudyPlanDDD.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        repository.save(courseInStudyPlanDDD1);
        repository.save(courseInStudyPlanDDD2);

        //act
        List<CourseInStudyPlanDDD> all = (List<CourseInStudyPlanDDD>) repository.findAll();

        //assert
        assertEquals(2, all.size());
        assertTrue(all.contains(courseInStudyPlanDDD1) && all.contains(courseInStudyPlanDDD2));
    }

    @Test
    void testOfIdentityReturnsCorrectCourseInStudyPlan() {
        //arrange
        CourseInStudyPlanDDD courseInStudyPlanDDD = mock(CourseInStudyPlanDDD.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        when(courseInStudyPlanDDD.identity()).thenReturn(id);

        repository.save(courseInStudyPlanDDD);

        //act
        Optional<CourseInStudyPlanDDD> found = repository.ofIdentity(id);

        //assert
        assertTrue(found.isPresent());
        assertEquals(courseInStudyPlanDDD, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);

        //act
        Optional<CourseInStudyPlanDDD> found = repository.ofIdentity(id);

        //assert
        assertFalse(found.isPresent());
    }

    @Test
    void testContainsOfIdentityReturnsTrueWhenExists() {
        //arrange
        CourseInStudyPlanDDD courseInStudyPlanDDD = mock(CourseInStudyPlanDDD.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        when(courseInStudyPlanDDD.identity()).thenReturn(id);

        repository.save(courseInStudyPlanDDD);

        //act + assert
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityReturnsFalseWhenNotExists() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);

        //act + assert
        assertFalse(repository.containsOfIdentity(id));
    }

    @Test
    void shouldReturnsListOfCourseInStudyPlanWithStudyPlanID() {
        // Arrange
        ICourseInStudyPlanDDDFactory courseInStudyPlanFactoryDouble = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactoryDouble = mock(ICourseInStudyPlanDDDListFactory.class);
        StudyPlanID studyPlanIDDouble = mock(StudyPlanID.class);
        StudyPlanID otherStudyPlanIDDouble = mock(StudyPlanID.class);

        List<CourseInStudyPlanDDD> listOfCoursesInStudyPlan = new ArrayList<>();
        when(listFactoryDouble.newArrayList()).thenReturn(listOfCoursesInStudyPlan);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(
                courseInStudyPlanFactoryDouble, listFactoryDouble);

        CourseInStudyPlanDDD course1Double = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD course2Double = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD course3Double = mock(CourseInStudyPlanDDD.class);

        when(course1Double.getStudyplanID()).thenReturn(studyPlanIDDouble);
        when(course2Double.getStudyplanID()).thenReturn(studyPlanIDDouble);
        when(course3Double.getStudyplanID()).thenReturn(otherStudyPlanIDDouble);

        listOfCoursesInStudyPlan.add(course1Double);
        listOfCoursesInStudyPlan.add(course2Double);
        listOfCoursesInStudyPlan.add(course3Double);

        // Act
        List<CourseInStudyPlanDDD> result = repository.getCoursesInStudyPlanByStudyPlanID(studyPlanIDDouble);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(course1Double));
        assertTrue(result.contains(course2Double));
        assertFalse(result.contains(course3Double));
    }

    @Test
    void testGetCoursesInStudyPlanByStudyPlanIDMultipleMatches() {
        // Arrange: criar a fábrica e a lista simulada
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        // Criar dois objectos com o mesmo StudyPlanID e um com StudyPlanID diferente
        StudyPlanID studyPlanIDComum = mock(StudyPlanID.class);
        StudyPlanID studyPlanIDDiferente = mock(StudyPlanID.class);

        CourseInStudyPlanDDD cip1 = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD cip2 = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD cip3 = mock(CourseInStudyPlanDDD.class);

        when(cip1.getStudyplanID()).thenReturn(studyPlanIDComum);
        when(cip2.getStudyplanID()).thenReturn(studyPlanIDComum);
        when(cip3.getStudyplanID()).thenReturn(studyPlanIDDiferente);

        // Criar o repositório e adicionar os objectos
        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        repository.save(cip1);
        repository.save(cip3);
        repository.save(cip2);

        // Act: obter os cursos pelo StudyPlanID comum
        List<CourseInStudyPlanDDD> resultado = repository.getCoursesInStudyPlanByStudyPlanID(studyPlanIDComum);

        // Assert: verificar que apenas os objectos com o StudyPlanID comum são retornados
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(cip1));
        assertTrue(resultado.contains(cip2));
        assertFalse(resultado.contains(cip3));
    }

    @Test
    void testOfIdentityReturnsCorrectCourseInStudyPlanWhenMatchIsNotFirst() {
        // Arrange: criar a fábrica e a lista simulada
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        // Criar três objectos com identidades diferentes
        CourseInStudyPlanID id1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID id2 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID id3 = mock(CourseInStudyPlanID.class);

        CourseInStudyPlanDDD cip1 = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD cip2 = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD cip3 = mock(CourseInStudyPlanDDD.class);

        when(cip1.identity()).thenReturn(id1);
        when(cip2.identity()).thenReturn(id2);
        when(cip3.identity()).thenReturn(id3);

        // Adicionar os objectos ao repositório na ordem: cip1, cip2, cip3
        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        repository.save(cip1);
        repository.save(cip2);
        repository.save(cip3);

        // Act: procurar a identidade do objecto do meio
        Optional<CourseInStudyPlanDDD> resultado = repository.ofIdentity(id2);

        // Assert: verificar que o objecto correto é retornado
        assertTrue(resultado.isPresent());
        assertEquals(cip2, resultado.get());
    }

    @Test
    void testContainsOfIdentityIteratesThroughAllItems() {
        // Arrange: criar a fábrica e a lista simulada
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        // Criar dois objectos com identidades específicas
        CourseInStudyPlanID id1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID id2 = mock(CourseInStudyPlanID.class);

        CourseInStudyPlanDDD cip1 = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD cip2 = mock(CourseInStudyPlanDDD.class);

        when(cip1.identity()).thenReturn(id1);
        when(cip2.identity()).thenReturn(id2);

        // Criar o repositório e adicionar os objectos
        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        repository.save(cip1);
        repository.save(cip2);

        // Act & Assert: verificar que as identidades existentes são reconhecidas e que uma identidade inexistente retorna false
        assertTrue(repository.containsOfIdentity(id1));
        assertTrue(repository.containsOfIdentity(id2));

        CourseInStudyPlanID idInexistente = mock(CourseInStudyPlanID.class);
        assertFalse(repository.containsOfIdentity(idInexistente));
    }

}
