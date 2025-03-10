package PAI.factory;

import PAI.repository.StudyPlan;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class StudyPlanFactoryImplT {

    @Test
    void shouldCreateNewStudyPlan() throws Exception {

        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanListFactoryImpl studyPlanArrayListFactory = mock(StudyPlanListFactoryImpl.class);
        CourseFactory courseFactory = mock(CourseFactory.class);
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();

        try (MockedConstruction<StudyPlan> mockConstruction = mockConstruction(StudyPlan.class)) {

            StudyPlan studyPlan = studyPlanFactory.newStudyPlan(courseInStudyPlanFactory, studyPlanArrayListFactory, courseFactory);
            
        List<StudyPlan> listaComDuplo = mockConstruction.constructed();
        assertEquals(1, listaComDuplo.size());
        }
    }

}
