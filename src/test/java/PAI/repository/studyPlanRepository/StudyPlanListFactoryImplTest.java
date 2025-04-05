package PAI.repository.studyPlanRepository;

import PAI.domain.studyPlan.StudyPlan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyPlanListFactoryImplTest {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        // Arrange
        IStudyPlanListFactory IStudyPlanListFactory = new StudyPlanListFactoryImpl();

        // Act
        List<StudyPlan> studyPlan = IStudyPlanListFactory.newArrayList();

        // Assert
        assertNotNull(studyPlan);
        assertInstanceOf(ArrayList.class, studyPlan);
    }
}