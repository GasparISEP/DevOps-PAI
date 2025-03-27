package PAI.domain;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
import PAI.factory.*;
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
    private IProgrammeCourseListFactory _I_programmeCourseListFactory;
    private ICourseInStudyPlanFactory _I_courseInStudyPlanFactory;
    private IStudyPlanListFactory _I_studyPlanListFactory;
    private IStudyPlanFactory _I_studyPlanFactory;
    private ICourseFactory _courseFactor;

    @BeforeEach
    void setup() throws Exception {
        _department = mock(Department.class);
        _teacher = mock(Teacher.class);
        _degreeType = mock(DegreeType.class);
        _I_programmeCourseListFactory = mock(IProgrammeCourseListFactory.class);
        _I_courseInStudyPlanFactory = mock(ICourseInStudyPlanFactory.class);
        _I_studyPlanListFactory = mock(IStudyPlanListFactory.class);
        _I_studyPlanFactory = mock(IStudyPlanFactory.class);
        _courseFactor = mock(ICourseFactory.class);

    }
    @Test
    void shouldReturnFalseWhenProgrammeIsNotInDepartment() throws Exception {
        // arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
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
        Programme programme = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        // act
        boolean result = programme.isInDepartment(_department);
        // assert
        assertTrue(result);
    }

    // Test to check the constructor
    @Test
    void createAProgramme () throws Exception {

        //arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        //assert
        assertNotNull(programme);
    }

    // Test to empty name in Programme
    @Test
    void emptyNameDontCreateAProgramme () throws IllegalArgumentException {
        //assert
        assertThrows(Exception.class, () -> new Programme("", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }


    // Test to a null name in Programme
    @Test
    void nullNameDontCreateAProgramme () throws IllegalArgumentException {
        //act + assert

        assertThrows(Exception.class, () -> new Programme(null, "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }


    // Test to empty Acronym in Programme
    @Test
    void emptyAcronymDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    // Test to a null Acronym in Programme
    @Test
    void nullAcronymDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", null, 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    // Test to check if negative number of ECTS doNot create a programme
    @Test
    void lessThan0ECTSDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", -1,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    // Test to check if number 0 of ECTS don't create a programme
    @Test
    void zeroECTSDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 0,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }


    @Test
    void moreThan30ECTSDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 31,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));

    }

    @Test
    void lessThanZeroSemestersDontCreateAProgramme () throws IllegalArgumentException {

        //act+ assert
        assertThrows(IllegalArgumentException.class, () -> new Programme("Computer Engineering", "CE", 30,-1,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));

    }

    @Test
    void ZeroSemestersDontCreateAProgramme () throws IllegalArgumentException {
        //act + assert
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 30,0,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));

    }

    @Test
    void specialCharactersInNameDontCreateAProgramme () throws IllegalArgumentException {
        //act + assert

        assertThrows(Exception.class, () -> new Programme("@Computer Science", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }


    @Test
    void numbersInAcronymDontCreateAProgramme () throws IllegalArgumentException {
        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "123", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void specialCharactersInAcronymDontCreateAProgramme () throws IllegalArgumentException {

        //act + assert

        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "@CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void nullDegreeTypeCreateAnException(){
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 20,6,null,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void nullDepartmentCreateAnException(){
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 20,6,_degreeType,null, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void nullTeacherCreateAnException(){
        assertThrows(Exception.class, () -> new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department,null, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    //equals

    @Test
    void equalsProgrammeReturnTrue () throws Exception {

        //arrange

        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertTrue(result);
    }

    @Test
    void returnFalseWhenAcronymIsDifferent () throws Exception {

        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Programme CEE = new Programme("Computer Engineering", "CEE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }

    @Test
    void returnFalseWhenNameIsDifferent () throws Exception {

        //arrange
        Programme CE = new Programme("Computers Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }
    @Test
    void returnFalseWhenEctsIsDifferent () throws Exception {

        //arrange
        QuantEcts quantEcts = new QuantEcts(21);
        QuantEcts quantEcts1 = new QuantEcts(20);
        QuantSemesters quantSemesters = new QuantSemesters(6);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Programme CE = new Programme(name, "CE", quantEcts,quantSemesters,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Programme CEE = new Programme(name, "CE", quantEcts1,quantSemesters,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }
    @Test
    void returnFalseWhenSemestersIsDifferent () throws Exception {

        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,5,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Programme CEE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }

    @Test
    void returnFalseWhenOneOfTheProgrammesIsNull () throws Exception {

        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,5,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Programme CEE = null;

        //act
        boolean result = CE.equals(CEE);

        //assert
        assertFalse(result);
    }

    @Test
    void equalsCompareObjectReturnTrue() throws Exception {

        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        //act
        boolean result = CE.equals(CE);

        //assert
        assertTrue(result);
    }

    @Test
    void equalsDontCompareDifferentObjectAndReturnFalse() throws Exception {

        //arrange
       Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //act
        boolean result = CE.equals( _teacher);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesIsEqualWithIsolation() throws Exception {
        //arrange
        Programme existingProgramme = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department,_teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor) ;
        Programme thisProgramme = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department,_teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor) ;
        //act
        boolean result = existingProgramme.isEquals(thisProgramme);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfProgrammesIsNotEqualWithIsolation() throws Exception {
        //arrange
        Programme existingProgramme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor) ;
        Programme thisProgramme = new Programme("Engenharia Informática", "LEI", 30, 2, _degreeType, _department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor) ;
        //act
        boolean result = existingProgramme.isEquals(thisProgramme);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenChangeDirector() throws Exception {
        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Teacher teacher1 = mock(Teacher.class);
        //act + assert
        boolean result = CE.newProgrammeDirector(teacher1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenChangeDirectorIsNull() throws Exception {
        //arrange
        Programme CE = new Programme("Computer Engineering", "CE", 20,6,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Teacher teacher1 = null;
        //act + assert
        boolean result = CE.newProgrammeDirector(teacher1);
        //assert
        assertFalse(result);
    }


    // AddCourseToASemesterOfProgramme tests

    @Test
    void shouldReturnTrueIfCourseIsAddedToAProgrammeCourseList() throws Exception {
        //arrange
        DegreeType degreeTypeDouble = mock(DegreeType.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);
        List<Course> courseListDouble = mock(List.class);
        when(_I_programmeCourseListFactory.createCourseList())
                .thenReturn(courseListDouble);

        Programme programme = new Programme("Engenharia Informática", "LEI", 30, 2, degreeTypeDouble, departmentDouble, teacherDouble, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        Course courseDouble = mock(Course.class);
         //act
        boolean result = programme.addCourseToAProgramme(courseDouble);
        //assert
        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfCourseAlreadyExistsInProgramme() throws Exception {
        //arrange
        DegreeType degreeTypeDouble = mock(DegreeType.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);
        Course courseDouble = mock(Course.class);
        List<Course> courseListDouble = mock(List.class);
        when(_I_programmeCourseListFactory.createCourseList())
                .thenReturn(courseListDouble);
        when(courseListDouble.contains(courseDouble)).thenReturn(true);
        Programme programme = new Programme("Engenharia Informática", "LEI", 30, 2, degreeTypeDouble, departmentDouble, teacherDouble, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        // act
        boolean result = programme.addCourseToAProgramme(courseDouble);
        // assert
        assertFalse(result);
    }


    @Test
    void shouldReturnCourseLists() throws Exception {
        //arrange
        Course course1 = mock(Course.class);
        Course course2 = mock(Course.class);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
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
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        // Act
        int quantityOfSemesters = programme.getQuantityOfSemester();

        // Assert
        assertEquals(6, quantityOfSemesters, "The quantity of semesters should be 6.");
    }

    @Test
    void shouldReturnCorrectQuantityOfEcts() throws Exception {
        // Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        // Act
        int quantityOfEcts = programme.getQuantityOfEcts();
        // Assert
        assertEquals(20, quantityOfEcts, "The quantity of ECTS should be 20.");
    }

    @Test
    void shouldCalculateNumberOfYearsDirectly() throws Exception {
        //arrange
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
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
        StudyPlan doubleStudyPlan = mock(StudyPlan.class);
        when(_I_studyPlanFactory.newStudyPlan(_I_courseInStudyPlanFactory, _I_studyPlanListFactory, _courseFactor)).thenReturn(doubleStudyPlan);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        // act
        StudyPlan result = programme.getStudyPlan();

        //assert
        assertEquals(result, doubleStudyPlan);
        }

    @Test
    void shouldReturnTrueIfNameIsAProgramme() throws Exception {
        // Arrange
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        Programme p1 = new Programme(programmeName, "CE", quantEcts, quantSemesters, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        // Act
        boolean result = p1.hasThisProgrammeName(programmeName);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameIsAProgramme() throws Exception {
        // Arrange
        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars programmeName2 = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        Programme p1 = new Programme(programmeName, "CE", quantEcts, quantSemesters, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);
        // Act
        boolean result = p1.hasThisProgrammeName(programmeName2);

        // Assert
        assertFalse(result);
    }
    @Test
    void shouldNotReturnAcronym() throws Exception {

        //Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //Act
        String expectedAcronym = programme.getAcronym();

        //Assert
        assertNotEquals("Invalid",expectedAcronym);
    }

    @Test
    void shouldReturnAcronym() throws Exception {

        //Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //Act
        String expectedAcronym = programme.getAcronym();

        //Assert
        assertEquals("CE", expectedAcronym);
    }

    @Test
    void shouldReturnProgrammeName() throws Exception {
        // Arrange
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        // Act
        String expectedProgrammeName = programme.getProgrammeName();

        // Assert
        assertEquals("Computer Engineering", expectedProgrammeName);
    }

    @Test
    void shouldReturnQuantOfSemesters () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Programme programme = new Programme(name, "CE", quantEcts, quantSemesters, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //Act
        QuantSemesters expectedQuantSemesters = programme.getQuantSemesters();

        //Assert
        assertEquals(expectedQuantSemesters, quantSemesters);
    }

    @Test
    void shouldReturnQuantEcts () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Programme programme = new Programme(name, "CE", quantEcts, quantSemesters, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //Act
        QuantEcts expectedQuantEcts = programme.getQuantEcts();

        //Assert
        assertEquals(expectedQuantEcts, quantEcts);
    }

    @Test
    void shouldCreateProgramme () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
        Programme programme = new Programme(name, "CE", quantEcts, quantSemesters, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor);

        //Act+Assert
        assertNotNull(programme);
    }

    @Test
    void shouldNotCreateProgrammeWhenQuantSemestersIsNull () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = null;
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");


        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "CE", quantEcts, quantSemesters, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void shouldNotCreateProgrammeWhenQuantEctsIsNull () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = null;
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "CE", quantEcts, quantSemesters, _degreeType, _department,  _teacher, _I_programmeCourseListFactory,_I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void shouldNotCreateProgrammeWhenNameIsEmpty () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "CE", quantEcts, quantSemesters, _degreeType, _department,  _teacher, _I_programmeCourseListFactory, _courseInStudyPlanFactory, _studyPlanListFactory, _studyPlanFactory, _courseFactor));
    }


    @Test
    void shouldNotCreateProgrammeWhenNameIsNull () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(null, "CE", quantEcts,quantSemesters,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void shouldNotCreateProgrammeWhenAcronymIsEmpty () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "", quantEcts,quantSemesters,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void shouldNotCreateProgrammeWhenAcronymIsNull () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, null, quantEcts,quantSemesters,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }


    @Test
    void shouldNotCreateProgrammeWhenAcronymHasNumbers () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "123", quantEcts,quantSemesters,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void shouldNotCreateProgrammeWhenAcronymHasSpecialChars () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "@CE", quantEcts,quantSemesters,_degreeType,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void shouldNotCreateProgrammeWhenDegreeTypeIsNull () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "CE", quantEcts,quantSemesters,null,_department, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void shouldNotCreateProgrammeWhenDepartmentIsNull () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "CE", quantEcts,quantSemesters,_degreeType,null, _teacher, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

    @Test
    void shouldNotCreateProgrammeWhenTeacherIsNull () throws Exception {
        //Arrange
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(20);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

        //Act+Assert
        assertThrows(Exception.class, () -> new Programme(name, "CE", quantEcts,quantSemesters,_degreeType,_department,null, _I_programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _courseFactor));
    }

}
