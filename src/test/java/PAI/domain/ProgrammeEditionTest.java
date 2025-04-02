package PAI.domain;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionTest {
    private Programme _programme;
    private SchoolYear _schoolYear;
    private ProgrammeEdition _programmeEdition;


    @BeforeEach
    void setup() throws Exception {
        _programme = mock(Programme.class);
        _schoolYear = mock(SchoolYear.class);
        _programmeEdition = new ProgrammeEdition(_programme, _schoolYear);
    }

    //CONSTRUCTOR TESTS
    @Test
    void validProgrammeAndSchoolYearCreatesAProgrammeEdition() throws Exception {
        //SUT: ProgrammeEdition

        // Assert
        assertNotNull(_programmeEdition);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIsNull() throws Exception {
        //SUT: ProgrammeEdition

        // Act + Assert
        assertThrows(Exception.class, () -> new ProgrammeEdition(null, _schoolYear));
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIsNull() throws Exception {
        //SUT: ProgrammeEdition

        // Act + Assert
        assertThrows(Exception.class, () -> new ProgrammeEdition(_programme, null));
    }

    //EQUALS TESTS
//    @Test
//    void shouldReturnFalseIfObjectComparedIsNotProgrammeEdition() throws Exception {
//        //SUT: ProgrammeEdition
//
//        // Arrange
//        AccessMethod am1 = mock(AccessMethod.class);
//
//        // Act
//        boolean result = _programmeEdition.equals(am1);
//
//        // Assert
//        assertFalse(result);
//    }

    @Test
    void shouldReturnTrueIfTwoProgrammeEditionsHaveTheSameProgrammesAndSchoolYears() throws Exception {
        //SUT: ProgrammeEdition

        // Arrange
        ProgrammeEdition pe2 = new ProgrammeEdition(_programme, _schoolYear);
        when(_schoolYear.isSameSchoolYear(_schoolYear)).thenReturn(true);

        // Act

        boolean result = _programmeEdition.equals(pe2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionsHaveDifferentProgrammesAndSameSchoolYears() throws Exception {
        //SUT: ProgrammeEdition

        // Arrange
        Programme p2 = mock(Programme.class);
        ProgrammeEdition pe2 = new ProgrammeEdition(p2, _schoolYear);
        when(_schoolYear.isSameSchoolYear(_schoolYear)).thenReturn(true);
        // Act
        boolean result = _programmeEdition.equals(pe2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionsHaveDifferentSchoolYearsAndSameProgramme() throws Exception {
        //SUT: ProgrammeEdition

        // Arrange
        SchoolYear sy2 = mock(SchoolYear.class);
        ProgrammeEdition pe2 = new ProgrammeEdition(_programme, sy2);
        when(_schoolYear.isSameSchoolYear(sy2)).thenReturn(false);
        // Act
        boolean result = _programmeEdition.equals(pe2);

        // Assert
        assertFalse(result);
    }

    //US17
    @Test
    void testEqualsWithDifferentObjectType() throws Exception {
        //SUT: ProgrammeEdition

        // Act + Assert
        assertFalse(_programmeEdition.equals("Not a ProgrammeEdition"));
    }

    //US17
    @Test
    void testFindProgrammeInProgrammeEdition() throws Exception {
        //SUT: ProgrammeEdition

        // Arrange
        ProgrammeEdition programmeEdition = new ProgrammeEdition(_programme, _schoolYear);

        // act
        Programme result = programmeEdition.findProgrammeInProgrammeEdition();

        // Assert
        assertNotNull(result);
        assertEquals(_programme, result);
    }

    //US17
    @Test
    void testFindSchoolYearInProgrammeEdition() throws Exception {
        //SUT: ProgrammeEdition

        // Arrange
        ProgrammeEdition programmeEdition = new ProgrammeEdition(_programme, _schoolYear);
        //act
        SchoolYear result = programmeEdition.findSchoolYearInProgrammeEdition();
        // Assert
        assertNotNull(result);
        assertEquals(_schoolYear, result);
    }

    //Test ensures that method returns true when both the department and school year are correctly associated with the programme edition
    @Test
    void shouldReturnTrueWhenEditionIsAssociatedToDepartmentAndSchoolYear() throws Exception {
        //SUT: ProgrammeEdition
        // arrange
        Department department = mock(Department.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Programme programme = mock(Programme.class);
        when(programme.isInDepartment(department)).thenReturn(true);
        when(schoolYear.isSameSchoolYear(schoolYear)).thenReturn(true);
        ProgrammeEdition edition = new ProgrammeEdition(programme, schoolYear);
        // act
        boolean result = edition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear);

        // assert
        assertTrue(result);
    }

    //Test ensures that method returns false when schoolYear is not associated with the programme edition
    @Test
    void shouldReturnFalseWhenSchoolYearIsNotAssociated() throws Exception {
        //SUT: ProgrammeEdition
        // arrange
        Department department = mock(Department.class);
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);
        Programme programme = mock(Programme.class);


        when(programme.isInDepartment(department)).thenReturn(true);
        when(schoolYear1.isSameSchoolYear(schoolYear2)).thenReturn(false);
        ProgrammeEdition edition = new ProgrammeEdition(programme, schoolYear1);

        // act
        boolean result = edition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear2);

        // assert
        assertFalse(result);
    }

    //Test ensures that method returns false when department is not associated with the programme edition
    @Test
    void shouldReturnFalseWhenDepartmentIsNotAssociated() throws Exception {
        //SUT: ProgrammeEdition
        // arrange
        Department department1 = mock(Department.class);
        Department department2 = mock(Department.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Programme programme = mock(Programme.class);


        when(programme.isInDepartment(department1)).thenReturn(true);
        when(programme.isInDepartment(department2)).thenReturn(false);
        when(schoolYear.isSameSchoolYear(schoolYear)).thenReturn(true);
        ProgrammeEdition edition = new ProgrammeEdition(programme, schoolYear);

        // act
        boolean result = edition.isEditionAssociatedToDepartmentAndSchoolYear(department2, schoolYear);

        // assert
        assertFalse(result);
    }

    //Test ensures that method returns false when department and schoolYear are not associated with the programme edition
    @Test
    void shouldReturnFalseWhenNeitherDepartmentNorSchoolYearAreAssociated() throws Exception {
        //SUT: ProgrammeEdition
        // arrange
        Department department1 = mock(Department.class);
        Department department2 = mock(Department.class);
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);
        Programme programme = mock(Programme.class);
        ProgrammeEdition edition = new ProgrammeEdition(programme, schoolYear1);
        when(programme.isInDepartment(department1)).thenReturn(false);
        when(programme.isInDepartment(department2)).thenReturn(false);
        when(schoolYear1.isSameSchoolYear(schoolYear2)).thenReturn(false);
        // act
        boolean result = edition.isEditionAssociatedToDepartmentAndSchoolYear(department2, schoolYear2);

        // assert
        assertFalse(result);
    }

//                                ISOLATED UNIT TESTS

    @Test
    void shouldCreateAValidProgrammeEditionMock() throws Exception {
        // SUT = ProgrammeEdition
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        // Act
        ProgrammeEdition ProgrammeEdition = new ProgrammeEdition(programme, schoolYear);

        // Assert
        assertNotNull(ProgrammeEdition);
    }

    @Test
    void shouldThrowExceptionIfProgrammeGivenAsAParameterToCreateProgrammeEditionIsNullMock() {
        // SUT = ProgrammeEdition
        // Arrange
        Programme programme = null;
        SchoolYear schoolYear = mock(SchoolYear.class);

        // Act & Assert
        assertThrows(Exception.class, () -> new ProgrammeEdition(programme, schoolYear));
    }

    @Test
    void shouldThrowExceptionIfSchoolYearGivenAsAParameterToCreateProgrammeEditionIsNullMock() {
        // SUT = ProgrammeEdition
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = null;

        // Act & Assert
        assertThrows(Exception.class, () -> new ProgrammeEdition(programme, schoolYear));
    }

    @Test
    void shouldReturnFalseIfObjectComparedIsNotProgrammeEditionMock() throws Exception {
        // SUT = ProgrammeEdition - equals
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);

        // Act
        boolean result = programmeEdition.equals(programme);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTwoProgrammeEditionsHaveTheSameProgrammesAndSchoolYearsMock() throws Exception {
        // SUT = ProgrammeEdition - equals
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme, schoolYear);

        when(schoolYear.isSameSchoolYear(schoolYear)).thenReturn(true);

        // Act
        boolean result = programmeEdition.equals(programmeEdition2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionsHaveDifferentSchoolYearsMock() throws Exception {
        // SUT = ProgrammeEdition - equals
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme, schoolYear2);

        when(schoolYear.isSameSchoolYear(schoolYear2)).thenReturn(false);

        // Act
        boolean result = programmeEdition.equals(programmeEdition2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionsHaveDifferentProgrammeMock() throws Exception {
        // SUT = ProgrammeEdition - equals
        // Arrange
        Programme programme = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme2, schoolYear);

        when(schoolYear.isSameSchoolYear(schoolYear)).thenReturn(true);

        // Act
        boolean result = programmeEdition.equals(programmeEdition2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnProgrammeOfProgrammeEditionMock() throws Exception {
        // SUT = ProgrammeEdition - findProgrammeInProgrammeEdition
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);

        // Act
        Programme getProgramme = programmeEdition.findProgrammeInProgrammeEdition();

        // Assert
        assertEquals(programme, getProgramme);
    }

    @Test
    void shouldReturnSchoolYearOfProgrammeEditionMock() throws Exception {
        // SUT = ProgrammeEdition - findProgrammeInProgrammeEdition
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);

        // Act
        SchoolYear getSchoolYear = programmeEdition.findSchoolYearInProgrammeEdition();

        // Assert
        assertEquals(schoolYear, getSchoolYear);
    }

    @Test
    void shouldReturnTrueWhenDepartmentAndSchoolYearAreAssociatedToTheProgrammeEditionMock() throws Exception {
        // SUT = ProgrammeEdition - isEditionAssociatedToDepartmentAndSchoolYear
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Department department = mock(Department.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        when(programme.isInDepartment(department)).thenReturn(true);
        when(schoolYear.isSameSchoolYear(schoolYear2)).thenReturn(true);

        // Act
        boolean result = programmeEdition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDepartmentIsNotAssociatedToTheProgrammeEditionMock() throws Exception {
        // SUT = ProgrammeEdition - isEditionAssociatedToDepartmentAndSchoolYear
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Department department = mock(Department.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        when(programme.isInDepartment(department)).thenReturn(false);
        when(schoolYear.isSameSchoolYear(schoolYear2)).thenReturn(true);

        // Act
        boolean result = programmeEdition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearIsNotAssociatedToTheProgrammeEditionMock() throws Exception {
        // SUT = ProgrammeEdition - isEditionAssociatedToDepartmentAndSchoolYear
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Department department = mock(Department.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        when(programme.isInDepartment(department)).thenReturn(true);
        when(schoolYear.isSameSchoolYear(schoolYear2)).thenReturn(false);

        // Act
        boolean result = programmeEdition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearAndDepartmentAreNotAssociatedToTheProgrammeEditionMock() throws Exception {
        // SUT = ProgrammeEdition - isEditionAssociatedToDepartmentAndSchoolYear
        // Arrange
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        Department department = mock(Department.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        when(programme.isInDepartment(department)).thenReturn(false);
        when(schoolYear.isSameSchoolYear(schoolYear2)).thenReturn(false);

        // Act
        boolean result = programmeEdition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionHasCourseInProgrammeCourseList() throws Exception {
        Programme programmeDouble = mock(Programme.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        Course courseDouble = mock(Course.class);

        programmeDouble.addCourseToAProgramme(courseDouble);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programmeDouble, schoolYearDouble);

        when(programmeDouble.getCourseList()).thenReturn(List.of(courseDouble));

        //act
        boolean result = programmeEdition.isCourseInProgrammeCourseListByProgrammeEdition(programmeEdition, courseDouble);

        //assert
        assertTrue(result);
        assertEquals(programmeEdition.findProgrammeInProgrammeEdition(), programmeDouble);
        verify(programmeDouble).getCourseList();
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionDoesNotHaveCourseInProgrammeCourseList() throws Exception {
        Programme programmeDouble = mock(Programme.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        Course courseDouble = mock(Course.class);

        ProgrammeEdition programmeEdition = new ProgrammeEdition(programmeDouble, schoolYearDouble);

        when(programmeDouble.getCourseList()).thenReturn(List.of());

        //act
        boolean result = programmeEdition.isCourseInProgrammeCourseListByProgrammeEdition(programmeEdition, courseDouble);

        //assert
        assertFalse(result);
        assertEquals(programmeEdition.findProgrammeInProgrammeEdition(), programmeDouble);
        verify(programmeDouble).getCourseList();
    }
}