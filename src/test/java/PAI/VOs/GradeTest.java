package PAI.VOs;

import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.domain.StudentGrade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GradeTest {

    @Test
    void shouldCreateGrade() throws Exception {

        // Act
       Grade grade1= new Grade(18);
        // Assert
        assertNotNull(grade1);
    }

}