package PAI.repository;

import PAI.domain.*;
import PAI.factory.ProgrammeCourseListFactory;
import PAI.factory.ProgrammeFactory;
import PAI.factory.ProgrammeListArrayListFactory;
import PAI.factory.StudentFactory;
import PAI.factory.StudentListFactory;
import PAI.repository.ProgrammeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeListTest {
    @Test
    void duplicatedShouldNotRegisterValidProgramme() throws Exception {

        // Arrange
        ProgrammeFactory programmeFactoryDouble = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactoryDouble, programmeListArrayListFactory);

        DegreeType master = mock(DegreeType.class);
        Department CSE = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        Programme programmeDouble = mock(Programme.class);
        when(programmeFactoryDouble.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory)).thenReturn(programmeDouble);
        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory);
        // Act
        boolean result = programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory);

        // Asssert
        assertFalse(result);
    }

    @Test
    void changeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeFactory programmeFactoryDouble = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactoryDouble, programmeListArrayListFactory);
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
        ProgrammeFactory programmeFactoryDouble = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactoryDouble, programmeListArrayListFactory);
        Programme programmeDouble = mock(Programme.class);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, null);

        // Asssert
        assertFalse(result);
    }

    @Test
    void shouldReturnCourseList() throws Exception {
        //arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = mock(Programme.class);
        DegreeType degreeType1 = mock(DegreeType.class);
        Department department1 = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        when(programmeFactory.registerProgramme("Engenharia Informática", "LEI", 20, 2, degreeType1, department1, teacher, programmeCourseListFactory)).thenReturn(programme);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory, programmeListArrayListFactory);

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
        ProgrammeFactory programmeFactoryDouble = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactoryDouble, programmeListArrayListFactory);
        DegreeType master = mock(DegreeType.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        when(programmeFactoryDouble.registerProgramme("Computer Engineering", "CE", 20, 6, master, departmentDouble, teacher, programmeCourseListFactory))
                .thenReturn(programmeDouble);

        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, departmentDouble, teacher, programmeCourseListFactory);
        when(programmeDouble.hasThisProgrammeName("Computer Engineering")).thenReturn(true);

        // Act
        Optional<Programme> result = programmeList.getProgrammeByName("Computer Engineering");

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnNullIfProgrammeWithTheRequiredNameDoesNotExist() throws Exception {
        // Arrange
        ProgrammeFactory programmeFactoryDouble = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactoryDouble, programmeListArrayListFactory);
        DegreeType master = mock(DegreeType.class);
        Department cse = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        Programme programme = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        when(programmeFactoryDouble.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse, teacher, programmeCourseListFactory))
                .thenReturn(programme);

        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse, teacher, programmeCourseListFactory);

        // Act
        Optional<Programme> result = programmeList.getProgrammeByName("Space Engineering");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnNullList() throws Exception {
        // Arrange
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory, programmeListArrayListFactory);

        List<Programme> programmeListMock = List.of(mock(Programme.class), mock(Programme.class));
        when(programmeListArrayListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        // Act
        List<Programme> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertNotNull(programmeList, "The returned list should not be null");
    }

    @Test
    void shouldReturnCorrectProgrammeList() throws Exception {
        // Arrange
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory, programmeListArrayListFactory);

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
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory, programmeListArrayListFactory);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory);

        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym(acronym1);

        //Assert
        assertNotNull(programme);
        assertEquals(programme, programme1);

    }

    @Test
    void shouldNotReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory, programmeListArrayListFactory);

        String name1 = "MATEMATICA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);

        Programme programme1 = mock(Programme.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory);

        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym("ENG");

        //Assert
        assertNull(programme);


    }

    @Test
    void shouldReturnAListOfProgrammeNamesMock() throws Exception {
        // SUT = ProgrammeList - getAllProgrammeNames
        // Arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory, programmeListArrayListFactory);

        String name1 = "MATEMATICA";
        String name2 = "ENGENHARIA";
        String acronym1 = "MAT";
        int quantityOfEcts1 = 90;
        int quantityOfSemesters1 = 4;
        DegreeType master1 = mock(DegreeType.class);
        Department cse1 = mock(Department.class);
        Teacher teacher1 = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        when(programmeFactory.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory)).thenReturn(programme1);
        when(programmeFactory.registerProgramme(name2, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory);
        programmeRepo.registerProgramme(name2, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1, programmeCourseListFactory);

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
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeRepo = new ProgrammeList(programmeFactory, programmeListArrayListFactory);

        // Act
        List<String> listOfProgrammeNames = programmeRepo.getAllProgrammeNames();

        // Assert
        assertEquals(0, listOfProgrammeNames.size());
    }
}