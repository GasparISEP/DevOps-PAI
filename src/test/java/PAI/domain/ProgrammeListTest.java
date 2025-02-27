package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeListTest {

    @Test
    void shouldRegisterValidProgramme() throws Exception {
        // Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList list = new ProgrammeList(programmeFactory);
        DegreeType master = mock(DegreeType.class);
        Department CSE = mock(Department.class);
        Teacher teacher = mock(Teacher.class);

        // Act
        boolean result = list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        // Asssert
        assertTrue(result);
    }


    @Test
    void duplicatedShouldNotRegisterValidProgramme() throws Exception {

        // Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList list = new ProgrammeList(programmeFactory);
        DegreeType master = mock(DegreeType.class);
        Department CSE = mock(Department.class);
        Teacher teacher = mock(Teacher.class);

        // Act
        list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        boolean result = list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

            // Asssert
        assertFalse(result);
    }

    @Test
    void changeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList list = new ProgrammeList(programmeFactory);
        Teacher teacher1 = mock(Teacher.class);

        //act + assert
        Programme CE = mock(Programme.class);

        // Act
        boolean result = list.changeProgrammeDirector(CE,teacher1);

        // Asssert
        assertTrue(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList list = new ProgrammeList(programmeFactory);

        //act + assert
        Programme CE = mock(Programme.class);

        // Act
        boolean result = list.changeProgrammeDirector(CE,null);

        // Asssert
        assertFalse(result);
    }

    @Test
    void shouldReturnCourseList() throws Exception {
        //arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = mock(Programme.class);
        DegreeType degreeType1 = mock(DegreeType.class);
        Department department1 = mock(Department.class);
        Teacher teacher = mock(Teacher.class);

        when(programmeFactory.registerProgramme("Engenharia Informática","LEI",20,2,degreeType1,department1,teacher)).thenReturn(programme);
        ProgrammeList list = new ProgrammeList(programmeFactory);

        when(programme.addCourseToAProgramme(course1)).thenReturn(true);
        when(programme.addCourseToAProgramme(course2)).thenReturn(true);

        when(programme.getCourseList()).thenReturn(List.of(course1,course2));


        // act
        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);
        List<Course> courseList = list.getCourseList(programme);

        //assert
        assertEquals(2, courseList.size(), "O número de cursos deve ser 2");
        assertTrue(courseList.contains(course1), "A lista deve conter o curso Programming");
        assertTrue(courseList.contains(course2), "A lista deve conter o curso Mathematics");

    }

    @Test
    void shouldReturnProgrammeWithTheRequiredName() throws Exception {
        // Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList repository = new ProgrammeList(programmeFactory);
        DegreeType master = mock(DegreeType.class);
        Department cse = mock(Department.class);
        Teacher teacher = mock(Teacher.class);

        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, cse, teacher);

        when(programmeFactory.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse, teacher))
                .thenReturn(programme);

        repository.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse, teacher);

        // Act
        Optional<Programme> result = repository.getProgrammeByName("Computer Engineering");

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnNullIfProgrammeWithTheRequiredNameDoesNotExist() throws Exception  {
        // Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);

        ProgrammeList repository = new ProgrammeList(programmeFactory);
        DegreeType master = mock(DegreeType.class);
        Department cse = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        Programme programme = mock(Programme.class);

        when(programmeFactory.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse, teacher))
                .thenReturn(programme);

        repository.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse, teacher);

        // Act
        Optional<Programme> result = repository.getProgrammeByName("Space Engineering");

        // Assert
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnAListOfProgrammes()throws Exception {

        //Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory);

        String name1 = "Informatica";
        String acronym1 = "INF";
        int quantityOfEcts1 = 80;
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

        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programmeFactory.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);
        programmeRepo.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2);

        //Act
        List<Programme> programmeList = programmeRepo.getAllProgrammes();

        //Assert

        assertEquals(2,programmeList.size());
        assertTrue(programmeList.contains(programme1));
        assertTrue(programmeList.contains(programme2));

    }
    @Test
    void shouldReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);

        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);

        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym(acronym1);

        //Assert
        assertNotNull(programme);
        assertEquals(programme,programme1);

    }
    @Test
    void shouldNotReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);

        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);

        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym("ENG");

        //Assert
        assertNull(programme);


    }


}