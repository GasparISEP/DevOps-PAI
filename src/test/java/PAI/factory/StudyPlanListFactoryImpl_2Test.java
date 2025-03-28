package PAI.factory;

import PAI.domain.CourseInStudyPlan;
import PAI.domain.CourseInStudyPlan_2;
import PAI.domain.StudyPlan_2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyPlanListFactoryImpl_2Test {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        // Arrange
        IStudyPlanListFactory_2 IStudyPlanListFactory_2 = new StudyPlanListFactoryImpl_2();

        // Act
        List<StudyPlan_2> studyPlan = IStudyPlanListFactory_2.newArrayList();

        // Assert
        assertNotNull(studyPlan);
        assertInstanceOf(ArrayList.class, studyPlan);
    }

}