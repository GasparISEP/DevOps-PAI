package PAI.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProgrammeTest {
    private Department _department;
    private Teacher _teacher;
    private DegreeType _degreeType;
    private ProgrammeCourseListFactory _programmeCourseListFactory;

    @BeforeEach
    void setup() throws Exception {
        _department = mock(Department.class);
        _teacher = mock(Teacher.class);
        _degreeType = mock(DegreeType.class);
        _programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
    }
    @Test
    void shouldReturnFalseWhenProgrammeIsNotInDepartment() throws Exception {
        // arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        Department department1 = mock(Department.class);

        // act
        boolean result = programme.isInDepartment(department1);
        // assert
        assertFalse(result);
    }

    //test ensures that the isInDepartment method returns true when the program is associated with the department
    @Test
    void shouldReturnTrueWhenProgrammeIsNotInDepartment() throws Exception {
        // arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        // act
        boolean result = programme.isInDepartment(_department);
        // assert
        assertTrue(result);
    }

    // Test to check the constructor
    @Test
    void createAProgramme () throws Exception {

        //arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        //assert
        assertNotNull(programme);
    }

    // Test to empty name in Programme
    @Test
    void emptyNameDontCreateAProgramme () throws IllegalArgumentException {
        //assert
        assertThrows(Exception.class, () -> new Programme("", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }


    // Test to a null name in Programme
    @Test
    void nullNameDontCreateAProgramme () throws IllegalArgumentException {
        //act + assert

        assertThrows(Exception.class, () -> new Programme(null, "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }


    // Test to empty Acronym in Programme
    @Test
    void emptyAcronymDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }

    // Test to a null Acronym in Programme
    @Test
    void nullAcronymDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", null, 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }

    // Test to check if negative number of ECTS doNot create a programme
    @Test
    void lessThan0ECTSDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", -1,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }

    // Test to check if number 0 of ECTS don't create a programme
    @Test
    void zeroECTSDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 0,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }


    @Test
    void moreThan30ECTSDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,6,_degreeType,_department, _teacher, _programmeCourseListFactory));

    }

    @Test
    void lessThanZeroSemestersDontCreateAProgramme () throws IllegalArgumentException {

        //act+ assert
        assertThrows(IllegalArgumentException.class, () -> new Programme("Computer Engineering", "CE", 30,-1,_degreeType,_department, _teacher, _programmeCourseListFactory));

    }

    @Test
    void ZeroSemestersDontCreateAProgramme () throws IllegalArgumentException {
        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 30,0,_degreeType,_department, _teacher, _programmeCourseListFactory));

    }

    @Test
    void specialCharactersInNameDontCreateAProgramme () throws IllegalArgumentException {
        //act + assert

        assertThrows(Exception.class, () -> new Programme("@Computer Science", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }


    @Test
    void numbersInAcronymDontCreateAProgramme () throws IllegalArgumentException {
        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "123", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }

    @Test
    void specialCharactersInAcronymDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "@CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory));
    }

    @Test
    void nullDegreeTypeCreateAnException(){
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 20,6,null,_department, _teacher, _programmeCourseListFactory));
    }

    @Test
    void nullDepartmentCreateAnException(){
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 20,6,_degreeType,null, _teacher, _programmeCourseListFactory));
    }

    @Test
    void nullTeacherCreateAnException(){
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department,null, _programmeCourseListFactory));
    }

    //equals

    @Test
    void equalsProgrammeReturnTrue () throws Exception {

        //arrange

        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertTrue(result);
    }

    @Test
    void returnFalseWhenAcronymIsDifferent () throws Exception {

        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        Programme CEE = new Programme("Computer Engineering", "CEE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }

    @Test
    void returnFalseWhenNameIsDifferent () throws Exception {

        //arrange
        Programme CE = new Programme("Computers Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }
    @Test
    void returnFalseWhenEctsIsDifferent () throws Exception {

        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 21,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }
    @Test
    void returnFalseWhenSemestersIsDifferent () throws Exception {

        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,5,_degreeType,_department, _teacher, _programmeCourseListFactory);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }

    @Test
    void returnFalseWhenOneOfTheProgrammesIsNull () throws Exception {

        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,5,_degreeType,_department, _teacher, _programmeCourseListFactory);
        Programme CEE = null;

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }

    @Test
    void equalsCompareObjectReturnTrue() throws Exception {

        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        //act
        boolean result = CE.equals(CE);

        //assert
        assertTrue(result);
    }

    @Test
    void equalsDontCompareDifferentObjectAndReturnFalse() throws Exception {

        //arrange
       Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);

        //act
        boolean result = CE.equals( _teacher);

        //assert
        assertFalse(result);
    }



    @Test
    void creatNewProgrammeDirector() throws Exception {
        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _programmeCourseListFactory);
        Teacher teacher1 = mock(Teacher.class);
        //act + assert
         CE.newProgrammeDirector(teacher1);


    }


    // AddCourseToASemesterOfProgramme tests

    @Test
    void shouldReturnTrueIfCourseIsAddedToAProgrammeCourseList() throws Exception {
        //arrange
        DegreeType degreeTypeDouble = mock(DegreeType.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);
        List<Course> courseListDouble = mock(List.class);
        when(_programmeCourseListFactoryImpl.createCourseList())
                .thenReturn(courseListDouble);

        Programme programme = new Programme("Engenharia Informática", "LEI", 30,
                2, degreeTypeDouble, departmentDouble, teacherDouble, _programmeCourseListFactory);
        Course courseDouble = mock(Course.class);
         //act
        boolean result = programme.addCourseToAProgramme(courseDouble);
        //assert
        assertTrue(result);
    }


    @Test
    void shouldThrowExceptionIfCourseAlreadyExistsInProgramme() throws Exception {
        //arrange
        DegreeType degreeTypeDouble = mock(DegreeType.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);
        Course courseDouble = mock(Course.class);
        List<Course> courseListDouble = mock(List.class);
        when(_programmeCourseListFactoryImpl.createCourseList())
                .thenReturn(courseListDouble);
        when(courseListDouble.contains(courseDouble)).thenReturn(true);
        Programme programme = new Programme("Engenharia Informática", "LEI", 30,
                2, degreeTypeDouble, departmentDouble, teacherDouble, _programmeCourseListFactory);

        //act & assert
        assertThrows(Exception.class, () -> programme.addCourseToAProgramme(courseDouble));
    }


    @Test
    void shouldReturnCourseLists() throws Exception {
        //arrange
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);
        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);
        //act
        List<Course> courseList = programme.getCourseList();
        //assert
        assertEquals(2, courseList.size(), "O número de cursos deve ser 2");
    }

    @Test
    void shouldReturnQuantityOfSemesters() throws Exception {
        // Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);
        // Act
        int quantityOfSemesters = programme.getQuantityOfSemester();

        // Assert
        assertEquals(6, quantityOfSemesters, "The quantity of semesters should be 6.");
    }

    @Test
    void shouldReturnCorrectQuantityOfEcts() throws Exception {
        // Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);
        // Act
        int quantityOfEcts = programme.getQuantityOfEcts();
        // Assert
        assertEquals(20, quantityOfEcts, "The quantity of ECTS should be 20.");
    }

    @Test
    void shouldCalculateNumberOfYearsDirectly() throws Exception {
        //arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);
        // Act + Assert for :Testar valores pares
        assertEquals(1, programme.calculateNumberOfYears(2));
        assertEquals(3, programme.calculateNumberOfYears(6));
        assertEquals(10, programme.calculateNumberOfYears(20));

        // Act + Assert for : Testar valores ímpares
        assertEquals(2, programme.calculateNumberOfYears(3));
        assertEquals(4, programme.calculateNumberOfYears(7));
        assertEquals(6, programme.calculateNumberOfYears(11));

        // Act + Assert for : Testar valores extremos
        assertEquals(1, programme.calculateNumberOfYears(1));
        assertEquals(50, programme.calculateNumberOfYears(99));
    }

    @Test
    void shouldReturnStudyPlan() throws Exception {
        //arrange
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);

        // act
        StudyPlan studyPlan = programme.getStudyPlan();

        //assert
        assertNotNull(studyPlan);
    }
    @Test
    void shouldReturnTrueIfNameIsAProgramme() throws Exception {
        // Arrange
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);

        // Act
        boolean result = p1.hasThisProgrammeName("Computer Engineering");

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameIsAProgramme() throws Exception {
        // Arrange
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);

        // Act
        boolean result = p1.hasThisProgrammeName("Space Engineering");

        // Assert
        assertFalse(result);
    }
    @Test
    void shouldNotReturnAcronym() throws Exception {

        //Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);

        //Act
        String expectedAcronym = programme.getAcronym();

        //Assert
        assertNotEquals("Invalid",expectedAcronym);
    }

    @Test
    void shouldReturnAcronym() throws Exception {

        //Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);

        //Act
        String expectedAcronym = programme.getAcronym();

        //Assert
        assertEquals("CE", expectedAcronym);
    }

    @Test
    void shouldReturnProgrammeName() throws Exception {
        // Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _programmeCourseListFactory);

        // Act
        String expectedProgrammeName = programme.getProgrammeName();

        // Assert
        assertEquals("Computer Engineering", expectedProgrammeName);
    }
}