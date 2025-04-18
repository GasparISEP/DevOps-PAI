package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanIDTest {

    @Test
    void defaultConstructorCreatesNonNullCourseInStudyPlanID() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        //act
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        //assert
        assertNotNull(id);
    }

    @Test
    void equalsMethodWithDifferentCourseInStudyPlanID() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseID courseID1 = mock(CourseID.class);
        StudyPlanID studyPlanID1 = mock(StudyPlanID.class);

        //act
        CourseInStudyPlanID id1 = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseInStudyPlanID id2 = new CourseInStudyPlanID(courseID1, studyPlanID1);
        //arrange
        assertNotEquals(id1, id2);
    }

    @Test
    void hashCodeConsistency() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        //act
        int hash1 = id.hashCode();
        int hash2 = id.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    void testEqualsSameObject() {
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        assertEquals(id, id);
    }

    @Test
    void testEqualsWithNull() {
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        assertNotEquals(null, id);
    }

    @Test
    void testEqualsWithDifferenteClass() {
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        String other = "not a CourseInStudyPlanID";
        assertNotEquals(other, id);
    }

    @Test
    void testEqualsWithIdenticalValues() {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID id1 = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseInStudyPlanID id2 = new CourseInStudyPlanID(courseID, studyPlanID);

        // Act & Assert
        assertEquals(id1, id2, "Os objetos com os mesmos valores devem ser iguais.");
        assertEquals(id1.hashCode(), id2.hashCode(), "Os hashCodes devem ser iguais para objetos iguais.");
    }

    @Test
    void testToString() {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(courseID.toString()).thenReturn("CourseID{mock}");
        when(studyPlanID.toString()).thenReturn("StudyPlanID{mock}");
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);

        // Act
        String expected = "CourseInStudyPlanID{_courseID=CourseID{mock}, _studyPlanID=StudyPlanID{mock}}";

        // Assert
        assertEquals(expected, id.toString());
    }

    @Test
    void shouldReturnCourseID() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);

        //act
        CourseID result = id.getCourseID();

        //assert
        assertEquals(result, courseID);
    }

    @Test
    void shouldReturnStudyPlanID() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);

        //act
        StudyPlanID result = id.getStudyPlanID();

        //assert
        assertEquals(result, studyPlanID);
    }
}