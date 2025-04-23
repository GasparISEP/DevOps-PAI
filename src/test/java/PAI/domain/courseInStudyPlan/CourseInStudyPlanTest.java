package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.course.Course;
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

        //Act
        CourseInStudyPlan CourseInStudyPlan = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, null, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts)
        );
        assertEquals("Course ID cannot be null", ex.getMessage());
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

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(null, curricularYear, courseID, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts)
        );
        assertEquals("Semester cannot be null", ex.getMessage());
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

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, null, courseID, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts)
        );
        assertEquals("Curricular Year cannot be null", ex.getMessage());
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

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, null,
                        courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts)
        );
        assertEquals("Study Plan ID cannot be null", ex.getMessage());
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

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID,
                        courseInStudyPlanID, null, quantityOfCreditsEcts)
        );
        assertEquals("Duration of Course cannot be null", ex.getMessage());
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
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID,
                        courseInStudyPlanID, durationOfCourse, null)
        );
        assertEquals("Quantity of Credits Ects cannot be null", ex.getMessage());
    }

    @Test
    void ConstructionOfCourseInStudyPlanShouldThrowExceptionWhenCourseInStudyPlanIDIsNull() throws Exception {
        // arrange
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);

        // act & assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID,
                        null, durationOfCourse, quantityOfCreditsEcts)
        );
        assertEquals("Course In Study Plan ID cannot be null", ex.getMessage());
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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        //Act
        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        //Act
        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        // Act
        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

        // Assert
        assertEquals(courseID, course.getCourseID());
        assertEquals(semester, course.getSemester());
        assertEquals(curricularYear, course.getCurricularYear());
        assertEquals(studyPlanID, course.getStudyplanID());
        assertEquals(durationOfCourse, course.getDurationOfCourse());
        assertEquals(quantityOfCreditsEcts, course.getQuantityOfCreditsEcts());
        assertEquals(courseInStudyPlanID, course.identity());
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

        // Act
        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear2, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester1, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester2, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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
        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

        Semester semester2 = mock(Semester.class);
        CurricularYear curricularYear2 = mock(CurricularYear.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID2 = mock(StudyPlanID.class);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester2, curricularYear2, courseID2, studyPlanID2, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
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

        CourseInStudyPlan courseInStudyPlan = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID2, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
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

        CourseInStudyPlan course1 = new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
        CourseInStudyPlan course2 = new CourseInStudyPlan(semester, curricularYear, courseID2, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
        // act & assert
        assertFalse(course1.sameAs(course2));
    }


}