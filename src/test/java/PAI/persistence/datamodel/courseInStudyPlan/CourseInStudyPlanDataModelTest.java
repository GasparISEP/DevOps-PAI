package PAI.persistence.datamodel.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.CourseInStudyPlanGeneratedID;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseInStudyPlanDataModelTest {

    @Test
    void shouldCreateCSPDataModelWithoutParameters() {
        // arrange + act
        CourseInStudyPlanDataModel courseInStudyPlanDataModel = new CourseInStudyPlanDataModel();
        // assert
        assertNotNull(courseInStudyPlanDataModel);
    }

    @Test
    void shouldCreateCSPDataModelWithParameters() {
        // arrange
        CourseInStudyPlanIDDataModel embeddedId = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanGeneratedIDDataModel generatedID = mock(CourseInStudyPlanGeneratedIDDataModel.class);

        int semester = 1;
        int year = 2;
        int durationOfCourse = 1;
        double quantityOfCreditsEcts = 1;

        // act
        CourseInStudyPlanDataModel model = new CourseInStudyPlanDataModel(
                generatedID, embeddedId, semester, year, durationOfCourse, quantityOfCreditsEcts
        );

        // assert
        assertNotNull(model);
        assertEquals(embeddedId, model.getCourseInStudyPlanIDDataModel());
        assertEquals(generatedID, model.getGeneratedID());
        assertEquals(semester, model.getSemester());
        assertEquals(year, model.getCurricularYear());
        assertEquals(durationOfCourse, model.getDurationOfCourse());
        assertEquals(quantityOfCreditsEcts, model.getQuantityOfCreditsEcts());
    }

    @Test
    void equalsShouldReturnTrueForSameInstance() {
        CourseInStudyPlanDataModel model = new CourseInStudyPlanDataModel();
        assertTrue(model.equals(model));
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        CourseInStudyPlanDataModel model = new CourseInStudyPlanDataModel();
        assertFalse(model.equals(null));
    }

    @Test
    void equalsShouldReturnFalseForDifferentType() {
        CourseInStudyPlanDataModel model = new CourseInStudyPlanDataModel();
        assertFalse(model.equals("not a model"));
    }

    @Test
    void equalsShouldReturnTrueForSameEmbeddedId() {
        CourseInStudyPlanIDDataModel id = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanGeneratedIDDataModel generatedID = mock(CourseInStudyPlanGeneratedIDDataModel.class);
        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(
                generatedID, id, 1, 1, 1, 1.0
        );
        CourseInStudyPlanDataModel m2 = new CourseInStudyPlanDataModel(
                generatedID, id, 2, 2, 2, 2.0
        );
        assertTrue(m1.equals(m2));
    }

    @Test
    void equalsShouldReturnFalseForDifferentEmbeddedId() {
        CourseInStudyPlanIDDataModel id1 = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanGeneratedIDDataModel gid1 = mock(CourseInStudyPlanGeneratedIDDataModel.class);
        CourseInStudyPlanIDDataModel id2 = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanGeneratedIDDataModel gid2 = mock(CourseInStudyPlanGeneratedIDDataModel.class);
        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(
                gid1, id1, 1, 1, 1, 1.0
        );
        CourseInStudyPlanDataModel m2 = new CourseInStudyPlanDataModel(
                gid2, id2, 1, 1, 1, 1.0
        );
        assertFalse(m1.equals(m2));
    }

    @Test
    void hashCodeShouldBeEqualForSameEmbeddedId() {
        CourseInStudyPlanIDDataModel id = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanGeneratedIDDataModel gid1 = mock(CourseInStudyPlanGeneratedIDDataModel.class);

        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(
                gid1, id,1, 1, 1, 1.0
        );
        CourseInStudyPlanDataModel m2 = new CourseInStudyPlanDataModel(
                gid1, id, 2, 2, 2, 2.0
        );

        assertEquals(m1.hashCode(), m2.hashCode());
    }


    @Test
    void constructorShouldThrowWhenNumericFieldsAreInvalid() {
        var id   = mock(CourseInStudyPlanIDDataModel.class);
        var gid = mock(CourseInStudyPlanGeneratedIDDataModel.class);

        // semester < 1
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanDataModel(gid, id, 0, 1, 1, 1.0)
        );

        // curricularYear < 1
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanDataModel(gid, id,1, 0, 1, 1.0)
        );

        // durationOfCourse < 1
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanDataModel(gid, id, 1, 1, 0, 1.0)
        );

        // quantityOfCreditsEcts < 1
        assertThrows(IllegalArgumentException.class, () ->
                new CourseInStudyPlanDataModel(gid, id, 1, 1, 1, 0.0)
        );
    }

    @Test
    void hashCodeShouldDifferForDifferentEmbeddedId() {

        CourseIDDataModel courseIDDataModel1 = mock(CourseIDDataModel.class);
        StudyPlanIDDataModel studyPlanIDDataModel1 = mock(StudyPlanIDDataModel.class);

        CourseIDDataModel courseIDDataModel2 = mock(CourseIDDataModel.class);
        StudyPlanIDDataModel studyPlanIDDataModel2 = mock(StudyPlanIDDataModel.class);

        CourseInStudyPlanIDDataModel id1 = new CourseInStudyPlanIDDataModel(studyPlanIDDataModel1, courseIDDataModel1);
        CourseInStudyPlanIDDataModel id2 = new CourseInStudyPlanIDDataModel(studyPlanIDDataModel2, courseIDDataModel2);

        CourseInStudyPlanGeneratedIDDataModel gid1 = mock(CourseInStudyPlanGeneratedIDDataModel.class);
        CourseInStudyPlanGeneratedIDDataModel gid2 = mock(CourseInStudyPlanGeneratedIDDataModel.class);

        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(
                gid1, id1,1, 1, 1, 1.0
        );
        CourseInStudyPlanDataModel m2 = new CourseInStudyPlanDataModel(
                gid2, id2, 1, 1, 1, 1.0
        );

        assertNotEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    void shouldReturnSPIDDataModel() {
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        CourseInStudyPlanIDDataModel id = new CourseInStudyPlanIDDataModel(studyPlanIDDataModel, courseIDDataModel);
        CourseInStudyPlanGeneratedIDDataModel gid = mock(CourseInStudyPlanGeneratedIDDataModel.class);
        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(gid, id, 1, 1, 1, 1.0);

        StudyPlanIDDataModel result = m1.getStudyPlanIDDataModel();

        assertEquals(studyPlanIDDataModel, result);
    }

    @Test
    void shouldReturnCourseIDDataModel() {
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        CourseInStudyPlanIDDataModel id = new CourseInStudyPlanIDDataModel(studyPlanIDDataModel, courseIDDataModel);
        CourseInStudyPlanGeneratedIDDataModel gid = mock(CourseInStudyPlanGeneratedIDDataModel.class);
        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(gid, id, 1, 1, 1, 1.0);

        CourseIDDataModel result = m1.getCourseIDDataModel();

        assertEquals(courseIDDataModel, result);
    }
}