package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    @Test
    void shouldCreateGrade() throws Exception {

        // Act
       Grade grade1= new Grade(18);
        // Assert
        assertNotNull(grade1);
    }

    @Test
    void shouldNotCreateGradeWithValueBelow0()  {

        // Act + Assert
        assertThrows(Exception.class, () -> new Grade(-1));
    }

    @Test
    void shouldNotCreateGradeWithValueAbove20() {

        // Act + Assert
        assertThrows(Exception.class, () -> new Grade(21));
    }

    @Test
    void shouldReturnCorrectGrade() throws Exception {
        // Arrange
        double expectedValue = 18;
        Grade grade1= new Grade(expectedValue);

        // Act
        double actualValue = grade1.knowGrade();

        // Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void shouldBeEqualWhenSameObject() throws Exception {
        Grade grade = new Grade(15);
        assertEquals(grade, grade);
    }

    @Test
    void shouldBeEqualWhenSameValue() throws Exception {
        Grade grade1 = new Grade(15);
        Grade grade2 = new Grade(15);
        assertEquals(grade1, grade2);
    }


}