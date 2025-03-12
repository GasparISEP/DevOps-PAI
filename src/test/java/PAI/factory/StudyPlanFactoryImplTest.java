package PAI.factory;

import PAI.repository.StudyPlan;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class StudyPlanFactoryImplTest {

    @Test
    void shouldCreateNewStudyPlan() throws Exception {
        // Preparação dos mocks
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
        StudyPlanListFactoryImpl studyPlanListFactory = mock(StudyPlanListFactoryImpl.class);
        CourseFactoryImpl courseFactoryImpl = mock(CourseFactoryImpl.class);
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();

        // Utilização do MockedConstruction para interceptar a criação de StudyPlan
        try (MockedConstruction<StudyPlan> mockedConstruction = mockConstruction(StudyPlan.class)) {
            StudyPlan studyPlan = studyPlanFactory.newStudyPlan(courseInStudyPlanFactory, studyPlanListFactory, courseFactoryImpl);

            // Verifica se foi criado exatamente um objeto
            List<StudyPlan> listaDeObjetosCriados = mockedConstruction.constructed();
            assertEquals(1, listaDeObjetosCriados.size());

            // Verifica que o objeto retornado não é null
            assertNotNull(studyPlan);
        }
    }
}