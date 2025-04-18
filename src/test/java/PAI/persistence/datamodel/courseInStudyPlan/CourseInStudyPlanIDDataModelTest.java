package PAI.persistence.datamodel.courseInStudyPlan;

import PAI.persistence.datamodel.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseInStudyPlanIDDataModelTest {

    @Test
    void shouldCreateCSPIDDataModelWithoutParameters() {
        //arrange + act
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = new CourseInStudyPlanIDDataModel();
        //assert
        assertNotNull(courseInStudyPlanIDDataModel);
    }

    @Test
    void shouldCreateCSPIDDataModelWithParameters() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        String name = "aaaaa";
        String acronym = "abc";
        CourseIDDataModel courseID = new CourseIDDataModel(name, acronym);
        //act
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = new CourseInStudyPlanIDDataModel(studyPlanIDDataModel, courseID);
        //assert
        assertNotNull(courseInStudyPlanIDDataModel);
    }

    @Test
    void shouldReturnCourseID() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        String name = "aaaaa";
        String acronym = "abc";
        CourseIDDataModel courseID = new CourseIDDataModel(name, acronym);        //act
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = new CourseInStudyPlanIDDataModel(studyPlanIDDataModel, courseID);
        //assert
        assertEquals(courseID, courseInStudyPlanIDDataModel.getCourseID());
    }

    @Test
    void shouldReturnStudyPlanIDDataModel() {
        //arrange
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        String name = "aaaaa";
        String acronym = "abc";
        CourseIDDataModel courseID = new CourseIDDataModel(name, acronym);        //act
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = new CourseInStudyPlanIDDataModel(studyPlanIDDataModel, courseID);
        //assert
        assertEquals(studyPlanIDDataModel, courseInStudyPlanIDDataModel.getStudyPlanIDDataModel());
    }

    @Test
    void equalsSameInstanceReturnsTrue() {
        CourseInStudyPlanIDDataModel id = new CourseInStudyPlanIDDataModel();
        assertTrue(id.equals(id));
    }

    @Test
    void equalsNullReturnsFalse() {
        CourseInStudyPlanIDDataModel id = new CourseInStudyPlanIDDataModel();
        assertFalse(id.equals(null));
    }

    @Test
    void equalsDifferentClassReturnsFalse() {
        CourseInStudyPlanIDDataModel id = new CourseInStudyPlanIDDataModel();
        assertFalse(id.equals(new Object()));
    }

    @Test
    void equalsEqualDataModelsReturnsTrue() {
        StudyPlanIDDataModel studyPlan = mock(StudyPlanIDDataModel.class);
        CourseIDDataModel course = new CourseIDDataModel("name", "acr");
        CourseInStudyPlanIDDataModel dm1 = new CourseInStudyPlanIDDataModel(studyPlan, course);
        CourseInStudyPlanIDDataModel dm2 = new CourseInStudyPlanIDDataModel(studyPlan, course);
        assertTrue(dm1.equals(dm2));
        assertEquals(dm1.hashCode(), dm2.hashCode());
    }

    @Test
    void equalsDifferentStudyPlanReturnsFalse() {
        StudyPlanIDDataModel sp1 = mock(StudyPlanIDDataModel.class);
        StudyPlanIDDataModel sp2 = mock(StudyPlanIDDataModel.class);
        CourseIDDataModel course = new CourseIDDataModel("name", "acr");
        CourseInStudyPlanIDDataModel dm1 = new CourseInStudyPlanIDDataModel(sp1, course);
        CourseInStudyPlanIDDataModel dm2 = new CourseInStudyPlanIDDataModel(sp2, course);
        assertFalse(dm1.equals(dm2));
    }

    @Test
    void equalsDifferentCourseReturnsFalse() {
        StudyPlanIDDataModel studyPlan = mock(StudyPlanIDDataModel.class);
        CourseIDDataModel c1 = new CourseIDDataModel("n1", "a1");
        CourseIDDataModel c2 = new CourseIDDataModel("n2", "a2");
        CourseInStudyPlanIDDataModel dm1 = new CourseInStudyPlanIDDataModel(studyPlan, c1);
        CourseInStudyPlanIDDataModel dm2 = new CourseInStudyPlanIDDataModel(studyPlan, c2);
        assertFalse(dm1.equals(dm2));
    }

    @Test
    void hashCodeConsistentWithEquals() {
        StudyPlanIDDataModel studyPlan = mock(StudyPlanIDDataModel.class);
        CourseIDDataModel course = new CourseIDDataModel("name", "acr");
        CourseInStudyPlanIDDataModel dm1 = new CourseInStudyPlanIDDataModel(studyPlan, course);
        CourseInStudyPlanIDDataModel dm2 = new CourseInStudyPlanIDDataModel(studyPlan, course);
        assertEquals(dm1, dm2);
        assertEquals(dm1.hashCode(), dm2.hashCode());
    }
}
