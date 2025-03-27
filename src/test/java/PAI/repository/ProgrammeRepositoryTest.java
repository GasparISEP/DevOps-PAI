package PAI.repository;

import PAI.VOs.QuantEcts;
import PAI.domain.*;
import PAI.factory.ProgrammeCourseListFactoryImpl;

import PAI.factory.*;

import org.junit.jupiter.api.Test;



import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeRepositoryTest {


    @Test
    void shouldRegisterValidProgramme() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType master = mock(DegreeType.class);
        Department CSE = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);
        Programme programmeDouble = mock(Programme.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        when(IProgrammeFactoryDouble.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, CSE,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programmeDouble);

        // Act
        boolean result = programmeList.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, CSE,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);

        // Asssert
        assertTrue(result);
    }


    @Test
    void duplicatedShouldNotRegisterValidProgramme() throws Exception {

        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(IProgrammeFactoryDouble, programmeRepoListFactory);

        DegreeType master = mock(DegreeType.class);
        Department CSE = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);

        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);

        Programme programmeDouble = mock(Programme.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        when(IProgrammeFactoryDouble.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, CSE,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programmeDouble);

        // Act
        programmeList.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, CSE,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);
        boolean result = programmeList.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, CSE,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);

        // Asssert
        assertFalse(result);
    }


    @Test
    void changeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        Programme programmeDouble = mock(Programme.class);
        Teacher teacher1 = mock(Teacher.class);
        when(programmeDouble.newProgrammeDirector(teacher1)).thenReturn(true);
        ProgrammeRepository programmeList = new ProgrammeRepository(IProgrammeFactoryDouble, programmeRepoListFactory);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, teacher1);

        // Assert
        assertTrue(result);
        verify(programmeDouble).newProgrammeDirector(teacher1);
    }

    @Test
    void shouldNotChangeProgrammedDirectorIfNewDirectorIsNull() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        Teacher teacher1 = null;
        Programme programmeDouble = mock(Programme.class);
        when(programmeDouble.newProgrammeDirector(teacher1)).thenReturn(false);
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, teacher1);

        // Asssert
        assertFalse(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeListListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(IProgrammeFactoryDouble, programmeListListFactory);
        Programme programmeDouble = mock(Programme.class);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, null);

        // Asssert
        assertFalse(result);
    }

    @Test
    void shouldReturnCourseList() throws Exception {
        //arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = mock(Programme.class);
        DegreeType degreeType1 = mock(DegreeType.class);
        Department department1 = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);
        QuantEcts quantEcts = mock(QuantEcts.class);


        when(IProgrammeFactory.registerProgramme("Engenharia Informática","LEI",quantEcts,2,degreeType1,department1, teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programme);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(IProgrammeFactory,programmeRepoListFactory);

        when(programme.addCourseToAProgramme(course1)).thenReturn(true);
        when(programme.addCourseToAProgramme(course2)).thenReturn(true);

        when(programme.getCourseList()).thenReturn(List.of(course1, course2));


        // act
        List<Course> courseList = programmeRepo.getCourseList(programme);

        //assert
        assertEquals(2, courseList.size(), "O número de cursos deve ser 2");
        assertTrue(courseList.contains(course1), "A lista deve conter o curso Programming");
        assertTrue(courseList.contains(course2), "A lista deve conter o curso Mathematics");

    }

    @Test
    void shouldReturnProgrammeWithTheRequiredName() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType master = mock(DegreeType.class);

        Department departmentDouble = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);

        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);
        QuantEcts quantEcts = mock(QuantEcts.class);

        Programme programme = new Programme("Computer Engineering", "CE", quantEcts, 6, master, departmentDouble,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);

        when(IProgrammeFactoryDouble.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, departmentDouble,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory))
                .thenReturn(programme);

        programmeRepo.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, departmentDouble,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);

        // Act
        Optional<Programme> result = programmeRepo.getProgrammeByName("Computer Engineering");

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnNullIfProgrammeWithTheRequiredNameDoesNotExist() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType master = mock(DegreeType.class);
        Department cse = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        Programme programme = mock(Programme.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);
        QuantEcts quantEcts = mock(QuantEcts.class);


        when(IProgrammeFactoryDouble.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, cse,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory))
                .thenReturn(programme);

        programmeList.registerProgramme("Computer Engineering", "CE", quantEcts, 6, master, cse,  teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);

        // Act
        Optional<Programme> result = programmeList.getProgrammeByName("Space Engineering");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnNullList() throws Exception {
        // Arrange
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(IProgrammeFactory, programmeRepoListFactory);

        List<Programme> programmeListMock = List.of(mock(Programme.class), mock(Programme.class));
        when(programmeRepoListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        String name2 = "Informatica";
        String acronym2= "INF";
        QuantEcts quantityOfEcts2 = mock(QuantEcts.class);
        int quantityOfSemesters2 = 4;
        DegreeType master2 = mock(DegreeType.class);
        Department cse2 = mock(Department.class);
        Teacher teacher2 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programme1);
        when(IProgrammeFactory.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);
        programmeRepo.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);

        //Act
        List<Programme> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertNotNull(programmeList, "The returned list should not be null");
    }

    @Test
    void shouldReturnCorrectProgrammeList() throws Exception {
        // Arrange
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(IProgrammeFactory, programmeRepoListFactory);

        List<Programme> programmeListMock = List.of(mock(Programme.class), mock(Programme.class));
        when(programmeRepoListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        // Act
        List<Programme> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertEquals(programmeListMock, programmeList, "The returned list should match the copied list");
    }

    @Test
    void shouldReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(IProgrammeFactory, programmeRepoListFactory);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);


        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory ,studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory ,studyPlanListFactory, studyPlanFactory, ICourseFactory);
        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym(acronym1);

        //Assert
        assertNotNull(programme);
        assertEquals(programme, programme1);

    }

    @Test
    void shouldNotReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(IProgrammeFactory, programmeRepoListFactory);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);


        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);

        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym("ENG");

        //Assert
        assertNull(programme);


    }

    @Test
    void shouldReturnAListOfProgrammeNamesMock() throws Exception {
        // SUT = ProgrammeList - getAllProgrammeNames
        // Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(IProgrammeFactory, programmeRepoListFactory);

        String name1 = "MATEMATICA";
        String name2 = "ENGENHARIA";
        String acronym1 = "MAT";
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programme1);
        when(IProgrammeFactory.registerProgramme(name2, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);
        programmeRepo.registerProgramme(name2, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, ICourseFactory);

        when(programme1.getProgrammeName()).thenReturn(name1);
        when(programme2.getProgrammeName()).thenReturn(name2);

        // Act
        List<String> listOfProgrammeNames = programmeRepo.getAllProgrammeNames();

        // Assert
        assertEquals(2, listOfProgrammeNames.size());
        assertTrue(listOfProgrammeNames.contains(name1));
        assertTrue(listOfProgrammeNames.contains(name2));
    }

    @Test
    void shouldReturnAnEmptyListOfProgrammeNamesIfThereAreNoProgrammesInTheProgrammeListMock() throws Exception {
        // SUT = ProgrammeList - getAllProgrammeNames
        // Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeListRepoFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(IProgrammeFactory, programmeListRepoFactory);

        // Act
        List<String> listOfProgrammeNames = programmeRepo.getAllProgrammeNames();

        // Assert
        assertEquals(0, listOfProgrammeNames.size());
    }
}