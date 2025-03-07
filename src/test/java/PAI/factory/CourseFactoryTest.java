package PAI.factory;

import PAI.domain.Course;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class CourseFactoryTest {

    @Test
    void shouldCreateCourseWhenConstructorIsInvoked2() throws Exception {

        // Arrange
        String name1 = "Informatics";
        String acronym1 = "INF";
        double quantityOfEcts1 = 6;
        int durationCourseInSemester1 = 1;

        CourseFactory courseFactory = new CourseFactory();


        try (MockedConstruction<Course> courseMocked = mockConstruction(Course.class, (mock, context) -> {
            String name = (String) context.arguments().get(0);
            String acronym = (String) context.arguments().get(1);
            double quantityOfEcts = (Double) context.arguments().get(2);
            int durationCourseInSemester = (int)  context.arguments().get(3);

            when(mock.getName()).thenReturn(name);
            when(mock.getAcronym()).thenReturn(acronym);
            when(mock.getQuantityCreditsEcts()).thenReturn(quantityOfEcts);
            when(mock.getDurationInSemester()).thenReturn(durationCourseInSemester);
        })) {

            // Act
            Course result = courseFactory.createCourse(name1, acronym1, quantityOfEcts1, durationCourseInSemester1);

            // Assert
            assertNotNull(result);

            // O metodo cria uma lista e insere os Objectos criados
            List<Course> courses = courseMocked.constructed();
            assertEquals(1, courses.size());

            // Verifica os valores do Objecto
            assertEquals(name1, result.getName());
            assertEquals(acronym1, result.getAcronym());
            assertEquals(quantityOfEcts1, result.getQuantityCreditsEcts());
            assertEquals(durationCourseInSemester1, result.getDurationInSemester());
        }
    }


}