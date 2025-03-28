package PAI.domain;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseInStudyPlan_2Test {

    @Test
    void ShouldConstructACourseInStudyPlan_2() throws Exception {

        //Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        //Act
        CourseInStudyPlan_2 CourseInStudyPlan_2 = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        //Assert
        assertNotNull(CourseInStudyPlan_2);
    }

    @Test
    void testEqualsSameCourseID() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlan_2 course1 = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlan_2 course2 = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

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

        CourseInStudyPlan_2 course1 = new CourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID);
        CourseInStudyPlan_2 course2 = new CourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID);

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

        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

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

        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

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
        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

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
        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanID id = course.getCourseInStudyPlanID();

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

        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Act: obter o identificador em duas chamadas consecutivas
        CourseInStudyPlanID id1 = course.getCourseInStudyPlanID();
        CourseInStudyPlanID id2 = course.getCourseInStudyPlanID();

        // Assert: ambas as chamadas devem retornar o mesmo objeto
        assertEquals(id1, id2);
    }
}
