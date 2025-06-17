package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanGeneratedIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseInStudyPlanTest {

    @Test
    void ShouldConstructACourseInStudyPlan() throws Exception {

        //Arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        //Act
        CourseInStudyPlan CourseInStudyPlan = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID,
                durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        //Assert
        assertNotNull(CourseInStudyPlan);
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenCourseIDIsNull() throws Exception {
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, null, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID)
        );
        assertEquals("Course ID cannot be null.", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenSemesterIsNull() throws Exception {
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(null, curricularYear, courseID, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID)
        );
        assertEquals("Semester cannot be null.", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenCurricularYearIsNull() throws Exception {
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, null, courseID, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID)
        );
        assertEquals("Curricular Year cannot be null.", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenStudyPlanIsNull() throws Exception {
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, null,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID)
        );
        assertEquals("Study Plan ID cannot be null.", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenDurationOfCourseIsNull() throws Exception {
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID,
                        courseInStudyPlanID, null, quantityOfCreditsEcts, generatedID, programmeID)
        );
        assertEquals("Duration of Course cannot be null.", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenQuantityOfCreditsEctsIsNull() throws Exception {
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, null, generatedID, programmeID)
        );
        assertEquals("Quantity of Credits Ects cannot be null.", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenCourseInStudyPlanIDIsNull() throws Exception {
        // arrange
        CourseID courseID = mock(CourseID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID,
                        null, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID)
        );
        assertEquals("Course In Study Plan ID cannot be null.", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenCourseInStudyPlanGeneratedIDIsNull() throws Exception {
        // arrange
        CourseID courseID = mock(CourseID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, null, programmeID)
        );
        assertEquals("Course In Study Plan Generated ID cannot be null.", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenProgrammeIDIsNull() throws Exception {
        // arrange
        CourseID courseID = mock(CourseID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, null)
        );
        assertEquals("Programme ID cannot be null.", ex.getMessage());
    }

    @Test
    void testEqualsSameCourseID() throws Exception {
        // Arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        // Assert
        assertEquals(course1, course2);
    }

    @Test
    void testEqualsWithNull() throws Exception {
        // Arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        //Act
        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        // Assert
        assertNotEquals(course, null);
    }

    @Test
    void testEqualsWithSameCourse() throws Exception {
        // Arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        //Act
        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        // Assert
        assertEquals(course, course);
    }

    @Test
    void testGetters() throws Exception {
        // Arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // Act
        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        // Assert
        assertEquals(courseID, course.getCourseID());
        assertEquals(semester, course.getSemester());
        assertEquals(curricularYear, course.getCurricularYear());
        assertEquals(studyPlanID, course.getStudyplanID());
        assertEquals(durationOfCourse, course.getDurationOfCourse());
        assertEquals(quantityOfCreditsEcts, course.getQuantityOfCreditsEcts());
        assertEquals(courseInStudyPlanID, course.identity());
        assertEquals(generatedID, course.getGeneratedID());
        assertEquals(programmeID, course.getProgrammeID());
    }

    @Test
    void testGetCourseInStudyPlanIDNotNull() throws Exception {
        // Arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // Act
        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        CourseInStudyPlanID id = course.identity();

        // Assert
        assertNotNull(id);
        assertEquals(courseInStudyPlanID, id);
    }

    @Test
    void testGetCourseInStudyPlanIDConsistent() throws Exception {
        // Arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        // Act: obter o identificador em duas chamadas consecutivas
        CourseInStudyPlanID id1 = course.identity();
        CourseInStudyPlanID id2 = course.identity();

        // Assert: ambas as chamadas devem retornar o mesmo objeto
        assertEquals(id1, id2);
    }

    @Test
    public void testEqualsSameObjectShouldReturnTrue() {
        //arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        //act + assert
        assertTrue(course.equals(course));
    }

    @Test
    public void testEqualsDifferentTypeShouldReturnFalse() {
        //arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        //act + assert
        assertNotEquals(courseID, course);
    }

    @Test
    public void testEqualsSameCourseIDShouldReturnTrue() {
        //arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        //act + assert
        assertTrue(course1.equals(course2));
    }

    @Test
    public void testIdentityShouldReturnCourseInStudyPlanID() {
        //arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        //act + assert
        assertEquals(courseInStudyPlanID, course1.identity());
    }

    @Test
    public void testSameAsSameIdentityShouldReturnTrue() {
        //arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        assertTrue(course1.sameAs(course2));
    }

    @Test
    public void shouldReturnTrueIfObjectsAreEqualButCurricularYearIsDifferent() {
        //arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CurricularYear curricularYear2 = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear2, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        //act & assert
        assertTrue(course1.sameAs(course2));
    }

    @Test
    public void shouldReturnTrueIfObjectsAreEqualButSemesterIsDifferent() {
        //arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester1 = mock(Semester.class);
        Semester semester2 = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester1, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester2, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        // act & assert
        assertTrue(course1.sameAs(course2));
    }

    @Test
    public void testSameAsDifferentIdentityShouldReturnFalse() {
        //arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        Semester semester2 = mock(Semester.class);
        CurricularYear curricularYear2 = mock(CurricularYear.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID2 = mock(StudyPlanID.class);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester2, curricularYear2, courseID2, studyPlanID2, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        //act + assert
        assertFalse(course1.sameAs(course2));
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame(){
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        // act & assert
        assertTrue(course1.sameAs(course1));
    }

    @Test
    void shouldReturnFalseWhenObjectsAreDifferent(){
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan courseInStudyPlan = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        Course course = mock(Course.class);
        // act & assert
        assertFalse(courseInStudyPlan.sameAs(course));
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanHasDifferentStudyPlanID(){
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        StudyPlanID studyPlanID2 = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID2, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        // act & assert
        assertFalse(course1.sameAs(course2));
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanHasDifferentCourseID(){
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID2, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        // act & assert
        assertFalse(course1.sameAs(course2));
    }

    @Test
    void testEqualsWithDifferentCourseInStudyPlanIDShouldReturnFalse() {
        // arrange
        StudyPlanID studyPlanID1 = mock(StudyPlanID.class);
        CourseID courseID1 = mock(CourseID.class);

        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID2 = mock(StudyPlanID.class);

        CourseInStudyPlanID id1 = new CourseInStudyPlanID(courseID1, studyPlanID1);
        CourseInStudyPlanID id2 = new CourseInStudyPlanID(courseID2, studyPlanID2);

        // mocks normais para os restantes
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act
        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID1, studyPlanID1, id1, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID2, studyPlanID2, id2, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

        // assert
        assertNotEquals(course1, course2);
    }
}
