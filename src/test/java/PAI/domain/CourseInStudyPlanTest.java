package PAI.domain;

import PAI.factory.*;
import PAI.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanTest {

    @Test
    void ShouldConstructACourseInStudyPlanWithMock() throws Exception {

        //Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);

        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        //Act
        CourseInStudyPlan courseInStudyPlan = new CourseInStudyPlan(1, 1, course, programme);

        //Assert
        assertNotNull(courseInStudyPlan);
    }

    @Test
    void testEqualsMethod() throws Exception {
        // Arrange
        Course course1 = new Course("Programming", "PROG", 5, 1);
        Course course2 = new Course("Mathematics", "MATH", 6, 1);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        IProgrammeCourseListFactory IProgrammeCourseListFactory = mock(IProgrammeCourseListFactory.class);
        ICourseInStudyPlanFactory ICourseInStudyPlanFactory = mock(ICourseInStudyPlanFactory.class);
        IStudyPlanListFactory IStudyPlanListFactory = mock(IStudyPlanListFactory.class);
        IStudyPlanFactory IStudyPlanFactory = mock(IStudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);

        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher, IProgrammeCourseListFactory, ICourseInStudyPlanFactory, IStudyPlanListFactory, IStudyPlanFactory, ICourseFactory);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);

        CourseInStudyPlan courseInStudyPlan1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan courseInStudyPlan2 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan courseInStudyPlan3 = new CourseInStudyPlan(1, 1, course2, programme);

        // Act & Assert
        assertEquals(courseInStudyPlan1, courseInStudyPlan2, "Objects with the same course should be equal");
        assertNotEquals(courseInStudyPlan1, courseInStudyPlan3, "Objects with different courses should not be equal");
        assertNotEquals(courseInStudyPlan1, new Object(), "Different types should not be equal");
        assertNotEquals(courseInStudyPlan1, null, "Comparison with null should return false");
    }

    @Test
    void testEqualsMethodWithMock() throws Exception {
        // Arrange
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = mock(Programme.class);

        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        CourseInStudyPlan courseInStudyPlan1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan courseInStudyPlan2 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan courseInStudyPlan3 = new CourseInStudyPlan(1, 1, course2, programme);

        // Act & Assert
        assertEquals(courseInStudyPlan1, courseInStudyPlan2, "Objects with the same course should be equal");
        assertNotEquals(courseInStudyPlan1, courseInStudyPlan3, "Objects with different courses should not be equal");
        assertNotEquals(courseInStudyPlan1, new Object(), "Different types should not be equal");
        assertNotEquals(courseInStudyPlan1, null, "Comparison with null should return false");
    }

    @Test
    void testEqualsMethodWithDifferentSemestersAndYears() throws Exception {
        CourseListFactoryImpl courseListFactoryImpl = mock(CourseListFactoryImpl.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);
        CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
        Course course1 = new Course("Programming", "PROG", 5, 1);
        courseRepository.registerCourse("Programming", "PROG", 5, 1);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        IProgrammeCourseListFactory IProgrammeCourseListFactory = mock(IProgrammeCourseListFactory.class);
        ICourseInStudyPlanFactory ICourseInStudyPlanFactory = mock(ICourseInStudyPlanFactory.class);
        IStudyPlanListFactory IStudyPlanListFactory = mock(IStudyPlanListFactory.class);
        IStudyPlanFactory IStudyPlanFactory = mock(IStudyPlanFactory.class);

        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher, IProgrammeCourseListFactory, ICourseInStudyPlanFactory, IStudyPlanListFactory, IStudyPlanFactory, ICourseFactory);

        programme.addCourseToAProgramme(course1);

        CourseInStudyPlan course1Sem1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan course1Sem2 = new CourseInStudyPlan(2, 1, course1, programme); // Mesmo curso, diferente semestre
        CourseInStudyPlan course1Year2 = new CourseInStudyPlan(1, 2, course1, programme); // Mesmo curso, diferente ano

        assertEquals(course1Sem1, course1Sem2, "Courses in different semesters should not be equal");
        assertEquals(course1Sem1, course1Year2, "Courses in different years should not be equal");
    }

    @Test
    void testEqualsMethodWithDifferentSemestersAndYearsWithMock() throws Exception {
        Course course1 = mock(Course.class);
        Programme programme = mock(Programme.class);

        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        CourseInStudyPlan course1Sem1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan course1Sem2 = new CourseInStudyPlan(2, 1, course1, programme); // Mesmo curso, diferente semestre
        CourseInStudyPlan course1Year2 = new CourseInStudyPlan(1, 2, course1, programme); // Mesmo curso, diferente ano

        assertEquals(course1Sem1, course1Sem2, "Courses in different semesters should not be equal");
        assertEquals(course1Sem1, course1Year2, "Courses in different years should not be equal");
    }

    @Test
    void shouldConstructCourseInStudyPlanSuccessfully() throws Exception {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        // Definimos um programa com 6 semestres (par) e, por exemplo, 3 anos
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        // Act
        CourseInStudyPlan cis = new CourseInStudyPlan(1, 1, course, programme);

        // Assert (verifica os getters)
        assertNotNull(cis);
        assertEquals(course, cis.getCourse());
        assertEquals(1, cis.getSemester());
        assertEquals(1, cis.getCurricularYear());
        assertEquals(programme, cis.getProgramme());
    }

    @Test
    void shouldThrowExceptionForInvalidSemesterBelowRange() {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        // Qualquer programa com 6 semestres e 3 anos
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        // Act & Assert: semestre 0 é inválido
        Exception ex = assertThrows(Exception.class, () -> {
            new CourseInStudyPlan(0, 1, course, programme);
        });
        assertEquals("Invalid semester.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidSemesterAboveRange() {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        // Act & Assert: semestre 3 é inválido (válidos apenas 1 ou 2)
        Exception ex = assertThrows(Exception.class, () -> {
            new CourseInStudyPlan(3, 1, course, programme);
        });
        assertEquals("Invalid semester.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidCurricularYearBelowRange() {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        // Act & Assert: ano curricular 0 é inválido
        Exception ex = assertThrows(Exception.class, () -> {
            new CourseInStudyPlan(1, 0, course, programme);
        });
        assertEquals("Invalid curricular year.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidCurricularYearAboveRange() {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        // Act & Assert: ano curricular 4 (maior que 3) é inválido
        Exception ex = assertThrows(Exception.class, () -> {
            new CourseInStudyPlan(1, 4, course, programme);
        });
        assertEquals("Invalid curricular year.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionForSecondSemesterOfLastYearInOddSemesterProgramme() {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        // Programa com número ímpar de semestres, por exemplo, 5 semestres
        when(programme.getQuantityOfSemester()).thenReturn(5);
        // Suponhamos que calculateNumberOfYears(5) retorna 3
        when(programme.calculateNumberOfYears(5)).thenReturn(3);

        // Act & Assert: Tentar adicionar no 2º semestre do último ano (ano 3) deve lançar exceção
        Exception ex = assertThrows(Exception.class, () -> {
            new CourseInStudyPlan(2, 3, course, programme);
        });
        assertEquals("Course cannot be added to second semester of last year.", ex.getMessage());
    }

    @Test
    void shouldAllowSecondSemesterOfLastYearInEvenSemesterProgramme() throws Exception {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        // Programa com número par de semestres, por exemplo, 6 semestres
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        // Act: Criar curso no 2º semestre do último ano deve ser permitido
        CourseInStudyPlan cis = new CourseInStudyPlan(2, 3, course, programme);

        // Assert
        assertNotNull(cis);
        assertEquals(2, cis.getSemester());
        assertEquals(3, cis.getCurricularYear());
    }

    @Test
    void testEqualsMethodForSameCourse() throws Exception {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        // Definindo um programa válido
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        // Criar duas instâncias com o mesmo curso, mas com diferentes valores de semestre e ano
        CourseInStudyPlan cis1 = new CourseInStudyPlan(1, 1, course, programme);
        CourseInStudyPlan cis2 = new CourseInStudyPlan(2, 3, course, programme);

        // Act & Assert: De acordo com a implementação, a igualdade depende apenas do curso
        assertEquals(cis1, cis2);
    }

    @Test
    void testNotEqualsForDifferentCourses() throws Exception {
        // Arrange
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = mock(Programme.class);
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        CourseInStudyPlan cis1 = new CourseInStudyPlan(1, 1, course1, programme);
        CourseInStudyPlan cis2 = new CourseInStudyPlan(1, 1, course2, programme);

        // Act & Assert
        assertNotEquals(cis1, cis2);
    }

    @Test
    void testEqualsWithNullAndDifferentType() throws Exception {
        // Arrange
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        when(programme.getQuantityOfSemester()).thenReturn(6);
        when(programme.calculateNumberOfYears(6)).thenReturn(3);

        CourseInStudyPlan cis = new CourseInStudyPlan(1, 1, course, programme);

        // Act & Assert
        assertNotEquals(cis, null);
        assertNotEquals(cis, "uma string");
    }

    @Test
    void shouldReturnTrueWhenSameLocation() throws Exception {
        // Arrange

        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);
        when(programme.getQuantityOfSemester()).thenReturn(4);
        when(programme.calculateNumberOfYears(4)).thenReturn(2);

        CourseInStudyPlan courseInStudyPlan = new CourseInStudyPlan(1,1,course,programme);

        //act
        boolean result = courseInStudyPlan.equals(courseInStudyPlan);
        //assert
        assertTrue(result);
    }

    }
