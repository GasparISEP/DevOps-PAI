package PAI.factory;

import PAI.domain.CourseInStudyPlan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyPlanListFactoryImplTest {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        // Arrange
        StudyPlanListFactory studyPlanListFactory = new StudyPlanListFactoryImpl();

        // Act
        List<CourseInStudyPlan> studyPlan = studyPlanListFactory.newArrayList();

        // Assert
        assertNotNull(studyPlan);
        assertInstanceOf(ArrayList.class, studyPlan);
    }
}