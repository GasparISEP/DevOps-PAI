package PAI.factory;

import PAI.domain.Course;
import PAI.domain.CourseInStudyPlan;
import PAI.domain.Programme;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CourseInStudyPlanFactoryImplTest {

    @Test
    void shouldCreateFactoryConstrutor() throws Exception {

        //arrange
        int semester = 1;
        int curricularYear = 2;
        Course course = mock(Course.class);
        Programme programme = mock(Programme.class);

        try (MockedConstruction<CourseInStudyPlan> mockConstruction = mockConstruction(CourseInStudyPlan.class, (mock, context) -> {
            int semesterAtual = (int) context.arguments().get(0);
            int curricularYearAtual = (int) context.arguments().get(1);
            Course courseAtual = (Course) context.arguments().get(2);
            Programme programmeAtual = (Programme) context.arguments().get(3);

            when(mock.getSemester()).thenReturn(semesterAtual);
            when(mock.getCurricularYear()).thenReturn(curricularYearAtual);
            when(mock.getCourse()).thenReturn(courseAtual);
            when(mock.getProgramme()).thenReturn(programmeAtual);
        })) {


            //act
            CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
            CourseInStudyPlan courseInStudyPlan = courseInStudyPlanFactory.newCourseInStudyPlan(semester, curricularYear, course, programme);

            //assert
            assertNotNull(courseInStudyPlan);
            assertEquals(1, mockConstruction.constructed().size());

            CourseInStudyPlan createdCourseInStudyPlan = mockConstruction.constructed().get(0);
            assertSame(createdCourseInStudyPlan, courseInStudyPlan);

            assertEquals(semester, createdCourseInStudyPlan.getSemester());
            assertEquals(curricularYear, createdCourseInStudyPlan.getCurricularYear());
            assertEquals(course, createdCourseInStudyPlan.getCourse());
            assertEquals(programme, createdCourseInStudyPlan.getProgramme());
        }
    }
}