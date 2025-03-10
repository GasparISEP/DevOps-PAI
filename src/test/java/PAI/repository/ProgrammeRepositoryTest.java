package PAI.repository;

import PAI.domain.*;
import PAI.factory.ProgrammeCourseListFactory;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.factory.ProgrammeRepositoryArrayListFactoryImpl;

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
        ProgrammeFactoryImpl programmeFactoryDouble = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactoryDouble,programmeListArrayListFactory);
        DegreeType master = mock(DegreeType.class);
        Department CSE = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);
        Programme programmeDouble = mock(Programme.class);
        when(programmeFactoryDouble.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programmeDouble);

        // Act
        boolean result = programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        // Asssert
        assertTrue(result);
    }


    @Test
    void duplicatedShouldNotRegisterValidProgramme() throws Exception {

        // Arrange
        ProgrammeFactoryImpl programmeFactoryDouble = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactoryDouble, programmeListArrayListFactory);

        DegreeType master = mock(DegreeType.class);
        Department CSE = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);

        Programme programmeDouble = mock(Programme.class);
        when(programmeFactoryDouble.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programmeDouble);

        // Act
        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);
        boolean result = programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        // Asssert
        assertFalse(result);
    }

    @Test
    void changeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeFactoryImpl programmeFactoryDouble = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactoryDouble, programmeListArrayListFactory);
        Teacher teacher1 = mock(Teacher.class);
        Programme programmeDouble = mock(Programme.class);
        doNothing().when(programmeDouble).newProgrammeDirector(teacher1);
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, teacher1);

        // Asssert
        assertTrue(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeFactoryImpl programmeFactoryDouble = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactoryDouble, programmeListArrayListFactory);
        Programme programmeDouble = mock(Programme.class);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, null);

        // Asssert
        assertFalse(result);
    }

    @Test
    void shouldReturnCourseList() throws Exception {
        //arrange
        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = mock(Programme.class);
        DegreeType degreeType1 = mock(DegreeType.class);
        Department department1 = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);


        when(programmeFactory.registerProgramme("Engenharia Informática","LEI",20,2,degreeType1,department1, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programme);
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory,programmeListArrayListFactory);

        when(programme.addCourseToAProgramme(course1)).thenReturn(true);
        when(programme.addCourseToAProgramme(course2)).thenReturn(true);

        when(programme.getCourseList()).thenReturn(List.of(course1, course2));


        // act
        List<Course> courseList = programmeList.getCourseList(programme);

        //assert
        assertEquals(2, courseList.size(), "O número de cursos deve ser 2");
        assertTrue(courseList.contains(course1), "A lista deve conter o curso Programming");
        assertTrue(courseList.contains(course2), "A lista deve conter o curso Mathematics");

    }

    @Test
    void shouldReturnProgrammeWithTheRequiredName() throws Exception {
        // Arrange
        ProgrammeFactoryImpl programmeFactoryDouble = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactoryDouble, programmeListArrayListFactory);
        DegreeType master = mock(DegreeType.class);

        Department departmentDouble = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);

        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, departmentDouble,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        when(programmeFactoryDouble.registerProgramme("Computer Engineering", "CE", 20, 6, master, departmentDouble,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory))
                .thenReturn(programme);

        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, departmentDouble,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        // Act
        Optional<Programme> result = programmeList.getProgrammeByName("Computer Engineering");

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnNullIfProgrammeWithTheRequiredNameDoesNotExist() throws Exception {
        // Arrange
        ProgrammeFactoryImpl programmeFactoryDouble = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactoryDouble, programmeListArrayListFactory);
        DegreeType master = mock(DegreeType.class);
        Department cse = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        Programme programme = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);


        when(programmeFactoryDouble.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory))
                .thenReturn(programme);

        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse,  teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        // Act
        Optional<Programme> result = programmeList.getProgrammeByName("Space Engineering");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnNullList() throws Exception {
        // Arrange
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        List<Programme> programmeListMock = List.of(mock(Programme.class), mock(Programme.class));
        when(programmeListArrayListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        String name2 = "Informatica";
        String acronym2= "INF";
        int quantityOfEcts2 = 80;
        int quantityOfSemesters2 = 4;
        DegreeType master2 = mock(DegreeType.class);
        Department cse2 = mock(Department.class);
        Teacher teacher2 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);

        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programme1);
        when(programmeFactory.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);
        programmeRepo.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        //Act
        List<Programme> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertNotNull(programmeList, "The returned list should not be null");
    }

    @Test
    void shouldReturnCorrectProgrammeList() throws Exception {
        // Arrange
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        List<Programme> programmeListMock = List.of(mock(Programme.class), mock(Programme.class));
        when(programmeListArrayListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        // Act
        List<Programme> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertEquals(programmeListMock, programmeList, "The returned list should match the copied list");
    }

    @Test
    void shouldReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);


        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);
        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym(acronym1);

        //Assert
        assertNotNull(programme);
        assertEquals(programme, programme1);

    }

    @Test
    void shouldNotReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);


        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym("ENG");

        //Assert
        assertNull(programme);


    }

    @Test
    void shouldReturnAListOfProgrammeNamesMock() throws Exception {
        // SUT = ProgrammeList - getAllProgrammeNames
        // Arrange
        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        String name1 = "MATEMATICA";
        String name2 = "ENGENHARIA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programme1);
        when(programmeFactory.registerProgramme(name2, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);
        programmeRepo.registerProgramme(name2, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

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
        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeRepositoryArrayListFactoryImpl programmeListArrayListFactory = mock(ProgrammeRepositoryArrayListFactoryImpl.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        // Act
        List<String> listOfProgrammeNames = programmeRepo.getAllProgrammeNames();

        // Assert
        assertEquals(0, listOfProgrammeNames.size());
    }
}