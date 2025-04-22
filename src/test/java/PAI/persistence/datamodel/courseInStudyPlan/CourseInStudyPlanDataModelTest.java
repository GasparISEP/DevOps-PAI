package PAI.persistence.datamodel.courseInStudyPlan;

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
        StudyPlanIDDataModel studyPlan = mock(StudyPlanIDDataModel.class);
        CourseIDDataModel course = mock(CourseIDDataModel.class);
        int semester = 1;
        int year = 2;
        // act
        CourseInStudyPlanDataModel model = new CourseInStudyPlanDataModel(
                embeddedId, studyPlan, course, semester, year
        );
        // assert
        assertNotNull(model);
        assertEquals(embeddedId, model.getCourseInStudyPlanIDDataModel());
        assertEquals(studyPlan, model.getStudyPlanIDDataModel());
        assertEquals(course, model.getCourseIDDataModel());
        assertEquals(semester, model.getSemester());
        assertEquals(year, model.getCurricularYear());
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
        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(
                id, mock(StudyPlanIDDataModel.class), mock(CourseIDDataModel.class), 1, 1
        );
        CourseInStudyPlanDataModel m2 = new CourseInStudyPlanDataModel(
                id, mock(StudyPlanIDDataModel.class), mock(CourseIDDataModel.class), 2, 2
        );
        assertTrue(m1.equals(m2));
    }

    @Test
    void equalsShouldReturnFalseForDifferentEmbeddedId() {
        CourseInStudyPlanIDDataModel id1 = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanIDDataModel id2 = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(
                id1, mock(StudyPlanIDDataModel.class), mock(CourseIDDataModel.class), 1, 1
        );
        CourseInStudyPlanDataModel m2 = new CourseInStudyPlanDataModel(
                id2, mock(StudyPlanIDDataModel.class), mock(CourseIDDataModel.class), 1, 1
        );
        assertFalse(m1.equals(m2));
    }

    @Test
    void hashCodeShouldBeEqualForSameEmbeddedId() {
        CourseInStudyPlanIDDataModel id = mock(CourseInStudyPlanIDDataModel.class);

        CourseInStudyPlanDataModel m1 = new CourseInStudyPlanDataModel(
                id, mock(StudyPlanIDDataModel.class), mock(CourseIDDataModel.class), 1, 1
        );
        CourseInStudyPlanDataModel m2 = new CourseInStudyPlanDataModel(
                id, mock(StudyPlanIDDataModel.class), mock(CourseIDDataModel.class), 2, 2
        );

        assertEquals(m1.hashCode(), m2.hashCode());
    }

}