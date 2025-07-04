
package PAI.domain.studentGrade;

import PAI.VOs.*;

import PAI.service.studentGrade.IStudentGradeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentGradeFactoryImplTest {

    @Test
    public void shouldCreateStudentGradeWhenAllValidWithoutSetup() throws Exception {
        // Arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);
        Grade grade = mock(Grade.class);

        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);


        when(studentGradeService.isDateGradeInRangeWithSchoolYear(courseEditionID, date))
                .thenReturn(true);
        when(studentGradeService.hasStudentAlreadyGradeAtThisCourseEdition(studentID, courseEditionID))
                .thenReturn(false);


        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();

        // Act
        StudentGrade result = factory.createGradeStudent(grade, date, studentID, courseEditionID);

        // Assert
        assertNotNull(result, "Factory should return a StudentGrade when inputs are valid");
        assertEquals(grade, result.getGrade());
        assertEquals(date, result.getDate());
        assertEquals(studentID, result.getStudentID());
        assertEquals(courseEditionID, result.getCourseEditionID());

    }

    @Test
    void shouldThrowWhenGradeIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Date date = mock(Date.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                factory.createGradeStudent(null, date, student, courseEditionID)
        );

        // Verify
        assertEquals("Grade cannot be null.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenDateIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Grade grade = mock(Grade.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                factory.createGradeStudent(grade, null, student, courseEditionID)
        );

        // Verify
        assertEquals("Date cannot be null or empty!", ex.getMessage());
    }

    @Test
    void shouldThrowWhenStudentIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                factory.createGradeStudent(grade, date, null, courseEditionID)
        );

        // Verify
        assertEquals("Student cannot be null", ex.getMessage());
    }

    @Test
    void shouldThrowWhenCourseEditionIdIsNull() {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID student = mock(StudentID.class);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                factory.createGradeStudent(grade, date, student, null)
        );

        // Verify
        assertEquals("Course Edition cannot be null", ex.getMessage());
    }

    @Test
    void shouldCreateStudentGradeWhenAllNonNull() throws Exception {
        // Arrange
        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        // Act
        StudentGrade studentGrade = factory.createGradeStudent(grade, date, student, courseEditionID);

        // Assert
        assertNotNull(studentGrade);
        assertEquals(grade, studentGrade.getGrade());
        assertEquals(date, studentGrade.getDate());
        assertEquals(student, studentGrade.getStudentID());
        assertEquals(courseEditionID, studentGrade.getCourseEditionID());
    }

    @Test
    void shouldReturnStudentGradeFromDataModel() throws Exception{

        //arrange
        StudentGradeFactoryImpl studentGradeFactory = new StudentGradeFactoryImpl();

        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        //act
        StudentGrade result = studentGradeFactory.createGradeStudent(grade,date,studentID,courseEditionID,studentGradeID, studentGradeGeneratedID);

        //assert
        assertNotNull(result);
    }
//    @Test
//    void shouldThrowExceptionWhenStudentHasAlreadyGradeAndDateIsInRange() throws Exception {
//
//        // arrange
//        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
//        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();
//        Grade grade = mock(Grade.class);
//        Date date = mock(Date.class);
//        StudentID studentID = mock(StudentID.class);
//        CourseEditionID courseEditionID = mock(CourseEditionID.class);
//        LocalDate localDate = LocalDate.of(2024, 4, 30);
//        when(date.getLocalDate()).thenReturn(localDate);
//        SchoolYear schoolYear = mock(SchoolYear.class);
//        when(schoolYear.getStartDate()).thenReturn(new Date(LocalDate.of(2024, 1, 1)));
//        when(schoolYear.getEndDate()).thenReturn(new Date(LocalDate.of(2024, 12, 31)));
//        CourseEdition courseEdition = mock(CourseEdition.class);
//        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
//        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
//        SchoolYearID schoolYearID = mock(SchoolYearID.class);
//        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
//        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
//        when(programmeEditionRepository.ofIdentity(programmeEditionID)).thenReturn(Optional.of(programmeEdition));
//        when(programmeEdition.findSchoolYearIDInProgrammeEdition()).thenReturn(schoolYearID);
//        when(schoolYearRepository.ofIdentity(schoolYearID)).thenReturn(Optional.of(schoolYear));
//        StudentGrade existingGrade = mock(StudentGrade.class);
//        when(existingGrade.hasThisStudentID(studentID)).thenReturn(true);
//        when(existingGrade.hasThisCourseEditionID(courseEditionID)).thenReturn(true);
//        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(existingGrade));
//
//        // assert
//        assertThrows(IllegalArgumentException.class, () -> factory.newGradeStudent(grade, date, studentID, courseEditionID));
//    }
}

