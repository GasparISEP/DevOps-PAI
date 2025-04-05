package PAI.repository.studyPlanRepo;

import PAI.domain.studyPlan.StudyPlan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyPlanListFactoryImplTest {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        // Arrange
        IStudyPlanDDDListFactory IStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();

        // Act
        List<StudyPlan> studyPlan = IStudyPlanDDDListFactory.newArrayList();

        // Assert
        assertNotNull(studyPlan);
        assertInstanceOf(ArrayList.class, studyPlan);
    }
}