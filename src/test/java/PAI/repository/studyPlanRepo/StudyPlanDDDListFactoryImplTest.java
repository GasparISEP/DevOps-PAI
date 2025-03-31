package PAI.repository.studyPlanRepo;

import PAI.domain.studyPlan.StudyPlanDDD;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyPlanDDDListFactoryImplTest {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        // Arrange
        IStudyPlanDDDListFactory IStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();

        // Act
        List<StudyPlanDDD> studyPlan = IStudyPlanDDDListFactory.newArrayList();

        // Assert
        assertNotNull(studyPlan);
        assertInstanceOf(ArrayList.class, studyPlan);
    }
}