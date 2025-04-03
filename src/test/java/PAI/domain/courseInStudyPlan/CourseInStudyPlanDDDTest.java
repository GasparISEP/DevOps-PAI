package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.course.CourseDDD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanDDDTest {

    @Test
    void ShouldConstructACourseInStudyPlan_2() throws Exception {

        //Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        //Act
        CourseInStudyPlanDDD CourseInStudyPlanDDD = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        //Assert
        assertNotNull(CourseInStudyPlanDDD);
    }

    @Test
    void testEqualsSameCourseID() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertEquals(course1, course2);
    }

    @Test
    void testEqualsDifferentCourseID() throws Exception {
        // Arrange
        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID1, studyPlanID);
        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester, curricularYear, courseID2, studyPlanID);

        // Assert
        assertNotEquals(course1, course2);
    }

    @Test
    void testEqualsWithNull() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlanDDD course = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertNotEquals(course, null);
    }

    @Test
    void testEqualsWithSameCourse() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlanDDD course = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertEquals(course, course);
    }

    @Test
    void testGetters() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        // Act
        CourseInStudyPlanDDD course = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertEquals(courseID, course.getCourseID());
        assertEquals(semester, course.getSemester());
        assertEquals(curricularYear, course.getCurricularYear());
        assertEquals(studyPlanID, course.getStudyplanID());
    }

    @Test
    void testGetCourseInStudyPlanID_NotNull() throws Exception {
        // Arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Act
        CourseInStudyPlanDDD course = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanID id = course.identity();

        // Assert
        assertNotNull(id);
    }

    @Test
    void testGetCourseInStudyPlanID_Consistent() throws Exception {
        // Arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        CourseInStudyPlanDDD course = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        // Act: obter o identificador em duas chamadas consecutivas
        CourseInStudyPlanID id1 = course.identity();
        CourseInStudyPlanID id2 = course.identity();

        // Assert: ambas as chamadas devem retornar o mesmo objeto
        assertEquals(id1, id2);
    }

    @Test
    public void testEqualsSameObjectShouldReturnTrue() {
        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        CourseInStudyPlanDDD course = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        //act + assert
        assertTrue(course.equals(course));
    }

    @Test
    public void testEqualsDifferentTypeShouldReturnFalse() {
        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        CourseInStudyPlanDDD course = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        //act + assert
        assertNotEquals("Não sou um CourseInStudyPlanDDD", course);
    }

    @Test
    public void testEquals_sameCourseID_shouldReturnTrue() {
        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        //act + assert
        assertTrue(course1.equals(course2));
    }

    @Test
    public void testEqualsDifferentCourseIDShouldReturnFalse() {
        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        Semester semester2 = mock(Semester.class);
        CurricularYear curricularYear2 = mock(CurricularYear.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID2 = mock(StudyPlanID.class);

        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester2, curricularYear2, courseID2, studyPlanID2);

        //act + assert
        assertFalse(course1.equals(course2));
    }

    @Test
    public void testIdentityShouldReturnCourseInStudyPlanID() {
        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        //act + assert
        assertEquals(courseInStudyPlanID, course1.identity());
    }

    @Test
    public void testSameAsSameIdentityShouldReturnTrue() {
        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        assertTrue(course1.sameAs(course2));
    }

    @Test
    public void shouldReturnTrueIfObjectsAreEqualButCurricularYearIsDifferent() {
        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CurricularYear curricularYear2 = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester, curricularYear2, courseID, studyPlanID);
        // act & assert
        assertTrue(course1.sameAs(course2));
    }

    @Test
    public void shouldReturnTrueIfObjectsAreEqualButSemesterIsDifferent() {
        //arrange
        Semester semester1 = mock(Semester.class);
        Semester semester2 = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester1, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester2, curricularYear, courseID, studyPlanID);
        // act & assert
        assertTrue(course1.sameAs(course2));
    }

    @Test
    public void testSameAs_differentIdentity_shouldReturnFalse() {
        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        Semester semester2 = mock(Semester.class);
        CurricularYear curricularYear2 = mock(CurricularYear.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID2 = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester2, curricularYear2, courseID2, studyPlanID2);
        //act + assert
        assertFalse(course1.sameAs(course2));
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame(){
        // arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);
        // act & assert
        assertTrue(course1.sameAs(course1));
    }

    @Test
    void shouldReturnFalseWhenObjectsAreDifferent(){
        // arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD courseInStudyPlanDDD = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);

        CourseDDD courseDDD = mock(CourseDDD.class);
        // act & assert
        assertFalse(courseInStudyPlanDDD.sameAs(courseDDD));
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanHasDifferentStudyPlanID(){
        // arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        StudyPlanID studyPlanID2 = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID2);
        // act & assert
        assertFalse(course1.sameAs(course2));
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanHasDifferentCourseID(){
        // arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanDDD course1 = new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanDDD course2 = new CourseInStudyPlanDDD(semester, curricularYear, courseID2, studyPlanID);
        // act & assert
        assertFalse(course1.sameAs(course2));
    }
}