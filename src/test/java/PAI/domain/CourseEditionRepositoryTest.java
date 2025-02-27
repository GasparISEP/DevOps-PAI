package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionRepositoryTest {

    @Test
    void shouldReturnTrueIfCourseEditionHasBeenCreated() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);
        CourseEdition courseEditionDouble = mock (CourseEdition.class);

        when(courseEditionFactoryDouble.newCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(courseEditionDouble);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseDouble,programmeEditionDouble);

        //assert
        assertTrue(result);

    }

    @Test
    void shouldReturnTrueIfTheAddedCourseEditionHasDifferentCourseButTheSameProgrammeEdition() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);

        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble)).thenReturn(courseEditionDouble1);
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble2, programmeEditionDouble)).thenReturn(courseEditionDouble2);

        courseEditionRepository.createAndSaveCourseEdition(courseDouble1,programmeEditionDouble);

        //act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseDouble2,programmeEditionDouble);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTheAddedCourseEditionHasSameCourseButDifferentProgrammeEdition() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);

        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble2)).thenReturn(courseEditionDouble2);

        //act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseDouble1,programmeEditionDouble2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTheCourseEditionIsAlreadyRegistered() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);

        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenReturn(courseEditionDouble1);

        courseEditionRepository.createAndSaveCourseEdition(courseDouble1,programmeEditionDouble1);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseDouble1,programmeEditionDouble1);
        //Assert
        assertFalse(result);

    }


    @Test
    void shouldReturnFalseIfCourseOrProgrammeEditionAreNull() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository(courseEditionFactoryDouble);

        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);

        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenThrow();

        //Act
        boolean result = courseEditionRepository1.createAndSaveCourseEdition(courseDouble1, programmeEditionDouble1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseEditionContainsRuc() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010",assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository(courseEditionFactory);
        courseEditionRepository1.createAndSaveCourseEdition(c1,pE1);
        CourseEdition ce1 = new CourseEdition(c1, pE1);

        Teacher ruc = new Teacher("AAA", "Joao Costa", "aaa@isep.ipp.pt", "123456780", "A106", "Doutoramento em Artes Circenses, 2004, ISEP", "Rua São Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);

        //act
        boolean result = courseEditionRepository1.setRucInACourseEdition(ce1, ruc);
        //assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseIfCourseEditionDoesNotContainsRuc() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010",assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository(courseEditionFactory);
        courseEditionRepository1.createAndSaveCourseEdition(c1,pE1);
        CourseEdition ce1 = new CourseEdition(c1, pE1);
        Teacher ruc1 = new Teacher("AAA", "Joao Costa", "aaa@isep.ipp.pt", "123456780", "A106", "Doutoramento em Artes Circenses, 2004, ISEP", "Rua São Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        courseEditionRepository1.setRucInACourseEdition(ce1, ruc1);
        Teacher ruc2 = new Teacher("ABA", "Mariana Antunes", "aba@isep.ipp.pt", "123456788", "A106", "Doutoramento em Artes Circenses, 2004, ISEP", "Rua São Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);

        //act
        boolean result = courseEditionRepository1.setRucInACourseEdition(ce1,ruc2);
        //assert
        assertFalse(result);
    }


    @Test
    void shouldReturnCourseEditionsWithSameProgrammeEdition() throws Exception {
        // Arrange
        Course doubleCourse1 = mock(Course.class);
        Course doubleCourse2 = mock(Course.class);
        ProgrammeEdition doubleProgrammeEdition1 = mock(ProgrammeEdition.class);

        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactory);
        courseEditionRepository.createAndSaveCourseEdition(doubleCourse1, doubleProgrammeEdition1);
        courseEditionRepository.createAndSaveCourseEdition(doubleCourse2, doubleProgrammeEdition1);


        // Act
        List<CourseEdition> result = courseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition1);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(new CourseEdition(doubleCourse1, doubleProgrammeEdition1)));
        assertTrue(result.contains(new CourseEdition(doubleCourse2, doubleProgrammeEdition1)));

    }


    @Test
    void shouldReturnProgrammeEditionWhenCourseEditionExists() throws Exception {
        // Arrange
        Course doubleCourse1 = mock(Course.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionFactory doubleCourseEditionFactory = mock(CourseEditionFactory.class);
        CourseEditionRepository repository = new CourseEditionRepository(doubleCourseEditionFactory);

        when(doubleCourseEditionFactory.newCourseEdition(doubleCourse1, programmeEdition)).thenReturn(courseEdition);

        repository.createAndSaveCourseEdition(doubleCourse1, programmeEdition);

        when(repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition)).thenReturn(programmeEdition);

        // Act
        ProgrammeEdition result = repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition);

        // Assert
        assertNotNull(result, "The returned ProgrammeEdition should not be null.");
        assertEquals(programmeEdition, result, "The returned ProgrammeEdition should match the one associated with the CourseEdition.");
    }

    //US16
    @Test
    void shouldReturnExceptionBecauseCourseEditionDoesNotExist_ListEmpty() throws Exception {
        //arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactory);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionDouble);
        });

        // assert
        assertEquals("The course edition does not belong to the course Edition Repository.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionBecauseCourseEditionDoesNotExist_ListWithElements() throws Exception {
        // arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactory);

        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);
        Course mockCourse1 = mock(Course.class);
        Course mockCourse2 = mock(Course.class);

        CourseEdition existingCourseEditionDouble = mock(CourseEdition.class);
        CourseEdition courseEditionToFindDouble = mock(CourseEdition.class);

        repository.createAndSaveCourseEdition(mockCourse1, mockProgrammeEdition);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionToFindDouble);
        });

        // assert
        assertEquals("The course edition does not belong to the course Edition Repository.", exception.getMessage());
    }

    @Test
    void shouldReturnTheProgrammeEditionThatBelongsToACourseEdition() throws Exception {
        //arrange
        CourseEditionFactory courseEditionFactory = mock(CourseEditionFactory.class);
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactory);

        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock(Course.class);

        when(courseEditionDouble.whatProgrammeEditionBelongsThisCourseEdition()).thenReturn(programmeEditionDouble);
        when(courseEditionFactory.newCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(courseEditionDouble);

        repository.createAndSaveCourseEdition(courseDouble, programmeEditionDouble);

        //act
        ProgrammeEdition result = repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionDouble);

        //assert
        assertEquals(programmeEditionDouble, result);
    }

    @Test
    void shouldReturnListOfCourseEditions() throws Exception {
        //arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("AAA", "João Costa", "AAA@isep.ipp.pt", "123456789",
                "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010",
                assistantProfessor, 100, CSE);

        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);

        Course c1 = new Course ("Informatics", "INF", 6, 1);
        repo1.createAndSaveCourseEdition(c1, pE1);


        Course c2 = new Course ("Cidadania", "CID", 6, 1);
        repo1.createAndSaveCourseEdition(c2, pE1);

        //act
        List<CourseEdition> result = repo1.getCourseEditions();

        //assert
        assertEquals(2, result.size());
    }
}