package PAI.domain;

import PAI.factory.CourseFactory;
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
        String name = "Informatics";
        String acronym = "INF";
        double quantityOfEcts = 6;
        int durationCourseInSemester = 1;

        CourseFactory courseFactory = new CourseFactory();


        try (MockedConstruction<Course> courseMocked = mockConstruction(Course.class, (mock, context) -> {
            when(mock.getName()).thenReturn(name);
            when(mock.getAcronym()).thenReturn(acronym);
            when(mock.getQuantityCreditsEcts()).thenReturn(quantityOfEcts);
            when(mock.getDurationInSemester()).thenReturn(durationCourseInSemester);
        })) {

            // Act
            Course result = courseFactory.createCourse(name, acronym, quantityOfEcts, durationCourseInSemester);

            // Assert
            assertNotNull(result);

            // O metodo cria uma lista e insere os Objectos criados
            List<Course> courses = courseMocked.constructed();
            assertEquals(1, courses.size());

            // Verifica os valores do Objecto
            assertEquals(name, result.getName());
            assertEquals(acronym, result.getAcronym());
            assertEquals(quantityOfEcts, result.getQuantityCreditsEcts());
            assertEquals(durationCourseInSemester, result.getDurationInSemester());
        }
    }


}